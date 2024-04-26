Feature: CALCULATE QUERY

  Background: Database connection
    Given Database connection is established.

  Scenario: In the email_template_types table, query the number of
  types by grouping the data whose module value is not null

    When Query23 is prepared to calculate for module value is not null and execute
    Then Process result and verify the result
    And The database connection is closed.
