package com.ya;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ListOfOrdersTest {
    @Test
    @DisplayName("Response body has list of orders")
    @Description("After get request response body has list of orders")
    public void responseHasListOfOrders() {

        Order.getOrders().then().body("orders", Matchers.notNullValue());
    }
}
