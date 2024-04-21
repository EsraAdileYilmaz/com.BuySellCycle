Feature: Bir yönetici olarak API baglantisi uzerinden belirtilen id numarasına sahip coupon bilgilerini silebilmek istiyorum.

  Scenario: When a DELETE body containing valid authorization information and the coupon ID to be deleted is sent to the
  /api/coupon/couponDelete endpoint, the returned status code should be 202, and the message information in the response
  body should be verified as "coupon deleted successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponAdd" path parameters
    * The api user prepares a POST request containing the "Team127_Reyhan","1234567",2,"2025-11-17","2025-11-17",20,0,2,25,0,1 information to send to the api couponAdd endpoint.
    * The api user sets "api/coupon/couponDelete" path parameters
    * The api user sends the DELETE request and saves the response returned from the api "couponDelete" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "coupon deleted successfully"
    * The api user verifies that the Deleted id information in the response body is the same as the id information in the request body.




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



  Scenario:When a DELETE body containing invalid authorization information and the coupon ID to be deleted is sent to
  the /api/coupon/couponDelete endpoint, the returned status code should be 401, and the message information in the
  response body should be verified as "Unauthenticated.".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponAdd" path parameters
    * The api user prepares a POST request containing the "Team127_Reyhan","1234567",2,"2025-11-17","2025-11-17",20,0,2,25,0,1 information to send to the api couponAdd endpoint.
    * The api user sets "api/coupon/couponDelete" path parameters
    * The api user sends the DELETE request with incorrect ID 12345 and saves the response returned from the api "couponDelete" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."













