package tmdb_tests;

import base.BaseServiceTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.MovieService;
import spec.ResponseSpec;

public class MovieTest extends BaseServiceTest {

    int movie_id = 0;

    @Test
    public void GetMovieDetails() {

        Response response = movieService.getMovieDetails(ResponseSpec.getResponseSpec(200));
        movie_id = response.body().path("id");
    }

    @Test(dependsOnMethods = "GetMovieDetails")
    public void AddRating() {
        Response response = movieService.addRating(ResponseSpec.getResponseSpec(201), movie_id);
        Assert.assertTrue(response.path("status_message").toString().contains("success"));
    }

    @Test(dependsOnMethods = "GetMovieDetails")
    public void DeleteRating() {
        Response response = movieService.deleteRating(ResponseSpec.getResponseSpec(200), movie_id);
        Assert.assertTrue(response.path("status_message").toString().contains("deleted success"));
    }

    @Test
    public void UnauthorizedAccess() {
        MovieService.unauthorizedAccess(ResponseSpec.getResponseSpec(401), 8299816);
    }


}
