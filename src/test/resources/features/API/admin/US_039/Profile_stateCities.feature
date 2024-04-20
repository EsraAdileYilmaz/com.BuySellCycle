Feature: As an administrator, I want to be able to access the city information registered in the system
  with the specified state_id via the API connection.

  Scenario: When a GET request body containing valid authorization credentials
  and the desired state_id to access city information is sent to the /api/profile/stateCities endpoint,
  the returned status code should be validated as 200,
  and the message information in the response body should confirm as "success".


  Scenario: When a GET request body containing valid authorization credentials
  and a state_id of 3669 is sent to the /api/profile/stateCities endpoint,
  the returned response body should be validated to confirm that the city
  with an ID number of 40325 has the "name" attribute as "Kizilcahamam".


  Scenario: When a GET request body containing valid authorization credentials
  and an incorrect (not found in the system) state_id is sent to the /api/profile/stateCities endpoint,
  the returned status code should be validated as 404,
  and the message information in the response body should confirm as "address not found".


  Scenario: When a GET request body containing invalid authorization credentials
  and the desired state_id to access city information is sent to the /api/profile/stateCities endpoint,
  the returned status code should be validated as 401,
  and the message information in the response body should confirm as "Unauthenticated."


