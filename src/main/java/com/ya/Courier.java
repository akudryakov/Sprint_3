package com.ya;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

public class Courier {

    private String login;
    private String password;
    private String firstName;
    private int id = 0;


    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Step("create random courier")
    public static Courier getRandom() {
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);

        Allure.addAttachment("логин: ", courierLogin);
        Allure.addAttachment("пароль: ", courierPassword);
        Allure.addAttachment("имя: ", courierFirstName);
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toJSON() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("login", login);
        reqBody.put("password", password);
        reqBody.put("firstName", firstName);
        reqBody.put("id", id);
        return (reqBody.toString());
    }


    public CourierCredential getCredential() {
        return new CourierCredential(login, password);
    }

}

