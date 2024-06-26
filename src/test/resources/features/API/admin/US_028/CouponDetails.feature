
Feature: Accessing Coupon Details with Valid Authorization

  Scenario: Validate information for coupon with specific ID in the response body
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponDetails" path parameters
    When  The api user sends a GET request containing the 10 in the body and saves the response
    Then  The api user verifies that the status code is 200
    And   The api user verifies that the message information in the response body is "success"




  Scenario: Validating data returned in the response body2
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponDetails" path parameters
    When  The api user sends a GET request containing the 10 in the body and saves the response
    Then The api user verifies that the status code is 200
    And  The api user verifies the response with the following JSON:
"""
  {
  "id": 10,
  "title": "Bedava alisveris",
  "coupon_code": "beles127",
  "coupon_type": 3,
  "start_date": "2024-03-25",
  "end_date": "2024-04-25",
  "discount": null,
  "discount_type": null,
  "minimum_shopping": null,
  "maximum_discount": 50,
  "created_by": 79,
  "updated_by": null,
  "is_expire": 0,
  "is_multiple_buy": 1,
  "multiple_buy_limit": null,
  "created_at": "2024-03-25T10:21:45.000000Z",
  "updated_at": "2024-03-25T10:21:45.000000Z"
  }
  """


  Scenario: Verify response for incorrect coupon ID
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponDetails" path parameters
    When  The api user sends a GET request containing the 2 in the body and saves the response
    Then  The api user verifies that the status code is 404
    And   The api user verifies that the message information in the response body is "coupon not found"


  Scenario: Verify response for invalid authorization and valid coupon ID
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/coupon/couponDetails" path parameters
    When  The api user sends a GET request containing the 10 in the body and saves the response
    Then  The api user verifies that the status code is 401
    And   The api user verifies that the message information in the response body is "Unauthenticated."