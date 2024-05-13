package Nigar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TMDB_Test {
    RequestSpecification reqSpec;
    int account_id = 0;

    @BeforeClass
    public void Setup() {

        baseURI = "https://api.themoviedb.org/3";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2MwNTUwN2EyOTg3ODBjMTk0ZmI5NDFhMzA2YjNjMSIsInN1YiI6IjY2MzUwZjk2ODNlZTY3MDEyNDQwZjNmNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jPvFOqHgKT2RKftyNKiEH7KSiBsqDHtUZd0DcY1c7vM")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void getFavoriteMovies() {

        List<String> titles =
                given()
                        .spec(reqSpec)
                        .when()
                        .get("/account/" + account_id + "/favorite/movies")

                        .then()
                     // .log().body()
                        .statusCode(200)
                        .extract().path("results.title");
        System.out.println("Favorite Movies: " + titles);

    }

    @Test
    public void getTopRatedMovies() {

        List<String> titles =
                given()
                        .spec(reqSpec)
                        .when()
                        .get("/movie/top_rated")

                        .then()
                     // .log().body()
                        .statusCode(200)
                        .extract().path("results.title");

        System.out.println("Top Rated Movies: \n ");
        for (String title : titles) {
            System.out.println(title);
        }
    }

    @Test
    public void getUpComingMovies() {

        List<String> titles =
                given()
                        .spec(reqSpec)
                        .when()
                        .get("/movie/upcoming")

                        .then()
                     // .log().body()
                        .statusCode(200)
                        .extract().path("results.title");

        System.out.println("Upcoming Movies: \n ");
        for (String title : titles) {
            System.out.println(title);
        }
    }
}
