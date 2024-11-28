
Feature: purchase order from Ecommerce website

  Background: 
    Given I landed on E-commerce page

  
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username <name> and password <pass>
    When I added a product <productName> to the cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed

    Examples: 
      | name                   | pass      | productName |
      | kasarakash04@gmail.com | Akash@123 | ZARA COAT 3 |

    