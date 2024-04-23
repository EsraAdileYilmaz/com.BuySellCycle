@addcitySmoke
Feature: Add a data in cities table
  Background: Database connection
    * Database connection is established.

  Scenario: Add a data containing the values (id,name,state_id,status,created_at) to the cities table.
  verify that the data has been added from the cities table.
    Given I have the data ready for a new city and executed.
    Then I add this data to the cities table and verify.
    And The database connection is closed.