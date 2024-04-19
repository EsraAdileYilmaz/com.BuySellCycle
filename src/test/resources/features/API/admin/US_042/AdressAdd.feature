Feature: As an administrator, I want to be able to add a new address record for a customer specified by customer_id via the API connection.


  Scenario Outline: When a POST body containing valid authorization credentials and necessary
  data (customer_id, name, email, address, phone, city, state, country,
  postal_code, address_type) is sent to the /api/profile/addressAdd endpoint,
  the returned status code should be validated as 201, and the message
  information in the response body should confirm as "address added successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/addressAdd " path parameters
    * The api user prepares a POST request containing the "<customer_id>", "<name>", "<email>","<address>", "<phone>", "<city>", "<state>", "<country>", "<postal_code>", "<address_type>" information to send to the api addressAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api addressAdd endpoint.
    * The api user verifies that the status code is 201
    * The api user verifies that the message information in the response body is "address added successfully"

  Examples:
|customer_id |name |email           |address|phone      |city |state|country|postal_code|address_type|
|     26     |senih |senih@gmail.com|  aut  |5548755560 |5909 | 42  |  1    | 38000     |      11    |

  Scenario Outline: The creation of the new address record via the API
  using the customer ID should be verified through the API itself.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/addressDetails " path parameters
    * The api user prepares a GET request containing the addressAdd <id> for which details are to be accessed, to send to the api addressDetails endpoint.
    * The api user sends a GET request and saves the response returned from the api addressDetails endpoint.
    * The api user verifies that the message information in the response body is "success"

    Examples:
      | id  |
      | 212 |

  Scenario Outline: When a POST body containing valid authorization credentials and
  an invalid email (not containing '@') is sent to the /api/profile/addressAdd endpoint,
  along with fields: customer_id, name, email, address, phone, city, state, country,
  postal_code, address_type, the expected status code returned should be 422,
  and the message in the response body should confirm as "The email must be a valid email address.".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/addressAdd " path parameters
    * The api user prepares a POST request containing the "<customer_id>", "<name>", "<email>","<address>", "<phone>", "<city>", "<state>", "<country>", "<postal_code>", "<address_type>" information to send to the api addressAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api addressAdd endpoint.
    * The api user verifies that the status code is 422
    * The api user verifies that the message information in the response body is "The email must be a valid email address."

    Examples:
      |customer_id |name |email           |address|phone      |city |state|country|postal_code|address_type|
      |     26     |melih|melihgmail.com|  aut  |5548755560 |5909 | 42  |  1    | 38000     |      11    |




  Scenario Outline: When a POST body containing invalid authorization credentials and necessary
  data (customer_id, name, email, address, phone, city, state, country, postal_code, address_type)
  is sent to the /api/profile/addressAdd endpoint, the returned status code should be
  validated as 401, and the message information in the response body should confirm as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile/addressAdd " path parameters
    * The api user prepares a POST request containing the "<customer_id>", "<name>", "<email>","<address>", "<phone>", "<city>", "<state>", "<country>", "<postal_code>", "<address_type>" information to send to the api addressAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api addressAdd endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      |customer_id |name |email           |address|phone      |city |state|country|postal_code|address_type|
      |     26     |melih|melih@gmail.com |  aut  |5548755560 |5909 | 42  |  1    | 38000     |      11    |

