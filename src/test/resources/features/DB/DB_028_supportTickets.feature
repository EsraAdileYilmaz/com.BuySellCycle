Feature: List Unique User IDs with and without '-' in Reference Number

  Background: Database connection
    * Database connection is established.

    Scenario:In the support_tickets table, list the unique user_ids of the data with and without '-' in reference_no.

      Given Query028 is prepared and executed.
      When  ResultSet028 results are processed.
      Then  The database connection is closed.