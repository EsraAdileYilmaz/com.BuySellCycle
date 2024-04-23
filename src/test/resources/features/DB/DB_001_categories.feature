@esraSmoke1
Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.

  Scenario:Verify the name value of a categorie with 'slug = fashion' in the Categories table.

    * Query01 is prepared and executed.
    * ResultSet01 results are processed.
    * The database connection is closed.