@Gamze
Feature: As an administrator, I want to be able to add a new department record via the API connection

  Scenario:  When a POST body containing valid authorization information and necessary data (name, details, status) is sent to the /api/departmentAdd endpoint, the returned status code should be 201, and the message
  information in the response body should be verified as "department added successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/departmentAdd" path parameters
    * The api user sends a POST request containing "Gamze Kozmetik","Kozmetik2",1 in the body and saves the response
    * The api user verifies that the status code is 201
    * The api user verifies that the message information in the response body is "department added successfully"
    * The api user sets "api/departmentDetails" path parameters
    * The api user prepares a GET request containing the department id(added_item_id)
    * The api user verifies that the status code is 200

  Scenario:  When a POST body containing invalid authorization information and necessary data (name, details, status) is sent to the /api/departmentAdd endpoint, the returned status code should be 401,
  and the message information in the response body should be verified as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/departmentAdd" path parameters
    * The api user sends a POST request containing these "Gamze Kozmetik","Kozmetik2",1 in the body and saves the response
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."