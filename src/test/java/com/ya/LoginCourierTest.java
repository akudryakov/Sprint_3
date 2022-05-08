package com.ya;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;


public class LoginCourierTest {
    Courier courier;

    @Before
    public void setUp() {
        courier = Courier.getRandom();
        CourierClient.create(courier);
    }

    @After
    public void tearDown() {
        CourierClient.delete(courier);
    }

    @Test
    @DisplayName("Courier authorization")
    @Description("The courier authorization then check that authorization was successfully")
    public void courierCanLogIn() {
        CourierClient.login(courier.getCredential()).then().statusLine("HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("All necessary fields should be filled for authorization")
    @Description("Fill login and password fields and check that courier authorization in successfully ")
    public void requiredFieldsShouldBePassed() {
        CourierClient.login(courier.getCredential()).then().body("id", is(not(0)));
    }

    @Test
    @DisplayName("Courier authorization with wrong login")
    @Description("Courier authorization with wrong login")
    public void courierCanLoginWithWrongLogin() {
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        CourierClient.login(courier.getCredential()).then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Courier authorization with wrong password")
    @Description("Courier authorization with wrong password")
    public void courierCanLoginWrongPassword() {
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        CourierClient.login(courier.getCredential()).then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Check necessity of password")
    @Description("Courier authorization without password to check that its impossible without password")
    public void courierLoginWithEmptyPassword() {
        courier.setPassword("");
        CourierClient.login(courier.getCredential()).then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check necessity of login")
    @Description("Try to courier authorization without login to check that its impossible without login")
    public void courierLoginWithEmptyLogin() {
        courier.setLogin("");
        CourierClient.login(courier.getCredential()).then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier authorization without password field")
    @Description("Courier authorization without password field to check that its impossible without password field")
    public void courierLoginWithoutPassword() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("login", RandomStringUtils.randomAlphabetic(10));
        CourierClient.login(reqBody.toString()).then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier authorization without login field")
    @Description("Courier authorization without login to check that its impossible without login field")
    public void courierLoginWithoutLogin() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("password", RandomStringUtils.randomAlphabetic(10));
        CourierClient.login(reqBody.toString()).then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Non existed courier authorization")
    @Description("Create courier and try authorization with non existing courier, then request return error")
    public void courierLoginAsNonExistentUser() {
        CourierClient.create(courier);
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        CourierClient.login(courier.getCredential()).then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Successful authorization response")
    @Description("After courier authorization response return id")
    public void returnCorrectId() {
        CourierClient.login(courier.getCredential()).then().body("id", is(not(0)));
    }


}
