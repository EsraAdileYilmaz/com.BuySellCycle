Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario: Verify whether there is data in the refund_reasons table with a 'reason' value of Null.

    * Query14 is prepared and executed.
    * ResultSet14 results are processed.
    * The database connection is closed.

