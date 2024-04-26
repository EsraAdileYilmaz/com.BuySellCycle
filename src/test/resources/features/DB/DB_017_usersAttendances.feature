Feature: CALCULATE QUERY

  Background: Database connection
    Given Database connection is established.

  Scenario: Verify the email address from the information of the data with id=5
  in the user table from the attendances created before 2022 in the attendances table.

    When Query17 is prepared  for users and attendances table and execute
    Then Process result and verify the email address
    And The database connection is closed.