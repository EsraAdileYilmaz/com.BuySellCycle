package stepdefinitions;

import com.github.javafaker.Faker;
import config_Requirements.ConfigReader;
import hooks.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;


import io.cucumber.java.en.Then;

import static org.hamcrest.Matchers.equalTo;


import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;
import io.restassured.http.ContentType;


import java.util.HashMap;
import java.util.Map;


import static hooks.HooksAPI.spec;
import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.given;



import static org.hamcrest.Matchers.*;

import static org.hamcrest.Matchers.equalTo;



import static org.junit.Assert.*;
import static utilities.API_Utilities.API_Methods.response;


public class API_Stepdefinitions {
    public static int id;
    public static String fullPath;


    //JSONObject requestBody;
    //JsonPath jsonPath;
    // HashMap<String,Object> reqBody;


    public static JSONObject requestBody;
    public static JsonPath jsonPath;


    HashMap<Object, String> reqBodyHash;

    public static int added_item_id;

    String endpoint;
    Response response;
    Faker faker = new Faker();
    Map<String, Object> reqBody;
    String password;




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


        jsonPath = response.jsonPath();
        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("addresses[222].id"));
        Assert.assertEquals(code, jsonPath.getString("addresses[222].code"));
        Assert.assertEquals(name, jsonPath.getString("addresses[222].name"));
    }

    @Given("The API user records the response from the api allCountries endpoint, confirming that the status code is '401' and the reason phrase is Unauthenticated.")
    public void the_api_user_records_the_response_from_the_api_all_countries_endpoint_confirming_that_the_status_code_is_and_the_reason_phrase_is_unauthenticated() {
        Assert.assertTrue(API_Methods.tryCatchGet().equals(ConfigReader.getProperty("unauthorizedExceptionMessage", "api")));
    }



    @When("The api users validates to  the response body match the {string}, {string}, {string},{string},{string} information")
    public void theApiUsersValidatesToTheResponseBodyMatchTheInformation(String wallet_running_balance, String wallet_pending_balance, String total_coupon, String total_wishlist, String total_cancel_order) {
        jsonPath = API_Methods.response.jsonPath();

        Assert.assertEquals(wallet_running_balance, jsonPath.getString("wallet_running_balance"));
        Assert.assertEquals(wallet_pending_balance, jsonPath.getString("wallet_pending_balance"));
        Assert.assertEquals(total_coupon, jsonPath.getString("total_coupon"));
        Assert.assertEquals(total_wishlist, jsonPath.getString("total_wishlist"));
        Assert.assertEquals(total_cancel_order, jsonPath.getString("total_cancel_order"));
    }

    //==US_001==============
    @When("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api register endpoint.")
    public void theApiUserPreparesAPOSTRequestContainingTheInformationToSendToTheApiRegisterAddEndpoint(String firstName, String lastName, String email, String password, String passwordConfirmation, String userType, String referralCode) {
        reqBody = new HashMap<>();
        password = faker.internet().password();
        reqBody.put("first_name", faker.name().firstName());
        reqBody.put("last_name", faker.name().lastName());
        reqBody.put("email", faker.internet().emailAddress());
        reqBody.put("password", password);
        reqBody.put("password_confirmation", password);
        reqBody.put("user_type", "customer");
        reqBody.put("referral_code", "0101010101");

    }

    @When("The api user sends the POST request and saves the response returned from the api register endpoint.")
    public void theApiUserSendsThePOSTRequestAndSavesTheResponseReturnedFromTheApiRegisterEndpoint() {
        API_Methods.postResponse(reqBody);
    }

    @When("The api user verifies that the register information in the response body is {string}, {string}, {string}.")
    public void theApiUserVerifiesThatTheRegisterInformationInTheResponseBodyIs(String firstName, String lastName, String email) {

        Map<String, Object> responseMap = API_Methods.response.as(HashMap.class);
        assertEquals((Map) reqBody.get("firstname"),
                ((Map) responseMap.get("user")).get("firstname"));
        assertEquals((Map) reqBody.get("lastname"),
                ((Map) responseMap.get("user")).get("lastname"));
        assertEquals(reqBody.get("email"),
                ((Map) responseMap.get("user")).get("email"));

    }

    @When("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api register endpoint for negatif test.")
    public void theApiUserPreparesAPOSTRequestContainingTheInformationToSendToTheApiRegisterEndpointForNegatifTest(String firstName, String lastName, String email, String password, String passwordConfirmation, String userType, String referralCode) {

        reqBody = new HashMap<>();
        password=faker.internet().password();
        reqBody.put("first_name",faker.name().firstName());
        reqBody.put("last_name",faker.name().lastName());
        reqBody.put("email","customer.esra@buysellcycle.com");
        reqBody.put("password",password);
        reqBody.put("password_confirmation",password);
        reqBody.put("user_type","customer");
        reqBody.put("referral_code","0101010101");
    }

    @When("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api register endpoint with empty email.")
    public void theApiUserPreparesAPOSTRequestContainingTheInformationToSendToTheApiRegisterEndpointWithEmptyEmail(String firstName, String lastName, String email, String password, String passwordConfirmation, String userType, String referralCode) {
        reqBody = new HashMap<>();
        password=faker.internet().password();
        reqBody.put("first_name",faker.name().firstName());
        reqBody.put("last_name",faker.name().lastName());
        reqBody.put("email","");
        reqBody.put("password",password);
        reqBody.put("password_confirmation",password);
        reqBody.put("user_type","customer");
        reqBody.put("referral_code","0101010101");

    }

    @When("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api register endpoint with empty password.")
    public void theApiUserPreparesAPOSTRequestContainingTheInformationToSendToTheApiRegisterEndpointWithEmptyPassword(String firstName, String lastName, String email, String password, String passwordConfirmation, String userType, String referralCode) {
        reqBody = new HashMap<>();
        password=faker.internet().password();
        reqBody.put("first_name",faker.name().firstName());
        reqBody.put("last_name",faker.name().lastName());
        reqBody.put("email",faker.internet().emailAddress());
        reqBody.put("password","");
        reqBody.put("password_confirmation",password);
        reqBody.put("user_type","customer");
        reqBody.put("referral_code","0101010101");

    }


    //US_021=====
    @When("The api user prepares a POST request containing the department id to be deleted to send to the api refund Reason delete endpoint.")
    public void theApiUserPreparesAPOSTRequestContainingTheDepartmentIdToBeDeletedToSendToTheApiRefundReasonDeleteEndpoint() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("reason", "product mismatch");
        API_Methods.postResponse(reqBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");

    }

    @When("The api user sends the DELETE request with incorrect department ID and saves the response returned from the api refund Reason delete endpoint.")
    public void theApiUserSendsTheDELETERequestWithIncorrectDepartmentIDAndSavesTheResponseReturnedFromTheApiRefundReasonDeleteEndpoint() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 987654321);
        API_Methods.deleteResponse(requestBody.toString());
    }

    @When("The api user sends the DELETE request and saves the response returned from the api refundReasonDelete endpoint.")
    public void theApiUserSendsTheDELETERequestAndSavesTheResponseReturnedFromTheApiRefundReasonDeleteEndpoint() {
        //API_Methods.deleteResponse(requestBody.toString());
        JSONObject requestBody = new JSONObject();
        requestBody.put("id",added_item_id);
        API_Methods.deleteResponse(requestBody.toString());

    }


    //==========API Esra Sonu======================================


    @Given("The api users sends a GET request and records the response from the api customerGetUser endpoint.")
    public void the_api_users_sends_a_get_request_and_records_the_response_from_the_api_customer_get_user_endpoint() {
        API_Methods.getResponse();
    }


    @Given("The api users validates to  the response body match the {string}, {string}, {string} information")

    public void the_api_users_validates_to_the_response_body_match_the_information(String first_name, String surname, String email) {
        jsonPath = API_Methods.response.jsonPath();
        assertEquals(first_name, jsonPath.getString("user.first_name"));
        assertEquals(surname, jsonPath.get("user.last_name"));
        assertEquals(email, jsonPath.getString("user.email"));

    }

    @Given("The api user validates the {string}, {string}, {string},{string}  of the response body with index {int}.")
    public void the_api_user_validates_the_of_the_response_body_with_index(String first_name, String username, String email, String name, Integer dataIndex) {
        API_Methods.response.then()
                .assertThat()
                .body("user[" + dataIndex + "].first_name", equalTo(first_name))
                .body("user[" + dataIndex + "].username", equalTo(username))
                .body("user[" + dataIndex + "].email", equalTo(email))
                .body("user[" + dataIndex + "].name", equalTo(name + " "));

    }


    @Given("The API user sends a GET request and records the response from the api {string} endpoint.")
    public void the_api_user_sends_a_get_request_and_records_the_response_from_the_api_customer_get_user_endpoint(String endPoint) {
        API_Methods.getResponse();
    }


    //============================Nazime===========================


    @Given("The api user validates the {string} and {string} of the response body with index {int}.")
    public void the_api_user_validates_the_and_of_the_response_body_with_index(String year, String name, int dataIndex) {
        API_Methods.response.then()
                .assertThat()
                .body("holiday[" + dataIndex + "].year", Matchers.equalTo(year));
    }

    //========== Gulnur Start ======================================


    @Given("The api user prepares a GET request containing the {int} for which details are to be accessed, to send to the api faqslist endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_for_which_details_are_to_be_accessed_to_send_to_the_api_faqslist_endpoint(Integer int1) {
        requestBody = new JSONObject();
        requestBody.put("id", id);
    }

    //=============AYCA START POINT==============//


    @Given("The api user prepares a POST request containing the {string}, {string}, {string} information to send to the api change-password endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_the_api_change_password_endpoint(String oldPassword, String password, String passwordConfirmation) {
        requestBody = new JSONObject();
        requestBody.put("old_password", oldPassword);
        requestBody.put("password", password);
        requestBody.put("password_confirmation", passwordConfirmation);

    }

    @Given("The api user sends the POST request and saves the response returned from the api change-password endpoint.")
    public void the_api_user_sends_the_post_request_and_saves_the_response_returned_from_the_api_change_password_endpoint() {


        API_Methods.postResponse(requestBody.toString());

    }

    @Given("The api user prepares a POST request containing the department id to be deleted to send to the api departmentDelete endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_department_to_be_deleted_to_send_to_the_api_department_delete_endpoint() {


        JSONObject reqBody = new JSONObject();
        reqBody.put("name", "Marketing AYCA");
        reqBody.put("details", "Marketing DEPARTMENT AYCA");
        reqBody.put("status", 1453);
        API_Methods.postResponse(reqBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");

    }

    @Given("The api user sends the DELETE request and saves the response returned from the api departmentDelete endpoint.")
    public void the_api_user_sends_the_delete_request_and_saves_the_response_returned_from_the_api_department_delete_endpoint() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        API_Methods.deleteResponse(requestBody.toString());

    }

    @Given("The api user verifies that the Deleted id information in the response body is the same as the id information in the request body.")
    public void the_api_user_verifies_that_the_deleted_id_information_in_the_response_body_is_the_same_as_the_id_information_in_the_request_body() {

        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(added_item_id, jsonPath.getInt("Deleted_Id"));

    }


    @Given("The api user prepares a PATCH request containing the {string} data to send to the api refundReasonUpdate endpoint.")
    public void the_api_user_prepares_a_patch_request_containing_the_data_to_send_to_the_api_refund_reason_update_endpoint(String reason) {
        requestBody = new JSONObject();
        requestBody.put("reason", reason);

    }

    @Given("The api user sends the PATCH request and saves the response returned from the api refundReasonUpdate endpoint.")
    public void the_api_user_sends_the_patch_request_and_saves_the_response_returned_from_the_api_refund_reason_update_endpoint() {

        API_Methods.patchResponse(requestBody.toString());
    }

    @Given("The api user verifies that the updated id information in the response body matches the id path parameter specified in the endpoint.")
    public void the_api_user_verifies_that_the_updated_id_information_in_the_response_body_matches_the_id_path_parameter_specified_in_the_endpoint() {

        // 1. adim
        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("updated_Id"));

        // 2. adim
        API_Methods.response.then()
                .assertThat()
                .body("updated_Id", equalTo(id));
    }


    @Given("The api user prepares a GET request containing the refund reason {int} for which details are to be accessed, to send to the api refundReasonDetails endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_refund_reason_for_which_details_are_to_be_accessed_to_send_to_the_api_refund_reason_details_endpoint(Integer int1) {

        requestBody = new JSONObject();
        requestBody.put("id", id);

    }

    @Given("The api user sends a GET request and saves the response returned from the api refundReasonDetails endpoint.")
    public void the_api_user_sends_a_get_request_and_saves_the_response_returned_from_the_api_refund_reason_details_endpoint() {

        API_Methods.getBodyResponse(requestBody.toString());

    }

    @Given("The api user verifies that the reason information in the response body is {string}.")
    public void the_api_user_verifies_that_the_reason_information_in_the_response_body_is(int id, String reason, String created_at, String updated_at) {

        jsonPath = API_Methods.response.jsonPath();

        Assert.assertEquals(id, jsonPath.getInt("refundReasonDetails[0].id"));
        Assert.assertEquals(reason, jsonPath.getString("refundReasonDetails[0].reason"));
        Assert.assertEquals(created_at, jsonPath.getString("refundReasonDetails[0].created_at"));
        Assert.assertEquals(updated_at, jsonPath.getString("refundReasonDetails[0].updated_at"));
    }

    @Given("The API user sends a GET request body and records the response from the api address-list endpoint.")
    public void the_api_user_sends_a_get_request_body_and_records_the_response_from_the_api_api_address_list_endpoint() {


       response =  API_Methods.getBodyResponse(requestBody.toString());

        response = API_Methods.getResponse();

    }

    @When("The api user sends the DELETE request with incorrect department ID and saves the response returned from the api departmentDelete endpoint.")
    public void theApiUserSendsTheDELETERequestWithIncorrectDepartmentIDAndSavesTheResponseReturnedFromTheApiDepartmentDeleteEndpoint() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 572894875);
        API_Methods.deleteResponse(requestBody.toString());

    }


    @When("The api user prepares a GET request containing the department id to be deleted to send to the api departmentDetails endpoint.")
    public void theApiUserPreparesAGETRequestContainingTheDepartmentIdToBeDeletedToSendToTheApiDepartmentDetailsEndpoint() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        API_Methods.deleteResponse(requestBody.toString());
        API_Methods.getBodyResponse(requestBody.toString());

    }

    @Then("The api user validates the {},{},{string},{string},{string},{string},{string},{string},{string},{string}, {},{},{string},{string} of the response body")
    public void theApiUserValidatesTheOfTheResponseBody(int id,int customer_id, String name,String email, String phone, String address, String city, String state, String country, String postal_code, int is_shipping_default, int is_billing_default, String created_at, String updated_at) {

        jsonPath = API_Methods.response.jsonPath();

        //System.out.println("RESPONSE ID---->>> " + jsonPath.getInt("addresses[0].id"));


        Assert.assertEquals(id, jsonPath.getInt("addresses[0].id"));
        Assert.assertEquals(name, jsonPath.getString("addresses[0].name"));
        Assert.assertEquals(email, jsonPath.getString("addresses[0].email"));
        Assert.assertEquals(customer_id, jsonPath.getInt("addresses[0].customer_id"));
        Assert.assertEquals(phone, jsonPath.getString("addresses[0].phone"));
        Assert.assertEquals(address, jsonPath.getString("addresses[0].address"));
        Assert.assertEquals(city, jsonPath.getString("addresses[0].city"));
        Assert.assertEquals(state, jsonPath.getString("addresses[0].state"));
        Assert.assertEquals(country, jsonPath.getString("addresses[0].country"));
        Assert.assertEquals(postal_code, jsonPath.getString("addresses[0].postal_code"));
        Assert.assertEquals(is_shipping_default, jsonPath.getInt("addresses[0].is_shipping_default"));
        Assert.assertEquals(is_billing_default, jsonPath.getInt("addresses[0].is_billing_default"));
        Assert.assertEquals(created_at, jsonPath.getString("addresses[0].created_at"));
        Assert.assertEquals(updated_at, jsonPath.getString("addresses[0].updated_at"));

    }



    //=============AYCA END OF STEPS=============//


    @Given("The api user sends a GET request and saves the response returned from the api faqslist endpoint.")
    public void the_api_user_sends_a_get_request_and_saves_the_response_returned_from_the_api_faqslist_endpoint() {
        API_Methods.getBodyResponse(requestBody.toString());
    }


    //========== Gulnur Finish ======================================

    @Given("The API user records the response from the api holidayList endpoint, confirming that the status code is {string} and the reason phrase is Unauthorized.")
    public void the_api_user_records_the_response_from_the_api_holiday_list_endpoint_confirming_that_the_status_code_is_and_the_reason_phrase_is_unauthorized(String string) {
        Assert.assertTrue(API_Methods.tryCatchGet().equals(ConfigReader.getProperty("unauthorizedExceptionMessage", "api")));
    }


    @Given("The api user prepares a POST request containing the {string}, {string}, {string} information to send to api holidayAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_api_endpoint(String year, String name, String date) {
        reqBody = new HashMap<>();

        reqBody.put("2025", year);
        reqBody.put("Kerst", name);
        reqBody.put("2025-01-01", date);

    }

    @Given("The api user send the POST request and saves the response returned from the api {string} endpoint.")
    public void the_api_user_send_the_post_request_and_saves_the_response_returned_from_the_api_endpoint(String string) {
        API_Methods.postResponse(reqBody);

    }

    //================================US_42============================================00
    @Given("The api user prepares a POST request containing the {string}, {string}, {string},{string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api addressAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_the_api_address_add_endpoint(String customer_id, String name, String email, String address, String phone, String city, String state, String country, String postal_code, String address_type) {
        reqBodyHash = new HashMap<>();

        reqBodyHash.put("customer_id", customer_id);
        reqBodyHash.put("name", name);
        reqBodyHash.put("email", email);
        reqBodyHash.put("address", address);
        reqBodyHash.put("phone", phone);
        reqBodyHash.put("city", city);
        reqBodyHash.put("state", state);
        reqBodyHash.put("country", country);
        reqBodyHash.put("postal_code", postal_code);
        reqBodyHash.put("address_type", address_type);
    }

    @Given("The api user sends the POST request and saves the response returned from the api addressAdd endpoint.")
    public void the_api_user_sends_the_post_request_and_saves_the_response_returned_from_the_api_address_add_endpoint() {
        API_Methods.postResponse(reqBodyHash);

    }


    //  Aslis
    @When("The api user prepares a POST request containing the holiday data {string}, {string}, {string}")
    public void theApiUserPreparesAPOSTRequestContainingTheHolidayData(String year, String name, String date) {
        requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("name", name);
        requestBody.put("date", date);

    }

    @And("The api user send POST request to the  endpoint.")
    public void theApiUserSendPOSTRequestToTheEndpoint() {
        API_Methods.postResponse(requestBody.toString());
    }

    @When("The api user prepares a GET request containing the refund reason <id> for which details are to be accessed, to send to the api holidayDetails endpoint.")
    public void theApiUserPreparesAGETRequestContainingTheRefundReasonIdForWhichDetailsAreToBeAccessedToSendToTheApiHolidayDetailsEndpoint() {
    }

    // Aslis End


    //**************Gamze**********************
    @Given("The api user verifies that the content of the data {int}, {string}, {string} in the response body.")
    public void the_api_user_verifies_that_the_content_of_the_data_in_the_response_body(Integer index, String name, String details) {
        jsonPath = API_Methods.response.jsonPath();

        Assert.assertEquals(name, jsonPath.getString("departments[" + index + "].name"));
        Assert.assertEquals(details, jsonPath.getString("departments[" + index + "].details"));
    }

    @Given("The api user sends a GET request containing the {int} in the body and saves the response")
    public void the_api_user_sends_a_get_request_containing_the_in_the_body_and_saves_the_response(Integer id) {
        requestBody = new JSONObject();
        requestBody.put("id", id);
        API_Methods.getBodyResponse(requestBody.toString());
    }

    @Given("The api user verifies that the content of the data {int}, {string}, {string}, {int},{string},{string} in the response body.")
    public void the_api_user_verifies_that_the_content_of_the_data_in_the_response_body(Integer id, String name, String details, Integer status, String created_at, String updated_at) {
        jsonPath = API_Methods.response.jsonPath();

        System.out.println("RESPONSE ID---->>> " + jsonPath.getInt("departmentDetails[0].id"));

        Assert.assertEquals(id, (Integer) jsonPath.getInt("departmentDetails[0].id"));
        Assert.assertEquals(name, jsonPath.getString("departmentDetails[0].name"));
        Assert.assertEquals(details, jsonPath.getString("departmentDetails[0].details"));
        Assert.assertEquals(status, (Integer) jsonPath.getInt("departmentDetails[0].status"));
        Assert.assertEquals(created_at, jsonPath.getString("departmentDetails[0].created_at"));
        Assert.assertEquals(updated_at, jsonPath.getString("departmentDetails[0].updated_at"));

    }

    @Given("The api user prepares a POST request containing the {string} information to send to the api refundReasonAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_the_api_refund_reason_add_endpoint(String reason) {
        reqBody = new HashMap<>();
        reqBody.put("reason", reason);
    }

    @Given("The api user sends the POST request and saves the response returned from the api refundReasonAdd endpoint.")
    public void the_api_user_sends_the_post_request_and_saves_the_response_returned_from_the_api_refund_reason_add_endpoint() {
        API_Methods.postResponse(reqBody);
    }

    @Given("The api user sends a POST request containing {string},{string},{int} in the body and saves the response")
    public void the_api_user_sends_a_post_request_containing_status_in_the_body_and_saves_the_response(String name, String details, Integer status) {
        requestBody = new JSONObject();
        requestBody.put("name", name).put("details", details).put("status", status);
        API_Methods.postResponse(requestBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");
    }

    @Given("The api user prepares a GET request containing the department id\\(added_item_id)")
    public void the_api_user_prepares_a_get_request_containing_the_department_id_added_item_id() {
        requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        API_Methods.getBodyResponse(requestBody.toString());
    }

    @Given("The api user sends a POST request containing these {string},{string},{int} in the body and saves the response")
    public void the_api_user_sends_a_post_request_containing_these_in_the_body_and_saves_the_response(String name, String details, Integer status) {
        requestBody = new JSONObject();
        requestBody.put("name", name).put("details", details).put("status", status);
        API_Methods.postResponse(requestBody.toString());
    }



    @When("The API user sends a GET request {int} and records the response from the api {string} endpoint.")
    public void theAPIUserSendsAGETRequestAndRecordsTheResponseFromTheApiEndpoint(int id, String endPoint) {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id",id);
        API_Methods.getBodyResponse(requestBody.toString());
    }




    @Given("The api user verifies that ID {int}  has the name attribute as {string}.")
    public void the_api_user_verifies_that_id_has_the_name_attribute_as(Integer id, String name) {
        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, (Integer) jsonPath.getInt("addresses[6].id"));
        Assert.assertEquals(name, jsonPath.getString("addresses[6].name"));
    }

    //=======nazime US_42_TC_02
    @Given("The api user prepares a GET request containing the addressAdd {int} for which details are to be accessed, to send to the api addressDetails endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_address_add_for_which_details_are_to_be_accessed_to_send_to_the_api_address_details_endpoint(Integer id) {
        requestBody = new JSONObject();
        requestBody.put("id", id);

    }


    @Given("The api user sends a GET request and saves the response returned from the api addressDetails endpoint.")
    public void the_api_user_sends_a_get_request_and_saves_the_response_returned_from_the_api_address_details_endpoint() {
        API_Methods.getResponse().toString();

    }


    @Given("The api user sends a GET request containing country_id {int} in the body and saves the response")
    public void the_api_user_sends_a_get_request_containing_country_id_in_the_body_and_saves_the_response(Integer countryID) {
        requestBody = new JSONObject();
        requestBody.put("country_id", countryID);
        API_Methods.getBodyResponse(requestBody.toString());
    }
}


