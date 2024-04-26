@smoke
Feature: SELECT QUERY EXECUTE
  Background: Database connection
    Given Database connection is established.

  Scenario: Check whether the phone data contains 5 according to the first 3 address information in the customer_addresses table
    Given Query07 is prepared to select phone numbers from the first three entries of customer_addresses.
    Then The phone data should be checked for the presence of digit '5'.
    And The database connection is closed.

