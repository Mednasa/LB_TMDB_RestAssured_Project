package Ahmet;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TMBD_Test {
    RequestSpecification reqSpec;
    int accountId = 0;

    @BeforeClass
    public void Setup() {
        baseURI = "https://api.themoviedb.org/3";
        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4NTgzNjMwNGEzYTA0NDI2ODFhZTE5OTEzNjQ5MGNiNyIsInN1YiI6IjY2NDc1MjNhZWU4Y2FiNjBkNzc4NjczMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lWW8mrQpxjAzp-sg7z7zos9rI2OhtHnUricLTp1H2_Q")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void retadMovies() {
        given().spec(reqSpec)

                .when().get("account/%7baccount_id%7d/rated/movies")

                .then().log().body()
                .statusCode(200)
        ;

    }

    @Test(dependsOnMethods = "retadMovies")
    public void getMovieGenres() {
        given().spec(reqSpec)

                .when().get("genre/movie/list")

                .then().log().body()
                .statusCode(200)
        ;

    }

    @Test(dependsOnMethods = "getMovieGenres")
    public void getNowPlayingMovies() {
        given().spec(reqSpec)

                .when().get("movie/now_playing")

                .then().log().body()
                .statusCode(200)
                .extract().path("id")
        ;
    }

    @Test
    public void AccountDetails() {
        accountId =
                given()
                        .spec(reqSpec)
                        .when()
                        .get("/account")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().path("id");

        System.out.println(accountId);
    }

    @Test(dependsOnMethods = "AccountDetails")
    public void addMovietoWatchlist() {
        given().spec(reqSpec)
                .body(" {\"media_type\":\"movie\",\"media_id\":11,\"watchlist\":true}\n")

                .when().post("account/" + accountId + "/watchlist")

                .then().log().body()
                .statusCode(201)
        ;
    }


}
