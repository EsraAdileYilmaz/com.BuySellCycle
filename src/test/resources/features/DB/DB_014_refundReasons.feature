Feature: SELECT QUERY EXECUTE

  Background: Database connection
    Given Database connection is established.

  Scenario: Verify whether there is data in the refund_reasons table with a 'reason' value of Null.

    Given Query14 is prepared and executed.
    When ResultSet14 results are processed.
    Then The database connection is closed.