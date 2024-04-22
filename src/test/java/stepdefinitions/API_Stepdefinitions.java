package stepdefinitions;

import com.beust.ah.A;
import com.github.javafaker.Faker;
import config_Requirements.ConfigReader;
import hooks.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static hooks.HooksAPI.spec;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static utilities.API_Utilities.API_Methods.messageAssert;
import static utilities.API_Utilities.API_Methods.response;


public class API_Stepdefinitions {
    public static int id;
    public static String fullPath;


    int updated_Id;

    private String requestJSONBody;
    private int idInPath;
    private int updatedIdInResponse;

    public static JSONObject requestBody, requestBody2;
    public static JsonPath jsonPath;
    HashMap<Object, String> reqBodyHash;
    public static int added_item_id;
    Response response;
    Faker faker = new Faker();
    Map<String, Object> reqBody;
    String password;
    private String jsonResponse;
    private String updatedReqBody;

    //========API Esra Baslangic=================================================================================


    //===US_037 ve US_003===

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

        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("addresses[222].id"));
        Assert.assertEquals(code, jsonPath.getString("addresses[222].code"));
        Assert.assertEquals(name, jsonPath.getString("addresses[222].name"));
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
        password = faker.internet().password();
        reqBody.put("first_name", faker.name().firstName());
        reqBody.put("last_name", faker.name().lastName());
        reqBody.put("email", "customer.esra@buysellcycle.com");
        reqBody.put("password", password);
        reqBody.put("password_confirmation", password);
        reqBody.put("user_type", "customer");
        reqBody.put("referral_code", "0101010101");
    }

    @When("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api register endpoint with empty email.")
    public void theApiUserPreparesAPOSTRequestContainingTheInformationToSendToTheApiRegisterEndpointWithEmptyEmail(String firstName, String lastName, String email, String password, String passwordConfirmation, String userType, String referralCode) {
        reqBody = new HashMap<>();
        password = faker.internet().password();
        reqBody.put("first_name", faker.name().firstName());
        reqBody.put("last_name", faker.name().lastName());
        reqBody.put("email", "");
        reqBody.put("password", password);
        reqBody.put("password_confirmation", password);
        reqBody.put("user_type", "customer");
        reqBody.put("referral_code", "0101010101");

    }

    @When("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api register endpoint with empty password.")
    public void theApiUserPreparesAPOSTRequestContainingTheInformationToSendToTheApiRegisterEndpointWithEmptyPassword(String firstName, String lastName, String email, String password, String passwordConfirmation, String userType, String referralCode) {
        reqBody = new HashMap<>();
        password = faker.internet().password();
        reqBody.put("first_name", faker.name().firstName());
        reqBody.put("last_name", faker.name().lastName());
        reqBody.put("email", faker.internet().emailAddress());
        reqBody.put("password", "");
        reqBody.put("password_confirmation", password);
        reqBody.put("user_type", "customer");
        reqBody.put("referral_code", "0101010101");

    }

    @When("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api register endpoint with mismatched password")
    public void theApiUserPreparesAPOSTRequestContainingTheInformationToSendToTheApiRegisterEndpointWithMismatchedPassword(String firstName, String lastName, String email, String password, String passwordConfirmation, String userType, String referralCode) {
        reqBody = new HashMap<>();
        password = faker.internet().password();
        reqBody.put("first_name", faker.name().firstName());
        reqBody.put("last_name", faker.name().lastName());
        reqBody.put("email", faker.internet().emailAddress());
        reqBody.put("password", password);
        reqBody.put("password_confirmation", "123456789");
        reqBody.put("user_type", "customer");
        reqBody.put("referral_code", "0101010101");
    }

    @When("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api register endpoint with fewer than {int} characters password")
    public void theApiUserPreparesAPOSTRequestContainingTheInformationToSendToTheApiRegisterEndpointWithFewerThanCharactersPassword(String firstName, String lastName, String email, String password, String passwordConfirmation, String userType, String referralCode, int caracter) {

        reqBody = new HashMap<>();
        reqBody.put("first_name", faker.name().firstName());
        reqBody.put("last_name", faker.name().lastName());
        reqBody.put("email", faker.internet().emailAddress());
        reqBody.put("password", "123456");
        reqBody.put("password_confirmation", "123456");
        reqBody.put("user_type", "customer");
        reqBody.put("referral_code", "0101010101");
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
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        API_Methods.deleteResponse(requestBody.toString());

    }


    //===US_015======

    @When("The api user prepares a PATCH request containing the {string} ,{string} data to send to the api faqs Update endpoint.")
    public void theApiUserPreparesAPATCHRequestContainingTheDataToSendToTheApiFaqsUpdateEndpoint(String title, String description) {
        requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("description", description);
    }

    @When("The api user sends the PATCH request and saves the response returned from the api {string} endpoint.")
    public void theApiUserSendsThePATCHRequestAndSavesTheResponseReturnedFromTheApiEndpoint(String endPoint) {
        API_Methods.patchResponse(requestBody.toString());
    }

    @When("The API user validates the {int} content of the data in the response body returned from the response.")
    public void theAPIUserValidatesTheContentOfTheDataInTheResponseBodyReturnedFromTheResponse(int id) {

        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("updated_Id"));
    }

    @When("The api user prepares a GET request containing the department {int} to verify that the record has been updated to send to the api faqsDetails endpoint.")
    public void theApiUserPreparesAGETRequestContainingTheDepartmentIdToVerifyThatTheRecordHasBeenUpdatedToSendToTheApiFaqsDetailsEndpoint(int id) {

        requestBody2 = new JSONObject();
        requestBody2.put("id", id);
        System.out.println(requestBody2);
        API_Methods.getBodyResponse(requestBody2.toString());
        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("FaqsDetails[0].id"));
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

    @Given("The api user sends the POST request and saves the response returned from the api {string} endpoint.")
    public void the_api_user_sends_the_post_request_and_saves_the_response_returned_from_the_api_change_password_endpoint(String endpoint) {

        API_Methods.postResponse(requestBody.toString());

    }

    @Given("The api user prepares a POST request containing the department id to be deleted to send to the api departmentAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_department_to_be_deleted_to_send_to_the_api_department_add_endpoint() {


        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Marketing AYCA");
        requestBody.put("details", "Marketing DEPARTMENT AYCA");
        requestBody.put("status", 1453);
        API_Methods.postResponse(requestBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");

    }

    @Given("The api user sends the DELETE request and saves the response returned from the api {string} endpoint.")
    public void the_api_user_sends_the_delete_request_and_saves_the_response_returned_from_the_api_department_delete_endpoint(String endPoint) {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        API_Methods.deleteResponse(requestBody.toString());

    }

    @Given("The api user verifies that the Deleted id information in the response body is the same as the {int} information in the request body.")
    public void the_api_user_verifies_that_the_deleted_id_information_in_the_response_body_is_the_same_as_the_id_information_in_the_request_body(int id) {

        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("Deleted_Id"));

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

        response = API_Methods.getResponse();

    }

    @When("The api user sends the DELETE request with incorrect ID {int} and saves the response returned from the api departmentDelete endpoint.")
    public void theApiUserSendsTheDELETERequestWithIncorrectDepartmentIDAndSavesTheResponseReturnedFromTheApiDepartmentDeleteEndpoint(int departmentID) {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", departmentID);
        API_Methods.deleteResponse(requestBody.toString());

    }


    @When("The api user prepares a GET request containing the department id to be deleted to send to the api departmentDetails endpoint.")
    public void theApiUserPreparesAGETRequestContainingTheDepartmentIdToBeDeletedToSendToTheApiDepartmentDetailsEndpoint() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        //API_Methods.deleteResponse(requestBody.toString());
        API_Methods.getBodyResponse(requestBody.toString());

    }

    @Then("The api user validates the {},{},{string},{string},{string},{string},{string},{string},{string},{string}, {},{},{string},{string} of the response body")
    public void theApiUserValidatesTheOfTheResponseBody(int id, int customer_id, String name, String email, String phone, String address, String city, String state, String country, String postal_code, int is_shipping_default, int is_billing_default, String created_at, String updated_at) {

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

    @When("The api user prepares a POST request containing the holiday id to be deleted to send to the api holidayAdd endpoint.")
    public void theApiUserPreparesAPOSTRequestContainingTheDepartmentIdToBeDeletedToSendToTheApiHolidayAddEndpoint() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("year", "2025");
        reqBody.put("name", "DILAN Holiday Hotel");
        reqBody.put("date", "2024-04-23");
        API_Methods.postResponse(reqBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");

    }

    @When("The api user prepares a POST request containing {string},{string},{string} the holiday id to be deleted to send to the api holidayAdd endpoint.")
    public void theApiUserPreparesAPOSTRequestContainingTheHolidayIdToBeDeletedToSendToTheApiHolidayAddEndpoint(String year, String name, String date) {

        JSONObject reqBody = new JSONObject();
        reqBody.put("year", year);
        reqBody.put("name", name);
        reqBody.put("date", date);
        API_Methods.postResponse(reqBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");
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
    
    @Then("The api user verifies that the content of the data {int}, {string} , {string} , {string} ,{string} in the response body.")
    public void theApiUserVerifiesThatTheContentOfTheDataIdInTheResponseBody(int id, String title, String coupon_code, String start_date, String end_date) {

        jsonPath = API_Methods.response.jsonPath();

        Assert.assertEquals(id, jsonPath.getInt("couponDetails[0].id"));
        Assert.assertEquals(title, jsonPath.getString("couponDetails[0].title"));
        Assert.assertEquals(coupon_code, jsonPath.getString("couponDetails[0].coupon_code"));
        Assert.assertEquals(start_date, jsonPath.getString("couponDetails[0].start_date"));
        Assert.assertEquals(end_date, jsonPath.getString("couponDetails[0].end_date"));
    }
    @And("The api user verifies the response with the following JSON:")
    public void theApiUserVerifiesTheResponseWithTheFollowingJSON(String expectedJsonBody) {

        JsonPath actualJsonPath = new JsonPath(API_Methods.response.getBody().asString());
        JsonPath expectedJsonPath = new JsonPath(expectedJsonBody);
        assertEquals(expectedJsonPath.getMap(""), actualJsonPath.getMap("couponDetails[0]"));


    }

    @When("The API user sends a PATCH request to the endpoint with the following body:")
    public void theAPIUserSendsAPATCHRequestToTheEndpointWithTheFollowingBody(String requestBody) {

        requestBody = requestBody.replace("<newName>", Faker.instance().name().fullName())
                .replace("<newEmail>", Faker.instance().internet().emailAddress())
                .replace("<newAddress>", Faker.instance().address().streetAddress())
                .replace("<newPhone>", Faker.instance().phoneNumber().cellPhone())
                .replace("<newCity>", Faker.instance().address().city())
                .replace("<newState>", Faker.instance().address().state())
                .replace("<newCountry>", Faker.instance().address().country())
                .replace("<newPostalCode>", Faker.instance().address().zipCode())
                .replace("<newAddressType>", "Home"); // Assume static value for address type

        API_Methods.patchResponse(requestBody);
        requestJSONBody= requestBody;

    }

    @When("The API user sends a PATCH request with invalid email to the endpoint with the following body:")
    public void theAPIUserSendsAPATCHRequestWithInvalidEmailToTheEndpointWithTheFollowingBody(String requestBody) {
        API_Methods.patchResponse(requestBody);
    }

    @And("The updated_id information in the response body should match the id specified in the path parameter {string}")
    public void theUpdated_idInformationInTheResponseBodyShouldMatchTheIdSpecifiedInThePathParameter(String pathParameter) {
        idInPath = Integer.parseInt(pathParameter.replaceAll("\\D", ""));
        System.out.println(idInPath);
        JsonPath resJP = new JsonPath(API_Methods.response.getBody().asString());
        updatedIdInResponse = resJP.getInt("updated_Id");
        System.out.println(updatedIdInResponse);
        assertEquals(updatedIdInResponse, idInPath);

    }


    @Then("The api verifies that Get Response Body matches with the updated Adress")
    public void theApiVerifiesThatGetResponseBodyMatchesWithTheUpdatedAdress() {
        JSONObject jsonObject = new JSONObject(API_Methods.response);
        JSONArray addressesArray = jsonObject.getJSONArray("addresses");
        JSONObject firstAddress = addressesArray.getJSONObject(0);
        JSONObject reqJSONBodyObject = new JSONObject(requestJSONBody);
        assertEquals(reqJSONBodyObject.get("name") ,firstAddress.getString("name"));


        /* {
            "name": "Miss Lavern Kshlerin",
                "email": "vicente.daugherty@yahoo.com",
                "address": "7435 Moore Prairie",
                "phone": "795-007-5654",
                "city": "East Lowellfurt",
                "state": "Nevada",
                "country": "El Salvador",
                "postal_code": "63976-9408",
                "address_type": "Home"
        }

        */





/*
        {
            "addresses": [
            {
                "id": 25,
                    "customer_id": 124,
                    "name": "Miss Lavern Kshlerin",
                    "email": "vicente.daugherty@yahoo.com",
                    "phone": "795-007-5654",
                    "address": "7435 Moore Prairie",
                    "city": "East Lowellfurt",
                    "state": "Nevada",
                    "country": "El Salvador",
                    "postal_code": "63976-9408",
                    "is_shipping_default": 0,
                    "is_billing_default": 0,
                    "created_at": "2024-03-22T21:00:33.000000Z",
                    "updated_at": "2024-04-22T12:21:22.000000Z",
                    "get_country": null,
                    "get_state": null,
                    "get_city": null
            }
    ],
            "message": "success"
        }

 */


    }

    @When("The api user sends a POST request with the following JSON:")
    public void theApiUserSendsAPOSTRequestWithTheFollowingJSON(String requestJSONBody) {
        
        API_Methods.postResponse(requestJSONBody);
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
        requestBody.put("id", id);
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
        API_Methods.getBodyResponse(requestBody.toString());


    }

    @Given("The api user sends a GET request containing country_id {int} in the body and saves the response")
    public void the_api_user_sends_a_get_request_containing_country_id_in_the_body_and_saves_the_response(Integer countryID) {
        requestBody = new JSONObject();
        requestBody.put("country_id", countryID);
        API_Methods.getBodyResponse(requestBody.toString());
    }

    @Given("The api user prepares a PATCH request containing the {string}, {string}, {string} data to send to the api departmentUpdate endpoint.")
    public void the_api_user_prepares_a_patch_request_containing_the_data_to_send_to_the_api_department_update_endpoint(String name, String details, String status) {
        requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("details", details);
        requestBody.put("status", status);

    }

    @Given("The API user records the response from the api departmentUpdate endpoint, confirming that the status code is '404' and the reason phrase is Not Found.")
    public void the_api_user_records_the_response_from_the_api_department_update_endpoint_confirming_that_the_status_code_is_and_the_reason_phrase_is_not_found() {

    }

    @Given("The API user records the response from the api departmentUpdate endpoint, confirming that the status code is {string} and the reason phrase is Unauthorized.")
    public void the_api_user_records_the_response_from_the_api_department_update_endpoint_confirming_that_the_status_code_is_and_the_reason_phrase_is_unauthorized() {
        Assert.assertTrue(API_Methods.tryCatchGet().equals(ConfigReader.getProperty("unauthorizedExceptionMessage", "api")));

    }

    @Given("The api user verifies that the updated {int} information in the response body matches the id path parameter specified in the endpoint.")
    public void the_api_user_verifies_that_the_updated_information_in_the_response_body_matches_the_id_path_parameter_specified_in_the_endpoint(Integer id) {

        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, (Integer) jsonPath.getInt("updated_Id"));

    }


    //===================================US_30=================================
    @Given("The api user prepares a PATCH request containing the {string},{string},{int},{string},{string},{int},{int},{int},{string},{int},{int} data to send to the api couponUpdate endpoint.")
    public void the_api_user_prepares_a_patch_request_containing_the_data_to_send_to_the_api_refund_reason_update_endpoint(String title, String coupon_code, int coupon_type, String start_date, String end_date, int discount, int discount_type, int minimum_shopping, String maximum_discount, int is_expire, int is_multiple_buy) {
        requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("coupon_code", coupon_code);
        requestBody.put("coupon_type", coupon_type);
        requestBody.put("start_date", start_date);
        requestBody.put("end_date", end_date);
        requestBody.put("discount", discount);
        requestBody.put("discount_type", discount_type);
        requestBody.put("minimum_shopping", minimum_shopping);
        requestBody.put("maximum_discount", maximum_discount);
        requestBody.put("is_expire", is_expire);
        requestBody.put("is_multiple_buy", is_multiple_buy);

    }

    @Given("The api user prepares a GET request containing the refund reason {int} for which details are to be accessed, to send to the api holidayList endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_refund_reason_for_which_details_are_to_be_accessed_to_send_to_the_api_holiday_list_endpoint(Integer id) {
        requestBody = new JSONObject();
        requestBody.put("holiday.id", id);
    }


    @Given("The api user prepares a PATCH request containing the {string},{string},{string} data to send to the api holidayUpdate endpoint.")
    public void the_api_user_prepares_a_patch_request_containing_the_data_to_send_to_the_api_holiday_update_endpoint(String year, String name, String date) {
        requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("name", name);
        requestBody.put("date", date);
    }


    @Given("The api user verifies that the updated id information in the response body matches the id path parameter specified in the holidayUpdate endpoint.")
    public void the_api_user_verifies_that_the_updated_id_information_in_the_response_body_matches_the_id_path_parameter_specified_in_the_holiday_update_endpoint() {
        jsonPath = API_Methods.response.jsonPath();

    }

    @Given("The api user send POST request to the {string} endpoint.")
    public void the_api_user_send_post_request_to_the_endpoint(String endpoint) {
        API_Methods.postResponse(endpoint);
    }


    @Given("The API user sends a POST request and records the response from the api {string} endpoint.")
    public void the_api_user_sends_a_post_request_and_records_the_response_from_the_api_endpoint(String endpoint) {
        API_Methods.postResponse(requestBody);
    }

    @Given("The api user prepares a POST request containing the {string}, {string} information to send to the api faqsAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_the_api_endpoint(String title, String description) {
        requestBody = new JSONObject();

        requestBody.put("title", title);
        requestBody.put("description", description);

    }

    @Given("The API user sends a POST request and records the response from the api faqsAdd endpoint.")
    public void the_api_user_sends_a_post_request_and_records_the_response_from_the_api_api_faqs_add_endpoint() {

       API_Methods.postResponse(requestBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");
    }


    @Given("The API user validates the  id  content of the data in the response body returned from the response.")
    public void the_api_user_validates_the_content_of_the_data_in_the_response_body_returned_from_the_response(Integer int1) {

       // jsonPath = API_Methods.response.jsonPath();
      //  Assert.assertEquals(id, jsonPath.getInt("updated_Id"));
        API_Methods.response.then()
                .assertThat().body("updated_Id",equalTo(id));


    }

    @Given("The api user sends the GET request and saves the response returned from the api {string} endpoint.")

    public void the_api_user_sends_the_get_request_and_saves_the_response_returned_from_the_api_endpoint(String endpoint) {
        API_Methods.postResponse(requestBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");


    }

    @Given("The api user sends the GET request and saves the response returned from the api faqsDetails endpoint.")

    public void the_api_user_sends_the_get_request_and_saves_the_response_returned_from_the_api_endpoint() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        API_Methods.getBodyResponse(requestBody.toString());
        assertEquals(id, jsonPath.getInt("FaqsDetails[0].added_item_id"));
    }

    @Given("The api user prepares a GET request containing the refund reason {string} for which details are to be accessed, to send to the api {string} endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_refund_reason_for_which_details_are_to_be_accessed_to_send_to_the_api_endpoint(String id, String endpoint) {
        requestBody = new JSONObject();
        requestBody.put("added_item_id", id);
        API_Methods.getBodyResponse(requestBody.toString());
        //requestBody.put("id", added_item_id);
        //API_Methods.deleteResponse(requestBody.toString());
    }


    @Given("The api user prepares a GET request containing the {int} for which details are to be accessed, to send to the api departmentDetails endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_for_which_details_are_to_be_accessed_to_send_to_the_api_department_details_endpoint(Integer id) {
        requestBody = new JSONObject();
        requestBody.put("id", id);
    }

    @Given("The api user sends a GET request and saves the response returned from the api {string} endpoint.")
    public void the_api_user_sends_a_get_request_and_saves_the_response_returned_from_the_api_endpoint(String string) {
        API_Methods.getBodyResponse(requestBody.toString());
    }

    @Given("The api user verifies the content of the data {int},{string} in the response body.")
    public void the_api_user_verifies_the_content_of_the_data_in_the_response_body(int id, String name) {


        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("addresses[12].id"));
        Assert.assertEquals(name, jsonPath.getString("addresses[12].name"));
    }

    @Given("The api user sends a GET request with {int} in the body and saves the response")
    public void the_api_user_sends_a_get_request_with_in_the_body_and_saves_the_response(Integer state_id) {
        requestBody = new JSONObject();
        requestBody.put("state_id", state_id);
        API_Methods.getBodyResponse(requestBody.toString());
    }

    @When("The api user verifies the content of the data {int}, {int}, {string} ,{string} , {string} ,{string} ,{string} ,{string} ,{string} ,{string} in the response body.")
    public void theApiUserVerifiesTheContentOfTheDataInTheResponseBody(int id, int customer_id, String name, String email, String phone, String address, String city, String state, String country, String postal_code) {
        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("addresses[0].id"));
        Assert.assertEquals(customer_id, jsonPath.getInt("addresses[0].customer_id"));
        Assert.assertEquals(name, jsonPath.getString("addresses[0].name"));
        Assert.assertEquals(email, jsonPath.getString("addresses[0].email"));
        Assert.assertEquals(phone, jsonPath.getString("addresses[0].phone"));
        Assert.assertEquals(address, jsonPath.getString("addresses[0].address"));
        Assert.assertEquals(city, jsonPath.getString("addresses[0].city"));
        Assert.assertEquals(state, jsonPath.getString("addresses[0].state"));
        Assert.assertEquals(country, jsonPath.getString("addresses[0].country"));
        Assert.assertEquals(postal_code, jsonPath.getString("addresses[0].postal_code"));

    }


    @When("The api user validates the {string} of the response body with index {int}.")
    public void theApiUserValidatesTheOfTheResponseBodyWithIndex(String reason, int dataIndex) {
        API_Methods.response.then()
                .assertThat()
                .body("refundReason[" + dataIndex + "].reason", equalTo(reason));
    }

    @When("The API user records the response from the api refundReasonList endpoint, confirming that the status code is '401' and the reason phrase is Unauthorized.")
    public void theAPIUserRecordsTheResponseFromTheApiRefundReasonListEndpointConfirmingThatTheStatusCodeIsAndTheReasonPhraseIsUnauthorized() {
        Assert.assertTrue(API_Methods.tryCatchGet().equals(ConfigReader.getProperty("unauthorizedExceptionMessage", "api")));
    }

    @When("The API user records the response from the api refundReasonDetails endpoint, verifying that the status code is '404' and the reason phrase is Not Found.")
    public void theAPIUserRecordsTheResponseFromTheApiRefundReasonDetailsEndpointVerifyingThatTheStatusCodeIsAndTheReasonPhraseIsNotFound(int arg0) {
        Assert.assertTrue(API_Methods.tryCatchGetBody(requestBody.toString()).equals(ConfigReader.getProperty("notFoundExceptionMessage", "api")));
    }

    @When("The API user records the response from the api refundReasonDetails endpoint, confirming that the status code is '401' and the reason phrase is Unauthorized.")
    public void theAPIUserRecordsTheResponseFromTheApiRefundReasonDetailsEndpointConfirmingThatTheStatusCodeIsAndTheReasonPhraseIsUnauthorized() {
        Assert.assertTrue(API_Methods.tryCatchGetBody(requestBody.toString()).equals(ConfigReader.getProperty("unauthorizedExceptionMessage", "api")));
    }


    @Given("The api user validates the {int}, {string}, {string}, {string},{string},{string}  of the response body with index {int}.")
    public void the_api_user_validates_the_id_of_the_response_body_with_index(Integer id, String first_name, String username, String email, String phone, String name, Integer dataIndex) {

        API_Methods.response.then()
                .assertThat()
                .body("user[" + dataIndex + "].id", equalTo(id))
                .body("user[" + dataIndex + "].first_name", equalTo(first_name))
                .body("user[" + dataIndex + "].username", equalTo(username))
                .body("user[" + dataIndex + "].email", equalTo(email))
                .body("user[" + dataIndex + "].phone", equalTo(phone))
                .body("user[" + dataIndex + "].name", equalTo(name + " "));

    }

    @Given("The api user validates the {int}, {string}, {string}, {int},{string}, {int}, {int}, {string}, {int}, {string}, {string}  of the response body")
    public void the_api_user_validates_the_of_the_response_body(Integer id, String first_name, String last_name, Integer role_id, String email, Integer is_verified, Integer is_active, String lang_code, Integer currency_id, String currency_code, String name) {


    }

    @Given("The API user sends a GET request, the returned response verifies the {int}, {string}, {string}, {int}, {string}, {string}, {string} data information.")
    public void the_api_user_sends_a_get_request_the_returned_response_verifies_the_data_information(int id, String year, String name, int type, String date, String created_at, String updated_at) {

        API_Methods.response.then()
                .assertThat()
                .body("holidayDetails[0].id", equalTo(id))
                .body("holidayDetails[0].year", equalTo(year))
                .body("holidayDetails[0].name", equalTo(name))
                .body("holidayDetails[0].type", equalTo(type))
                .body("holidayDetails[0].date", equalTo(date))
                .body("holidayDetails[0].name", equalTo(created_at))
                .body("holidayDetails[0].name", equalTo(updated_at));

    }

    @Given("The api user prepares a POST request containing the {string},{string},{int},{string},{string},{int},{int},{int},{int},{int},{int} information to send to the api couponAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_the_api_faqs_add_endpoint(String title, String coupon_code,Integer coupon_type, String start_date, String end_date,Integer discount,Integer discount_type,Integer minimum_shopping,Integer maximum_discount,Integer is_expire,Integer is_multiple_buy ) {

        requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("coupon_code", coupon_code);
        requestBody.put("coupon_type", coupon_type);
        requestBody.put("start_date", start_date);
        requestBody.put("end_date", end_date);
        requestBody.put("discount", discount);
        requestBody.put("discount_type", discount_type);
        requestBody.put("minimum_shopping", minimum_shopping);
        requestBody.put("maximum_discount", maximum_discount);
        requestBody.put("is_expire", is_expire);
        requestBody.put("is_multiple_buy", is_multiple_buy);
        API_Methods.postResponse(requestBody.toString());


    }



    @Given("The api user sends the DELETE request and saves the response returned from the api couponDelete endpoint.")
    public void the_api_user_sends_the_delete_request_and_saves_the_response_returned_from_the_api_coupon_delete_endpoint() {
        API_Methods.deleteResponse(requestBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");
    }


    @Given("The api user sends the DELETE request with incorrect ID {int} and saves the response returned from the api {string} endpoint.")
    public void the_api_user_sends_the_delete_request_with_incorrect_id_and_saves_the_response_returned_from_the_api_coupon_delete_endpoint(Integer id, String endpoint) {
         JSONObject requestBody = new JSONObject();
         requestBody.put("id", id);
         API_Methods.deleteResponse(requestBody.toString());
    }




    @Given("The api user prepares a POST request containing the {int} to be deleted to send to the {string} endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_to_be_deleted_to_send_to_the_endpoint(Integer int1, String string) {
        requestBody = new JSONObject();
        requestBody.put("name", "Gulnur");
        requestBody.put("email", "gugu@gmail.com");
        requestBody.put("address", "Rue des Intellos");
        requestBody.put("phone", "0606060606");
        requestBody.put("city", "Valence");
        requestBody.put("state", "RhoneAlpes");
        requestBody.put("country", "France");
        requestBody.put("postal_code", "26000");
        requestBody.put("address_type", "11");
        API_Methods.postResponse(requestBody.toString());
        jsonPath = API_Methods.response.jsonPath();
    }

    //US_30/TC_04
    @Given("The api user prepares a GET request containing the department {int} to verify that the record has been updated to send to the api couponDetails endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_department_to_verify_that_the_record_has_been_updated_to_send_to_the_api_coupon_details_endpoint(Integer id) {
        jsonPath = API_Methods.response.jsonPath();

    }

    @Given("The api user prepares a PATCH request containing the {string},{string},{string},{string},{string},{string},{string},{string},{string},{string} data")
    public void the_api_user_prepares_a_patch_request_containing_the_data(String customer_id, String name, String email, String phone, String address, String city, String state, String country, String postal_code, String address_type) {
        requestBody = new JSONObject();
        requestBody.put("customer_id", customer_id);
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("phone", phone);
        requestBody.put("address", address);
        requestBody.put("city", city);
        requestBody.put("state", state);
        requestBody.put("country", country);
        requestBody.put("postal_code", postal_code);
        requestBody.put("address_type", address_type);
    }

    @Given("The api user sends the PATCH request and saves the response")
    public void the_api_user_sends_the_patch_request_and_saves_the_response() {
        API_Methods.patchResponse(requestBody.toString());
    }

    @Given("The api user verifies equality the content of the updated Id in the response body and {int}")
    public void the_api_user_verifies_equality_the_content_of_the_updated_id_in_the_response_body_and(Integer id) {
        jsonPath = API_Methods.response.jsonPath();
        updated_Id = jsonPath.getInt("updated_Id");
        Assert.assertEquals(id, (Integer) jsonPath.getInt("updated_Id"));
    }

    @Given("The API user sends a GET request  using updated_Id and verify  that the {string} has been updated")
    public void the_api_user_sends_a_get_request_using_updated_id_and_verify_that_the_record_has_been_updated
            (String name) {
        requestBody = new JSONObject();
        requestBody.put("id", updated_Id);
        API_Methods.getBodyResponse(requestBody);
        jsonPath = API_Methods.response.jsonPath();
        Assert.assertEquals(name, jsonPath.getString("addresses[0].name"));
    }


    @Given("The API user sends a GET request and records the response")
    public void the_api_user_sends_a_get_request_and_records_the_response() {
        API_Methods.getResponse();
    }


    @Given("The api user sends the DELETE request and saves the response returned from the {string} endpoint.")
    public void the_api_user_sends_the_delete_request_and_saves_the_response_returned_from_the_endpoint(String string) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        API_Methods.deleteResponse(requestBody.toString());
    }

    @Given("The api user prepares a DELETE request containing the {int} to be deleted to send to the {string} endpoint.")
    public void the_api_user_prepares_a_delete_request_containing_the_to_be_deleted_to_send_to_the_endpoint(Integer int1, String string) {
        requestBody = new JSONObject();
        requestBody.put("id", id);
    }

    @Given("The api user prepares a POST request containing {string} the holiday id to be deleted to send to the api holidayAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_holiday_id_to_be_deleted_to_send_to_the_api_holiday_add_endpoint(String Deleted_Id) {
        JSONObject reqBody = new JSONObject();
        reqBody.put("Deleted_Id", Deleted_Id);
    }

    @Given("The api user prepares a GET request containing the {int} for which details are to be accessed, to send to the api profile customerDetailsAddress endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_for_which_details_are_to_be_accessed_to_send_to_the_api_profile_customer_details_address_endpoint(Integer int1) {
        requestBody = new JSONObject();
        requestBody.put("id", id);
    }

   /* @Given("The api user validates the {int}, {String}, {string}, {string}, {int}, {string}, {string}  of the response body with index {int}")
    public void the_api_user_validates_the_of_the_response_body_with_index_data_index(int id, String user_id, String title, String description, int status, String created_at, String updated_at, int dataIndex) {

        API_Methods.response.then()
                .assertThat()
                .body("FaqsDetails[" + dataIndex + "].id", equalTo(id))
                .body("FaqsDetails[" + dataIndex + "].user_id", equalTo(user_id))
                .body("FaqsDetails[" + dataIndex + "].title", equalTo(title))
                .body("FaqsDetails[" + dataIndex + "].description", equalTo(description))
                .body("FaqsDetails[" + dataIndex + "].status", equalTo(status))
                .body("FaqsDetails[" + dataIndex + "].created_at", equalTo(created_at))
                .body("FaqsDetails[" + dataIndex + "].updated_at", equalTo(updated_at));

    }*/

    @Given("The api user prepares a POST request containing the {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} information to send to the api couponAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_the_api_coupon_add_endpoint(String title, String coupon_code, String coupon_type, String start_date, String end_date, String discount, String discount_type, String minimum_shopping, String maximum_discount, String is_expire, String is_multiple_buy) {

        reqBody = new HashMap<>();
        reqBody.put("title", title);
        reqBody.put("coupon_code", coupon_code);
        reqBody.put("coupon_type", coupon_type);
        reqBody.put("start_date", start_date);
        reqBody.put("end_date", end_date);
        reqBody.put("discount", discount);
        reqBody.put("discount_type", discount_type);
        reqBody.put("minimum_shopping", minimum_shopping);
        reqBody.put("maximum_discount", maximum_discount);
        reqBody.put("is_expire", is_expire);
        reqBody.put("is_multiple_buy", is_multiple_buy);

    }

    @Given("The api user sends the POST request and saves the response returned from the api couponAdd endpoint.")
    public void the_api_user_sends_the_post_request_and_saves_the_response_returned_from_the_api_coupon_add_endpoint() {
        API_Methods.postResponse(reqBody);
    }

    @Given("The api user prepares a GET request containing the refund reason {int} for which details are to be accessed, to send to the api couponDetails endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_refund_reason_for_which_details_are_to_be_accessed_to_send_to_the_api_coupon_details_endpoint(Integer id) {
        requestBody = new JSONObject();
        requestBody.put("id", id);
    }

    @Given("The api user sends a GET request and saves the response returned from the api couponDetails endpoint.")
    public void the_api_user_sends_a_get_request_and_saves_the_response_returned_from_the_api_coupon_details_endpoint() {
        API_Methods.getBodyResponse(requestBody.toString());
    }

    @Given("The api user prepares a POST request containing the {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} information to send to the api refundReasonAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_to_the_api_refund_reason_add_endpoint(String title, String coupon_code, String coupon_type, String start_date, String end_date, String discount, String discount_type, String minimum_shopping, String maximum_discount, String is_expire, String is_multiple_buy) {
        reqBody = new HashMap<>();
        reqBody.put("title", title);
        reqBody.put("coupon_code", coupon_code);
        reqBody.put("coupon_type", coupon_type);
        reqBody.put("start_date", start_date);
        reqBody.put("end_date", end_date);
        reqBody.put("discount", discount);
        reqBody.put("discount_type", discount_type);
        reqBody.put("minimum_shopping", minimum_shopping);
        reqBody.put("maximum_discount", maximum_discount);
        reqBody.put("is_expire", is_expire);
        reqBody.put("is_multiple_buy", is_multiple_buy);
    }


    @When("The api user validates the {int}, {string}, {string}, {string}, {int}, {string}, {string}  of the response body with index {int}")
    public void theApiUserValidatesTheOfTheResponseBodyWithIndex(int id, String user_id, String title, String description, int status, String created_at, String updated_at, int dataIndex) {

        API_Methods.response.then()
                .assertThat()
                .body("FaqsDetails[" + dataIndex + "].id", equalTo(id))
                .body("FaqsDetails[" + dataIndex + "].user_id", equalTo(user_id))
                .body("FaqsDetails[" + dataIndex + "].title", equalTo(title))
                .body("FaqsDetails[" + dataIndex + "].description", equalTo(description))
                .body("FaqsDetails[" + dataIndex + "].status", equalTo(status))
                .body("FaqsDetails[" + dataIndex + "].created_at", equalTo(created_at))
                .body("FaqsDetails[" + dataIndex + "].updated_at", equalTo(updated_at));

    }

    @Given("The api user prepares a DELETE request containing the addresses {string} to be deleted to send to the api deleteAddress endpoint.")
    public void the_api_user_prepares_a_delete_request_containing_the_addresses_to_be_deleted_to_send_to_the_api_delete_address_endpoint(String id) {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", added_item_id);
        API_Methods.deleteResponse(requestBody.toString());

    }

    @Given("The api user sends the DELETE request and saves the response returned from the api deleteAddress endpoint.")
    public void the_api_user_sends_the_delete_request_and_saves_the_response_returned_from_the_api_delete_address_endpoint() {
        API_Methods.deleteResponse(requestBody.toString());
    }

    //US_44/TC_02
    @Given("The API user records the response from the api deleteAddress endpoint, confirming that the status code is {string} and {string}.")
    public void the_api_user_records_the_response_from_the_api_delete_address_endpoint_confirming_that_the_status_code_is_and(String string, String string2) {
        Assert.assertTrue(API_Methods.tryCatchDelete(requestBody.toString()).equals(ConfigReader.getProperty("notFoundExceptionMessage", "api")));


    }

    //TC_03
    @Given("The api user prepares a DELETE request containing the address {int} to be deleted to send to the api deleteAddress endpoint.")
    public void the_api_user_prepares_a_delete_request_containing_the_address_to_be_deleted_to_send_to_the_api_delete_address_endpoint(Integer id) {
        requestBody = new JSONObject();
        requestBody.put("id", id);
    }

    @Given("The API user saves the response from the api deleteAddress endpoint, verifying that the status code is {string} and the reason phrase is Unauthorized.")
    public void the_api_user_saves_the_response_from_the_api_delete_address_endpoint_verifying_that_the_status_code_is_and_the_reason_phrase_is_unauthorized(String string) {
        Assert.assertTrue(API_Methods.tryCatchDelete(requestBody.toString()).equals(ConfigReader.getProperty("notFoundExceptionMessage", "api")));
    }

    //Tc01

    @Given("The api user prepares a POST request containing the {int},{string},{string},{string},{string},{string},{string},{string},{string},{string} information to send the api addressAdd endpoint.")
    public void the_api_user_prepares_a_post_request_containing_the_information_to_send_the_api_address_add_endpoint(int customer_id, String name, String email, String address, String phone, String city, String state, String country, String postal_code, String address_type) {
        requestBody = new JSONObject();
        requestBody.put("customer_id", customer_id);
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("address", address);
        requestBody.put("phone", phone);
        requestBody.put("city", city);
        requestBody.put("state", state);
        requestBody.put("country", country);
        requestBody.put("postal_code", postal_code);
        requestBody.put("address_type", address_type);

        API_Methods.postResponse(requestBody.toString());
        jsonPath = API_Methods.response.jsonPath();
        added_item_id = jsonPath.getInt("added_item_id");


    }

    //TC_04
    @Given("The api user prepares a GET request containing the  id to be deleted to send to the api addressDetails endpoint.")
    public void the_api_user_prepares_a_get_request_containing_the_id_to_be_deleted_to_send_to_the_api_address_details_endpoint() {
        requestBody = new JSONObject();
        requestBody.put("id", id);
        API_Methods.getBodyResponse(requestBody.toString());


    }

    @Given("The api user sends the DELETE request and saves the response with {int} from the api {string} endpoint.")
    public void the_api_user_sends_the_delete_request_and_saves_the_response_with_from_the_api_endpoint(int Deleted_Id, String endpoint) {
       JSONObject requestBody = new JSONObject();
       requestBody.put("id", Deleted_Id);
       API_Methods.deleteResponse(requestBody.toString());
    }


    @When("The api user sends a GET request containing {int} to send to endpoint")
    public void theApiUserSendsAGETRequestContainingIdToSendToEndpoint(int id) {
        requestBody = new JSONObject();
        requestBody.put("id", id);
        API_Methods.getBodyResponse(requestBody.toString());
    }
}




































