Feature: As a user, I want to be able to delete the information of the address
  in my profile with the specified ID number through the API connection.

  Scenario Outline: When a DELETE body containing valid authorization credentials
  and the ID of the address to be deleted is sent to the /api/profile/customerDeleteAddress endpoint,
  the returned status code should be validated as 202,
  and the message information in the response body should confirm as "address deleted successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/address-store" path parameters
    * The api user prepares a POST request containing the <added_item_id> to be deleted to send to the "api profile customerDeleteAddress" endpoint.
    * The api user sets "api/profile/customerDeleteAddress" path parameters
    * The api user sends the DELETE request and saves the response returned from the "api profile customerDeleteAddress" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "address deleted successfully"
    * The api user verifies that the Deleted id information in the response body is the same as the id information in the request body.

    Examples:
      | added_item_id |
      | 293           |


  Scenario: When a DELETE body containing valid authorization credentials
  and an incorrect (non-existent in the system) address ID is sent to the /api/profile/customerDeleteAddress endpoint,
  the returned status code should be validated as 404,
  and the message information in the response body should confirm as "address not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/customerDeleteAddress" path parameters
    * The api user sends the DELETE request with incorrect ID 572894875 and saves the response returned from the api departmentDelete endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "address not found"


  Scenario Outline: When a DELETE body containing invalid authorization credentials
  and the ID of the address to be deleted is sent to the /api/profile/customerDeleteAddress endpoint,
  the returned status code should be validated as 401,
  and the message information in the response body should confirm as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile/customerDeleteAddress" path parameters
    * The api user prepares a DELETE request containing the <id> to be deleted to send to the "api profile customerDeleteAddress" endpoint.
    * The api user sends the DELETE request and saves the response returned from the "api profile customerDeleteAddress" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | id  |
      | 293 |


  Scenario Outline: The Deleted_Id information in the response body returned from the /api/profile/customerDeleteAddress endpoint
  should be verified to match the id information in the DELETE request body sent
  to the same /api/profile/customerDeleteAddress endpoint.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/address-store" path parameters
    * The api user prepares a POST request containing "<Deleted_Id>" the holiday id to be deleted to send to the api holidayAdd endpoint.
    * The api user sets "api/profile/customerDeleteAddress" path parameters
    * The api user sends the DELETE request and saves the response returned from the api "profile customerDeleteAddress" endpoint.
    * The api user sets "api/profile/customerDetailsAddress" path parameters
    * The api user prepares a GET request containing the department id to be deleted to send to the api departmentDetails endpoint.
    * The api user verifies that the message information in the response body is "address not found"
    Examples:
      | Deleted_Id |
      | 293        |


  Scenario Outline: "The deletion of the address record via the API should be confirmed through the API itself.
  (The Deleted_Id returned in the response body can be used to send a GET request
  to the /api/profile/customerDetailsAddress endpoint to confirm that the record has been deleted.)"

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/customerDetailsAddress" path parameters
    * The api user prepares a GET request containing the <id> for which details are to be accessed, to send to the api profile customerDetailsAddress endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "address not found"

    Examples:
      | id  |
      | 293 |


