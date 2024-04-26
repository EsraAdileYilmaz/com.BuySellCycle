
Feature: Adding and Verifying holidays via API

@holidayadd09
  Scenario: Successfully add a holiday with valid authorization and data
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/holidayAdd" path parameters
    When  The api user sends a POST request with the following JSON:
    """
    {
    "year": "<year>",
    "name": "Kurban Bayrami",
    "date": "<date>"
    }
    """
    Then  The api user verifies that the status code is 201
    And   The api user verifies that the message information in the response body is "holiday added successfully"


  @asliHoliday
  Scenario Outline: The creation of the new Holiday record via the API should be verified.
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/holidayDetails" path parameters
    When  The api user sends a GET request containing <id> to send to endpoint
    Then  The api user verifies that the message information in the response body is "success"
    Examples:
      | id |
      | 104 |


  Scenario Outline: Handle duplicate holiday request
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/holidayAdd" path parameters
    When  The api user prepares a POST request containing the holiday data "<year>", "<name>", "<date>"
    And   The api user send POST request to the  endpoint.
    Then  The api user verifies that the status code is 400
    And   The api user verifies that the message information in the response body is "dublicate holiday request"

    Examples:
      | year | name      | date       |
      | 2025 | Safari    | 28.09.2024 |


  Scenario Outline: Handle unauthenticated request
    Given The api user constructs the base url with the "invalid" token.
    And   The api user sets "api/holidayAdd" path parameters
    When  The api user prepares a POST request containing the holiday data "<year>", "<name>", "<date>"
    And   The api user send POST request to the  endpoint.
    Then  The api user verifies that the status code is 401
    And   The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | year | name      | date       |
      | 2026 | HydePark | 29.12.2026  |




