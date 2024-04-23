Feature: As an administrator, I want to be able to delete the address information of the customer with the specified ID number via the API connection.

  Scenario: When a DELETE request body containing valid authorization information and the address ID to be deleted is
  sent to the /api/profile/deleteAddress endpoint, the returned status code should be 202, and the message in the response body
  should confirm that it is "address deleted successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/addressAdd" path parameters
    * The api user prepares a POST request containing the 26,"Nazime","nazime@buysellcycle.com","Augsburg","5548755560","1520","422","1","38000","11" information to send the api addressAdd endpoint.
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/deleteAddress" path parameters
    * The api user sends the DELETE request and saves the response returned from the api "deleteAddress" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "address deleted successfully"
    * The api user verifies that the Deleted id information in the response body is the same as the id information in the request body.



  Scenario: When a DELETE request body containing valid authorization information and an incorrect
  (non-existent in the system) address ID is sent to the /api/profile/deleteAddress endpoint, the returned status code should be 404,
  and the message in the response body should confirm that it is "address not found".
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/addressAdd" path parameters
    * The api user prepares a POST request containing the 26,"Nazime","nazime@buysellcycle.com","Augsburg","5548755560","1520","422","1","38000","11" information to send the api addressAdd endpoint.
    * The api user sets "api/profile/deleteAddress" path parameters
    * The api user sends the DELETE request with incorrect ID 600 and saves the response returned from the api departmentDelete endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "address not found"


  Scenario: When a DELETE request body containing invalid authorization information and the address ID to be deleted is sent
  to the /api/profile/deleteAddress endpoint, the returned status code should be 401, and the message in the response body should confirm that it is "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile/deleteAddress" path parameters
    * The api user sends the DELETE request with incorrect ID 600 and saves the response returned from the api departmentDelete endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."



  Scenario: The deletion of the address record should be verified through the API.
  (The deletion of the record can be confirmed by sending a GET request to the
  /api/profile/addressDetails endpoint with the Deleted_Id obtained from the response body.)
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/addressAdd" path parameters
    * The api user prepares a POST request containing the 26,"Nazime","nazime@buysellcycle.com","Augsburg","5548755560","1520","422","1","38000","11" information to send the api addressAdd endpoint.
    * The api user sets "api/profile/deleteAddress" path parameters
    * The api user sends the DELETE request and saves the response returned from the api "deleteAddress" endpoint.
    * The api user sets "api/profile/addressDetails" path parameters
    * The api user prepares a GET request containing the  id to be deleted to send to the api addressDetails endpoint.
    * The api user verifies that the message information in the response body is "address not found"


