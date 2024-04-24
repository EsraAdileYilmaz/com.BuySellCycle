@esraSmoke4
Feature:UPDATE QUERY EXECUTE

 Background: Database connection
  * Database connection is established.

 Scenario:Add a data containing (id,name,email,query_type,message) information and update the message information.
     Given Query04 is prepared and executed.
     When UpdateQuery04 is prepared and executed.
     Then The database connection is closed.


