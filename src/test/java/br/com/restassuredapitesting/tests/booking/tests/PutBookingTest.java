package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

@Feature(" Feature de Atualização de Reservas ")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    public PutBookingTest() throws JSONException {
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Alterar uma reserva, utilizando o token")
    public void validatesChangeOfAReservationUsingToken() throws JSONException {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Alterar uma reserva, utilizando o Autorização Basic auth")
    public void validatesChangeOfAReservationUsingBasicAuth() throws JSONException {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingBasicAuth(primeiroId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));


    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTest.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva , sem o token")
    public void validatesChangeOfAReservationWithoutToken() throws JSONException {

        putBookingRequest.updateBookingWithoutToken(buscaIdBooking())
                .then()
                .statusCode(403);

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTest.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva, utilizando o token errado")
    public void validatesChangeOfAReservationWithWrongToken() throws JSONException {

        putBookingRequest.updateBookingToken(buscaIdBooking(), "esseVaiDarErro")
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Alterar uma reserva caso id não exista")
    public void validatesChangeOfAReservationThatDoesNotExist() throws JSONException {

        putBookingRequest.updateBookingToken(100000, postAuthRequest.getToken())
                .then()
                .statusCode(405);

    }

    private int buscaIdBooking() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        return getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }
}

