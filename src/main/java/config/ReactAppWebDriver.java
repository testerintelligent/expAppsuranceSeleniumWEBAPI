package config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class ReactAppWebDriver {
    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--remote-allow-origins=*");
//        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String browserVersion = capabilities.getBrowserVersion();
        System.out.println("Chrome Browser Version: " + browserVersion);

        driver.manage().window().maximize();
        driver.get("http://10.192.190.148:3000/");

        return driver;
    }
}