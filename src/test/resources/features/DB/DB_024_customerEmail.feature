Feature: SELECT QUERY EXECUTE
  Background: Database connection
    Given Database connection is established.

  Scenario: In the orders table, list the data that does not contain "customer"
  in customer_email data and whose sub_total is less than 2000 in reverse order of "order_number".

    Given Query24 is prepared and executed.
    When ResultSet24 results are processed
    Then The database connection is closed.