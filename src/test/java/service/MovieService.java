package service;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import spec.RequestSpec;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MovieService extends RequestSpec {

    Map<String, Object> RatingCredential = new HashMap<>();
    static Map<String, Integer> credentials = new HashMap<>();

    public Response getMovieDetails(ResponseSpecification responseSpec) {

        return given()
                .spec(getRequestSpec())
                .when()
                .get("/movie/693134")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response addRating(ResponseSpecification responseSpec, int movieId) {

        RatingCredential = new HashMap<>();
        RatingCredential.put("value", 9);

        return given()
                .spec(getRequestSpec())
                .body(RatingCredential)
                .when()
                .post("/movie/" + movieId + "/rating")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response deleteRating(ResponseSpecification responseSpec, int movieId) {

        return given()
                .spec(getRequestSpec())
                .when()
                .delete("/movie/" + movieId + "/rating")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public static void unauthorizedAccess(ResponseSpecification responseSpec, int listId) {

        credentials = new HashMap<>();
        credentials.put("media_id", 755450);

        String statusMessage =
                given()
                        .queryParam("session_id", "4534")
                        .spec(getRequestSpec())
                        .pathParam("list_id", listId)
                        .body(credentials)
                        .when()
                        .post("/list/{list_id}/add_item")
                        .then()
                        .log().body()
                        .spec(responseSpec)
                        .extract().path("status_message");

        Assert.assertTrue(statusMessage.contains("You do not have permissions to access the service."));
    }
}
