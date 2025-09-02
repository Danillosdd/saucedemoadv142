package stepsPO;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.Base;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ConfirmationPage;
import pages.HomePage;
import pages.InventoryPage;

public class ComprarProdutosPO {

    WebDriver driver;
    HomePage homePage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    @Dado("que estou na página de login do SauceDemo")
    public void que_estou_na_pagina_de_login_do_SauceDemo() {
        driver = Base.getDriver();
        driver.get("https://www.saucedemo.com/");
        homePage = new HomePage(driver);
    }

    @Quando("faço login com usuário {string} e senha {string}")
    public void faco_login_com_usuario_e_senha(String usuario, String senha) {
        homePage.login(usuario, senha);

        // Tenta fechar o pop-up de senha do Chrome
        try {
            Thread.sleep(1000); // Aguarda o modal aparecer
            driver.findElement(By.xpath("//button[text()='OK']")).click();
            Thread.sleep(500); // Aguarda o modal sumir
        } catch (Exception e) {
            // Ignora se não encontrar o modal
        }

        Assert.assertTrue(homePage.isLoggedIn());
        inventoryPage = new InventoryPage(driver);
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Quando("adiciono o produto {string} ao carrinho")
    public void adiciono_o_produto_ao_carrinho(String produto) {
        inventoryPage.addProductToCart(produto);
        Assert.assertTrue(inventoryPage.isProductAdded(produto));
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Quando("acesso o carrinho")
    public void acesso_o_carrinho() {
        inventoryPage.goToCart();
        cartPage = new CartPage(driver);
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Quando("prossigo para o checkout")
    public void prossigo_para_o_checkout() {
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack")
                || cartPage.isProductInCart("Sauce Labs Bike Light")
                || cartPage.isProductInCart("Sauce Labs Bolt T-Shirt")
                || cartPage.isProductInCart("Sauce Labs Fleece Jacket"));
        cartPage.checkout();
        checkoutPage = new CheckoutPage(driver);
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Quando("preencho os dados de compra com nome {string}, sobrenome {string} e CEP {string}")
    public void preencho_os_dados_de_compra(String nome, String sobrenome, String cep) {
        checkoutPage.fillInformation(nome, sobrenome, cep);
        Assert.assertTrue(checkoutPage.isOnOverviewPage());
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Quando("finalizo a compra")
    public void finalizo_a_compra() {
        checkoutPage.finish();
        confirmationPage = new ConfirmationPage(driver);
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Então("devo ver a mensagem de confirmação {string}")
    public void devo_ver_a_mensagem_de_confirmacao(String mensagem) {
        Assert.assertEquals(mensagem, confirmationPage.getConfirmationMessage());
        // Remova o driver.quit(); aqui!
    }
}
