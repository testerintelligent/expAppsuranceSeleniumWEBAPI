Feature: Policy Creation via API

  @smoke1
  @loginCheck
  Scenario: Validate login using POST method and validate response code
    Given I have the following login details
      | username | test@test.com |
      | password | 12345         |
    When I send a POST request to Endpoint "http://10.192.190.148:5000/home"
    Then the response code should be 200
    And the response body should contain "test34@test.com"


  @smoke
  @policy
  Scenario: Create a new policy using POST method and validate response code
    Given I have the following policy details
      | Name        | random                |
      | email       | random                |
      | Address     | Chennai               |
      | DateOfBirth | 2024-09-23            |
      | Gender      | Male                  |      
      | PolicyType  | Health Insurance      |
      | PolicyCoverage  | Hospitalization costs |      
      | Premium     | 10089                 |
      | SumInsured  | 100000                |
    When I send a POST request to Endpoint "http://10.192.190.148:5000/Dashboard"
    Then the response code should be 201
    And the response body should contain "Insurance policy created successfully."

  @smoke_performance
  Scenario: Create bulk policies for load test
    Given I have created 5 policies in the endpoint "http://10.192.190.148:5000/Dashboard"

  @smoke
  Scenario Outline: Create multiple policies with data combination using POST method and validate response code
    Given I have the following policy details
      | Name        | random       |
      | email       | random       |
      | Address     | <address>    |
      | DateOfBirth | 2024-05-23   |
      | Gender      | Female         |
      | PolicyType  | <policyType> |
      | PolicyCoverage  | <policyCoverage> |       
      | Premium     | <premium>    |
      | SumInsured  | 100000       |
    When I send a POST request to Endpoint "http://10.192.190.148:5000/Dashboard"
    Then the response code should be 201
    And the response body should contain "Insurance policy created successfully."

    Examples:
      | premium | policyType         | address        | policyCoverage        |  
      | 5000   | Health Insurance   | ChennaiMEPZ    | Hospitalization costs |
      | 6000   | Vechicle Insurance | PrinceINFOCITY | Comprehensive         |