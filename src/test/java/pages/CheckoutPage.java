package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInformation(String nome, String sobrenome, String cep) {
        driver.findElement(By.id("first-name")).sendKeys(nome);
        driver.findElement(By.id("last-name")).sendKeys(sobrenome);
        driver.findElement(By.id("postal-code")).sendKeys(cep);
        driver.findElement(By.id("continue")).click();
    }

    public boolean isOnOverviewPage() {
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }

    public void finish() {
        driver.findElement(By.id("finish")).click();
    }
}