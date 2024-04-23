Feature:As a user, I want to add a new address record to my profile via API connection.
  Scenario Outline: When a valid POST body containing authorization credentials
  and necessary data(name, email, address, phone, city, state, country, postal_code, address_type)
  is sent to the /api/profile/address-store endpoint, the returned status code should be validated as 201,
  and the message information in the response body should confirm as "address added successfully".

    * The api user constructs the base url with the "customer" token.
    * The api user sets "api/profile/address-store" path parameters
    * The api user prepares a POST request containing the "<name>", "<email>","<address>", "<phone>", "<city>", "<state>", "<country>", "<postal_code>", "<address_type>" information to send to the api addressAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api addressAdd endpoint.
    * The api user verifies that the status code is 201
    * The api user verifies that the message information in the response body is "address added successfully"

    Examples:
      |name |email          |address     |phone    |city |state|country|postal_code|address_type|
      |Dilan|dilan@gmail.com|Tokat merkez|547895562|Almus|Tokat|Turkey |38000      |11          |


  Scenario Outline:When a valid authorization information and an invalid email
  (not containing '@') are sent in the POST body (name, email, address, phone, city, state,
  country, postal_code, address_type) to the /api/profile/address-store endpoint, the expected
  status code to return is 422, and the message in the response body should confirm that
  "The email must be a valid email address.".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/address-store" path parameters
    * The api user prepares a POST request containing the "<name>", "<email>","<address>", "<phone>", "<city>", "<state>", "<country>", "<postal_code>", "<address_type>" information to send to the api addressAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api addressAdd endpoint.
    * The api user verifies that the status code is 422
    * The api user verifies that the message information in the response body is "The email must be a valid email address."

    Examples:

      |name |email          |address     |phone    |city |state|country|postal_code|address_type|
      |Dilan|dilangmail.com|Tokat merkez|547895562|Almus|Tokat|Turkey |38000      |11          |

  Scenario:When an invalid POST body containing necessary data
  (name, email, address, phone, city, state, country, postal_code, address_type)
  and invalid authorization credentials are sent to the /api/profile/address-store endpoint,
  the returned status code should be validated as 401, and the message information in the
  response body should confirm as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile/customerDetailsAddress" path parameters
    * The API user sends a GET request and records the response from the api "customerDetailAddress" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."