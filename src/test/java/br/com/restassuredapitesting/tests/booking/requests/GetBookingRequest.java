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
    public Response bookingReturnedById(int id){
        return given()
                .header("Accept","application/json")
                .when()
                .get("booking/" +id);
    }

    @Step("Retorna Id da Busca Pelo Primeiro Nome")
    public Response bookingReturnedByFirstName(String name){
        return given()
                .header("Accept","application/json")
                .when()
                .get("booking?firstname=" +name);
    }

    @Step("Retorna Id da Busca Pelo Sobrenome Nome")
    public Response bookingReturnedByLastName(String name){
        return given()
                .header("Accept","application/json")
                .when()
                .get("booking?lastname=" +name);
    }

    @Step("Retorna Id da Busca Pela data de checkin ")
    public Response bookingReturnIdsByCheckinDate(String date){
        return given()
                .header("Accept","application/json")
                .when()
                .get("booking?checkin=" +date);
    }

    @Step("Retorna Id da Busca Pela data de checkin ")
    public Response bookingReturnIdsByCheckoutDate(String date){
        return given()
                .header("Accept","application/json")
                .when()
                .get("booking?checkout=" +date);
    }

}
