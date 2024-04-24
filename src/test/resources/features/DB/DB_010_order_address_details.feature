Feature: CALCULATE QUERY

  Background: Database connection
    Given Database connection is established.

  Scenario: Verify the number of users whose 'shipping_address' and 'billing_address'
  are not the same in the order_address_details table.

    When Query10 is prepared and executed.
    Then ResultSet10 results are processed.
    And The database connection is closed.