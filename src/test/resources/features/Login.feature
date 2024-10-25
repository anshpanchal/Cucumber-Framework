@Regression
Feature: Login Functionality
  Background:
    Given user should be on login page

  @login
  Scenario: TC_01_Valid_Login
    When user enter valid credentials and click on login page
    Then user should be navigated to home page
    And close the browser
  Scenario: TC_02_Invalid_Login
    When user enter invalid credentials and click on login page
    Then user should be navigated to login page
    And user can see the error message
    And close the browser

@param
  Scenario Outline: TC_03_Invalid_Login_Parametrized
    When user enters user_id as "<userid>" and password as "<password>" and click on login page
    Then user should be navigated to login page
    And user can see the error message
    And close the browser
    Examples:
      | userid | password |
      | test   | test     |
      | ansh   | ansh123  |
      | admin  | admin123 |