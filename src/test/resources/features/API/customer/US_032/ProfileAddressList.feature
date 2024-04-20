@address
Feature: As a user, I want to view the addresses in my profile via the API connection.
  Scenario: GET query containing valid authorization credentials and requested address ID to access detailed information
    Given The api user constructs the base url with the "aycacustomer" token.
    And The api user sets "api/profile/address-list" path parameters
    When The API user sends a GET request 28 and records the response from the api "address-list" endpoint.
    Then The api user verifies that the status code is 200
    And The api user verifies that the message information in the response body is "success"


  Scenario Outline: GET request body containing valid authorization credentials and the desired address id to access
  detailed information  the data returned in the response body should be validated.
    Given The api user constructs the base url with the "aycacustomer" token.
    And The api user sets "api/profile/address-list" path parameters
    When The API user sends a GET request <id> and records the response from the api "address-list" endpoint.
    Then The api user validates the <id>,<customer_id>,"<name>","<email>","<phone>","<address>","<city>","<state>","<country>","<postal_code>", <is_shipping_default>,<is_billing_default>,"<created_at>","<updated_at>" of the response body

    Examples:
      | id | customer_id | name | email                          | phone      | address         | city  | state | country | postal_code | is_shipping_default | is_billing_default | created_at                  | updated_at                  |
      | 28 | 118         | AYÇA | customer.ayca@buysellcycle.com | 5345343434 | karsıyaka izmir | 40698 | 3704  | 223     | 34500       | 0                   | 0                  | 2024-03-23T08:18:01.000000Z | 2024-03-27T11:21:10.000000Z |


  Scenario: GET query containing valid authorization credentials and requested address ID to access detailed information
    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/profile/address-list" path parameters
    When The API user sends a GET request 28 and records the response from the api "address-list" endpoint.
    Then The api user verifies that the status code is 401
    And The api user verifies that the message information in the response body is "Unauthenticated."


