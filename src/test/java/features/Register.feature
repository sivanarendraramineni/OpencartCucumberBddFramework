Feature: User Registration Feature

  @RegressionTest
  Scenario: User create account with all fields using Data Table
    Given user navigate to registration page
    When user enter the details into fields
      | firstname       | siva                |
      | lastname     | narendra           |
      | email           |Rsn@gmail.com      |
        | telephone       | 1234567890         |
        | password        | 123456             |
        | confirmpassword | 123456             |
    And user select privacy policy
    Then user clicks on continue button
    Then user account created successfully

  @RegressionTest
  Scenario: User create account with all fields using util class methods
    Given user navigate to registration page
    When user enter the details into fields using util class
    And user select privacy policy
    Then user clicks on continue button
    Then user account created successfully

  @SanityTest
  Scenario: user create account without filling details
    Given user navigate to registration page
    When user not enter any details
    And user clicks on continue button
    Then user get warning message for mandatory fields

  @SanityTest
  Scenario: Register user with data from Excel
    Given user navigate to registration page
    When user registers using data from "Register" sheet at row "1"
    And user select privacy policy
    Then user clicks on continue button
    Then user get warning message for duplicate email address
  @SanityTest
  Scenario Outline: Register user with data from Excel multiple times
    Given user navigate to registration page
    When user registers using data from "Register" sheet at row "<rowNumber>"
    And user select privacy policy
    Then user clicks on continue button
    Then user get warning message for duplicate email address

    Examples:
      | rowNumber |
      | 1         |
      | 2         |
      | 3         |