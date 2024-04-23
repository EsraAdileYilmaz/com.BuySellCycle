Feature: Bir yönetici olarak API baglantisi uzerinden belirtilen id numarasına sahip coupon bilgilerini silebilmek istiyorum.

  Scenario Outline: When a DELETE body containing valid authorization information and the coupon ID to be deleted is sent to the
  /api/coupon/couponDelete endpoint, the returned status code should be 202, and the message information in the response
  body should be verified as "coupon deleted successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponAdd" path parameters
    * The api user prepares a POST request containing the "Team127_Reyhan","1234567",2,"2025-11-17","2025-11-17",20,0,2,25,0,1 information to send to the api couponAdd endpoint.
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponDelete" path parameters
    * The api user sends the DELETE request and saves the response with <id> from the api "couponDelete" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "coupon deleted successfully"
    * The api user verifies that the Deleted id information in the response body is the same as the <id> information in the request body.

    Examples:
    |id |
    |119|



  Scenario:When a DELETE body containing valid authorization information and an incorrect (non-existent in the system)
  coupon ID is sent to the /api/coupon/couponDelete endpoint, the returned status code should be 404, and the message
  information in the response body should be verified as "coupon not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponAdd" path parameters
    * The api user prepares a POST request containing the "Team127_Reyhan","1234567",2,"2025-11-17","2025-11-17",20,0,2,25,0,1 information to send to the api couponAdd endpoint.
    * The api user sets "api/coupon/couponDelete" path parameters
    * The api user sends the DELETE request with incorrect ID 7654 and saves the response returned from the api "couponDelete" endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "coupon not found"



  Scenario Outline:When a DELETE body containing invalid authorization information and the coupon ID to be deleted is sent to
  the /api/coupon/couponDelete endpoint, the returned status code should be 401, and the message information in the
  response body should be verified as "Unauthenticated.".


    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponAdd" path parameters
    * The api user prepares a POST request containing the "Team127_Reyhan","1234567",2,"2025-11-17","2025-11-17",20,0,2,25,0,1 information to send to the api couponAdd endpoint.
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponDelete" path parameters
    * The api user sends the DELETE request and saves the response with <id> from the api "couponDelete" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "Unauthenticated."

      Examples:
      |id |
      |11119|



Scenario Outline: The deletion of the coupon record via the API should be verified from the API itself.
(The deletion of the record can be confirmed by sending a GET request to the /api/coupon/couponDetails
endpoint with the Deleted_Id returned in the response body.)

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayList" path parameters
    * The api user prepares a GET request containing the refund reason <id> for which details are to be accessed, to send to the api holidayList endpoint.
    * The API user sends a GET request and records the response from the api "holidayList" endpoint.
    * The api user verifies that the message information in the response body is "success"

    Examples:
      | id |
      | 77 |







