package api.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static io.restassured.RestAssured.given;

public class APIUtils {

    private static final Logger logger = LogManager.getLogger(APIUtils.class);

    // Common request specification
    private static RequestSpecification getRequestSpec() {
        return given()
                .baseUri(ConfigManager.getProperty("baseUrl"))
                .header("Content-Type", "application/json")
                .log().all();  // Log all request details
    }

    /**
     * Performs GET request
     * @param endpoint API endpoint
     * @return Response object
     */
    public static Response getRequest(String endpoint) {
        logger.info("Sending GET request to: {}", endpoint);
        Response response = getRequestSpec()
                .when()
                .get(endpoint)
                .then()
                .log().all()  // Log all response details
                .extract().response();
        
        logResponseDetails(response);
        return response;
    }

    /**
     * Performs POST request
     * @param endpoint API endpoint
     * @param payload Request payload
     * @return Response object
     */
    public static Response postRequest(String endpoint, Object payload) {
        logger.info("Sending POST request to: {} with payload: {}", endpoint, payload);
        Response response = getRequestSpec()
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
        
        logResponseDetails(response);
        return response;
    }

    /**
     * Performs PUT request
     * @param endpoint API endpoint
     * @param payload Request payload
     * @return Response object
     */
    public static Response putRequest(String endpoint, Object payload) {
        logger.info("Sending PUT request to: {} with payload: {}", endpoint, payload);
        Response response = getRequestSpec()
                .body(payload)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();
        
        logResponseDetails(response);
        return response;
    }

    /**
     * Performs DELETE request
     * @param endpoint API endpoint
     * @return Response object
     */
    public static Response deleteRequest(String endpoint) {
        logger.info("Sending DELETE request to: {}", endpoint);
        Response response = getRequestSpec()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
        
        logResponseDetails(response);
        return response;
    }

    /**
     * Logs important response details
     * @param response API response
     */
    private static void logResponseDetails(Response response) {
        logger.info("Response Status: {}", response.getStatusCode());
        logger.info("Response Time: {} ms", response.getTime());
        logger.debug("Response Body: {}", response.getBody().asString());
    }

    /**
     * Adds authentication header to requests
     * @param token Authentication token
     * @return RequestSpecification with auth header
     */
    public static RequestSpecification withAuth(String token) {
        return getRequestSpec().header("Authorization", "Bearer " + token);
    }
}