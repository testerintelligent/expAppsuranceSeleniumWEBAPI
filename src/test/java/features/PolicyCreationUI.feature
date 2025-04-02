Feature: Policy Creation via WEB

  @smoke_UI1
  @policy_UI
  Scenario: Create a policy and validate the success message
    Given I login ReactApp using below credentials
      | Username | test@test.com |
    When I create policy with below data
      | Address         | ChennaiMEPZ      |
      | DateOfBirth     | 02/24/1989       |
      | Gender          | Male             |
      | PolicyType      | Health Insurance |
      | policyCoverage  | Treatment Cost   |      
      | Premium         | 10089            |
      | SumInsured      | 100000           |
    Then I should see the "Insurance policy created successfully." message displayed

  @smoke_UI
    @policy_UI
  Scenario Outline: Create multiple policies with different data and validate the success message
    Given I login ReactApp using below credentials
      | Username | test@test.com |
    When I create policy with below data
      | Address         | <address>         |
      | DateOfBirth     | 02/24/1989        |
      | PolicyType      | <policyType>      |
      | Premium         | <premium>         |
      | policyCoverage  | <policyCoverage>  |  
      | SumInsured      | 100000            |
    Then I should see the "Insurance policy created successfully." message displayed

    Examples:
      | address       | policyType        | premium | policyCoverage |
      | ChennaiPrince | Health Insurance  | 10089   | Treatment Cost |
      | ChennaiMEPZ   | Vehicle Insurance | 9089    | Comprehensive  |

  @smoke_UI_negativecheck
  @policy_UI
  Scenario: Login with invalid credentials - NEGATIVE CHECK FOR REPORT
    Given I login ReactApp using below credentials
      | Username | test1@test.com |