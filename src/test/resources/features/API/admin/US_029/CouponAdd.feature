Feature: As an administrator, I want to be able to create a new coupon record via the API connection.


  Scenario Outline: When a POST body containing valid authorization information and necessary data
  (title, coupon_code, coupon_type, start_date, end_date, discount, discount_type, minimum_shopping, maximum_discount, is_expire, is_multiple_buy)
  is sent to the /api/coupon/couponAdd endpoint, the returned status code should be 201, and the message information in the response body should be
  verified as "coupon added successfully".


    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponAdd<id>" path parameters
    * The api user prepares a POST request containing the "<title>","<coupon_code>","<coupon_type>","<start_date>","<end_date>","<discount>","<discount_type>","<minimum_shopping>","<maximum_discount>","<is_expire>","<is_multiple_buy>" information to send to the api couponAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api couponAdd endpoint.
    * The api user verifies that the status code is 201
    * The api user verifies that the message information in the response body is "coupon added successfully"


    Examples:
      | id |   title |coupon_code |coupon_type |start_date |end_date    |discount |discount_type|minimum_shopping|maximum_discount |is_expire |   is_multiple_buy        |
      | 25 |Orderder |  ordered   |    2       |2024-04-18 |2024-04-20  |  10     |       0     |         1      |    2            |     0    |         1                |


  Scenario Outline: "The creation of the new coupon record via the API should be verified from the API itself.
  (The creation of the record can be confirmed by sending a GET request to the /api/coupon/couponDetails endpoint with
  the added_item_id returned in the response body.)"

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponDetails" path parameters
    * The api user prepares a GET request containing the refund reason <id> for which details are to be accessed, to send to the api couponDetails endpoint.
    * The api user sends a GET request and saves the response returned from the api couponDetails endpoint.
    * The api user verifies that the message information in the response body is "success"

    Examples:
      | id |
      | 25 |



  @Ilhan
  Scenario Outline: When a POST body containing invalid authorization information and necessary data
  (title, coupon_code, coupon_type, start_date, end_date, discount, discount_type, minimum_shopping, maximum_discount,
  is_expire, is_multiple_buy) is sent to the /api/coupon/couponAdd endpoint, the returned status code should be 401,
  and the message information in the response body should be verified as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/coupon/couponAdd" path parameters
    * The api user prepares a POST request containing the "<title>", "<coupon_code>", "<coupon_type>", "<start_date>", "<end_date>", "<discount>", "<discount_type>", "<minimum_shopping>", "<maximum_discount>", "<is_expire>", "<is_multiple_buy>" information to send to the api refundReasonAdd endpoint.
    * The api user sends the POST request and saves the response returned from the api couponAdd endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | title   |coupon_code |coupon_type |start_date |end_date    |discount |discount_type|minimum_shopping|maximum_discount |is_expire |is_multiple_buy |
      |Orderder |  ordered   |    2       |2024-04-18 |2024-04-20  |  10     |       0     |         1      |    2            |     0    |      1         |