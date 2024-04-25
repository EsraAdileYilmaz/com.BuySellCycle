@esraSmoke1
Feature: SELECT QUERY EXECUTE
  Background: Database connection
    Given Database connection is established.

  Scenario:List the information of the first 3 data in the customer_coupon_stores table from the users table.
    Given Query15 is prepared and executed.
    When ResultSet15 results are processed.
    Then The database connection is closed.