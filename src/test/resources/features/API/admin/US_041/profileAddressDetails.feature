
Feature: As an administrator, I want to be able to access the address information of a
  customer with a specified ID number via the API connection.

  Scenario: GET query containing valid authorization credentials and requested address ID to access detailed information
    Given The api user constructs the base url with the "admin" token.
    And The api user sets "api/profile/addressDetails" path parameters
    When The API user sends a GET request 1 and records the response from the api "addressDetails" endpoint.
    Then The api user verifies that the status code is 200
    And The api user verifies that the message information in the response body is "success"


  Scenario Outline: GET request body containing valid authorization credentials and the desired address id to access
  detailed information  the data returned in the response body should be validated.
    Given The api user constructs the base url with the "admin" token.
    And The api user sets "api/profile/addressDetails" path parameters
    When The API user sends a GET request <id> and records the response from the api "addressDetails" endpoint.
    Then The api user validates the <id>,<customer_id>,"<name>","<email>","<phone>","<address>","<city>","<state>","<country>","<postal_code>", <is_shipping_default>,<is_billing_default>,"<created_at>","<updated_at>" of the response body

    Examples:
      | id | customer_id | name    | email             | phone            | address | city  | state | country | postal_code | is_shipping_default | is_billing_default | created_at                  | updated_at                  |
      | 1  | 5           | ra_name | ra_mail@gmail.com | 0123456789123456 | DE      | 18744 | 1357  | 82      | 45857       | 0                   | 0                  | 2024-02-11T07:49:48.000000Z | 2024-03-27T11:21:10.000000Z |

  Scenario: GET query containing valid authorization credentials and requested address incorrect ID to access detailed information,
    the returned status code should be validated as 404.
    Given The api user constructs the base url with the "admin" token.
    And The api user sets "api/profile/addressDetails" path parameters
    When The API user sends a GET request 1345 and records the response from the api "addressDetails" endpoint.
    Then The api user verifies that the status code is 404
    And The api user verifies that the message information in the response body is "address not found"

  @Details
  Scenario: GET query containing invalid authorization credentials and requested address correct ID to access detailed information,
  the returned status code should be validated as 401.
    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/profile/addressDetails" path parameters
    When The API user sends a GET request 1 and records the response from the api "addressDetails" endpoint.
    Then The api user verifies that the status code is 401
    And The api user verifies that the message information in the response body is "Unauthenticated."


