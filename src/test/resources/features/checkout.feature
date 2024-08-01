Feature: Checkout Product Functionality

  Scenario: Checkout product in basket
    Given I am on the current home page
    When I click on desired product type
    And I click on desired laptop
    And I click add to cart
    And I navigate to the basket
    And I navigate to place order
    And I enter the following details
      | Name    | Country       | City        | Card Number      | Expiry Month | Expiry Year |
      | Sean    | United Kingdom | Peterborough | 1111111111111111 | 08           | 28          |

    And I click purchase
    Then Item is purchased