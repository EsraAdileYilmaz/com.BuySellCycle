@Ilhan
Feature: As an administrator, I should be able to access detailed information of the user with the specified id number via API connection.

  Scenario Outline: When a GET request containing valid authorization credentials and the customer
  id data for the desired detailed information is sent to the /api/get-user endpoint,
  the expected status code returned should be 200, and the message in the response body should confirm: "success".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/get-users" path parameters
    * The API user sends a GET request and records the response from the api "getUser" endpoint.
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"
    * The api user validates the <id>, "<first_name>", "<username>", "<email>","<phone>","<name>"  of the response body with index <dataIndex>.

    Examples:
      | id |  dataIndex | first_name |  username   | email                  | phone       | name   |
      | 4  |   3        | Admin      |  1234567895 | admin@buysellcycle.com | 1234567895  | Admin  |


  Scenario Outline: When a GET request containing valid authorization credentials and the customer id data for the
  desired detailed information is sent to the /api/get-user endpoint, the user information returned in the response body
  (id, first_name, last_name, role_id, email, is_verified, is_active, lang_code, currency_id, currency_code, name) should be validated.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/get-users" path parameters
    * The API user sends a GET request and records the response from the api "getUser" endpoint.
    * The api user validates the <id>, "<first_name>", "<last_name>", <role_id>,"<email>", <is_verified>, <is_active>, "<lang_code>", <currency_id>, "<currency_code>", "<name>"  of the response body


    Examples:
      | id  | first_name  | last_name | role_id | email                          | is_verified | is_active | lang_code | currency_id | currency_code | name                |
      | 217 | aleynadilan | ciftcier  |   4     | dilannciftcier@buysellcycle.com|    0        |   1       |     en    |      2      |     USD       | aleynadilan ciftcier|