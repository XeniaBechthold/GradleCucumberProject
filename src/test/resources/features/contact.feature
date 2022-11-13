Feature: Add Contact
  @addContact
  Scenario Outline: Add Contact after Login
    Given Navigate to Page Phone Book
    When Click on Login Tab
    And Enter the valid data
    And Click on Login Button
    And Click on Add Button
    And Enter Contact Data
      |name  | lastname  |  phone  |  email  |  city  |  comment   |
      |<name> |<lastname>| <phone> | <email> | <city> | <comment>  |
    Then Contact appeared in List
    Examples:
      | name    | lastname | phone      | email      | city   | comment  |
      | Drako   | Malfoy   | 0123456789 | malgoy@g.co| London | Slizerin |
      | Harry   | Potter   | 0123457777 | potter@g.co| London |Griffindor|