@negatifUpdate
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario: Verify that the opening_balance value of the data
  with bank_name=? in the bank_accounts table is not updated with a negative value.

    * Query19 is prepared and executed.
    * ResultSet19 results are processed.
    * The database connection is closed.
