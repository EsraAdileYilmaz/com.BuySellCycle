Feature: As an administrator, I want to be able to update the information of the FAQ record with the specified FAQ id via API connection.

   Scenario Outline: When a PATCH request containing valid authorization credentials, the FAQ id to be updated,
                     and the updated information of the FAQ (title) is sent to the /api/faqsUpdate/{id} endpoint,
                     the expected status code returned should be 202, and the message in the response body should confirm: "faqs updated successfully"

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/faqsUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>" ,"<description>" data to send to the api faqs Update endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "faqs update" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "faqs updated successfully"

    Examples:
      | title        | description        | id |
      | API testleri | Eglenceli ve guzel | 46 |


  Scenario Outline: When a PATCH request containing valid authorization credentials and an incorrect (non-existent in the system) FAQ id,
                      along with the updated information of the FAQ (title, description), is sent to the /api/faqsUpdate/{id} endpoint,
                      the expected status code returned should be 404, and the message in the response body should confirm: "faqs not found".
      * The api user constructs the base url with the "admin" token.
      * The api user sets "api/faqsUpdate/<id>" path parameters
      * The api user prepares a PATCH request containing the "<title>" ,"<description>" data to send to the api faqs Update endpoint.
      * The api user sends the PATCH request and saves the response returned from the api "faqs update" endpoint.
      * The api user verifies that the status code is 404
      * The api user verifies that the message information in the response body is "faqs not found"

      Examples:
        | title        | description        | id |
        | API testleri | Eglenceli ve guzel | 99 |


    Scenario Outline:When a PATCH request containing invalid authorization credentials, the FAQ id to be updated,
                     and the updated information of the FAQ (title, description) is sent to the /api/faqsUpdate/{id} endpoint,
                     the expected status code returned should be 401, and the message in the response body should confirm: "Unauthenticated.".

      * The api user constructs the base url with the "invalid" token.
      * The api user sets "api/faqsUpdate/<id>" path parameters
      * The api user prepares a PATCH request containing the "<title>" ,"<description>" data to send to the api faqs Update endpoint.
      * The api user sends the PATCH request and saves the response returned from the api "faqs update" endpoint.
      * The api user verifies that the status code is 401
      * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | title        | description        | id |
      | API testleri | Eglenceli ve guzel | 41  |



    Scenario Outline: The updated_Id information in the response body from the /api/faqsUpdate/{id} endpoint
                      should be verified to be the same as the id path parameter specified in the /api/faqsUpdate/{id} endpoint.

      * The api user constructs the base url with the "admin" token.
      * The api user sets "api/faqsUpdate/<id>" path parameters
      * The api user prepares a PATCH request containing the "<title>" ,"<description>" data to send to the api faqs Update endpoint.
      * The api user sends the PATCH request and saves the response returned from the api "faqs update" endpoint.
      * The API user validates the <id> content of the data in the response body returned from the response.

      Examples:
        | title        | description        | id |
        | API testleri | Eglenceli ve guzel | 54 |



  @esra
    Scenario Outline:The update of the record can be verified by sending a GET request to the /api/faqsDetails endpoint
                     with the updated_Id returned in the response body.

      * The api user constructs the base url with the "admin" token.
      * The api user sets "api/faqsUpdate/<id>" path parameters
      * The api user prepares a PATCH request containing the "<title>" ,"<description>" data to send to the api faqs Update endpoint.
      * The api user sends the PATCH request and saves the response returned from the api "faqs update" endpoint.
      * The api user sets "api/faqsDetails" path parameters
      * The api user prepares a GET request containing the department <id> to verify that the record has been updated to send to the api faqsDetails endpoint.

      Examples:
        | title        | description        | id |
        | API testleri | Eglenceli ve guzel | 54 |