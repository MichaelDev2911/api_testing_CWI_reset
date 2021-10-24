package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class PostBookingRequest {


    public Response createBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(BookingPayloads.payloadValidBooking().toString())
                .post("booking");
    }

    public Response createInvalidBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(BookingPayloads.payloadInvalidBooking().toString())
                .post("booking");
    }

    public Response createBookingWithAdditionalParameters() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(BookingPayloads.payloadBookingWIthAdditionalParameters().toString())
                .post("booking");
    }

    public Response createBookingWithHeaderWrong() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application")
                .when()
                .body(BookingPayloads.payloadValidBooking().toString())
                .post("booking");
    }
}
