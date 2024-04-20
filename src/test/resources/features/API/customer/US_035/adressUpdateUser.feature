@adressUpdateUser
Feature: Updating customer address information

  Scenario: Updating address information with valid credentials

    Given The api user constructs the base url with the "aslicustomer" token.
    And The api user sets "api/profile/customerAddressUpdate/25" path parameters
    When The API user sends a PATCH request to the endpoint with the following body:
      """
      {
        "name": "<newName>",
        "email": "<newEmail>",
        "address": "<newAddress>",
        "phone": "<newPhone>",
        "city": "<newCity>",
        "state": "<newState>",
        "country": "<newCountry>",
        "postal_code": "<newPostalCode>",
        "address_type": "<newAddressType>"
      }
      """
    Then The api user verifies that the status code is 202
    And The api user verifies that the message information in the response body is "address updated successfully"
