Feature: As an administrator, I want to access the Refund & Reason List through the API connection.

  Scenario: When a GET request with valid authorization information is sent to the /api/refundReasonList endpoint, the
  returned status code should be verified as 200, and the message in the response body should be verified as "success".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/refundReasonList" path parameters
    * The API user sends a GET request and records the response from the api "refundReasonList" endpoint.
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"


  Scenario Outline: When a GET request containing valid authorization information is sent to the /api/refundReasonList endpoint,
  the returned information in the response body for the reason with ID 'x' should be validated.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/refundReasonList" path parameters
    * The API user sends a GET request and records the response from the api "refundReasonList" endpoint.
    * The api user validates the "<reason>" of the response body with index <dataIndex>.

    Examples:
      | dataIndex | reason              |
      | 0         | Product mismatch    |
      | 2         | Order wrong product |


  Scenario: When a GET request with invalid authorization information is sent to the /api/refundReasonList endpoint, it
  should be verified that the returned status code is 401, and the message in the response body is "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/refundReasonList" path parameters
    * The API user records the response from the api refundReasonList endpoint, confirming that the status code is '401' and the reason phrase is Unauthorized.

