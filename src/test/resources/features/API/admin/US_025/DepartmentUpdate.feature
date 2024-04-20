Feature: As an administrator, I want to be able to update department information
  with the specified ID number via the API connection.

  Scenario Outline: When a PATCH request body containing valid authorization information,
  the department ID to be updated, and necessary data (name, details, status)
  is sent to the /api/departmentUpdate/{id} endpoint,
  the returned status code should be 202,
  and the message information in the response body should be verified as "department updated successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/departmentUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<name>", "<details>", "<status>" data to send to the api departmentUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api 'departmentUpdate' endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "department updated successfully"
    * The api user verifies that the updated <id> information in the response body matches the id path parameter specified in the endpoint.

    Examples:

      | id | name     | details                | status |
      | 75 | Logistic | Logistic International | 3      |


  Scenario Outline: When a PATCH request body containing valid authorization information
  and an incorrect (non-existent in the system) department ID,
  along with necessary data (name, details, status),
  is sent to the /api/departmentUpdate/{id} endpoint,
  the returned status code should be 404,
  and the message information in the response body should be verified as "department not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/departmentUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<name>", "<details>", "<status>" data to send to the api departmentUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api 'departmentUpdate' endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "department not found"

    Examples:

      | id  | name     | details                | status |
      | 770 | Logistic | Logistic International | 3      |


  Scenario Outline: When a PATCH request body containing invalid authorization information,
  along with the department ID to be updated and necessary data (name, details, status),
  is sent to the /api/departmentUpdate/{id} endpoint,
  the returned status code should be 401,
  and the message information in the response body should be verified as "Unauthenticated."

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/refundReasonUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<name>", "<details>", "<status>" data to send to the api departmentUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api 'departmentUpdate' endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:

      | id | name     | details                | status |
      | 75 | Logistic | Logistic International | 3      |


  Scenario Outline: The updated_Id information in the response body from the /api/departmentUpdate/{id} endpoint
  should be verified to be identical to the id path parameter specified in the /api/departmentUpdate/{id} endpoint.

  * The api user constructs the base url with the "admin" token.
  * The api user sets "api/departmentUpdate/<id>" path parameters
  * The api user prepares a PATCH request containing the "<name>", "<details>", "<status>" data to send to the api departmentUpdate endpoint.
  * The api user sends the PATCH request and saves the response returned from the api "department update" endpoint.
  * The API user validates the <id> content of the data in the response body returned from the response.

  Examples:

    | id | name     | details                | status |
    | 75 | Logistic | Logistic International | 3      |


  @gt
  Scenario Outline: "The update of the desired department record via the API should be confirmed from the API itself.
  (The update of the record can be verified by sending a GET request to the /api/departmentDetails endpoint
  with the updated_Id returned in the response body.)"

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/departmentDetails" path parameters
    * The api user sends a GET request containing the <id> in the body and saves the response
    * The api user verifies that the message information in the response body is "success"

    Examples:
      | id |
      | 75 |


