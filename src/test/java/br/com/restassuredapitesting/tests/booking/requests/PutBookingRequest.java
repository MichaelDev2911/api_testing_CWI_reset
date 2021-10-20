package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.restassured.response.Response;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    String validBooking = BookingPayloads.payloadValidBooking().toString();

    public PutBookingRequest() throws JSONException {
    }

    public Response updateBookingToken(int id,String token){
        return  given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie",token)
                .when()
                .body(validBooking)
                .put("booking/"+ id);
    }
}
