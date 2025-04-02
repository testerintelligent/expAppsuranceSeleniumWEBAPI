package pages;

import config.DriverComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InsurancePolicyDetailsPage extends DriverComponents {
    WebDriver driver;

    private String name = "//input[@name='Name']";
    private String email = "//input[@name='email']";
    private String address = "//input[@name='Address']";
    private String dateOfBirth = "//input[@name='DateOfBirth']";
    private String sumInsured = "//input[@name='SumInsured']";
    private String premium = "//input[@name='Premium']";
    private String submit = "//button[normalize-space()='Submit']";
    private String successMessage = "//p[text()='Insurance policy created successfully.']";
    private String logout = "//button[normalize-space()='Logout']";
    

    public InsurancePolicyDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public InsurancePolicyDetailsPage setName(String name) {
        waitForVisibilityOfElement(By.xpath(this.name), 5);

        driver.findElement(By.xpath(this.name))
                .sendKeys(name);

        return this;
    }

    public InsurancePolicyDetailsPage setEmail(String email) {
        driver.findElement(By.xpath(this.email))
                .sendKeys(email);

        return this;
    }

    public InsurancePolicyDetailsPage setAddress(String address) {
        driver.findElement(By.xpath(this.address))
                .sendKeys(address);

        return this;
    }

    public InsurancePolicyDetailsPage setDateOfBirth(String dateOfBirth) {
        driver.findElement(By.xpath(this.dateOfBirth))
                .sendKeys(dateOfBirth);

        return this;
    }

    public InsurancePolicyDetailsPage setGender(String gender) {
        driver.findElement(By.xpath("//div[@class='flex items-center space-x-4']//descendant::input[@type='radio' and @value='" + gender + "']"))
                .click();

        return this;
    }


    public InsurancePolicyDetailsPage setPolicyType(String policyType) {
        driver.findElement(By.name("PolicyType"));
 //       Select dropdown = new Select(dropdownElement);
//        dropdown.selectByValue(policyType);        
        return this;
    }


    public InsurancePolicyDetailsPage setPolicyCoverage(String policyCoverage) {
        driver.findElement(By.name("Coverage"));
  //      Select dropdown = new Select(dropdownElement);
    //    dropdown.selectByValue(policyCoverage);        
        return this;
    }

    

    public InsurancePolicyDetailsPage setSumInsured(String sumInsured) {
        driver.findElement(By.xpath(this.sumInsured))
                .sendKeys(sumInsured);

        return this;
    }

    public InsurancePolicyDetailsPage setPremium(String premium) {
        driver.findElement(By.xpath(this.premium))
                .sendKeys(premium);

        return this;
    }

    public InsurancePolicyDetailsPage clickSubmit() {
        driver.findElement(By.xpath(this.submit))
                .click();

        return this;
    }

    public String getSuccessMessage() {
        waitForVisibilityOfElement(By.xpath(successMessage), 5);

        return driver.findElement(By.xpath(successMessage)).getText();
    }

    public InsurancePolicyDetailsPage logout() {
        driver.findElement(By.xpath(this.logout))
                .click();

        return this;
    }
}