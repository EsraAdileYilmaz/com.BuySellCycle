@esraDB
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario:List data in the transactions table that has a unique description and has both 'Stripe' and 'Cash On Delivery' payment methods

    * Query27 is prepared and executed.
    * ResultSet27 results are processed.
    * The database connection is closed.