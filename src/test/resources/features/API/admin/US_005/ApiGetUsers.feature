Feature: As an administrator, I should be able to access all registered user information via API connection.

  Scenario Outline: When a GET request is sent to the /api/get-users endpoint with valid authorization credentials,
  the expected status code returned should be 200, and the message in the response body should confirm: "success".
  The information returned in the response body for the user with id(x) should be validated, including fields such as
  first_name, username, email, name.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/get-users" path parameters
    * The API user sends a GET request and records the response from the api "customerGet-User" endpoint.
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"
    * The api user validates the "<first_name>", "<username>", "<email>","<name>"  of the response body with index <dataIndex>.

    Examples:
    | dataIndex| first_name |  username | email                 | name   |
    | 0        | Super      |  0181     | info@buysellcycle.com | Super  |
    | 2        | Seller     | 123456987 | seller@gmail.com      | Seller |



  Scenario: When a GET request is sent to the /api/get-users endpoint with invalid authorization credentials,
  the expected status code returned should be 401, and the message in the response body should confirm: "Unauthenticated."..

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "customerGet-User" path parameters
    * The API user sends a GET request and records the response from the api "customerGet-User" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."
