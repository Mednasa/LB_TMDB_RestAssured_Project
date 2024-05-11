package Onur;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class TMDB_Test {


    RequestSpecification reqSpec;
    int account_id=0;
    int movie_id=0;

    @BeforeClass
    public void Setup() {

        baseURI = "https://api.themoviedb.org/3";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MmU2OWE1ZDM2MGYwMTk2ZDZjYTViY2E0OTBmODA0MyIsInN1YiI6IjY2M2M4ZTMzYjQ2YzEwZjI5NWE5NjI5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.youhAgS_1DrUcmQKKwnNkKvtz1Xhfn1ynSOTAPB50Bo")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void PopularMovies() {

        given()
                .spec(reqSpec)
                .when()
                .get("/movie/popular")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void AccountDetails() {


        account_id =
                given()
                        .spec(reqSpec)
                        .when()
                        .get("/account")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().path("id");

        System.out.println(account_id);

    }

    @Test(dependsOnMethods = "AccountDetails")
    public void WatchlistMovies(){

        given()
                .spec(reqSpec)
                .when()
                .get("/account/"+account_id+"/watchlist/movies")
                .then()
                .log().body()
                .statusCode(200);
        System.out.println("/account/"+account_id+"/watchlist/movies");
    }

    @Test
    public void MovieDetails(){

        movie_id =
        given()
                .spec(reqSpec)
                .when()
                .get("/movie/693134")
                .then()
                .log().body()
                .statusCode(200)
                .extract().path("id");
        System.out.println(movie_id);

    }

    @Test(dependsOnMethods = "MovieDetails")
    public void MovieRating(){

        Map<String,Integer> movieRating = new HashMap<>();
        movieRating.put("value",9);

        given()
                .spec(reqSpec)
                .body(movieRating)
                .when()
                .post("/movie/"+movie_id+"/rating")
                .then()
                .log().body()
                .statusCode(201);


    }

    @Test
    public void SearchforMovies(){

        given()
                .param("query","Harry Potter")
                .spec(reqSpec)
                .log().uri()
                .when()
                .get("https://api.themoviedb.org/3/search/movie")
                .then()
                .log().body()
                .statusCode(200);

    }
}
