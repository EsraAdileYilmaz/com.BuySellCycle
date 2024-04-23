@smoke
Feature: Add a data in cities table
  Background: Database connection
    * Database connection is established.

  Scenario: Add a data containing the values (id,name,state_id,status,created_at) to the cities table.
  verify that the data has been added from the cities table.
    * Query02 is prepared and executed.
    * ResultSet02 results are processed.
    * The database connection is closed.