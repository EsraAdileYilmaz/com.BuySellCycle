Feature: Calculate the total cost of products with is_buy_now=1 before 2024-03-30 in the carts table

  Background: Database connection
    * Database connection is established.

  Scenario:Calculate the total cost of products with is_buy_now=1 before 2024-03-30 in the carts table

    * Prepare Query for data and executed.
    * ResultSet30 results are processed.
    * The database connection is closed.