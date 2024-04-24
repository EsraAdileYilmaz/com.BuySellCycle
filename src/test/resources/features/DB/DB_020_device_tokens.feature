Feature: SELECT QUERY EXECUTE

  Background: Database connection
    Given Database connection is established.

  Scenario: Enter 10 data into the device_tokens table at the same time and verify that it is added to the table.

    Given Query20 is prepared and executed.
    When ResultSet20 results are processed.
    And The database connection is closed.