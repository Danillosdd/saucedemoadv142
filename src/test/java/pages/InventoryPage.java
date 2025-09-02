package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProductToCart(String productName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        driver.findElement(By.xpath(xpath)).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public boolean isProductAdded(String productName) {
        // Verifica se o botão mudou para "Remove"
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Remove']", productName);
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}
