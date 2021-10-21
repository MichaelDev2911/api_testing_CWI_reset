package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class GetBookingTest extends BaseTest {

    @Test
    @Category({AllTests.class})
    public void validaListagemDeIdsReservas() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }

    @Test
    @Category({AllTests.class})
    public void validaSchemaDaListagemDeReservas() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))));
    }
}
