package Erdem;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TMDB_Test {
    RequestSpecification reqSpec;
    int movie_id;

    @BeforeClass
    public void Setup() {

        baseURI = "https://api.themoviedb.org/3";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MmU2OWE1" +
                        "ZDM2MGYwMTk2ZDZjYTViY2E0OTBmODA0MyIsInN1YiI6IjY2M2M4ZTMzYjQ2YzE" +
                        "wZjI5NWE5NjI5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.youhAgS" +
                        "_1DrUcmQKKwnNkKvtz1Xhfn1ynSOTAPB50Bo")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void GetMovieDetails() {
        movie_id =
                given()
                        .spec(reqSpec)
                        .when()
                        .get("/movie/693134")
                        .then()
                        .statusCode(200)
                        .extract().path("id");
    }

    @Test
    public void SearchForKeywords() {
        given()
                .spec(reqSpec)
                .param("query", "Arrival")
                .when()
                .get("/search/movie")
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "GetMovieDetails")
    public void DeleteRating() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/movie/" + movie_id + "/rating")
                .then()
                .statusCode(200);
    }

    @Test
    public void UnauthorizedAccess() {
        given()
                .spec(reqSpec)
                .when()
                .post("/list/8299816/add_item?session_id=4534")
                .then()
                .statusCode(401);
    }


}
