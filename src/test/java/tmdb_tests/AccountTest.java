package tmdb_tests;

import base.BaseServiceTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import service.AccountService;
import spec.ResponseSpec;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class AccountTest extends BaseServiceTest {

    int account_id = 0;

    @Test
    public void GetAccountDetails() {

        Response response = accountService.getAccountDetails(ResponseSpec.getResponseSpec(200));
        account_id = response.body().path("id");
    }

    @Test(dependsOnMethods = "GetAccountDetails")
    public void AddMovieToFavorites() {
        Response response = accountService.addMovieToFavorites(ResponseSpec.getResponseSpec(201), account_id);
        assertThat(response.path("status_message").toString().toLowerCase(), is(containsString("success")));

    }

    @Test(dependsOnMethods = "GetAccountDetails")
    public void AddMovieToWatchlist() {
        Response response = accountService.addMovieToWatchlist(ResponseSpec.getResponseSpec(201), account_id);
        assertThat(response.path("status_message").toString().toLowerCase(), is(containsString("success")));

    }

    @Test(dependsOnMethods = "GetAccountDetails")
    public void GetFavoriteMovies() {
        AccountService.getMovieFavoriteMovies(ResponseSpec.getResponseSpec(200), account_id);
    }

    @Test(dependsOnMethods = "GetAccountDetails")
    public void GetRatedMovies() {
        AccountService.getRatedMovies(ResponseSpec.getResponseSpec(200), account_id);
    }

    @Test(dependsOnMethods = "GetAccountDetails")
    public void GetWatchlistMovies() {
        AccountService.getWathclistMovies(ResponseSpec.getResponseSpec(200), account_id);
    }

}
