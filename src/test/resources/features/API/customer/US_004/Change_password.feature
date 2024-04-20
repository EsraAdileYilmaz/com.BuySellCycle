@PASSWORD
Feature: As a user, I should be able to edit my user information via API connection.

  Scenario Outline: Successfully changing the password with correct data (old_password, password, password_confirmation)

    * The api user constructs the base url with the "aycacustomer" token.
    * The api user sets "api/change-password" path parameters
    * The api user prepares a POST request containing the "<old_password>", "<password>", "<password_confirmation>" information to send to the api change-password endpoint.
    * The api user sends the POST request and saves the response returned from the api "change-password" endpoint.
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "password change successfully"
    Examples:
      | old_password | password  | password_confirmation |
      #| 123123123    | 12345678  | 12345678              |
      | 12345678     | 123123123 | 123123123             |


  Scenario Outline: Attempt to change password with incorrect old password
    * The api user constructs the base url with the "aycacustomer" token.
    * The api user sets "api/change-password" path parameters
    * The api user prepares a POST request containing the "<old_password>", "<password>", "<password_confirmation>" information to send to the api change-password endpoint.
    * The api user sends the POST request and saves the response returned from the api "change-password" endpoint.
    * The api user verifies that the status code is 409
    * The api user verifies that the message information in the response body is "Invalid Credintials."
    Examples:
      | old_password | password | password_confirmation |
      | 123123123a    | 123123123 | 123123123              |


  Scenario Outline: POST request with valid authorization credentials and mismatched password and password_confirmation information
    * The api user constructs the base url with the "aycacustomer" token.
    * The api user sets "api/change-password" path parameters
    * The api user prepares a POST request containing the "<old_password>", "<password>", "<password_confirmation>" information to send to the api change-password endpoint.
    * The api user sends the POST request and saves the response returned from the api "change-password" endpoint.
    * The api user verifies that the status code is 422
    * The api user verifies that the message information in the response body is "The password confirmation does not match."
    Examples:
      | old_password | password  | password_confirmation |
      | 12345678     | 123123123 | 12312312a             |

  Scenario Outline: POST request with invalid authorization credentials and correct data (old_password, password, password_confirmation)

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/change-password" path parameters
    * The api user prepares a POST request containing the "<old_password>", "<password>", "<password_confirmation>" information to send to the api change-password endpoint.
    * The api user sends the POST request and saves the response returned from the api "change-password" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."
    Examples:
      | old_password | password | password_confirmation |
      | 123123123    | 12345678 | 12345678              |







