@administrator
Feature: Access FAQ Details via API

  Scenario Outline: Accessing FAQ details with valid ID
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/faqsDetails" path parameters
    * The API user sends a GET request and records the response from the api "FaqDetails" endpoint.

    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"
    * The api user validates the "<id>", "<user_id>", "<title>","<description>","<status>", "<created_at>", "<updated_at>"  of the response body with index <dataIndex>

    Examples:
      |dataIndex| id| user_id |  title | description| status   | created_at| updated_at|
      |0         | 2| null |  How do I know when my order is here?  | A representative will call you as soon as they are at your house to let you know about your delivery.| 1   | 2024-01-22T23:18:58.000000Z| 2024-01-22T23:18:58.000000Z|




  Scenario Outline: Accessing FAQ details with invalid ID
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/faqsDetails" path parameters
    * The API user sends a GET request and records the response from the api "FaqDetails" endpoint.

    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"
    * The api user validates the "<id>", "<user_id>", "<title>","<description>","<status>", "<created_at>", "<updated_at>"  of the response body with index <dataIndex>

    Examples:
      |dataIndex| id| user_id |  title | description | status   | created_at| updated_at|
      |0         | 3| null |  How do I know when my order is here?  |A representative will call you as soon as they are at your house to let you know about your delivery.| 1   | 2024-01-22T23:18:58.000000Z| 2024-01-22T23:18:58.000000Z|