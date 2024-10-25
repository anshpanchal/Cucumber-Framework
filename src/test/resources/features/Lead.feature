@Regression
Feature: Lead Functionality
  Background:
    Given user should be on login page
    When user enter valid credentials and click on login page
    Then user should be navigated to home page

@leads
  Scenario: TC04_CreateLead_With_MandatoryFields
    Given user should be on lead creation form
    When user enters all the mandatory fields and click on save button
    Then lead should be created successfully
    And close the browser