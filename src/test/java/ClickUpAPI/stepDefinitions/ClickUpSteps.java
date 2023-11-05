package ClickUpAPI.stepDefinitions;

import ClickUpAPI.domain.Folder;
import ClickUpAPI.domain.List;
import ClickUpAPI.domain.Task;
import ClickUpAPI.helpers.HelperFunctions;
import ClickUpAPI.helpers.TestCaseContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;

import static ClickUpAPI.client.ClickUpClient.*;
import static ClickUpAPI.constants.ProjectConstants.LIST_NAME;

public class ClickUpSteps {
    String taskName;

    @Given("I create a ClickUp folder called {string}")
    public void iCreateAClickUpFolderCalled(String name) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        Response res = createFolder(obj);

        Folder folder = res.as(Folder.class);
        TestCaseContext.setFolder(folder);

        Assertions.assertThat(folder.getName())
                .as("Check that the returned folder name is the same as sent in request")
                .isEqualTo(name);
    }

    @And("I create a new list in the created folder")
    public void iCreateANewListInTheCreatedFolder() {
        JSONObject obj = new JSONObject();
        obj.put("name", LIST_NAME);
        Response res = createList(obj);

        List list = res.as(List.class);
        TestCaseContext.setList(list);
    }

    @Then("The correct list is added to the correct folder")
    public void theCorrectListIsAddedToTheCorrectFolder() {
        Folder folder = TestCaseContext.getFolder();
        List list = TestCaseContext.getList();

        Assertions.assertThat(list.getName())
                .as("Check that the list name is correct")
                .isEqualTo(LIST_NAME);

        Assertions.assertThat(list.getFolder().get("id"))
                .as("Check that the list is added to the correct folder")
                .isEqualTo(folder.getId());
    }

    @When("I create a new task in the list")
    public void iCreateANewTaskInTheList() {
        JSONObject obj = new JSONObject();
        taskName  = HelperFunctions.randomString();
        obj.put("name", taskName);
        Response res = createTask(obj);

        Task task = res.as(Task.class);
        TestCaseContext.setTask(task);
    }

    @Then("The task is created with the correct name")
    public void theTaskIsCreatedWithTheCorrectName() {
        Task task = TestCaseContext.getTask();

        Assertions.assertThat(task.getName())
                .as("Check that the task was created with the correct name")
                .isEqualTo(taskName);
    }

    @Then("I remove the task from the list")
    public void iRemoveTheTaskFromTheList() {
       Response res = removeTask();
    }
}
