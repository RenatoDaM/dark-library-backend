package com.dark.library.darklibrary.integrationtest.Controller.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.dark.library.darklibrary.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUiPage() {
        String content =
                // Imported static method given from RestAssured
                // Same result: RestAssured.given
                given()
                        .basePath("/swagger-ui/index.html")
                        // Just using the port constant. We get the same result if we just say 8888.
                        .port(TestConfig.SERVER_PORT)
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString();

        assertTrue(content.contains("swagger-ui"));
    }

}
