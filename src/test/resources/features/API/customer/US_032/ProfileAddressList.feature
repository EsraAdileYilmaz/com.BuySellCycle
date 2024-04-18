@address
Feature: As a user, I want to view the addresses in my profile via the API connection.
  Scenario: GET request body containing valid authorization information is sent to the
  /api/profile/address-list endpoint, the returned status code should be 200,
  and the message information in the response should be verified as "success".
    * The api user constructs the base url with the "customer" token.
    * The api user sets "api/profile/address-list" path parameters
    * The API user sends a GET request body and records the response from the api address-list endpoint.
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"


 Scenario Outline: When a GET request body containing valid authorization information is sent
 to the /api/profile/address-list endpoint, the response body for the address with ID 'x'
 should be verified to contain the following information: customer_id, name, email, phone, address, city,
 state, country, postal_code, is_shipping_default, is_billing_default, created_at, updated_at.
   * The api user constructs the base url with the "customer" token.
   * The api user sets "api/profile/address-list" path parameters
   * The API user sends a GET request body and records the response from the api address-list endpoint.
   * The api user validates the <customer_id>,"<name>","<email>",<phone>,"<address>","<city>","<state>","<country>","<postal_code>", <is_shipping_default>,<is_billing_default>,"<created_at>","<updated_at>" of the response body with index <dataIndex>.


   Examples:
     | dataIndex | customer_id | name        | email                          | phone       | address | city  | state | country | postal_code | is_shipping_default | is_billing_default | created_at                  | updated_at                  |
     | 0         | 119         | Esra Yilmaz | customer.esra@buysellcycle.com | 12534567825 | farmaci | 47863 | 3956  | 231     | null        | 0                   | 0                  | 2024-03-26T14:21:38.000000Z | 2024-03-26T14:22:51.000000Z |



