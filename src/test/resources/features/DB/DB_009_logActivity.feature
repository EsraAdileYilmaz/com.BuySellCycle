Feature: CALCULATE QUERY

  Background: Database connection
    Given Database connection is established.
  @query
  Scenario: In the log_activity table, calculate and verify the number of
  topics with ip= ? and method='Delete'

    When Query09 is prepared to calculate on log_activity and executed
    Then ResultSet09 results are processed.
    And The database connection is closed.