Feature: As an administrator, I want to be able to add a new address record for a customer specified by customer_id via the API connection.

@nk
  Scenario Outline: When a POST body containing valid authorization credentials and necessary
  data (customer_id, name, email, address, phone, city, state, country,
  postal_code, address_type) is sent to the /api/profile/addressAdd endpoint,
  the returned status code should be validated as 201, and the message
  information in the response body should confirm as "address added successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/addressAdd " path parameters
    * The api user prepares a POST request containing the "<customer_id>", "<name>" "<email>","<address>", "<phone>", "<city>", "<state>", "<country>", "<postal_code>", "<address_type>" information to send to the api addressAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api addressAdd endpoint.
    * The api user verifies that the status code is 201
    * The api user verifies that the message information in the response body is "address added successfully"

  Examples:
|customer_id |name |email           |address|phone      |city |state|country|postal_code|address_type|
|     26     |senih |senih@gmail.com|  aut  |5548755560 |5909 | 42  |  1    | 38000     |      11    |