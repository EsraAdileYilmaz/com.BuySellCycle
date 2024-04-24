Feature: As an administrator, I want to be able to access detailed information about the
  department with the specified ID number via the API connection.

  Scenario : When a GET request body containing invalid authorization information and the department ID to access detailed information is sent to the /api/departmentDetails endpoint, the returned status code should be 401,
  and the message information should be verified as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/departmentDetails" path parameters
    * The api user sends a GET request containing the 16000 in the body and saves the response
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated"
