package stepdefinitions;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import utilities.API_Utilities.API_Methods;

public class API_Stepdefinitions {
    public static int id;
    public static String fullPath;
    JSONObject requestBody;
    JsonPath jsonPath;

    @Given("The api users constructs the base url with the {string} token.")
    public void the_api_users_constructs_the_base_url_with_the_token(String token) {
        HooksAPI.setUpApi(token);
    }
    @Given("The api users sets {string} path parameters")
    public void the_api_users_sets_path_parameters(String fullPath) {
        API_Methods.pathParamsMethod(fullPath);
    }
    @Given("The api users sends a GET request and records the response from the api customerGetUser endpoint.")
    public void the_api_users_sends_a_get_request_and_records_the_response_from_the_api_customer_get_user_endpoint() {
    API_Methods.getResponse();
    }
    @Given("The api users verifies that the status code is {int}")
    public void the_api_users_verifies_that_the_status_code_is(Integer statusCode) {
        API_Methods.statusCodeAssert(statusCode);
    }
    @Given("The api users verifies that the message information in the response body is {string}")
    public void the_api_users_verifies_that_the_message_information_in_the_response_body_is(String message) {
        API_Methods.messageAssert(message);
    }




}
