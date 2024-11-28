Feature: error vaidation for incorrect id passwrord

  Background: 
    Given I landed on E-commerce page

@regression
  Scenario Outline: error validations
    When Logged in with username <name> and password <pass>
    Then "Incorrect email or password." message will be displayed

    Examples: 
      | name                  | pass     |
      | kasarakash4@gmail.com | Akash@12 |
