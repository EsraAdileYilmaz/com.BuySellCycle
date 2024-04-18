
Feature: As an administrator, I want to be able to delete department information with the specified ID number via the API connection.
  @AYCA
  Scenario: When a DELETE body containing valid authorization information and the department ID
  to be deleted is sent to the /api/departmentDelete endpoint, the returned status code should be 202,
  and the message information in the response body should be verified as "department deleted successfully".

   * The api user constructs the base url with the "admin" token.
   * The api user sets "api/departmentDelete" path parameters
   * The api user prepares a DELETE request containing the department id to be deleted to send to the api departmentDelete endpoint.
   * The api user sends the DELETE request and saves the response returned from the api departmentDelete endpoint.
   * The api user verifies that the status code is 202
   * The api user verifies that the message information in the response body is "department deleted successfully"
   * The api user verifies that the Deleted id information in the response body is the same as the id information in the request body.



  Scenario: DELETE body containing valid authorization information and an incorrect (non-existent in the system) department ID
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/departmentDelete" path parameters
    * The api user prepares a DELETE request containing the department id to be deleted to send to the api departmentDelete endpoint.
    * The API user records the response from the api departmentDelete endpoint, confirming that the status code is '404' and the reason phrase is Not Found.

