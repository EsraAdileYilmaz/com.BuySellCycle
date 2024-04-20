Feature: As an administrator, I want to be able to access the city information registered in the system
  with the specified state_id via the API connection.

  Scenario Outline: When a GET request body containing valid authorization credentials
  and the desired state_id to access city information is sent to the /api/profile/stateCities endpoint,
  the returned status code should be validated as 200,
  and the message information in the response body should confirm as "success".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/stateCities" path parameters
    * The api user sends a GET request with <state_id> in the body and saves the response
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"


  Examples:
    | state_id |
    | 3669     |


  Scenario Outline: When a GET request body containing valid authorization credentials
  and a state_id of 3669 is sent to the /api/profile/stateCities endpoint,
  the returned response body should be validated to confirm that the city
  with an ID number of 40325 has the "name" attribute as "Kizilcahamam".


    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/stateCities" path parameters
    * The api user sends a GET request with <state_id> in the body and saves the response
    * The api user verifies the content of the data <id>,"<name>" in the response body.

    Examples:
      | state_id | id    | name         |
      | 3669     | 40325 | Kizilcahamam |


  Scenario Outline: When a GET request body containing valid authorization credentials
  and an incorrect (not found in the system) state_id is sent to the /api/profile/stateCities endpoint,
  the returned status code should be validated as 404,
  and the message information in the response body should confirm as "address not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/stateCities" path parameters
    * The api user sends a GET request with <state_id> in the body and saves the response
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "address not found"

    Examples:
      | state_id |
      | 9009     |

  @gt1
  Scenario Outline: When a GET request body containing invalid authorization credentials
  and the desired state_id to access city information is sent to the /api/profile/stateCities endpoint,
  the returned status code should be validated as 401,
  and the message information in the response body should confirm as "Unauthenticated."

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile/stateCities" path parameters
    * The api user sends a GET request with <state_id> in the body and saves the response
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | state_id |
      | 3669     |


