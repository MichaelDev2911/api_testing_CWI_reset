package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class PostBookingRequest {


    public Response createBooking(){
        return given()
                .header("Content-Type","application/json")
                .when()
                .body(BookingPayloads.payloadValidBooking().toString())
                .post("booking");
    }
}
