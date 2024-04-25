Feature: SELECT QUERY EXECUTE

  Background: Database connection
    Given Database connection is established.

  Scenario: Enter 1 data in the digital_gift_cards table and delete the added data
  by entering the id number in the digital_gift_cards table.
    Given Query22 is prepared and executed.
    When ResultSet22 results are processed.
    Then The database connection is closed.