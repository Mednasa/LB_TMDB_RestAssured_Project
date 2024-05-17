package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class RequestSpec {

    private static final String baseUri = "https://api.themoviedb.org/3";

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjOGUzMGI0NDk2MDAyNTkwODhlZTQyZjIzODJmMjZiNyIsInN1YiI6IjY2Mzc4NTcxYzYxNmFjMDEyNTFiMGZiZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QMrlLZ3t9h7eDbEvEpGfcc-fKgbjQvH95Xf42Umss0U")
                .setContentType(ContentType.JSON)
                .build();


    }
}
