Feature: Importing of Records

  Scenario: Importing a file
    Given I have an empty dataset
    When I import "src/test/resources/TenValidRecords.csv"
    Then The dataset has 10 records

  Scenario: Importing a partially invalid file
    Given I have an empty dataset
    When I import "src/test/resources/TenValidTenInvalidRecords.csv"
    Then The dataset has 10 records

  Scenario: Appending a file
    Given I have a dataset imported from "src/test/resources/TenValidRecords.csv"
    When I import "src/test/resources/TenValidTenInvalidRecords.csv"
    Then The dataset has 20 records

  Scenario: Importing the same file twice
    Given I have a dataset imported from "src/test/resources/TenValidRecords.csv"
    When I import "src/test/resources/TenValidRecords.csv"
    Then The dataset has 10 records