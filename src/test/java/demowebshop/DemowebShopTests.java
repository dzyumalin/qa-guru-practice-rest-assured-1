package demowebshop;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class DemowebShopTests {

    // Отправка запроса из java
    @Test
    void addToCartTest() {
        Response response =
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&" +
                        "product_attribute_72_6_19=54&" +
                        "product_attribute_72_3_20=57&" +
                        "addtocart_72.EnteredQuantity=1")
                .cookie("Nop.customer=a797b2f0-3ee2-43b6-a195-a14200935d14; ")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .extract().response();
        //                .body("updatetopcartsectionhtml", is("(13)"));
        System.out.println("Response: " + response.path("updatetopcartsectionhtml"));
    }
}

