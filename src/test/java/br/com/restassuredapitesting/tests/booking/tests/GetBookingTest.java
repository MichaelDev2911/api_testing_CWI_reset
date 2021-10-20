package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GetBookingTest {

    @Test
    @Category({AllTests.class})
    public void validaListagemDeIdsReservas() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }
}
