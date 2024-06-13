Feature: Checkout Product Functionality

  Scenario: Checkout product in basket
    Given I am on the current home page
    When I click on desired product type
    And I click on desired laptop
    And I click add to cart
    And I navigate to the basket
    And I navigate to place order
    And I enter my name
    And I enter my country
    And I enter my city
    And I enter my card
    And I enter my month
    And I enter my year
    And I click purchase
    Then Item is purchased