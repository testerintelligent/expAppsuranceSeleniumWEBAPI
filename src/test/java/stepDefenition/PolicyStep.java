package stepDefenition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utility.FunctionLibrary;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class PolicyStep extends FunctionLibrary {
    private static final Log log = LogFactory.getLog(PolicyStep.class);
    private Map<String, String> policyDetails;
    private Response response;

    @Given("^I have the following (?:policy|login) details$")
    public void i_have_the_following_policy_details(DataTable table) {
        policyDetails = new HashMap<>(table.asMap(String.class, String.class));

        if (policyDetails.get("Name") != null && policyDetails.get("Name").equalsIgnoreCase("random")) {
            policyDetails.replace("Name", generateRandomName());
        }

        if (policyDetails.get("email") != null && policyDetails.get("email").equalsIgnoreCase("random")) {
            policyDetails.replace("email", generateRandomEmail());
        }

        log.info(policyDetails);
    }

    @When("I send a POST request to Endpoint {string}")
    public void iSendAPOSTRequestTo(String endPoint) {
        response = given()
                .header("Content-Type", "application/json")
                .body(policyDetails)
                .when()
                .post(endPoint);
    }

    @Then("the response code should be {int}")
    public void the_response_code_should_be(int expectedStatusCode) {
        response.then()
                .statusCode(expectedStatusCode);

        log.info("Response status code: " + response.getStatusCode());
    }

    @And("the response body should contain {string}")
    public void theResponseBodyShouldContain(String expectedMessage) {
        response.then()
                .body("message", containsString(expectedMessage));

        log.info("Response Body below: ");
        response.then().log().body();
    }

    @Given("I have created {int} policies in the endpoint {string}")
    public void iHaveCreatedPoliciesInTheEndpoint(int count, String endPoint) {
        for (int i = 1; i <= count; i++) {
            policyDetails = new HashMap<>();

            policyDetails.put("Name", generateRandomName());
            policyDetails.put("email", generateRandomEmail());
            policyDetails.put("Address", "ChennaiMEPZ");
            policyDetails.put("DateOfBirth", "1990-09-23 ");
            policyDetails.put("Gender", "Male");
            policyDetails.put("PolicyType", "Health Insurance");
            policyDetails.put("PolicyCoverage", "Laboratory Expenses");
            policyDetails.put("Premium", "10089");
            policyDetails.put("SumInsured", "100000");

            //Create policy
            response = given()
                    .header("Content-Type", "application/json")
                    .body(policyDetails)
                    .when()
                    .post(endPoint);

            //Status code validation
            the_response_code_should_be(201);
            log.info("Response status code: " + response.getStatusCode());

            //Body response validation
            theResponseBodyShouldContain("Insurance policy created successfully.");
            log.info("Response Body below: ");
            response.then().log().body();
            log.info("Policy " + i + " created");
        }
    }
}
