Feature: As a user, I want to be able to delete the information of the address
  in my profile with the specified ID number through the API connection.

  Scenario: When a DELETE body containing valid authorization credentials
  and the ID of the address to be deleted is sent to the /api/profile/customerDeleteAddress endpoint,
  the returned status code should be validated as 202,
  and the message information in the response body should confirm as "address deleted successfully".


  Scenario: When a DELETE body containing valid authorization credentials
  and an incorrect (non-existent in the system) address ID is sent to the /api/profile/customerDeleteAddress endpoint,
  the returned status code should be validated as 404,
  and the message information in the response body should confirm as "address not found".


  Scenario: When a DELETE body containing invalid authorization credentials
  and the ID of the address to be deleted is sent to the /api/profile/customerDeleteAddress endpoint,
  the returned status code should be validated as 401,
  and the message information in the response body should confirm as "Unauthenticated.".


  Scenario: The Deleted_Id information in the response body returned from the /api/profile/customerDeleteAddress endpoint
  should be verified to match the id information in the DELETE request body sent
  to the same /api/profile/customerDeleteAddress endpoint.


  Scenario: "The deletion of the address record via the API should be confirmed through the API itself.
  (The Deleted_Id returned in the response body can be used to send a GET request
  to the /api/profile/customerDetailsAddress endpoint to confirm that the record has been deleted.)"



