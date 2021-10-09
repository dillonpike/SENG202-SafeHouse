Feature: Manually adding, deleting and removing records.

  Scenario: Adding a record to an empty dataset
    Given I have an empty dataset
    When I add an arbitrary valid record
    Then The dataset has 1 records

  Scenario: Adding a record to an occupied dataset
    Given I have a dataset imported from "src/test/resources/TenValidRecords.csv"
    When I add an arbitrary valid record
    Then The dataset has 11 records

  Scenario: Deleting a record
    Given I have a dataset imported from "src/test/resources/TenValidRecords.csv"
    When I delete the last record from the dataset
    Then The dataset has 9 records

  Scenario: Editing a record
    Given I have a dataset imported from "src/test/resources/TenValidRecords.csv"
    When I change the 1 th record's primary description to "Naughty Things"
    Then The primary description of the 1 th record is "Naughty Things"
