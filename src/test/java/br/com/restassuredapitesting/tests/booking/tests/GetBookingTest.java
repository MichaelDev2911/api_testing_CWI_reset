package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.*;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature de Retorno de Reservas")
public class GetBookingTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Listar Ids de reservas")
    public void validatesBookingIdsListing() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("[0].bookingid", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class, SmokeTests.class})
    @DisplayName("Listar uma reserva específica por id")
    public void validatesReturnOfASpecificReservation() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        getBookingRequest.bookingReturnedById(122)
                .then()
                .statusCode(200)
                .body("firstname", Matchers.anything("Goku"))
                .body("lastname", Matchers.anything("Son"))
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Listar Ids de reservas pelo primeiro nome")
    public void validIdReturnByFirstName() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        String firstname = getBookingRequest.bookingReturnedById(buscaIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("firstname");

        getBookingRequest.bookingReturnedByFirstName(firstname)
                .then()
                .statusCode(200)
                .body("[0].bookingid", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void validateErrorByPoorlyFormattedFilter() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        String fname = getBookingRequest.bookingReturnedById(buscaIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("fname");

        getBookingRequest.bookingReturnedByFirstName(fname)
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Listar Ids de reservas pelo último nome")
    public void validatesReturnByLastName() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        String lastname = getBookingRequest.bookingReturnedById(buscaIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("lastname");

        getBookingRequest.bookingReturnedByLastName(lastname)
                .then()
                .statusCode(200)
                .body("[0].bookingid", greaterThanOrEqualTo(1));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Lista os Ids Buscando pela data de checkin")
    public void validateReturnOfAgeByCheckinDate() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        String checkinDate = getBookingRequest.bookingReturnedById(buscaIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkin");

        getBookingRequest.bookingReturnIdsByCheckinDate(checkinDate)
                .then()
                .statusCode(200)
                .body("[0].bookingid", greaterThanOrEqualTo(1));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Lista os Ids Buscando pela data de checkout")
    public void validatesReturnOfIdsByCheckoutDate() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        String checkoutDate = getBookingRequest.bookingReturnedById(buscaIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByCheckoutDate(checkoutDate)
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Lista os Ids Buscando pela data de checkout and checkout")
    public void validatesReturnIdsByCheckoutAndCheckoutDate() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        String checkoutDate = getBookingRequest.bookingReturnedById(buscaSevenIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");

        String checkoutDateTwo = getBookingRequest.bookingReturnedById(buscaIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByCheckoutAndCheckout(checkoutDate, checkoutDateTwo)
                .then()
                .statusCode(400);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Listar Ids de reservas pelo nome,checkin,checkout")
    public void validatesReturnOfIdsByNameAndCheckinAndCheckout() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        String firstname = getBookingRequest.bookingReturnedById(buscaOtherIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("firstname");
        String checkin = getBookingRequest.bookingReturnedById(buscaOtherIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkin");
        String checkout = getBookingRequest.bookingReturnedById(buscaOtherIdBooking())
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");


        getBookingRequest.bookingReturnIdsByFirstNameAndCheckoutAndCheckout(firstname, checkin, checkout)
                .then()
                .statusCode(200)
                .body("[0].bookingid", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reservas")
    public void validateBookingListingSchema() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils
                        .getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema de retorno de uma reserva específica")
    public void validateSpecificReservationSearchSchema() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        getBookingRequest.bookingReturnedById(buscaIdBooking())
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils
                        .getSchemaBasePath("booking", "bookingbyid"))));
    }

    private int buscaIdBooking() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        return getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }

    private int buscaOtherIdBooking() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        return getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[1].bookingid");
    }

    private int buscaSevenIdBooking() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        return getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[7].bookingid");
    }
}
