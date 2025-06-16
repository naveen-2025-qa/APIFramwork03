package api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.utilities.ConfigManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HeaderValidationTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.getProperty("baseUrl"); // Replace with your API base URL
    }

    @Test
    public void validateResponseHeaders() {
        Response response = given()
                .when()
                .get("/objects")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        // Checking specific headers
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
        Assert.assertEquals(response.getHeader("Server"), "cloudflare");

        // Using Hamcrest matchers for validation
        response.then().header("Content-Type", containsString("json"));
                 
    }

    @Test
    public void validateAuthorizationHeader() {
        Response response = given()
                .header("Authorization", "Bearer sample_token") 
                .when()
                .get("/users/2")
                .then()
                .extract().response();

        // Ensure Authorization Header is passed
        Assert.assertTrue(response.getHeaders().hasHeaderWithName("Authorization"));
    }
}