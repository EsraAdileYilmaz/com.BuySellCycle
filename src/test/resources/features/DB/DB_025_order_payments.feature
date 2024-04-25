Feature: Calculate the total cost of products with is_buy_now=1 before 2024-03-30 in the carts table

  Background: Database connection
    * Database connection is established.

  Scenario:Calculate the total cost of products with is_buy_now=1 before 2024-03-30 in the carts table

    * Prepare a query that adds datas to the carts table..
    * ResultSet25 results are processed.
    * The database connection is closed.
