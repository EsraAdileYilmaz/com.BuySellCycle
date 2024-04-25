Feature: Finding Product Count by Coupons
  As a user, I want to see how many products are associated with a specific coupon.

  Background: Database connection
    * Database connection is established.
@db6
  Scenario: Finding product count for a specific coupon
    Given I query to group the coupon_products table by coupon_id
    When  I find out how many products for each coupon
    Then  The database connection is closed.
