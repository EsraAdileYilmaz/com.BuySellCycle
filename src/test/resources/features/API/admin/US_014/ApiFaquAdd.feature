Feature: As an administrator, I want to be able to create a new FAQ record via API connection

  Scenario: When a POST request containing valid authorization credentials and necessary information (title, description)
  is sent to the /api/faqsAdd endpoint, the expected status code returned should be 201, and the message in the response
  body should confirm: "Faqs added successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/faqsAdd" path parameters
    * The api user prepares a POST request containing the "Title Example", "Description Example" information to send to the api faqsAdd endpoint.
    * The API user sends a POST request and records the response from the api faqsAdd endpoint.
    * The api user verifies that the status code is 201
    * The api user verifies that the message information in the response body is "Faqs added successfully"



    Scenario: The creation of the new FAQ record via the API should be verified through the API itself.
    (The creation of the record can be verified by sending a GET request to the /api/faqsDetails endpoint with the added_item_id returned in the response body.)

      * The api user constructs the base url with the "admin" token.
      * The api user sets "api/faqsDetails" path parameters
      * The api user sends the GET request and saves the response returned from the api "faqsDetails" endpoint.



      Scenario: When a POST request containing invalid authorization credentials and necessary information (title, description) is sent to the /api/faqsAdd
      endpoint, the expected status code returned should be 401, and the message in the response body should confirm: "Unauthenticated.".

        * The api user constructs the base url with the "invalid" token.
        * The api user sets "api/faqsAdd" path parameters
        * The api user prepares a POST request containing the "Title Example", "Description Example" information to send to the api faqsAdd endpoint.
        * The API user sends a POST request and records the response from the api faqsAdd endpoint.
        * The api user verifies that the status code is 401
        * The api user verifies that the message information in the response body is "Unauthenticated."








