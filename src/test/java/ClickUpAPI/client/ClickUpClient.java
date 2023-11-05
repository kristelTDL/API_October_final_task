package ClickUpAPI.client;

import ClickUpAPI.domain.List;
import ClickUpAPI.domain.Task;
import ClickUpAPI.helpers.TestCaseContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static ClickUpAPI.constants.ProjectConstants.API_TOKEN;
import static ClickUpAPI.constants.ProjectConstants.SPACE_ID;

public class ClickUpClient {

    public static Response createFolder(JSONObject obj) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(obj)
                .when()
                .post("https://api.clickup.com/api/v2/space/" + SPACE_ID + "/folder")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createList(JSONObject obj) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(obj)
                .when()
                .post("https://api.clickup.com/api/v2/folder/" + TestCaseContext.getFolder().getId() + "/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createTask(JSONObject obj) {
        List list = new List();
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(obj)
                .when()
                .post("https://api.clickup.com/api/v2/list/" + TestCaseContext.getList().getId() + "/task")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response removeTask() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .delete("https://api.clickup.com/api/v2/task/" + TestCaseContext.getTask().getId())
                .then().log().all()
                /*
                Documentation says the status code should be 200, however it returns 204 instead, and I checked
                that the task was properly deleted manually before going against the documentation with 204
                 */
                .statusCode(204)
                .extract().response();
    }

    public static Response deleteFolder() {
        return RestAssured
                .given()
                .header("Authorization", API_TOKEN)
                .when()
                .delete("https://api.clickup.com/api/v2/folder/" + TestCaseContext.getFolder().getId())
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}
