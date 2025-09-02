package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductInCart(String productName) {
        String xpath = String.format("//div[@class='cart_item']//div[text()='%s']", productName);
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public void checkout() {
        driver.findElement(By.id("checkout")).click();
    }
}