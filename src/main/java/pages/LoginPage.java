package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    private String username = "//input[@id='email']";
    private String password = "//input[@id='Password']";
    private String submit = "//button[normalize-space()='Submit']";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage setUsername(String username) {
        driver.findElement(By.xpath(this.username))
                .sendKeys(username);

        return this;
    }

    public LoginPage setPassword(String password) {
        driver.findElement(By.xpath(this.password))
                .sendKeys(password);

        return this;
    }

    public LoginPage clickSubmit() {
        driver.findElement(By.xpath(submit))
                .click();

        return this;
    }

    public void login(String username, String password) {
        setUsername(username)
                .setPassword(password)
                .clickSubmit();
    }
}