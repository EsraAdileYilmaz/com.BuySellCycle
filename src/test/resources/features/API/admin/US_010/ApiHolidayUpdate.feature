Feature:
  Scenario Outline:
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayAdd" path parameters
    * The api user prepares a POST request containing the holiday data "<year>", "<name>", "<date>"
    * The api user send POST request to the "holidayUpdate" endpoint.

      Examples:
      | year | name      | date       |
      | 2026 | Kerst     | 09.12.2026 |

  Scenario Outline: The creation of the new HolidayList record via the API should be verified.
  (The creation of the record can be verified by sending a GET request to the /api/holidayList endpoint
  with the added_item_id returned in the response body.)

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayList" path parameters
    * The api user prepares a GET request containing the refund reason <id> for which details are to be accessed, to send to the api holidayList endpoint.
    * The API user sends a GET request and records the response from the api "holidayList" endpoint.
    * The api user verifies that the message information in the response body is "success"

    Examples:
      | id |
      | 77 |

    Scenario Outline: When a PATCH request containing valid authorization credentials, the holiday id to be updated,
    and the updated holiday data (year, name, date) is sent to the /api/holidayUpdate/{id} endpoint, the expected status
    code returned should be 202, and the message in the response body should confirm: "holiday updated successfully".

      * The api user constructs the base url with the "admin" token.
      * The api user sets "api/holidayUpdate/<id>" path parameters
      * The api user prepares a PATCH request containing the "<year>","<name>","<date>" data to send to the api holidayUpdate endpoint.
      * The api user sends the PATCH request and saves the response returned from the api "holidayUpdate" endpoint.
      * The api user verifies that the status code is 202
      * The api user verifies that the message information in the response body is "holiday updated successfully"


      Examples:
      |  id  |    year | name      | date       |
      |  77  |    2025 | Herfst    | 09.12.2025 |




  Scenario: /api/holidayUpdate/{id} endpoint'ine gecerli authorization bilgileri ile yanlis (sistemde bulunmayan)
  holiday id ve güncel holiday verilerini (year, name, date) iceren bir PATCH body gönderildiginde dönen status code'in
  404 ve response body'deki message bilgisinin "holiday not found" oldugu dogrulanmali.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/holidayUpdate/{id}" path parameters
    * The api user sends the PATCH request and saves the response returned from the api refundReasonUpdate endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "holiday updated successfully"