
Feature: As an administrator, I want to be able to access the state information registered
  in the system with the specified country_id via the API connection.

  Scenario:   When a GET request body containing valid authorization credentials and the desired country id to access state information is sent to the /api/profile/countryStates endpoint, the returned status code should be validated as 200,
  and the message information in the response body should confirm as "success".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/countryStates" path parameters
    * The api user sends a GET request containing country_id 1 in the body and saves the response
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"

  Scenario: When a GET request body containing valid authorization credentials and a country_id of 223 is sent to the /api/profile/countryStates endpoint, the returned response body should be validated to confirm that
  the state with an ID number of 3669 has the "name" attribute as "Ankara".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/countryStates" path parameters
    * The api user sends a GET request containing country_id 223 in the body and saves the response
    * The api user verifies that ID 3669  has the name attribute as "Ankara".

  Scenario: When a GET request body containing valid authorization credentials and an incorrect (not found in the system) country id is sent to the /api/profile/countryStates endpoint, the returned status code should be validated as 404,
  and the message information in the response body should confirm as "address not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/countryStates" path parameters
    * The api user sends a GET request containing country_id 10000 in the body and saves the response
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "address not found"

  Scenario: When a GET request body containing invalid authorization credentials and the desired country id to access state information is sent to the /api/profile/countryStates endpoint, the returned status code should be validated as 401,
  and the message information in the response body should confirm as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile/countryStates" path parameters
    * The api user sends a GET request containing country_id 1 in the body and saves the response
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."