package com.ya;


import org.json.JSONObject;

public class CourierCredential {
    private final String login;
    private final String password;


    public CourierCredential(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public String toJSON() {
        JSONObject credentials = new JSONObject();
        credentials.put("login", getLogin());
        credentials.put("password", getPassword());
        return (credentials.toString());
    }

}

