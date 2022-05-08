package com.ya;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CourierClient extends ScooterRestClient {

    private static final String COURIER_PATH = "/api/v1/courier";

    @Step("Login as courier")
    public static Response login(CourierCredential credential) {

        return given()
                .spec(getBaseSpec())
                .body(credential.toJSON())
                .when()
                .post(COURIER_PATH + "/login");

    }

    @Step("Login as courier with String input")
    public static Response login(String credentialJSON) {

        return given()
                .spec(getBaseSpec())
                .body(credentialJSON)
                .when()
                .post(COURIER_PATH + "/login");

    }


    @Step("Register a courier")
    public static Response create(Courier courier) {

        Response response = given()
                .spec(getBaseSpec())
                .and()
                .body(courier.toJSON())
                .when()
                .post("/api/v1/courier");


        Response responseLogin = given()
                .spec(getBaseSpec())
                .and()
                .body(courier.getCredential().toJSON())
                .when()
                .post("/api/v1/courier/login");

        Object id = responseLogin.then().extract().path("id"); // in case, if there is no "id" in the body.
        if (id != null)
            courier.setId((int) id);

        return response;

    }


    @Step("Delete courier with id")
    public static Response delete(Courier courier) {
        JSONObject forDel = new JSONObject();
        forDel.put("id", String.valueOf(courier.getId()));
        return given()
                .spec(getBaseSpec())
                .delete("/api/v1/courier/" + courier.getId());
    }

}
