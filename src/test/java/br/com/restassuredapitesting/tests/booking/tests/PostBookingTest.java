package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.*;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Feature de Criação de Reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class,SmokeTests.class})
    @DisplayName("criar uma Nova Reserva")
    public void validationSuccessfulReservationCreation() {
        postBookingRequest.createBooking()
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("firstname", anything("Jim"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("validar retorno 500 quando o payload da reserva estiver inválido")
    public void validateInvalidPayloadError() {
        postBookingRequest.createInvalidBooking()
                .then()
                .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class, SmokeTests.class})
    @DisplayName("criar Reservas em sequência")
    public void validateSuccessfulCreationOfMultipleReservations() {
        postBookingRequest.createBooking()
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("firstname", anything("Jim"));
        postBookingRequest.createBooking()
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("firstname", anything("Jim"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, E2eTests.class, SecurityTests.class})
    @DisplayName("validar a criação de uma reserva enviando mais parâmetros no payload da reserva")
    public void validatesReservationCreationAddMoreParameters() {
        postBookingRequest.createBookingWithAdditionalParameters()
                .then()
                .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("validar erro 418 quando header Accept for inválido")
    public void validateErrorInvalidHeader() {
        postBookingRequest.createBookingWithHeaderWrong()
                .then()
                .statusCode(418);

    }
}
