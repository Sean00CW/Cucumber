Feature: Add Product Functionality

  Scenario: Add product to basket
    Given I am on the product home page
    When I click on product laptops
    And I click on laptop
    And I click add to basket
    And I go to the basket
    Then Item is added to basket