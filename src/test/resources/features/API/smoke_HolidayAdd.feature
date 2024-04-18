Feature: Adding and Verifying holidays via API

  @asli
  Scenario Outline: Successfully add a holiday with valid authorization and data
    Given The api user constructs the base url with the "admin" token.
    And   The api user sets "api/holidayAdd" path parameters
    When  The api user prepares a POST request containing the holiday data "<year>", "<name>", "<date>"
    And   The api user send POST request to the  endpoint.
    Then  The api user verifies that the status code is 201
    And   The api user verifies that the message information in the response body is "holiday added successfully"
    And   The api user sets "api/holidayDetails" path parameters
    When  The api user prepares a GET request containing the refund reason <id> for which details are to be accessed, to send to the api holidayDetails endpoint.
    And   The API user sends a GET request and records the response from the api "api/holidayDetails" endpoint.
    Then  The api user verifies that the message information in the response body is "success"
    Examples:
      | id |

    Examples:
      | year | name      | date       |
      | 2025 | ed  | 29.12.2025 |