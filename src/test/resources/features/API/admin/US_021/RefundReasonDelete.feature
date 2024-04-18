Feature: As an administrator, I want to be able to delete Refund&Reason information with the specified ID number
        via the API connection.

  Scenario Outline: When a DELETE body containing valid authorization information and the refund&reason ID to be deleted is sent
                   to the /api/refundReasonDelete endpoint, the returned status code should be 202, and the message information
                   in the response body should be verified as "refundReason deleted successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/refundReasonDelete" path parameters
    * The api user prepares a DELETE request containing the refund reason <id> to be deleted to send to the api refundReasonDelete endpoint.
    * The api user sends the DELETE request and saves the response returned from the api refundReasonDelete endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "refundReason deleted successfully"
    * The api user verifies that the Deleted id information in the response body is the same as the id information in the request body.

    Examples:
      | id |
      | 43  |

  @API
  Scenario Outline: When a DELETE request body containing valid authorization information and an incorrect (non-existent in the system) refund&reason ID is sent to the /api/refundReasonDelete endpoint,
                   the returned status code should be 404, and the message information in the response body should be verified as "refundReason not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/refundReasonDelete" path parameters
    * The api user prepares a DELETE request containing the refund reason <id> to be deleted to send to the api refundReasonDelete endpoint.
    #* The API user records the response from the api refundReasonDelete endpoint, confirming that the status code is '404' and the reason phrase is Not Found.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "refundReason not found"


    Examples:
      | id |
      | 26 |