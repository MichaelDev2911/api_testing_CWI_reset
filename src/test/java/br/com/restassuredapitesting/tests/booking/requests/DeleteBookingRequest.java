package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest  {

    @Step("Excluir uma reserva com sucesso")
    public Response bookingDeletedById(int id, String token){
        return given()
                .header("Content-Type","application/json")
                .header("Cookie",token)
                .when()
                .delete("booking/" +id);
    }

    @Step("Excluir uma reserva sem autenticação")
    public Response  bookingDeletedByIdNoAuthentication(int id){
        return given()
                .header("Content-Type","application/json")
                .when()
                .delete("booking/" + id);
    }
}
