Feature: As an administrator, I should be able to access holiday data for staff members via API connection.

Scenario: When a GET request is sent to the /api/holidayList endpoint with valid authorization credentials,
the expected status code returned should be 200, and the message in the response body should confirm: "success".

* The api user constructs the base url with the "admin" token.
* The api user sets "api/holidayList" path parameters
* The API user sends a GET request and records the response from the api "holidayList" endpoint.
* The api user verifies that the status code is 200
* The api user verifies that the message information in the response body is "success"

  Scenario Outline: /api/holidayList endpoint'ine gecerli authorization bilgileri ile bir
  GET request gönderildiginde id(x) olaninin response body'de donen bilgileri (year, name) dogrulanmali.

    * The api user constructs the base url with the "admin" token.

    * The api user sets "api/holidayList " path parameters

    * The API user sends a GET request and records the response from the api "holidayList" endpoint.

    * The api user validates the "<year>" and "<name>" of the response body with index <dataIndex>.


    Examples:
      | dataIndex | year |              name                         |
      | 0         |2023  | 23 Nisan Ulusal Egemenlik ve Çocuk Bayramı|
      | 1         |2023  | 23 Nisan Ulusal Egemenlik ve Çocuk Bayramı|

  Scenario: When a GET request with invalid authorization information is sent to the /api/holidayList endpoint, it
  should be verified that the returned status code is 401, and the message in the response body is "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.

    * The api user sets "api/holidayList" path parameters

    * The API user records the response from the api holidayList endpoint, confirming that the status code is '401' and the reason phrase is Unauthorized.


