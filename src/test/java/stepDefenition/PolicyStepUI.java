package stepDefenition;

import config.ReactAppWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.InsurancePolicyDetailsPage;
import pages.LoginPage;
import utility.FunctionLibrary;

import java.util.Map;

public class PolicyStepUI extends ReactAppWebDriver {
    LoginPage loginPage;
    InsurancePolicyDetailsPage insurancePolicyDetailsPage;
    DashboardPage dashboardPage;
    WebDriver driver = getDriver();
    FunctionLibrary functionLibrary = new FunctionLibrary();

    @After("@smoke_UI")
    public void afterScenario(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");

                driver.close();
                driver.quit();
            } else {
                driver.close();
                driver.quit();
            }
        }
    }

    @Given("I login ReactApp using below credentials")
    public void i_login_react_app_using_below_credentials(Map<String, String> table) {
        String username = table.get("Username") != null ? (table.get("Username")) : "test@test.com";
        String password = table.get("Password") != null ? table.get("Password") : "12345";

        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @When("I create policy with below data")
    public void iCreatePolicyWithBelowData(Map<String, String> table) {
        String name = table.get("Name") != null ? (table.get("Name")) : functionLibrary.generateRandomName();
        String email = table.get("email") != null ? table.get("email") : functionLibrary.generateRandomEmail();
        String address = table.get("Address") != null ? table.get("Address") : "ChennaiMEPZ";
        String dateOfBirth = table.get("DateOfBirth") != null ? table.get("DateOfBirth").replace("/", "") : "02241989";
        String gender = table.get("Gender") != null ? table.get("Gender") : "Male";
        String policyType = table.get("policyType") != null ? table.get("policyType") : "Health Insurance";
        String policyCoverage = table.get("policyCoverage") != null ? table.get("policyCoverage") : "Treatment Cost";
        String sumInsured = table.get("SumInsured") != null ? table.get("SumInsured") : "5,00,000";
        String premium = table.get("Premium") != null ? table.get("Premium") : "11889";

        dashboardPage = new DashboardPage(driver);

        dashboardPage.clickCreateNewInsurance();

        insurancePolicyDetailsPage = new InsurancePolicyDetailsPage(driver);

        insurancePolicyDetailsPage.setName(name)
                .setEmail(email)
                .setAddress(address)
                .setDateOfBirth(dateOfBirth)
                .setGender(gender)
                .setPolicyType(policyType)
                .setPolicyCoverage(policyCoverage)
                .setSumInsured(sumInsured)
                .setPremium(premium)
                .clickSubmit();
    }

    @Then("I should see the {string} message displayed")
    public void iShouldSeeTheMessageDisplayed(String message) {
        Assert.assertEquals(message, insurancePolicyDetailsPage.getSuccessMessage());
    }
}
