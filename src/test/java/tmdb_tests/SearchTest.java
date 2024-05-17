package tmdb_tests;

import base.BaseServiceTest;
import org.testng.annotations.Test;
import spec.ResponseSpec;

public class SearchTest extends BaseServiceTest {


    @Test
    public void SearchForMovies() {
        searchService.searchForMovies(ResponseSpec.getResponseSpec(200), "Harry Potter");
    }

    @Test
    public void SearchForKeywords() {
        searchService.searchForKeywords(ResponseSpec.getResponseSpec(200), "Arrival");
    }


}
