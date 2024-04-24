@esraSmoke1
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario:Verify the name value of a categorie with 'slug = fashion' in the Categories table.
    Given Query01 is prepared and executed.
    When ResultSet01 results are processed.
    Then The database connection is closed.