
Feature: As a user, I should be able to access the financial information of the customer via API connection.

Scenario: When a GET request is sent to the /api/profile/get-customer-data endpoint with valid authorization credentials,
         the expected status code returned should be 200, and the message in the response body should confirm: "success".

  * The api user constructs the base url with the "customer" token.
  * The api user sets "api/profile/get-customer-data" path parameters
  * The API user sends a GET request and records the response from the api "finans data" endpoint.
  * The api user verifies that the status code is 200
  * The api user verifies that the message information in the response body is "success"


Scenario Outline: When a GET request is sent to the /api/profile/get-customer-data endpoint with valid authorization credentials,
        the data returned in the response body (wallet_running_balance, wallet_pending_balance, total_coupon, total_wishlist, total_cancel_order) should be validated.

  * The api user constructs the base url with the "customer" token.
  * The api user sets "api/profile/get-customer-data" path parameters
  * The API user sends a GET request and records the response from the api "finans data" endpoint.
  * The api users validates to  the response body match the "<wallet_running_balance>", "<wallet_pending_balance>", "<total_coupon>","<total_wishlist>","<total_cancel_order>" information
    Examples:
      | wallet_running_balance | wallet_pending_balance | total_coupon | total_wishlist | total_cancel_order |  |
      | 0                      | 1002                   | 0            | 0              | 0                  |  |


  Scenario: When a GET request is sent to the /api/profile/get-customer-data endpoint with invalid authorization credentials,
         the expected status code returned should be 401, and the message in the response body should confirm: "Unauthenticated.".

   * The api user constructs the base url with the "invalid" token.
   * The api user sets "api/profile/get-customer-data" path parameters
    * The API user sends a GET request and records the response from the api "finans data" endpoint.
   * The api user verifies that the status code is 401
   * The api user verifies that the message information in the response body is "Unauthenticated."




