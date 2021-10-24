package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
String auth = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    public PutBookingRequest() throws JSONException {
    }

    @Step("Atualiza uma reserva específica com o parâmetro token")
    public Response updateBookingToken(int id,String token) throws JSONException {
        return  given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie",token)
                .when()
                .body(BookingPayloads.payloadValidBooking().toString())
            .put("booking/"+ id);
}

    @Step("Atualiza uma reserva específica com o parâmetro Basic auth")
    public Response updateBookingBasicAuth(int id) throws JSONException {
        return  given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization",auth)
                .when()
                .body(BookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }
    @Step("Atualiza uma reserva específica sem o parâmetro token")
    public Response updateBookingWithoutToken(int id) throws JSONException {
        return  given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .when()
                .body(BookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }
}
