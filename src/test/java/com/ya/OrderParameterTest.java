package com.ya;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.hasKey;

@RunWith(Parameterized.class)
public class OrderParameterTest {
    private final String[] color;

    public OrderParameterTest(String[] color) {

        this.color = color;
    }

    @Parameterized.Parameters
    public static String[][][] getTestData() {
        return new String[][][]{
                {{}},
                {{"BLACK"}},
                {{"GREY"}},
                {{"BLACK", "GREY"}}
        };
    }

    @Test
    @DisplayName("Create new order")
    @Description("Create new order with different combination parameters of colors ")
    public void createNewOrder() {
        Order order = Order.getRandom();
        order.setColor(color);
        order.createOrder().then().body("$", hasKey("track"));
    }


}
