
Feature: As a user, I should be able to access detailed information about the user who logged into the system via API connection

Scenario Outline: When a GET request is sent to the /api/customerGetUser endpoint with valid authorization credentials,
the expected status code returned should be 200, and the message in the response body should confirm: "success".

* The api user constructs the base url with the "customer" token.
* The api user sets "api/customerGetUser" path parameters
* The API user sends a GET request and records the response from the api "customerGetUser" endpoint.
* The api user verifies that the status code is 200
* The api user verifies that the message information in the response body is "success"
* The api users validates to  the response body match the "<first_name>", "<last_name>", "<email>" information
  Examples:
    | first_name     | last_name   | email  |
    |                |             |        |

  Scenario: When a GET request is sent to the /api/customerGetUser endpoint with invalid authorization credentials,
  the expected status code returned should be 401, and the message in the response body should confirm: "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/customerGetUser" path parameters
    * The API user sends a GET request and records the response from the api "customerGetUser" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."
