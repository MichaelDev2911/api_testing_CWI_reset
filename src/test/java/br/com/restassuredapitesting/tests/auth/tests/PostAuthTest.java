package br.com.restassuredapitesting.tests.auth.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.notNullValue;

@Feature("Feature de Autenticação de usuário")
public class PostAuthTest extends BaseTest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Retorna um token para um usuário")
    public void validaRetornoDeTokenParaUsuario() throws JSONException {
        postAuthRequest.tokenReturn()
                .then()
                .statusCode(200)
                .body("token",notNullValue());

    }
}
