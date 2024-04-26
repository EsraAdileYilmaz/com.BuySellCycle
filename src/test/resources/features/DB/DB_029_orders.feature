
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario: Calculate and verify the average grand_total value of paid orders (is_paid =1) in the orders table.

    * Query29 is prepared and executed.
    * ResultSet29 results are processed.
    * The database connection is closed.