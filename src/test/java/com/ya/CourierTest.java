package com.ya;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CourierTest {

    Courier courier;

    @Before
    public void setUp() {
        courier = Courier.getRandom();
    }

    @After
    public void tearDown() {
        CourierClient.delete(courier);
    }


    @Test
    @DisplayName("Create new courier")
    @Description("Create new courier and then try to verify if he is really has created")
    public void createCourier() {
        CourierClient.create(courier).then().statusLine("HTTP/1.1 201 Created");
    }

    @Test
    @DisplayName("Cannot create two identical couriers")
    @Description("Creation of two identical couriers and check if they are created")
    public void cannotCreateTwoIdenticalCouriers() {
        CourierClient.create(courier);
        CourierClient.create(courier).then().statusCode(409).and().body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Create user with all necessary fields")
    @Description("Create user with all necessary fields: login, password, firstName")
    public void createCourierWithAllNecessaryField() {
        CourierClient.create(courier).then().statusCode(201).and().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Get correct status code")
    @Description("The request return correct status code after successful courier creation")
    public void createCourierWithStatusCodeIS201() {
        CourierClient.create(courier).then().statusCode(201);
    }

    @Test
    @DisplayName("Request return ok:true")
    @Description("The request return correct answer after successful courier creation ")
    public void createCourierWithCorrectResponse() {
        CourierClient.create(courier).then().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Courier cannot create if one of necessary fields doesn't exist ")
    @Description("Try to create courier without password field ")
    public void createCourierWithoutPassword() {
        courier.setPassword("");
        CourierClient.create(courier).then().statusCode(400).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Courier cannot create if one of necessary fields doesn't exist")
    @Description("Try to create courier without login field")
    public void createCourierWithoutLogin() {
        courier.setLogin("");
        CourierClient.create(courier).then().statusCode(400).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Courier cannot create if one of necessary fields doesn't exist")
    @Description("Try to create courier without password firstName")
    public void createCourierWithoutFirstName() {
        courier.setFirstName("");
        CourierClient.create(courier).then().statusCode(400).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Create courier with the existing login")
    @Description("Try to create courier with existing login if it possible return error")
    public void createCourierWithDuplicateLogin() {
        CourierClient.create(courier);
        CourierClient.create(courier).then().statusCode(409).and().body("message", equalTo("Этот логин уже используется"));
    }

}
