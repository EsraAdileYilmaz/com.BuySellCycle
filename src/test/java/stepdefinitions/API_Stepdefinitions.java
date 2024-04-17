package stepdefinitions;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import utilities.API_Utilities.API_Methods;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class API_Stepdefinitions {
    public static int id;
    public static String fullPath;
    JSONObject requestBody;
    JsonPath jsonPath;
    String endpoint;
    Response response;



  //========API Esra Baslangic===================================
    //US_037
   @Given("The api user constructs the base url with the {string} token.")
   public void the_api_user_constructs_the_base_url_with_the_token(String token) {
       HooksAPI.setUpApi(token);
    }
    @Given("The api user sets {string} path parameters")
    public void the_api_user_sets_path_parameters(String rawPaths) {
        API_Methods.pathParamsMethod(rawPaths);
    }
    @Given("The API user sends a GET request and records the response from the api allCountries endpoint.")
    public void the_api_user_sends_a_get_request_and_records_the_response_from_the_api_all_countries_endpoint() {
        API_Methods.getResponse();
    }
    @Given("The api user verifies that the status code is {int}")
    public void the_api_user_verifies_that_the_status_code_is(Integer code) {
        API_Methods.statusCodeAssert(code);
    }
    @Given("The api user verifies that the message information in the response body is {string}")
    public void the_api_user_verifies_that_the_message_information_in_the_response_body_is(String message) {
        API_Methods.messageAssert(message);
    }


    @When("The api user verifies the content of the data {int}, {string}, {string} in the response body.")
    public void theApiUserVerifiesTheContentOfTheDataInTheResponseBody(int id, String code, String name) {
       jsonPath=API_Methods.response.jsonPath();

        Assert.assertEquals(id, jsonPath.getInt("addresses[222].id"));
        Assert.assertEquals(code, jsonPath.getString("addresses[222].code"));
        Assert.assertEquals(name, jsonPath.getString("addresses[222].name"));
    }
    //==========API Esra Sonu======================================

   
    @Given("The api users sends a GET request and records the response from the api customerGetUser endpoint.")
    public void the_api_users_sends_a_get_request_and_records_the_response_from_the_api_customer_get_user_endpoint() {
    API_Methods.getResponse();
    }


    @Given("The api users validates to  the response body match the {string}, {string}, {string} information")
    public void the_api_users_validates_to_the_response_body_match_the_information(String name, String surname, String email) {
       assertEquals(API_Methods.getBodyResponse("first_name"),name);
       assertEquals(API_Methods.getBodyResponse("last_name"),surname);
       assertEquals(API_Methods.getBodyResponse("email"),email);


    }


    //=============AYCA START POINT==============//
    @Given("The api user prepares a POST request containing the {string}, {string}, {string} information to send to the api change-password endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_the_api_change_password_endpoint(String oldPassword, String password, String passwordConfirmation) {
        requestBody = new JSONObject();
        requestBody.put("old_password",oldPassword);
        requestBody.put("password",password);
        requestBody.put("password_confirmation",passwordConfirmation);

    }
    @Given("The api user sends the POST request and saves the response returned from the api change-password endpoint.")
    public void the_api_user_sends_the_post_request_and_saves_the_response_returned_from_the_api_change_password_endpoint() {


       API_Methods.postResponse(requestBody.toString());


    }
    //=============AYCA END OF STEPS=============//



}
