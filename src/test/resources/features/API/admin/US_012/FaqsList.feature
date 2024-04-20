Feature: As an administrator, I want to be able to access the FAQ's list via API connection.

  Scenario: When a GET request containing valid authorization credentials is sent to the /api/faqsList endpoint,
  the expected status code returned should be 200, and the message in the response body should confirm: "success".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/faqsList" path parameters
    * The API user sends a GET request and records the response from the api faqsList endpoint.
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"

  Scenario Outline: When a GET request containing valid authorization credentials is sent to the /api/faqsList endpoint,
  the information returned in the response body for the FAQ with id(x) should be validated, including the (title) information.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/faqsList" path parameters
    * The API user sends a GET request and records the response from the api faqsList endpoint.
    * The api user prepares a GET request containing the <id> for which details are to be accessed, to send to the api faqslist endpoint.
    * The api user sends a GET request and saves the response returned from the api faqslist endpoint.
    * The api user verifies that the message information in the response body is "success"

    Examples:
      | id |
      | 23 |


  Scenario: When a GET request containing invalid authorization credentials is sent to the /api/faqsList endpoint,
  the expected status code returned should be 401, and the message in the response body should confirm: "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/faqsList" path parameters
    * The API user sends a GET request and records the response from the api faqsList endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."


