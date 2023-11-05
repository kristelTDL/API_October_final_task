Feature: ClickUp Features tests

  Scenario: Create a new Folder, List, and Task, and delete the Task
    Given I create a ClickUp folder called "API Tests"
    And I create a new list in the created folder
    Then The correct list is added to the correct folder
    When I create a new task in the list
    Then The task is created with the correct name
    Then I remove the task from the list