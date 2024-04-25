Feature: Listing Orders Shipped to Switzerland
  A feature to list orders shipped to Switzerland.

  Scenario: Listing orders shipped to Switzerland
    When the user queries ids with  with shipping_address=Switzerland in the order_address_details table according to the orders table. to list orders shipped to Switzerland according to the orders table.
    And The user list the ids from the resultset
    Then The database connection is closed.
