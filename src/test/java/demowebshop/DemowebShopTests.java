package demowebshop;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemowebShopTests {

    // Отправка запроса из java
    // Всегда будет возвращать 1
    @Test
    void addToCartTest() {
        Response response =
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&" +
                        "product_attribute_72_6_19=54&" +
                        "product_attribute_72_3_20=57&" +
                        "addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"))
                .extract().response();
        System.out.println("Response: " + response.path("updatetopcartsectionhtml")); // Response: (17)
    }

    @Test
    void addToCartWithCookieTest() {
        // todo get exist cart size
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("product_attribute_72_5_18=53&" +
                                "product_attribute_72_6_19=54&" +
                                "product_attribute_72_3_20=57&" +
                                "addtocart_72.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                        // .body("updatetopcartsectionhtml", is("(1)"))
                        .extract().response();

        System.out.println("Response: " + response.path("updatetopcartsectionhtml")); // Response: (17)
    }
}

