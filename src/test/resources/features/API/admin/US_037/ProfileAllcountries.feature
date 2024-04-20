Feature: As an administrator, I want to be able to access all the country information registered in the system via the API connection.

Scenario: When a GET request body containing valid authorization credentials is sent to the /api/profile/allCountries endpoint,
         the returned status code should be validated as 200, and the message information in the response body should confirm as "success".

  * The api user constructs the base url with the "admin" token.
  * The api user sets "api/profile/allCountries" path parameters
  * The API user sends a GET request and records the response from the api "allCountries" endpoint.
  * The api user verifies that the status code is 200
  * The api user verifies that the message information in the response body is "success"


 Scenario Outline:  When a GET request body containing valid authorization credentials is sent to the /api/profile/allCountries endpoint,
                  the returned response body should be validated to confirm that the country with an ID of 223 has the following attributes: "code": "TR" and "name": "Turkey".

   * The api user constructs the base url with the "admin" token.
   * The api user sets "api/profile/allCountries" path parameters
   * The API user sends a GET request and records the response from the api "allCountries" endpoint.
   * The api user verifies the content of the data <id>, "<code>", "<name>" in the response body.

   Examples:
     | id  | code | name   |  |
     | 223 | TR   | Turkey |  |


 Scenario: When a GET request body containing invalid authorization credentials is sent to the /api/profile/allCountries endpoint,
           the returned status code should be validated as 401, and the message information in the response body should confirm as "Unauthenticated.".

     * The api user constructs the base url with the "invalid" token.
     * The api user sets "api/profile/allCountries" path parameters
     * The API user sends a GET request and records the response from the api "allCountries" endpoint.
     * The api user verifies that the status code is 401
     * The api user verifies that the message information in the response body is "Unauthenticated."

