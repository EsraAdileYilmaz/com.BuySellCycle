@query03
Feature: Add a data in cities table
  Background: Database connection
    * Database connection is established.

  Scenario: A Delete the data containing the values (id=?,name=?) in the cities table. Verify that it has been deleted.
    * Query03 is prepared and executed.
    * Delete the data on Query03 and verify deletion
    * The database connection is closed.