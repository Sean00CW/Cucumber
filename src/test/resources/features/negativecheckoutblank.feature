Feature: Negative Checkout Blank Product Functionality

  Scenario: Checkout product in basket blank
    Given I am on the current home page 2
    When I click on desired product type 2
    And I click on desired laptop 2
    And I click add to cart 2
    And I navigate to the basket 2
    And I navigate to place order 2
    Then Item should not be purchased