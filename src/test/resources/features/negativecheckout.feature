Feature: Negative Checkout Product Functionality

  Scenario: Checkout product in basket
    Given I am on the current home page 1
    When I click on desired product type 1
    And I click on desired laptop 1
    And I click add to cart 1
    And I navigate to the basket 1
    And I navigate to place order 1
    And I enter invalid checkout details
      | name | country | city | card | month | year |
      |      |         |      |      |       |      |
    And I click purchase 1
    Then Item is not purchased