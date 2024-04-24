Feature: As an administrator, I want to be able to update coupon information with
  the specified ID number via the API connection.

  Scenario Outline:When a PATCH request body containing valid authorization information, the coupon ID to be updated, and necessary data
  (title, coupon_code, coupon_type, start_date, end_date, discount, discount_type, minimum_shopping, maximum_discount, is_expire, is_multiple_buy)
  is sent to the /api/coupon/couponUpdate/{id} endpoint, the returned status code should be 202, and the message information in the response body
  should be verified as "coupon updated successfully".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>","<coupon_code>",<coupon_type>,"<start_date>","<end_date>",<discount>,<discount_type>,<minimum_shopping>,"<maximum_discount>",<is_expire>,<is_multiple_buy> data to send to the api couponUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "couponUpdate" endpoint.
    * The api user verifies that the status code is 202
    * The api user verifies that the message information in the response body is "coupon updated successfully"

    Examples:
      | id |   title |coupon_code|coupon_type|start_date|end_date    |discount|discount_type|minimum_shopping|maximum_discount|is_expire|   is_multiple_buy        |
      |  69|Orderder |  ordered  |    2      |2021-02-26|2025-03-30  |  10    |       0     |         1      |    null        |     0   |         1                |

  Scenario Outline: When a PATCH request body containing valid authorization information
  and an incorrect (non-existent in the system) coupon ID, along with necessary data
  (title, coupon_code, coupon_type, start_date, end_date, discount, discount_type,minimum_shopping, maximum_discount,
  is_expire, is_multiple_buy), is sent to the /api/coupon/couponUpdate/{id} endpoint, the returned status code
  should be 404, and the message information in the response body should be verified as "coupon not found"..

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>","<coupon_code>",<coupon_type>,"<start_date>","<end_date>",<discount>,<discount_type>,<minimum_shopping>,"<maximum_discount>",<is_expire>,<is_multiple_buy> data to send to the api couponUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "couponUpdate" endpoint.
    * The api user verifies that the status code is 404
    * The api user verifies that the message information in the response body is "coupon not found"
    Examples:
      | id |   title |coupon_code|coupon_type|start_date|end_date    |discount|discount_type|minimum_shopping|maximum_discount|is_expire|   is_multiple_buy        |
      | 250|Orderder |  ordered  |    2      |2021-02-26|2025-03-30  |  10    |       0     |         1      |    null        |     0   |         1                |

  Scenario Outline:When a PATCH request body containing invalid authorization information,
  along with the coupon ID to be updated and necessary data
  (title, coupon_code, coupon_type, start_date, end_date, discount, discount_type,
  minimum_shopping, maximum_discount, is_expire, is_multiple_buy), is sent to the /api/coupon/couponUpdate/{id}
  endpoint, the returned status code should be 401, and the message information in the response
  body should be verified as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/coupon/couponUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>","<coupon_code>",<coupon_type>,"<start_date>","<end_date>",<discount>,<discount_type>,<minimum_shopping>,"<maximum_discount>",<is_expire>,<is_multiple_buy> data to send to the api couponUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "couponUpdate" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | id |   title |coupon_code|coupon_type|start_date|end_date    |discount|discount_type|minimum_shopping|maximum_discount|is_expire|   is_multiple_buy        |
      |  69|Orderder |  ordered  |    2      |2021-02-26|2025-03-30  |  10    |       0     |         1      |    null        |     0   |         1                |

  Scenario Outline:The updated_Id information in the response body from the /api/coupon/couponUpdate/{id} endpoint
  should be verified to be identical to the id path parameter written in the /api/coupon/couponUpdate/{id} endpoint.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>","<coupon_code>",<coupon_type>,"<start_date>","<end_date>",<discount>,<discount_type>,<minimum_shopping>,"<maximum_discount>",<is_expire>,<is_multiple_buy> data to send to the api couponUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "couponUpdate" endpoint.
    * The API user validates the <id> content of the data in the response body returned from the response.

Examples:
      | id |   title |coupon_code|coupon_type|start_date|end_date    |discount|discount_type|minimum_shopping|maximum_discount|is_expire|   is_multiple_buy        |
      |  69|Orderder |  ordered  |    2      |2021-02-26|2025-03-30  |  10    |       0     |         1      |    null        |     0   |         1                |


  Scenario Outline:The updated_Id information in the response body from the /api/coupon/couponUpdate/{id} endpoint
  should be verified to be identical to the id path parameter written in the /api/coupon/couponUpdate/{id} endpoint.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>","<coupon_code>",<coupon_type>,"<start_date>","<end_date>",<discount>,<discount_type>,<minimum_shopping>,"<maximum_discount>",<is_expire>,<is_multiple_buy> data to send to the api couponUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "couponUpdate" endpoint.
    * The API user validates the <id> content of the data in the response body returned from the response.
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponDetails" path parameters
    * The api user prepares a GET request containing the department <id> to verify that the record has been updated to send to the api couponDetails endpoint.


    Examples:
      | id |   title |coupon_code|coupon_type|start_date|end_date    |discount|discount_type|minimum_shopping|maximum_discount|is_expire|   is_multiple_buy        |
      |  69|Orderder |  ordered  |    2      |2021-02-26|2025-03-30  |  10    |       0     |         1      |    null        |     0   |         1                |


  Scenario Outline:When a PATCH request body containing invalid authorization information,
  along with the coupon ID to be updated and necessary data
  (title, coupon_code, coupon_type, start_date, end_date, discount, discount_type,
  minimum_shopping, maximum_discount, is_expire, is_multiple_buy), is sent to the /api/coupon/couponUpdate/{id}
  endpoint, the returned status code should be 401, and the message information in the response
  body should be verified as "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/coupon/couponUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>","<coupon_code>",<coupon_type>,"<start_date>","<end_date>",<discount>,<discount_type>,<minimum_shopping>,"<maximum_discount>",<is_expire>,<is_multiple_buy> data to send to the api couponUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "couponUpdate" endpoint.
    * The api user verifies that the status code is 401
    * The api user verifies that the message information in the response body is "Unauthenticated."

    Examples:
      | id |   title |coupon_code|coupon_type|start_date|end_date    |discount|discount_type|minimum_shopping|maximum_discount|is_expire|   is_multiple_buy        |
      |  69|Orderder |  ordered  |    2      |2021-02-26|2025-03-30  |  10    |       0     |         1      |    null        |     0   |         1                |

  Scenario Outline:The updated_Id information in the response body from the /api/coupon/couponUpdate/{id} endpoint
  should be verified to be identical to the id path parameter written in the /api/coupon/couponUpdate/{id} endpoint.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>","<coupon_code>",<coupon_type>,"<start_date>","<end_date>",<discount>,<discount_type>,<minimum_shopping>,"<maximum_discount>",<is_expire>,<is_multiple_buy> data to send to the api couponUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "couponUpdate" endpoint.
    * The API user validates the <id> content of the data in the response body returned from the response.

Examples:
      | id |   title |coupon_code|coupon_type|start_date|end_date    |discount|discount_type|minimum_shopping|maximum_discount|is_expire|   is_multiple_buy        |
      |  69|Orderder |  ordered  |    2      |2021-02-26|2025-03-30  |  10    |       0     |         1      |    null        |     0   |         1                |


  Scenario Outline:The updated_Id information in the response body from the /api/coupon/couponUpdate/{id} endpoint
  should be verified to be identical to the id path parameter written in the /api/coupon/couponUpdate/{id} endpoint.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponUpdate/<id>" path parameters
    * The api user prepares a PATCH request containing the "<title>","<coupon_code>",<coupon_type>,"<start_date>","<end_date>",<discount>,<discount_type>,<minimum_shopping>,"<maximum_discount>",<is_expire>,<is_multiple_buy> data to send to the api couponUpdate endpoint.
    * The api user sends the PATCH request and saves the response returned from the api "couponUpdate" endpoint.
    * The API user validates the <id> content of the data in the response body returned from the response.
    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coupon/couponDetails" path parameters
    * The api user prepares a GET request containing the department <id> to verify that the record has been updated to send to the api couponDetails endpoint.


    Examples:
      | id |   title |coupon_code|coupon_type|start_date|end_date    |discount|discount_type|minimum_shopping|maximum_discount|is_expire|   is_multiple_buy        |
      |  69|Orderder |  ordered  |    2      |2021-02-26|2025-03-30  |  10    |       0     |         1      |    null        |     0   |         1                |

