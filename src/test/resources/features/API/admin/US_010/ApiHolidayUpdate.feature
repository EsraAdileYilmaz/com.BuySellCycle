Feature: As an administrator, I want to be able to update holiday information for the specified id number via API connection

  Scenario Outline: Creating new data for the query below, the holiday id to be updated, and the updated holiday data
  (year, name, date) is sent to the /api/holidayUpdate/{id} endpoint

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayAdd" path parameters
    * The api user prepares a POST request containing the holiday data "<year>", "<name>", "<date>"
    * The api user send POST request to the  endpoint.

    Examples:
      | year | name      | date       |
      | 2025 | Kerst     | 09.12.2025 |

  Scenario: When a PATCH request containing valid authorization credentials, the holiday id to be updated, and the
  updated holiday data (year, name, date) is sent to the /api/holidayUpdate/{id} endpoint, the expected status code 
  returned should be 202, and the message in the response body should confirm: "holiday updated successfully".
    
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayUpdate/{id}" path parameters
    * The api user sends the POST request and saves the response returned from the api addressAdd endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "holiday updated successfully"