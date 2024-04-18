@coupon
Feature: Testing the Coupon List API

  Scenario: Retrieve coupon list with valid authorization credentials
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponList" path parameters
    When  The API user sends a GET request and records the response from the api "api/coupon/couponList" endpoint.
    Then  The api user verifies that the status code is 200
    And   The api user verifies that the message information in the response body is "success"


  Scenario: Validate information for coupon with specific ID in the response body
    Given The api user constructs the base url with the "admin" token.
    And  The api user sets "api/coupon/couponList" path parameters
    When The api user sends a GET request containing the <id> in the body and saves the response
    Then The api user validates the following information for the coupon with ID '<couponID>' in the response body:
      | title       | <expectedTitle>     |
      | coupon_code | <expectedCouponCode>|
      | start_date  | <expectedStartDate> |
      | end_date    | <expectedEndDate>   |
