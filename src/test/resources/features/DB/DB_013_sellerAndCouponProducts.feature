@coupon
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario: List and validate the first 3 products that are not associated
  with any coupon in the seller_products table.

    * Query13 is prepared and executed.
    * ResultSet13 results are processed.
    * The database connection is closed.

