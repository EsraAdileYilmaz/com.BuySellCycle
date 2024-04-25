Feature: list the unique 'notes' in the attendances table, separated by days

  Background: Database connection
    * Database connection is established.

  Scenario:list the unique 'notes' in the attendances table, separated by days

    * Prepare a query that adds datas to the attendances table..
    * ResultSet12 results are processed.
    * The database connection is closed.
