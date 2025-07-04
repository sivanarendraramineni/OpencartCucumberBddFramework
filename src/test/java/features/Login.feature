
  Feature: Login Feature
    @RegressionTest
    Scenario: Login with valid credentials
      Given user navigate to login page
      When user entered email address "sivanarendra@gmail.com" into email field
      And user enter password "12345" into password field
      And click on login button
      Then user loggedin successfully
    @RegressionTest
    Scenario: Login with invalid credentials
      Given user navigate to login page
      When user entered email address "siva@gmail.com" into email field
      And user enter password "123" into password field
      And click on login button
      Then user get warning massage for invalid credentials

    @SanityTest
    Scenario Outline: Login with Multiple Credentials
      Given user navigate to login page
      When user entered email address "<email>" into email field
      And user enter password "<password>" into password field
      And click on login button
      Then user get "<expectedMessage>" message

        Examples:
            | email                   | password | expectedMessage                      |
            |sivanarendra@gmail.com   | 12345    | True            |
            |ramineni@gmail.com          | 123456  | True  |
            | testuser@example.com    | wrong123 | Warning: No match for E-Mail Address and/or Password.|
            | admin@dummy.com         | admin    | Warning: No match for E-Mail Address and/or Password.|
            |                         | 12345    | Warning: No match for E-Mail Address and/or Password.|
            | sivanarendra@gmail.com  |          | Warning: No match for E-Mail Address and/or Password.|



