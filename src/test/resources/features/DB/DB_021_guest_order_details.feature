@gulcanSmoke
Feature: A description

  Background: Database connection
    * Database connection is established.

  Scenario: Calculate the number of orders placed according to the order_id in the guest_order_details table.
  Update the shipping_name information of the data with order number (order_id=?).
    * Query21 is prepared and executed.
    * ResultSet21 results are processed.
    * The database connection is closed.