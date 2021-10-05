Feature: Importing

  Scenario: Importing a file
    Given I have an empty dataset
    When I import "src/test/resources/TenValidRecords.csv"
    Then The dataset has 10 records