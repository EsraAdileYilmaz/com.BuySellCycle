Feature: Enter 5 data in bulk to the bank_accounts table and verify that it is added.

  Background: Database connection
    * Database connection is established.

  Scenario: Enter 5 data in bulk to the bank_accounts table and verify that it is added.

    * Prepare a query that adds datas to the bank_accounts table in bulk.
    * Enter the data in bulk. Check that it is added to the table.
    * The database connection is closed.
