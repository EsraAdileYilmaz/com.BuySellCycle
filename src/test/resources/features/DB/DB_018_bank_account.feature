Feature: Enter 5 data in bulk to the bank_accounts table and verify that it is added.

  Background: Database connection
    * Database connection is established.

  Scenario:Enter 5 data in bulk to the bank_accounts table and verify that it is added.

    * Query18 is prepared and executed.
    * ResultSet18 results are processed.
    * The database connection is closed.
