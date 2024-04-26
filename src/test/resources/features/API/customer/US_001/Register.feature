@smoke
@priority1
Feature: As a user/administrator, I should be able to create a new customer record in the system via API connection.

Scenario: When a POST body consisting of valid data (first_name, last_name, email, password, password_confirmation, user_type, referral_code) is sent to the /api/register endpoint,
          the expected status code returned should be 201, and the response message should confirm: "Successfully registered".

  * The api user constructs the base url with the "customer" token.
  * The api user sets "api/register" path parameters
  * The api user prepares a POST request containing the "first_name", "last_name", "email", "password", "password_confirmation", "user_type", "referral_code" information to send to the api register endpoint.
  * The api user sends the POST request and saves the response returned from the api register endpoint.
  * The api user verifies that the status code is 201
  * The api user verifies that the message information in the response body is "Successfully registered"


 Scenario: When a valid POST body containing data fields (first_name, last_name, email, password, password_confirmation, user_type, referral_code) is sent to the /api/register endpoint,
          the data in the response body (first_name, last_name, email) should be verified to match the data sent in the request body.

   * The api user constructs the base url with the "customer" token.
   * The api user sets "api/register" path parameters
   * The api user prepares a POST request containing the "first_name", "last_name", "email", "password", "password_confirmation", "user_type", "referral_code" information to send to the api register endpoint.
   * The api user sends the POST request and saves the response returned from the api register endpoint.
   * The api user verifies that the register information in the response body is "first_name", "last_name", "email".


 Scenario: When an invalid email (an existing email) from the data is sent in the POST body (first_name, last_name, email, password, password_confirmation, user_type, referral_code)
           to the /api/register endpoint, the expected status code returned should be 422, and the response message should confirm: "The email has already been taken.".

   * The api user constructs the base url with the "customer" token.
   * The api user sets "api/register" path parameters
   * The api user prepares a POST request containing the "first_name", "last_name", "email", "password", "password_confirmation", "user_type", "referral_code" information to send to the api register endpoint for negatif test.
   * The api user sends the POST request and saves the response returned from the api register endpoint.
   * The api user verifies that the status code is 422
   * The api user verifies that the message information in the response body is "The email has already been taken."


 Scenario: When a POST body with incomplete data (missing email) consisting of (first_name, last_name, email, password, password_confirmation, user_type, referral_code)
          is sent to the /api/register endpoint, the expected status code returned should be 422, and the response message should confirm: "The email field is required.".

   * The api user constructs the base url with the "customer" token.
   * The api user sets "api/register" path parameters
   * The api user prepares a POST request containing the "first_name", "last_name", "email", "password", "password_confirmation", "user_type", "referral_code" information to send to the api register endpoint with empty email.
   * The api user sends the POST request and saves the response returned from the api register endpoint.
   * The api user verifies that the status code is 422
   * The api user verifies that the message information in the response body is "The email field is required."


 Scenario: When a POST body with incomplete data (missing password) consisting of (first_name, last_name, email, password, password_confirmation, user_type, referral_code)
           is sent to the /api/register endpoint, the expected status code returned should be 422, and the response message should confirm: "The password field is required.".

   * The api user constructs the base url with the "customer" token.
   * The api user sets "api/register" path parameters
   * The api user prepares a POST request containing the "first_name", "last_name", "email", "password", "password_confirmation", "user_type", "referral_code" information to send to the api register endpoint with empty password.
   * The api user sends the POST request and saves the response returned from the api register endpoint.
   * The api user verifies that the status code is 422
   * The api user verifies that the message information in the response body is "The password field is required."


 Scenario: When a POST body containing mismatched password and password_confirmation is sent to the /api/register endpoint (first_name, last_name, email, password, password_confirmation, user_type, referral_code),
          the expected status code returned should be 422, and the response message should confirm: "The password confirmation does not match.".

   * The api user constructs the base url with the "customer" token.
   * The api user sets "api/register" path parameters
   * The api user prepares a POST request containing the "first_name", "last_name", "email", "password", "password_confirmation", "user_type", "referral_code" information to send to the api register endpoint with mismatched password
   * The api user sends the POST request and saves the response returned from the api register endpoint.
   * The api user verifies that the status code is 422
   * The api user verifies that the message information in the response body is "The password confirmation does not match."


 Scenario: When a POST body containing a password with fewer than 8 characters is sent to the /api/register endpoint (first_name, last_name, email, password, password_confirmation, user_type, referral_code),
           the expected status code returned should be 422, and the response message should confirm: "The password field minimum 8 character.".

   * The api user constructs the base url with the "customer" token.
   * The api user sets "api/register" path parameters
   * The api user prepares a POST request containing the "first_name", "last_name", "email", "password", "password_confirmation", "user_type", "referral_code" information to send to the api register endpoint with fewer than 8 characters password
   * The api user sends the POST request and saves the response returned from the api register endpoint.
   * The api user verifies that the status code is 422
   * The api user verifies that the message information in the response body is "The password field minimum 8 character."
