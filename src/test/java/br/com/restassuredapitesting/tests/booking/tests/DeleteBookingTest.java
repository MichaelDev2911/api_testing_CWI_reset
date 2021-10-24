package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Feature("Feature de Deleção de Reservas")
@Log
public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Deletar uma reserva específica com sucesso")
    public void deleteASpecificReservation() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[1].bookingid");

        deleteBookingRequest.bookingDeletedById(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Deletar uma reserva que não existe")
    public void tryToDeleteReservationThatDoesntExist() {

        deleteBookingRequest.bookingDeletedById(100, postAuthRequest.getToken())
                .then()
                .statusCode(405);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Deletar uma reserva sem autenticação")
    public void tryToDeleteReservationWithoutAuthentication() {

        deleteBookingRequest.bookingDeletedByIdNoAuthentication(8)
                .then()
                .statusCode(403);
    }
}
