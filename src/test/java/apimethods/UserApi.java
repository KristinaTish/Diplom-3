package apimethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {

    static final String STELLAR_URL = "https://stellarburgers.nomoreparties.site/";
    static final String PATH_CREATE_USER = "api/auth/register";
    static final String PATH_LOGIN_USER = "api/auth/login";
    static final String PATH_DELETE_CHANGE_USER = "api/auth/user";

    public static Response loginUser(UserLogin user2) {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user2)
                .when()
                .post(PATH_LOGIN_USER);
        return response;
    }

    public static Response createUser(UserReg user) {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(PATH_CREATE_USER);
        return response;
    }

    public static void deleteUser(String token) {
        Response response = given()
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(PATH_DELETE_CHANGE_USER);
    }

    public static String getToken(Response response) {
        if (response.getStatusCode() == 200) {
            String accessToken = response.jsonPath().getString("accessToken");
            System.out.println("Extracted Access Token: " + accessToken);
            return accessToken;
        } else {
            throw new RuntimeException("Failed to create user: " + response.getStatusLine());
        }
    }
}

