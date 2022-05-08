package com.ya;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class Order extends ScooterRestClient {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
    private int track = 0;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, String rentTime, String deliveryDate, String comment, String[] color) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public Order() {

    }

    @Step("create random courier")
    public static Order getRandom() {
        String FirstName = RandomStringUtils.randomAlphabetic(10);
        String LastName = RandomStringUtils.randomAlphabetic(10);
        String Address = RandomStringUtils.randomAlphabetic(10);
        String MetroStation = RandomStringUtils.randomAlphabetic(10);
        String Phone = "+1" + RandomStringUtils.randomNumeric(9);
        String RentTime = RandomStringUtils.randomNumeric(1);
        String DeliveryDate = LocalDate.now().toString();
        String Comment = RandomStringUtils.randomAlphabetic(10);
        String[] Color = {};

        return new Order()
                .setFirstName(FirstName)
                .setLastName(LastName)
                .setAddress(Address)
                .setMetroStation(MetroStation)
                .setPhone(Phone)
                .setRentTime(RentTime)
                .setDeliveryDate(DeliveryDate)
                .setComment(Comment)
                .setColor(Color);
    }

    public static Response getOrders(String params) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(params)
                .when()
                .get("/api/v1/orders");

    }

    public static Response getOrders() {
        return getOrders("");
    }

    public String getFirstName() {
        return firstName;
    }

    public Order setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Order setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public Order setMetroStation(String metroStation) {
        this.metroStation = metroStation;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Order setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getRentTime() {
        return rentTime;
    }

    public Order setRentTime(String rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public Order setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Order setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String[] getColor() {
        return color;
    }

    public Order setColor(String[] color) {
        this.color = color;
        return this;
    }

    public String toJSON() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("firstName", firstName);
        reqBody.put("lastName", lastName);
        reqBody.put("address", address);
        reqBody.put("metroStation", metroStation);
        reqBody.put("phone", phone);
        reqBody.put("rentTime", rentTime);
        reqBody.put("deliveryDate", deliveryDate);
        reqBody.put("comment", comment);
        reqBody.put("color", color);
        return (reqBody.toString());
    }

    @Step("create order")
    public Response createOrder() {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(this.toJSON())
                .when()
                .post("/api/v1/orders");

    }

}
