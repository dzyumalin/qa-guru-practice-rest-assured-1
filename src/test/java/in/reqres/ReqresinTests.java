package in.reqres;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.is;

public class ReqresinTests {

    @Test
    void singleUser() {
        given()
                .contentType(JSON)
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    void singleUserNotFound() {
        given()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void updateUser() {
        Response response =
                given()
                        .contentType(JSON)
                        .body("{\"name\": \"morpheus\"," +
                                "\"job\": \"zion resident\"}")
                        .when()
                        .put("https://reqres.in/api/users/2")
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println(response.asString());
    }

    @Test
    void registerSuccessfull() {
        given()
                .contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"pistol\"}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void registerUnSuccessfull() {
        given()
                .contentType(JSON)
                .body("{\"email\": \"sydney@fife\"}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createUserTest() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\"," +
                        "\"job\": \"leader\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
}
