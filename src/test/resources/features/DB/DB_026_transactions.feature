Feature:Feature: Calculate Total Amount of Transactions by Payment Method

  Background: Database connection
    * Database connection is established.

  Scenario: Group the data in the transactions table (id, title, amount, payment_method) according to the payment_method information and calculate the sum of the amount values and sort the data with a total amount value above 7000 in reverse order according to the payment_method information.
    * Query026 is prepared and executed.
    * ResultSet026 results are processed.
    * The database connection is closed.