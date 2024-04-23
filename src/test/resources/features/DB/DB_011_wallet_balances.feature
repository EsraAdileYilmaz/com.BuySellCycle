
Feature: Calculate sum of amount values for referrals within a specific ID range
  Background: Database connection
    * Database connection is established.

  Scenario: Calculate the sum of the amount values of the data with type='Referral' and 'id' between 10 and 20 in the wallet_balances table
    * Query011 is prepared and executed.
    * ResultSet011 results are processed.
    * The database connection is closed.
