Feature: SELECT QUERY EXECUTE

  Background: Database connection
    Given Database connection is established.

  Scenario: Calculate the number of orders placed according to the order_id in the
  guest_order_details table. Update the shipping_name information of the data with order number (order_id=?).
    Given Query21 is prepared and executed.
    When ResultSet21 results are processed.
    Then The database connection is closed.