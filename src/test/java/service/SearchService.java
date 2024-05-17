package service;

import io.restassured.specification.ResponseSpecification;
import spec.RequestSpec;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchService extends RequestSpec {

    public void searchForMovies(ResponseSpecification responseSpec, String movieName) {

        List<String> searchMovie =
                given()
                        .spec(getRequestSpec())
                        .param("query", movieName)
                        .when()
                        .get("/search/movie")
                        .then()
                        .spec(responseSpec)
                        .extract().path("results.original_title");
        for (String s : searchMovie) {
            System.out.println(s);
        }
    }

    public void searchForKeywords(ResponseSpecification responseSpec, String movieName) {

        List<String> Keywords =
                given()
                        .spec(getRequestSpec())
                        .param("query", movieName)
                        .when()
                        .get("/search/movie")
                        .then()
                        .spec(responseSpec)
                        .extract().path("results.original_title");

        for (String k : Keywords) {
            System.out.println(k);
        }
    }
}
