Feature: As an administrator, I should be able to access all registered user information via API connection.

  Scenario: When a GET request is sent to the /api/get-users endpoint with valid authorization credentials,
  the expected status code returned should be 200, and the message in the response body should confirm: "success".
  The information returned in the response body for the user with id(x) should be validated, including fields such as
  first_name, username, email, name.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/get-users" path parameters
    * The API user sends a GET request and records the response from the api "customerGet-User" endpoint.
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"