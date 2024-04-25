Feature: Finding Product Count by Coupons
  As a user, I want to see how many products are associated with a specific coupon.

  Background: Database connection
    * Database connection is established.

  Scenario: Finding product count for a specific coupon
    Given a coupon with ID 123
    And the products associated with the coupon are:
      | Product ID | Product Name |
      | 1          | Product 1    |
      | 2          | Product 2    |
      | 3          | Product 3    |
    When the user queries the product count for the coupon
    Then I should see that there are 3 products for coupon ID 123