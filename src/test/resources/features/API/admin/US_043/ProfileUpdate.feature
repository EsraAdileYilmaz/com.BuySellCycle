Feature: As an administrator, I want to be able to update the existing address information of a customer with the specified address
  ID number and belonging customer_id via the API connection.

  Scenario Outline:  When a PATCH request body, containing the address ID and the correct customer_id, along with valid authorization information, is sent to the /api/profile/addressUpdate/{id}
  endpoint for update, with fields (customer_id, name, email, address, phone, city, state, country, postal_code, address_type), the returned status code should be 202, and the message in
  the response body should confirm that it is "address updated successfully". And The update of the address record should be confirmed through the API.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile/addressUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<customer_id>","<name>","<email>","<phone>","<address>","<city>","<state>","<country>","<postal_code>","<address_type>" data
    * The api user sends the PATCH request and saves the response
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "address updated successfully"
    * The api user verifies equality the content of the updated Id in the response body and <id>
    * The api user sets "api/profile/addressDetails" path parameters
    * The API user sends a GET request  using updated_Id and verify  that the "<name>" has been updated


    Examples:
      | id  | customer_id | name    | email                           | phone    | address | city     | state | country | postal_code | address_type |
      | 136 | 110         | GamzeCa | customer.gamze@buysellcycle.com | 05555555 | adresss | Istanbul | 123   | 75      | 11          | 12           |

  Scenario Outline:  When a PATCH request body containing valid authorization information and an incorrect (non-existent in the system) address ID is sent to the
  /api/profile/addressUpdate/{id} endpoint, with fields (customer_id, name, email, address, phone, city, state, country, postal_code, address_type), the returned status code should be 404, and the message in the response body should confirm that it is "address not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile//addressUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<customer_id>","<name>","<email>","<phone>","<address>","<city>","<state>","<country>","<postal_code>","<address_type>" data
    * The api user sends the PATCH request and saves the response
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "address not found"

    Examples:
      | id  | customer_id | name    | email                           | phone    | address | city     | state | country | postal_code | address_type |
      | 13600 | 110         | GamzeCa | customer.gamze@buysellcycle.com | 05555555 | adresss | Istanbul | 123   | 75      | 11          | 12           |

  Scenario Outline:  When a PATCH request body containing valid authorization information and an incorrect customer_id is sent to the /api/profile/addressUpdate/{id} endpoint, with fields
  (customer_id, name, email, address, phone, city, state, country, postal_code, address_type), the returned status code should be 404, and the message in the response body should confirm that it is "address not found".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/profile//addressUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<customer_id>","<name>","<email>","<phone>","<address>","<city>","<state>","<country>","<postal_code>","<address_type>" data
    * The api user sends the PATCH request and saves the response
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "address not found"

    Examples:
      | id  | customer_id | name    | email                           | phone    | address | city     | state | country | postal_code | address_type |
      | 136 | 11000        | GamzeCa | customer.gamze@buysellcycle.com | 05555555 | adresss | Istanbul | 123   | 75      | 11          | 12           |

  Scenario Outline:  When a PATCH request body containing invalid authorization information is sent to the /api/profile/addressUpdate/{id} endpoint, with the address ID to be
  updated and the correct customer_id (customer_id, name, email, address, phone, city, state, country, postal_code, address_type), including  the expected status code upon response verification should be 401, and the message in the response body should confirm as '"Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/profile//addressUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<customer_id>","<name>","<email>","<phone>","<address>","<city>","<state>","<country>","<postal_code>","<address_type>" data
    * The api user sends the PATCH request and saves the response
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | id  | customer_id | name    | email                           | phone    | address | city     | state | country | postal_code | address_type |
      | 136 | 11000        | GamzeCa | customer.gamze@buysellcycle.com | 05555555 | adresss | Istanbul | 123   | 75      | 11          | 12           |
