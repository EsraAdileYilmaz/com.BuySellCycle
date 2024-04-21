Feature: As a user, I should be able to access detailed information about the address in my profile with the specified ID via the API connection.
Scenario Outline:When a valid GET request containing the address ID data
that is intended to access detailed information is sent to the /api/profile/customerDetailsAddress
endpoint with valid authorization credentials, the returned status code should be 200, and the message
in the response should be verified as "success".
* The api user constructs the base url with the "customer" token.
* The api user sets "api/profile/customerDetailsAddress" path parameters
* The api user prepares a GET request containing <id> for which details are to be accessed, to send to the api endpoint.
* The API user sends a GET request and records the response from the api "api/profile/customerDetailsAddress" endpoint.
* The api user verifies that the status code is 200
* The api user verifies that the message information in the response body is "success"

Examples:
  |id|
  |165|

Scenario Outline: When a valid GET request containing the address ID data intended
to access detailed information is sent to the /api/profile/customerDetailsAddress endpoint
with valid authorization credentials, the data in the response body, including
(id, customer_id, name, email, phone, address, city, state, country, postal_code,
is_shipping_default, is_billing_default, created_at, updated_at), should be verified.

  * The api user constructs the base url with the "admin" token.
  * The api user sets "api/faqsDelete" path parameters
  * The api user verifies the data in the response body
  * The api user sends the DELETE request and saves the response returned from to the api to the api end point



Examples:
  |id| |customer_id| |name| |email| |phone| |address| |city| |state| |country| |postal_code| |is_shipping_default| |is_billing_default|

