Feature: As an administrator, I should be able to access detailed information about the holiday with the specified id number via API connection.


Scenario: When a GET request containing valid authorization credentials and the holiday id data for the desired detailed
information is sent to the /api/holidayDetails endpoint, the expected status code returned should be 200, and the message
in the response body should confirm: "success".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayDetails" path parameters
    * The API user sends a GET request 91 and records the response from the api "holidayDetails" endpoint.
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"

    Scenario: When a GET request containing valid authorization credentials and the holiday id data for the desired
    detailed information is sent to the /api/holidayDetails endpoint, the data returned in the response body
    (id, year, name, type, date, created_at, updated_at) should be validated.

      * The api user constructs the base url with the "admin" token.
      * The api user sets "api/holidayDetails" path parameters
      * The API user sends a GET request 2 and records the response from the api "holidayDetails" endpoint.
      * The API user sends a GET request, the returned response verifies the 2, "2025", "Spring", 0, "", "2024-03-12T15:32:02.000000Z", "2024-04-19T13:31:47.000000Z" data information.


    Scenario: When a GET request containing valid authorization credentials and an incorrect (non-existent in the system)
    holiday id is sent to the /api/holidayDetails endpoint, the expected status code returned should be 404, and
    the message in the response body should confirm: "holiday not found".

      * The api user constructs the base url with the "admin" token.
      * The api user sets "api/holidayDetails" path parameters
      * The API user sends a GET request 5 and records the response from the api "holidayDetails" endpoint.
      * The api user verifies that the status code is 404
      * The api user verifies that the message information in the response body is "holiday not found"

  @IP
    Scenario: When a GET request containing invalid authorization credentials and the holiday id data for the desired
  detailed information is sent to the /api/holidayDetails endpoint, the expected status code returned should be 401,
  and the message in the response body should confirm: "Unauthenticated.".


    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/holidayDetails" path parameters
    * The API user sends a GET request and records the response from the api "holidayDetails" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."



