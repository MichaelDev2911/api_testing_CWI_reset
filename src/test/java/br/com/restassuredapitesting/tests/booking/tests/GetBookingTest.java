package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
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

@Feature("Feature de Retorno de Reservas")
public class GetBookingTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar Ids de reservas")
    public void validaListagemDeIdsReservas() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar uma reserva específica por id")
    public void validaRetornoDeUmaReservaEspecifica() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThanOrEqualTo(1));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Listar Ids de reservas pelo primeiro nome")
    public void validaRetornoDeUmIdPeloPrimeiroNome() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String firstname = getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("firstname");


        getBookingRequest.bookingReturnedByFirstName(firstname)
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(5))
                .body("[0].bookingid", Matchers.greaterThanOrEqualTo(1));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Listar Ids de reservas pelo sobrenome")
    public void validaRetornoDeUmIdPeloSobrenomeNome() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String lastname = getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("lastname");

        getBookingRequest.bookingReturnedByLastName(lastname)
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(5))
                .body("[0].bookingid", Matchers.greaterThanOrEqualTo(1));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Lista os Ids Buscando pela data de checkin")
    public void validaRetornoDeIdspelaDataDeCheckin() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String checkinDate = getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkin");

        getBookingRequest.bookingReturnIdsByCheckinDate(checkinDate)
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(6))
                .body("[0].bookingid", Matchers.greaterThanOrEqualTo(1));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Lista os Ids Buscando pela data de checkout")
    public void validaRetornoDeIdspelaDataDeCheckout() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String checkoutDate = getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");
        System.out.println(checkoutDate);

        getBookingRequest.bookingReturnIdsByCheckoutDate(checkoutDate)
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(5))
                .body("[0].bookingid", Matchers.greaterThanOrEqualTo(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Lista os Ids Buscando pela data de checkout and checkout")
    public void validaRetornoDeIdspelaDataDeCheckoutAndChechout() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[7].bookingid");

        String checkoutDate = getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");
        System.out.println(checkoutDate);

        int segundoId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String checkoutDateTwo = getBookingRequest.bookingReturnedById(segundoId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");
        System.out.println(checkoutDate);

        getBookingRequest.bookingReturnIdsByCheckoutAndCheckout(checkoutDate,checkoutDateTwo)
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(5))
                .body("[0].bookingid", Matchers.greaterThanOrEqualTo(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Listar Ids de reservas pelo nome,checkin,checkout")
    public void validaRetornoDeIdsPeloPrimeiroNomeAndCheckinAndCheckout() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[1].bookingid");

        String firstname = getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("firstname");
        String checkin = getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkin");
        String checkout = getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");


        getBookingRequest.bookingReturnIdsByFirstNameAndCheckoutAndCheckout(firstname,checkin,checkout)
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(6))
                .body("[0].bookingid", Matchers.greaterThanOrEqualTo(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reservas")
    public void validaSchemaDaListagemDeReservas() {
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
    public void validaSchemaDaBuscaDeUmaReservaEspicifica() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        getBookingRequest.bookingReturnedById(primeiroId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils
                        .getSchemaBasePath("booking", "bookingbyid"))));
    }
}
