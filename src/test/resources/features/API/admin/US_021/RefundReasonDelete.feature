Feature: As an administrator, I want to be able to delete Refund&Reason information with the specified ID number
        via the API connection.

  Scenario: When a DELETE body containing valid authorization information and the refund&reason ID to be deleted is sent
            to the /api/refundReasonDelete endpoint, the returned status code should be 202, and the message information
            in the response body should be verified as "refundReason deleted successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/refundReasonAdd" path parameters
    * The api user prepares a POST request containing the department id to be deleted to send to the api refund Reason delete endpoint.
    * The api user sets "api/refundReasonDelete" path parameters
    * The api user sends the DELETE request and saves the response returned from the api refundReasonDelete endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "refundReason deleted successfully"
    * The api user verifies that the Deleted id information in the response body is the same as the id information in the request body.



  @API
  Scenario: When a DELETE request body containing valid authorization information and an incorrect (non-existent in the system) refund&reason ID is sent to the /api/refundReasonDelete endpoint,
           the returned status code should be 404, and the message information in the response body should be verified as "refundReason not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/refundReasonAdd" path parameters
    * The api user prepares a POST request containing the department id to be deleted to send to the api refund Reason delete endpoint.
    * The api user sets "api/refundReasonDelete" path parameters
    * The api user sends the DELETE request with incorrect department ID and saves the response returned from the api refund Reason delete endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "refundReason not found"



  Scenario: When a DELETE body containing invalid authorization information and the refund&reason ID to be deleted is sent to the /api/refundReasonDelete endpoint,
           the returned status code should be 401, and the message information in the response body should be verified as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/refundReasonAdd" path parameters
    * The api user prepares a POST request containing the department id to be deleted to send to the api refund Reason delete endpoint.
    * The api user sets "api/refundReasonDelete" path parameters
    * The api user sends the DELETE request with incorrect department ID and saves the response returned from the api refund Reason delete endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

