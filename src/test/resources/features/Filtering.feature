Feature: Filtering records

  Scenario: Filtering by Date
    Given I have no filters
    When I filter for dates "01/01/2021" to "31/12/2021"
    Then There are 5 records left

  Scenario: Filtering by Primary Description
    Given I have no filters
    When I filter for primary description for "BATTERY"
    Then There are 3 records left

  Scenario: Filtering by Location
    Given I have no filters
    When I filter for location description "STREET"
    Then There are 2 records left

  Scenario: Filtering by Beat
    Given I have no filters
    When I filter for beats 400 to 1000
    Then There are 4 records left

  Scenario: Filtering by Ward
    Given I have no filters
    When I filter for wards between 5 to 10
    Then There are 1 records left

  Scenario: Filtering by Arrest
    Given I have no filters
    When I filter for Yes Arrests
    Then There are 1 records left

  Scenario: Filtering by Domestic
    Given I have no filters
    When I filter for Yes Domestic
    Then There are 2 records left