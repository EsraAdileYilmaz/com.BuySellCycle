@deleteCity
Feature: Add a data in cities table
  Background: Database connection
    * Database connection is established.

  Scenario: A Delete the data containing the values (id=?,name=?) in the cities table. Verify that it has been deleted.
    Given I have a city with id and name in the cities table
    When I delete the city with id and name
    Then The city with id and name should no longer exist in the table
    And The database connection is closed.