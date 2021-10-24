package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os Ids da listagem de reservas ")
    public Response bookingReturnIds() {
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna uma reserva específica")
    public Response bookingReturnedById(int id) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking/" + id);
    }

    @Step("Retorna Id da Busca Pelo Primeiro Nome")
    public Response bookingReturnedByFirstName(String name) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?firstname=" + name);
    }

    @Step("Retorna Id da Busca Pelo Sobrenome ")
    public Response bookingReturnedByLastName(String name) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?lastname=" + name);
    }

    @Step("Retorna Id da Busca Pela data de checkin ")
    public Response bookingReturnIdsByCheckinDate(String date) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkin=" + date);
    }

    @Step("Retorna Id da Busca Pela data de checkout ")
    public Response bookingReturnIdsByCheckoutDate(String date) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkout=" + date);
    }

    @Step("Retorna Id da Busca Pela data de checkout and checkout ")
    public Response bookingReturnIdsByCheckoutAndCheckout(String checkoutOne
            , String checkoutTwo) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?checkout=" + checkoutOne
                        + "&?checkout=" + checkoutTwo);
    }

    @Step("Retorna Id da Busca Pelo primeiro nome,checkin,checkout ")
    public Response bookingReturnIdsByFirstNameAndCheckoutAndCheckout(String firstname
            , String checkin
            , String checkout) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking?firstname=" + firstname
                        + "&?checkin=" + checkin
                        + "&?checkout=" + checkout);
    }
}
