@gulcan
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario: Enter 1 data in the digital_gift_cards table and delete the added data
  by entering the id number in the digital_gift_cards table.
    * Query22 is prepared and executed.
    * ResultSet22 results are processed.
    * The database connection is closed.