
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


  Scenario: Updating address information with valid credentials

    Given The api user constructs the base url with the "aslicustomer" token.
    And The api user sets "api/profile/customerAddressUpdate/280" path parameters
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
    Then The api user verifies that the status code is 404
    And The api user verifies that the message information in the response body is "address not found"


  Scenario: Handling PATCH request with invalid email
    Given The api user constructs the base url with the "aslicustomer" token.
    And The api user sets "api/profile/customerAddressUpdate/25" path parameters
    When The API user sends a PATCH request with invalid email to the endpoint with the following body:
      """
      {
        "name": "John Doe",
        "email": "invalid_email",
        "address": "Goethe Str",
        "phone": "123-456-7890",
        "city": "Berlin",
        "state": "Berlin",
        "country": "DE",
        "postal_code": "10001",
        "address_type": "Home"
      }
      """
    Then The api user verifies that the status code is 422
    And The api user verifies that the message information in the response body is "The email must be a valid email address."


  Scenario: Sending PATCH request with invalid authorization credentials
    Given The api user constructs the base url with the "invalid" token.
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
    Then The api user verifies that the status code is 401
    But The api user verifies that the message information in the response body is "Unauthenticated."



    Scenario Outline: Verify updated ID matches the ID in the path parameter
      Given The api user constructs the base url with the "aslicustomer" token.
      And The api user sets "api/profile/customerAddressUpdate/<id>" path parameters
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
      Then The updated_id information in the response body should match the id specified in the path parameter "/api/profile/customerAddressUpdate/<id>"
      Examples:
        | id |
        | 25 |

  @asli_adressUser
  Scenario: Verify updated ID matches the ID in the path parameter
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
    And The api user record the updated_Id from the response body
    And The api user sets "api/profile/customerDetailsAddres" path parameters
    And The api user prepares a GET request containing updated_Id for which details are to be accessed, to send to the  endpoint.
    Then The api verifies that Get Response Body matches with the updated Adress
