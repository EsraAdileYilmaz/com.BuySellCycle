Feature: Testing the Coupon List API

  Scenario: Retrieve coupon list with valid authorization credentials
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponList" path parameters
    When  The API user sends a GET request and records the response from the api "api/coupon/couponList" endpoint.
    Then  The api user verifies that the status code is 200
    And   The api user verifies that the message information in the response body is "success"


  Scenario: Validate information for coupon with ID 'x' in the response body
    Given The api user constructs the base url with the "admin" token.
    When  The api user sets "api/coupon/couponList" path parameters
    Then  The api user verifies that response should contain coupon information for ID 25 with the following fields:
      | Field        | Expected Value         |
      | title        | [expected_title]       |
      | coupon_code  | [expected_coupon_code] |
      | start_date   | [expected_start_date]  |
      | end_date     | [expected_end_date]    |