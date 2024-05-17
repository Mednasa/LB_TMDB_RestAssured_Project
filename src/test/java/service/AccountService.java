package service;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import spec.RequestSpec;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AccountService extends RequestSpec {

    public HashMap<String, Object> requestBodyWatchList = new HashMap<>();
    public HashMap<String, Object> requestBodyFavorites = new HashMap<>();


    public Response getAccountDetails(ResponseSpecification responseSpec) {

        return given()
                .spec(getRequestSpec())
                .when()
                .get("/account")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response addMovieToFavorites(ResponseSpecification responseSpec, int accountId) {
        requestBodyFavorites.put("media_type", "movie");
        requestBodyFavorites.put("media_id", "5e959bc3db72c00014ad69d6");
        requestBodyFavorites.put("favorite", true);

        return given()
                .spec(getRequestSpec())
                .body(requestBodyFavorites)
                .when()
                .post("/account/" + accountId + "/favorite")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response addMovieToWatchlist(ResponseSpecification responseSpec, int accountId) {

        requestBodyWatchList.put("media_type", "movie");
        requestBodyWatchList.put("media_id", 11);
        requestBodyWatchList.put("watchlist", true);

        return given().
                spec(getRequestSpec())
                .body(requestBodyWatchList)
                .when()
                .post("/account/" + accountId + "/watchlist")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public static void getMovieFavoriteMovies(ResponseSpecification responceSpec, int accountId) {

        List<String> titleList =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .get("/account/" + accountId + "/favorite/movies")
                        .then()
                        .spec(responceSpec)
                        .extract().path("results.original_title");
        Assert.assertTrue(titleList.contains("Kung Fu Panda 4"));

    }

    public static void getRatedMovies(ResponseSpecification responseSpec, int accountId) {

        List<String> titles =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .get("/account/" + accountId + "/rated/movies")
                        .then()
                        .spec(responseSpec)
                        .extract().path("results.original_title");

        Assert.assertTrue(titles.contains("Kingdom of the Planet of the Apes"));
    }

    public static void getWathclistMovies(ResponseSpecification responseSpec, int accountId) {

        List<String> titles =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .get("/account/" + accountId + "/watchlist/movies")
                        .then()
                        .spec(responseSpec)
                        .extract().path("results.original_title");

        Assert.assertTrue(titles.contains("Interstellar"));

        for (String t : titles) {
            System.out.println(t);
        }


    }


}
