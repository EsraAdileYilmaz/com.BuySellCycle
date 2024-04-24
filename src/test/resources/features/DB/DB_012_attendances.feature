Feature: list the unique 'notes' in the attendances table, separated by days

  Background: Database connection
    * Database connection is established.

  Scenario:list the unique 'notes' in the attendances table, separated by days

    * Query12 is prepared and executed.
    * ResultSet12 results are processed.
    * The database connection is closed.
