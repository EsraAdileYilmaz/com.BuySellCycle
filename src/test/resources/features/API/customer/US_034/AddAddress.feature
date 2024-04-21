Feature:As a user, I want to add a new address record to my profile via API connection.
  Scenario Outline: When a valid POST body containing authorization credentials
  and necessary data(name, email, address, phone, city, state, country, postal_code, address_type)
  is sent to the /api/profile/address-store endpoint, the returned status code should be validated as 201,
  and the message information in the response body should confirm as "address added successfully".

    * The api user constructs the base url with the "customer" token.
    * The api user sets "name, email, address, phone, city, state, country, postal_code, address_typ" path parameters
    * The api user prepares a GET request containing <id> for which details are to be accessed, to send to the api endpoint.
    * The API user sends a GET request and records the response from the api "api/profile/address-store" endpoint.
    * The api user verifies that the status code is 201
    * The api user verifies that the message information in the response body is "address added successfully"

    Examples:
    |name| |email| |address| |phone| |city| |state| |country| |postal_code| |address_typ|
    |
