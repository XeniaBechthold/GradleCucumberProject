Feature: Login

  @loginWithValidData
  Scenario: Login with the valid data
    Given Navigate to Page Phone Book
    When Click on Login Tab
    And Enter the valid data
    And Click on Login Button
    Then Sign Out Button displayed

  @loginWithInvalidPassword
   Scenario Outline: Login with a valid email and an invalid password
    Given Navigate to Page Phone Book
    When Click on Login Tab
    And Enter a valid email and an invalid password
    |email   | password   |
    |<email> |<password>  |
    And Click on Login Button
    Then Alert appeared
    Examples:
      | email                 |    password |
      |monketester13@gmail.com| 1q2W3e4R   |
      |monketester13@gmail.com| 1q2w3e4r_   |

