Feature: SELECT QUERY EXECUTE

  Background: Database connection
    * Database connection is established.
  @IP
  Scenario: Refund_reasons tablosunda 'reason' degeri Null olan veri olup olmadigini dogrulayiniz.

    * Query14 is prepared and executed.
    * ResultSet14 results are processed.
    * The database connection is closed.

