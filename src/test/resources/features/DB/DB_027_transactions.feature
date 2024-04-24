
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario:List data in the transactions table that has a unique description and has both 'Stripe' and 'Cash On Delivery' payment methods
       Given Query27 is prepared and executed.
       When ResultSet27 results are processed.
       Then The database connection is closed.