Feature: Negative Checkout Product Functionality

  Scenario: Checkout product in basket
    Given I am on the current home page 1
    When I click on desired product type 1
    And I click on desired laptop 1
    And I click add to cart 1
    And I navigate to the basket 1
    And I navigate to place order 1
    And I enter my name 1
    And I enter my country 1
    And I enter my city 1
    And I enter my card 1
    And I enter my month 1
    And I enter my year 1
    And I click purchase 1
    Then Item is purchased 1