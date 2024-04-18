Feature: As an administrator, I want to be able to access detailed information about the
  department with the specified ID number via the API connection.

  Background:
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/departmentDetails" path parameters

  Scenario Outline: /api/departmentDetails endpoint'ine gecerli authorization bilgileri ve detayli bilgilerine erisilmek istenen department id'sini iceren bir GET request body gönderildiginde dönen status code'in 200 ve message bilgisinin "success" oldugu dogrulanmali.
  /api/departmentDetails endpoint'ine gecerli authorization bilgileri ve detayli bilgilerine erisilmek istenen department id'sini iceren bir GET request body gönderildiginde dönen response body icerisindeki datalar (id, name ,details, status, cerated_at, updated_at) dogrulanmali.

    * The api user sends a GET request containing the <id> in the body and saves the response
    * The api user verifies that the status code is 200
    * The api user verifies that the message information in the response body is "success"

    Examples:
      | id |
      | 1  |
      | 16 |

  Scenario Outline: When a GET request body containing valid authorization information and the department ID to access detailed information is
  sent to the /api/departmentDetails endpoint, the data (id, name ,details, status, cerated_at, updated_at) in the returned response body should be validated.

    * The api user sends a GET request containing the <id> in the body and saves the response
    * The api user verifies that the content of the data <id>, "<name>", "<details>", <status>,"<created_at>","<updated_at>" in the response body.

    Examples:
      | id | name         | details                | status | created_at                  | updated_at                  |
      | 5  | Marketing 23 | Marketing Department 2 | 1      | 2024-03-14T17:14:38.000000Z | 2024-03-21T14:03:48.000000Z |
      | 16 | Marketing    | Marketing Department   | 1      | 2024-04-09T11:59:42.000000Z | 2024-04-09T12:30:03.000000Z |

  Scenario Outline: When a GET request body containing valid authorization information and an incorrect (non-existent in the system) department ID is sent to the /api/departmentDetails endpoint, the returned status code should be 404,
  and the message information should be verified as "department not found".

    * The api user sends a GET request containing the <id> in the body and saves the response
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "department not found"

    Examples:
      | id  |
      | 100 |
      | 160 |