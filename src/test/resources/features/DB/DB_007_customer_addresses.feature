@query07
Feature: SELECT QUERY EXECUTE
  Background: Database connection
    * Database connection is established.

  Scenario: Check whether the phone data contains 5 according to the first 3 address information in the customer_addresses table
    * Query07 is prepared and executed.
    * ResultSet07 results are processed.
    * The database connection is closed.

