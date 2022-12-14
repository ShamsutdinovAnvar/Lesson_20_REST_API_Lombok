package com.wegotrip.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import com.wegotrip.models.lombok.LoginBodyLombokModel;
import com.wegotrip.models.lombok.LoginResponseLombokModel;
import com.wegotrip.models.pojo.LoginBodyPojoModel;
import com.wegotrip.models.pojo.LoginResponsePojoModel;
import org.junit.jupiter.api.Test;

import static com.wegotrip.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static com.wegotrip.specs.LoginSpecs.loginRequestSpec;
import static com.wegotrip.specs.LoginSpecs.loginResponseSpec;

public class ReqresInExtendedTests {

    @Test
    void loginTest() {
        // BAD PRACTICE, move body to model
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
        // { "token": "QpwL5tke4Pnpja7X4" }
    }

//    @Test
//        // Pojo = Plain Old Java Object — старый добрый Java-объект», простой Java-объект
//        // или Java Bean
//    void loginWithPojoModelTest1() {
//        LoginBodyPojoModel body = new LoginBodyPojoModel();
//        body.setName("Anvar");
//        body.setJob("leader");
//
//        LoginResponsePojoModel response = given()
//                .log().all()
//                .contentType(JSON)
//                .body(body)
//                .when()
//                .post("https://reqres.in/api/users")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .extract().as(LoginResponsePojoModel.class);
//
//        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
//    }

    @Test
    void loginWithLombokModelTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithAllureListenerTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithCustomAllureListenerTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithSpecsTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given() // given(loginRequestSpec)
                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

}