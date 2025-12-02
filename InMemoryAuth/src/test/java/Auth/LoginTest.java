package Auth;
import Base.BaseTest;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class LoginTest extends BaseTest {
    @Test(description = "Verify Login with valid credentials")
    public void verifyLogin(){
    given().header("Content-Type","application/json")
                .body("{\n" +
                              "  \"username\": \"string\",\n" +
                              "  \"password\": \"string\"\n" +
                              "}")
                .when()
                .post("/api/Auth/login")
                .then()
                .statusCode(200);
}
@Test(description = "Verify Login with invalid credentials")
    public void VerifyLoginInvalid(){
        given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"username\": \"string1\",\n" +
                        "  \"password\": \"sdaasddas\"\n" +
                        "}")
                .when()
                .post("/api/Auth/login")
                .then()
                .statusCode(401).body(equalTo("Invalid credentials."));
    }
}