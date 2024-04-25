@query08
Feature: Verify the names in the delivery_processes table
Background: Database connection
  Given Database connection is established.

  Scenario: Verify that the first 5 data names in the delivery_processes table are in reverse order
  (Shipped, Recieved, Processing, Pending, Delivered).

    Given Query08 is prepared to select the first five names from delivery_processes and executed.
    Then The results Query08 should be in reverse order: Shipped, Received, Processing, Pending, Delivered.
    And Database connection is established.