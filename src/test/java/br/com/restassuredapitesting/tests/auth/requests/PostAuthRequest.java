package br.com.restassuredapitesting.tests.auth.requests;

import br.com.restassuredapitesting.tests.auth.requests.payloads.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    @Step("Retorna o token")
    public Response tokenReturn() throws JSONException {

        AuthPayloads authPayloads = new AuthPayloads();


        return given()
                .header("Content-Type","application/json")
                .when()
                .body(authPayloads.jsonAuthLogin().toString())
                .post("auth");

    }

    @Step("Busca o token")
    public String getToken() throws JSONException {
        return "token="+this.tokenReturn()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
