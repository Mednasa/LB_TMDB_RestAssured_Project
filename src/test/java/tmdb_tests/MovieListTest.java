package tmdb_tests;

import base.BaseServiceTest;
import org.testng.annotations.Test;
import spec.ResponseSpec;

public class MovieListTest extends BaseServiceTest {

    @Test
    public void GetNowPlayingMovies() {
        movieListService.getNowPlayingMovies(ResponseSpec.getResponseSpec(200));
    }

    @Test
    public void GetPopularMovies() {
        movieListService.getPopularMovies(ResponseSpec.getResponseSpec(200));
    }

    @Test
    public void GetTopRatedMovies() {
        movieListService.getTopRatedMovies(ResponseSpec.getResponseSpec(200));
    }

    @Test
    public void GetUpcomingMovies() {
        movieListService.getUpcomingMovies(ResponseSpec.getResponseSpec(200));
    }


}
