Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.
  @IP
  Scenario: Enter 10 data into the device_tokens table at the same time and verify that it is added to the table.

    * Query20 is prepared and executed.
    * ResultSet020 results are processed.
    * The database connection is closed.
