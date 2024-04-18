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
