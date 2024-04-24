Feature: As an administrator, I should be able to access holiday data for staff members via API connection.

Scenario: When a GET request is sent to the /api/holidayList endpoint with valid authorization credentials,
the expected status code returned should be 200, and the message in the response body should confirm: "success".

* The api user constructs the base url with the "admin" token.
* The api user sets "api/holidayList" path parameters
* The API user sends a GET request and records the response from the api "holidayList" endpoint.
* The api user verifies that the status code is 200
* The api user verifies that the message information in the response body is "success"

  Scenario Outline: When a GET request is sent to the /api/holidayList endpoint with valid authorization credentials,
  the information returned in the response body for the holiday with id(x) should be validated,
  including fields such as year and name.

    * The api user constructs the base url with the "admin" token.

    * The api user sets "api/holidayList " path parameters

    * The API user sends a GET request and records the response from the api "holidayList" endpoint.

    * The api user validates the "<year>" and "<name>" of the response body with index <dataIndex>.


    Examples:
      | dataIndex | year |              name                         |
      | 0         |2025  | Spring                                    |
      | 1         |2023  | 23 Nisan Ulusal Egemenlik ve Çocuk Bayram |

  Scenario: When a GET request with invalid authorization information is sent to the /api/holidayList endpoint, it
  should be verified that the returned status code is 401, and the message in the response body is "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/holidayList" path parameters
    * The API user sends a GET request and records the response from the api "holiday data" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."



