package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.suites.SecurityTests;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature de Criação de Reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("criar uma Nova Reserva")
    public void validacriacaoDeUmaReservaComSucesso(){
        postBookingRequest.createBooking()
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.notNullValue())
                .body("firstname",Matchers.anything("Jim"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("validar retorno 500 quando o payload da reserva estiver inválido")
    public void validaErroPayloadInvalido(){
        postBookingRequest.createInvalidBooking()
                .then()
                .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("criar Reservas em sequência")
    public void validacriacaoMaisDeUmaReservaComSucesso(){
        postBookingRequest.createBooking()
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.notNullValue())
                .body("firstname",Matchers.anything("Jim"));
        postBookingRequest.createBooking()
                .then()
                .statusCode(200)
                .body("bookingid", Matchers.notNullValue())
                .body("firstname",Matchers.anything("Jim"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class, SecurityTests.class})
    @DisplayName("validar uma reserva enviando mais parâmetros no payload da reserva")
    public void validaErroAoAdicionarParametrosAMais(){
        postBookingRequest.createBookingWithAdditionalParameters()
                .then()
                .statusCode(400);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("validar erro 418 quando header Accept for inválido")
    public void validaErroHeaderErrado(){
        postBookingRequest.createBookingWithHeaderWrong()
                .then()
                .statusCode(418);

    }
}
