Feature: As an administrator, I want to be able to update holiday information for the specified id number via API connection

  Scenario Outline: Creating new data for the query below, the holiday id to be updated, and the updated holiday data
  (year, name, date) is sent to the /api/holidayUpdate/{id} endpoint

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayAdd" path parameters
    * The api user prepares a POST request containing the "<year>", "<name>", "<date>" information to send to api holidayAdd endpoint.
    * The api user send the POST request and saves the response returned from the api "holidayAdd" endpoint.


    Examples:
    | year | name |    date    |
    | 2025 | Kerst | 2023-01-01 |

  Scenario: When a PATCH request containing valid authorization credentials, the holiday id to be updated, and the
  updated holiday data (year, name, date) is sent to the /api/holidayUpdate/{id} endpoint, the expected status code 
  returned should be 202, and the message in the response body should confirm: "holiday updated successfully".
    
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayUpdate/{id}" path parameters
    #* The api user send a PATCH request and records the response from the api "holidayUpdate/{id}" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "holiday updated successfully"