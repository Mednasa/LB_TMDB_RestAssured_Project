package tmdb_tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class LoginTest {

    String authenticity_token = "";

    @Test
    public void getLogin() {

        Response response = RestAssured.get("https://www.themoviedb.org/login");

        String htmlContent = response.body().asString();

        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(htmlContent);

        authenticity_token = doc.select("input[name=authenticity_token]").attr("value");


    }

    @Test(dependsOnMethods = "getLogin")
    public void postLogin() {

        String formParam1 = "username";
        String formParam2 = "password";
        String formParam3 = authenticity_token;
        System.out.println(authenticity_token);

        given()

                .config(
                        RestAssured.config()
                                .encoderConfig(
                                        encoderConfig()
                                                .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
//
                .contentType("multipart/form-data")
                .formParam("username", formParam1)
                .formParam("password", formParam2)
                .formParam("authenticity_token", formParam3)
                .when()
                .post("https://www.themoviedb.org/login")
                .then()
                .log().body();
    }
}
