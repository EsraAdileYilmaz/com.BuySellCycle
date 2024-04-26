
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    Given Database connection is established.

  Scenario: Verify that the opening_balance value of the data
  with bank_name=? in the bank_accounts table is not updated with a negative value.

    When Query19Update is prepared and executed.
    And Query19 is prepared and executed.
    Then I verify that no records have negative opening balance
    And The database connection is closed.
