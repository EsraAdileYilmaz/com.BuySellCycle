
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
      |166|

  Scenario Outline:When a valid GET request containing the address ID data intended
  to access detailed information is sent to the /api/profile/customerDetailsAddress endpoint
  with valid authorization credentials, the data in the response body, including
  (id, customer_id, name, email, phone, address, city, state, country, postal_code,
  is_shipping_default, is_billing_default, created_at, updated_at), should be verified.

    * The api user constructs the base url with the "customer" token.
    * The api user sets "api/profile/customerDetailsAddress" path parameters
    * The API user sends a GET request and records the response from the api "customerDetailAddress" endpoint.
    * The api user verifies the content of the data <id>, <customer_id>, "<name>", "<email>","<phone>", "<address>","<city>","<state>","<country>","<postal_code>",<is_shipping_default>,<is_billing_default>,"<created_at>","<updated_at>"  in the response body.

    Examples:
      |id|customer_id|name|email|phone|address|city|state|country|postal_code|is_shipping_default|is_billing_default|created_at|updated_at|
      |166|217       |Dilan|dilan@gmail.com|547895562|Tokat merkez|Almus|Tokat|Turkey|38000|0    |0  |2024-04-10T19:34:14.000000Z|2024-04-10T19:34:14.000000Z|




  Scenario Outline:When a GET request containing the incorrect (non-existent in the system)
  address ID data is sent to the /api/profile/customerDetailsAddress endpoint with valid
  authorization credentials,the status code returned should be 404, and the
  message in the response body should be verified to be "address not found".

    * The api user constructs the base url with the "customer" token.
    * The api user sets "api/profile/customerDetailsAddress" path parameters
    * The api user prepares a GET request containing <id> for which details are to be accessed, to send to the api endpoint.
    * The API user sends a GET request and records the response from the api "api/profile/customerDetailsAddress" endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "address not found"

    Examples:
      |id|
      |987|



  Scenario:When a GET request containing the address ID data to be accessed
  is sent to the /api/profile/customerDetailsAddress endpoint with invalid
  authorization credentials, the status code returned should be 401, and the message
  in the response body should be verified to be "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile/customerDetailsAddress" path parameters
    * The API user sends a GET request and records the response from the api "customerDetailAddress" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."
