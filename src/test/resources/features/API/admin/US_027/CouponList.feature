Feature: Testing the Coupon List API

  Scenario: Retrieve coupon list with valid authorization credentials
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponList" path parameters
    When  The API user sends a GET request and records the response from the api "api/coupon/couponList" endpoint.
    Then  The api user verifies that the status code is 200
    And   The api user verifies that the message information in the response body is "success"

  @aslicouponlist
  Scenario Outline: Validate information for coupon with specific ID in the response body
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponList" path parameters
    When  The api user sends a GET request containing the <id> in the body and saves the response
    Then  The api user verifies that the content of the data <id>, "<title>" , "<coupon_code>" , "<start_date>" ,"<end_date>" in the response body.

  Examples:
     | id |title            |  coupon_code | start_date   | end_date     |
     | 10 |Bedava alisveris |  beles127   |  2024-03-25   |  2024-04-25  |


  Scenario: Retrieve coupon list with invalid authorization credentials
    Given The api user constructs the base url with the "invalid" token.
    And   The api user sets "api/coupon/couponList" path parameters
    When  The API user sends a GET request and records the response from the api "api/coupon/couponList" endpoint.
    Then  The api user verifies that the status code is 401
    And   The api user verifies that the message information in the response body is "Unauthenticated."