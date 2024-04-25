Feature:Add and deleting a data in contacts table

  Background: Database connection
    * Database connection is established.
  @q5
  Scenario:Add a data containing (id,name,email,query_type,message) information and update the message information.
    Given Query_05 is prepared and executed.
    When I delete the added contact with email from the table
    Then I verify that the contact data with email is no longer exist in the table
    And The database connection is closed.

