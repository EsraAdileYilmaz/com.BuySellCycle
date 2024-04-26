
Feature: As an administrator, I want to be able to delete holiday information for the specified id number via API connection.

  Scenario: Successful deletion of the body containing VALID authorization information and department ID

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayAdd" path parameters
    * The api user prepares a POST request containing the holiday id to be deleted to send to the api holidayAdd endpoint.
    * The api user sets "api/holidayDelete" path parameters
    * The api user sends the DELETE request and saves the response returned from the api "holidayDelete" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "holiday deleted successfully"
    * The api user verifies that the Deleted id information in the response body is the same as the id information in the request body.

  Scenario: Deleting body containing VALID authorization information and an INCORRECT (non-existent in the system) department ID
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayAdd" path parameters
    * The api user prepares a POST request containing the holiday id to be deleted to send to the api holidayAdd endpoint.
    * The api user sets "api/holidayDelete" path parameters
    * The api user sends the DELETE request with incorrect ID 572894875 and saves the response returned from the api departmentDelete endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "holiday not found"

  Scenario: DELETE body containing INVALID authorization information and the department ID to be deleted

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/holidayDelete" path parameters
    * The api user sends the DELETE request with incorrect ID 572894875 and saves the response returned from the api departmentDelete endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."
  @HolidayDelete
  Scenario Outline: The Deleted_Id information in the response body from the /api/departmentDelete endpoint should be verified
  to be identical to the id information in the DELETE request body sent to the /api/departmentDelete endpoint.
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayAdd" path parameters
    * The api user prepares a POST request containing "<year>","<name>","<date>" the holiday id to be deleted to send to the api holidayAdd endpoint.
    * The api user sets "api/holidayDelete" path parameters
    * The api user sends the DELETE request and saves the response returned from the api "holidayDelete" endpoint.
    * The api user sets "api/holidayDetails" path parameters
    * The api user prepares a GET request containing the department id to be deleted to send to the api departmentDetails endpoint.
    * The api user verifies that the message information in the response body is "holiday not found"
    Examples:
      | year | name        | date       |
      | 2026 | FUN HOLDIAY | 2024-04-29 |
