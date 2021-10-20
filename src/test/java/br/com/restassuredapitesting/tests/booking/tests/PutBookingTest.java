package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PutBookingTest extends BaseTest  {

    PutBookingRequest putBookingRequest = new PutBookingRequest();

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    public PutBookingTest() throws JSONException {
    }

    @Test
    @Category(AllTests.class)
    public void validarAlateracaoDeUmaReservaUtilizandoToken() throws JSONException {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        System.out.println(primeiroId);
        putBookingRequest.updateBookingToken(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));


    }
}

