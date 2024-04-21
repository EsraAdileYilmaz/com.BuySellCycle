Feature: Delete FAQ Record via API

  Scenario Outline: Deleting FAQ record with valid ID
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/faqsDelete" path parameters
    * The api user prepares a DELETE request containing the  <id> to be deleted to send to the api to the api end point
    * The api user sends the DELETE request and saves the response returned from to the api to the api end point
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "faqs deleted successfully"

    Examples:
    |id|
    | 2|

    Scenario Outline: When a DELETE request containing valid authorization credentials and
    an incorrect (non-existent in the system) FAQ's id is sent to the /api/faqsDelete endpoint,
    the expected status code returned should be 404, and the message in the response body should confirm: "faqs not found".
      * The api user constructs the base url with the "admin" token.
      * The api user sets "api/faqsDelete" path parameters
      * The api user prepares a DELETE request containing the  <id> to be deleted to send to the api to the api end point
      * The api user sends the DELETE request and saves the response returned from to the api to the api end point
      * The api user verifies that the status code is 404
      * The api user verifies that the message information in the response body is "faqs not found"

      Examples:
        |id|
        | 1|

      Scenario Outline: When a DELETE request containing invalid authorization credentials
      and the FAQ's id to be deleted is sent to the /api/faqsDelete endpoint, the expected
      status code returned should be 401, and the message in the response body should confirm: "Unauthenticated.".
        * The api user constructs the base url with the "invalid" token.
        * The api user sets "api/faqsDelete" path parameters
        * The api user prepares a DELETE request containing the  <id> to be deleted to send to the api to the api end point
        * The api user sends the DELETE request and saves the response returned from to the api to the api end point
        * The api user verifies that the status code is 401
        * The api user verifies that the message information in the response body is "Unauthenticated."

        Examples:
          |id|
          | 1|