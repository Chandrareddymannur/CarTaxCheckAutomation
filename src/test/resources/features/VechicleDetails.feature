@ui
Feature: Vehicle Details Verification

  Scenario Outline: Verify Vehicle details
        - Read expected vehicle details from car_output.txt
        - Read actual vehicle details from cartaxcheck website
        - Verify if the both the details match up.

    Given I have vehicle registration numbers list in the "<input_file>"
    And I have expected vehicle details in the "<output_file>"
    When I navigate to cartaxcheck website to check vehicle details
    Then Expected vehicle details should match with Actual details on the website
    Examples:
      | input_file                                | output_file                                |
      | src/test/resources/testdata/car_input.txt | src/test/resources/testdata/car_output.txt |
