package service;

import io.restassured.specification.ResponseSpecification;
import spec.RequestSpec;

import java.util.List;

import static io.restassured.RestAssured.given;

public class MovieListService extends RequestSpec {

    public void getNowPlayingMovies(ResponseSpecification responseSpec) {

        List<String> nowPlayingMovies =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .get("/movie/now_playing")
                        .then()
                        .spec(responseSpec)
                        .extract().path("results.original_title");

        for (String n : nowPlayingMovies) {
            System.out.println(n);
        }
    }

    public void getPopularMovies(ResponseSpecification responseSpec) {

        List<String> popularMovies =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .get("/movie/popular")
                        .then()
                        .spec(responseSpec)
                        .extract().path("results.original_title");

        for (String p : popularMovies) {
            System.out.println(p);
        }

    }

    public void getTopRatedMovies(ResponseSpecification responseSpec) {

        List<String> topRatedMovies =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .get("/movie/top_rated")
                        .then()
                        .spec(responseSpec)
                        .extract().path("results.original_title");

        for (String t : topRatedMovies) {
            System.out.println(t);
        }
    }

    public void getUpcomingMovies(ResponseSpecification responseSpec) {

        List<String> upComingMovies =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .get("/movie/upcoming")
                        .then()
                        .spec(responseSpec)
                        .extract().path("results.original_title");

        for (String u : upComingMovies) {
            System.out.println(u);
        }
    }

}
