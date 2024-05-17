package tmdb_tests;

import base.BaseServiceTest;
import org.testng.annotations.Test;
import spec.ResponseSpec;

public class GenreTest extends BaseServiceTest {

    @Test
    public void GetMovieGenres() {

        genreService.GetMovieGenres(ResponseSpec.getResponseSpec(200));
    }


}
