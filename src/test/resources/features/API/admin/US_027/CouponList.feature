
Feature: Testing the Coupon List API

  Scenario: Retrieve coupon list with valid authorization credentials
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponList" path parameters
    When  The API user sends a GET request and records the response from the api "api/coupon/couponList" endpoint.
    Then  The api user verifies that the status code is 200
    And   The api user verifies that the message information in the response body is "success"

  @coupon
  Scenario Outline: Validate information for coupon with specific ID in the response body
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponList" path parameters
    When  The api user sends a GET request to retrieve coupon details with ID 10 and saves the response
    Then  The api user validates the following information for the coupon with ID '10' in the response body:
      | Field            | Expected Value     |
      | Title            | 'Bedava alisveris'  |
      | Coupon Code      | 'beles127'         |
      | Start Date       | '2024-03-25'       |
      | End Date         | '2024-04-25'       |

