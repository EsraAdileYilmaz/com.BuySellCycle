Feature: As an administrator, I want to be able to update coupon information with
  the specified ID number via the API connection.
Scenario Outline: When a PATCH request body containing valid authorization information,
the coupon ID to be updated, and necessary data
(title, coupon_code, coupon_type, start_date, end_date,
discount, discount_type, minimum_shopping, maximum_discount, is_expire, is_multiple_buy)
is sent to the /api/coupon/couponUpdate/{id} endpoint, the returned status code
should be 202, and the message information in the response body should be verified as
"coupon updated successfully".