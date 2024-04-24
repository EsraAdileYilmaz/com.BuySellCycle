Feature: Customer_coupon_stores
  Background: Database connection
    * Database connection is established.

  @IP
    Scenario: List the information of the first 3 data in the customer_coupon_stores table from the users table.

      * Query15 is prepared and executed.
      * ResultSet15 results are processed.
      * The database connection is closed.