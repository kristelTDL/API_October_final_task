package ClickUpAPI.stepDefinitions;

import ClickUpAPI.client.ClickUpClient;
import io.cucumber.java.After;

public class Hooks {
    @After
    public void after() {
        ClickUpClient.deleteFolder();
    }
}
