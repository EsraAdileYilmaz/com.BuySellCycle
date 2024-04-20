Feature:As an administrator, I want to be able to access all customer addresses registered in the system via the API connection.

 Scenario: When a GET request body containing valid authorization credentials is sent to the /api/profile/allAddressList endpoint,
          the returned status code should be validated as 200, and the message information in the response body should confirm as "success".

   * The api user constructs the base url with the "admin" token.
   * The api user sets "api/profile/allAddressList" path parameters
   * The API user sends a GET request and records the response from the api "allAdressList" endpoint.
   * The api user verifies that the status code is 200
   * The api user verifies that the message information in the response body is "success"


  Scenario Outline:  When a GET request body containing valid authorization credentials is sent to the /api/profile/allCountries endpoint,
  the returned response body should be validated to confirm that the country with an ID of 223 has the following attributes: "code": "TR" and "name": "Turkey".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/allAddressList" path parameters
    * The API user sends a GET request and records the response from the api "allAdressList" endpoint.
    * The api user verifies the content of the data <id>, <customer_id>, "<name>" ,"<email>" , "<phone>" ,"<address>" ,"<city>" ,"<state>" ,"<country>" ,"<postal_code>" in the response body.

    Examples:
      | id | customer_id | name    | email             | phone            | address | city  | state | country | postal_code |
      | 1  | 5           | ra_name | ra_mail@gmail.com | 0123456789123456 | DE      | 18744 | 1357  | 82      | 45857       |

  @esra
  Scenario: When a GET request body containing invalid authorization credentials is sent to the /api/profile/allAddressList endpoint,
            the returned status code should be validated as 401, and the message information in the response body should confirm as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile/allAddressList" path parameters
    * The API user sends a GET request and records the response from the api "allAdressList" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."
