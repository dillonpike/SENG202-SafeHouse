Feature: Exporting A file
  # Note that we must use some importing functionality
  # To Test the exporting functionality
  # Since the whole point of exporting
  # Is so we can import back with no errors

  Scenario: Exporting a file
    Given I have a dataset imported from "src/test/resources/TenValidRecords.csv"
    When I export the dataset to "src/test/resources/TenExportedRecords.csv"
    Then The file "src/test/resources/TenExportedRecords.csv" exists

  Scenario: Exporting then re-importing a file
    Given I have a dataset imported from "src/test/resources/TenValidRecords.csv"
    And I export the dataset to "src/test/resources/TenExportedRecords.csv"
    And I have an empty dataset
    When I import "src/test/resources/TenExportedRecords.csv"
    Then The dataset has the same records as "src/test/resources/TenValidRecords.csv"