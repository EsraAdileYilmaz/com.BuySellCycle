@Gamze
Feature: As an administrator, I want to be able to add a new department record via the API connection

  Scenario:  When a POST body containing valid authorization information and necessary data (name, details, status) is sent to the /api/departmentAdd endpoint, the returned status code should be 201, and the message
  information in the response body should be verified as "department added successfully".
