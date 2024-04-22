Feature: Adding and Verifying holidays via API


  Scenario Outline: Successfully add a holiday with valid authorization and data
  Scenario: Successfully add a holiday with valid authorization and data
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/holidayAdd" path parameters
    When  The api user prepares a POST request containing the holiday data "<year>", "<name>", "<date>"
    And   The api user send POST request to the  endpoint.
    When  The api user sends a POST request with the following JSON:
    """
    {
    "year": "2024",
    "name": "Hawai",
    "date": "28.09.2024"
    }
    """
    Then  The api user verifies that the status code is 201
    And   The api user verifies that the message information in the response body is "holiday added successfully"

    Examples:
      | year | name      | date       |
      | 2025 | Sos    | 29.12.2025 |



  Scenario: The creation of the new Refund&Reason record via the API should be verified.
  @asliHoliday
  Scenario Outline: The creation of the new Holiday record via the API should be verified.
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/holidayDetails" path parameters
    When  The api user prepares a GET request containing the refund reason <id> for which details are to be accessed, to send to the api holidayDetails endpoint.
    And   The api user sends a GET request containing the <id> in the body and saves the response
    When  The api user sends a GET request containing <id> to send to endpoint
    Then  The api user verifies that the message information in the response body is "success"

    Examples:
      | id |
      | 104 |


  Scenario Outline: Handle duplicate holiday request
    @@ -34,8 +37,8 @@ Feature: Adding and Verifying holidays via API
    And   The api user verifies that the message information in the response body is "dublicate holiday request"

    Examples:
      | year | name   | date       |
      | 2025 | Sos    | 29.12.2025 |
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


