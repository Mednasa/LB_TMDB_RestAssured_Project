package service;

import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import spec.RequestSpec;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GenreService extends RequestSpec {

    public void GetMovieGenres(ResponseSpecification responseSpec) {

        List<String> names =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .get("/genre/movie/list")
                        .then()
                        .spec(responseSpec)
                        .extract().path("genres.name");

        Assert.assertTrue(names.contains("Science Fiction"));

        for (String n : names) {
            System.out.println(n);
        }
    }
}
