Feature: As an administrator, I want to be able to add a new Refund&Reason record to the system via the API connection.

@gt
  Scenario Outline: When a POST body containing valid authorization information and necessary data (reason) is sent to the
  /api/refundReasonAdd endpoint, the returned status code should be 201, and the message information in the
  response body should be verified as "refundReason added successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/refundReasonAdd" path parameters
    * The api user prepares a POST request containing the "<reason>" information to send to the api refundReasonAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api refundReasonAdd endpoint.
    * The api user verifies that the status code is 201
    * The api user verifies that the message information in the response body is "refundReason added successfully"

    Examples:
      | reason           |
      | product mismatch |


  Scenario Outline: The creation of the new Refund&Reason record via the API should be verified.
  (The creation of the record can be verified by sending a GET request to the /api/refundReasonDetails endpoint
  with the added_item_id returned in the response body.)

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/refundReasonDetails" path parameters
    * The api user prepares a GET request containing the refund reason <id> for which details are to be accessed, to send to the api refundReasonDetails endpoint.
    * The api user sends a GET request and saves the response returned from the api refundReasonDetails endpoint.
    * The api user verifies that the message information in the response body is "success"

    Examples:
      | id |
      | 25 |


  Scenario Outline: When a POST body containing invalid authorization information and necessary data (reason) is sent to the
  /api/refundReasonAdd endpoint, the returned status code should be 401, and the message information in the
  response body should be verified as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/refundReasonAdd" path parameters
    * The api user prepares a POST request containing the "<reason>" information to send to the api refundReasonAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api refundReasonAdd endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | reason           |
      | product mismatch |

