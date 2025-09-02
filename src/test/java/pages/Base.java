package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {

    protected static WebDriver driver;

    public static void iniciarDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--disable-features=PasswordManager,AutofillServerCommunication");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--user-data-dir=/tmp/chrome-test-profile");
            options.addArguments("--incognito");
            options.addArguments("--profile-directory=Default");
            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
                put("credentials_enable_service", false);
                put("profile.password_manager_enabled", false);
            }});
            driver = new ChromeDriver(options);
        }
    }

    public static void fecharDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-notifications");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--disable-features=PasswordManager,AutofillServerCommunication");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--user-data-dir=/tmp/chrome-test-profile");
            options.addArguments("--incognito");
            options.addArguments("--profile-directory=Default");
            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
                put("credentials_enable_service", false);
                put("profile.password_manager_enabled", false);
            }});
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void aguardarModal() {
        try {
            WebElement btnOk = driver.findElement(By.xpath("//button[text()='OK']"));
            btnOk.click();
            Thread.sleep(500); // Aguarda o modal sumir
        } catch (Exception e) {
            // Ignora se não encontrar o modal
        }
    }
}
