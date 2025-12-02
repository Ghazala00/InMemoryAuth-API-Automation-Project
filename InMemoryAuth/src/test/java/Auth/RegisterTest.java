package Auth;
import Base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class RegisterTest extends BaseTest {

    @Test(description ="varify Registration with valid data" )
    public void VerifyRegister(){
       String username = generateUsername();
        given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"username\": \""+username+"\",\n" +
                        "  \"password\": \"passworddoesn'tmatter\"\n" +
                        "}")
                .when()
                .post("/api/Auth/register")
                .then()
                .statusCode(200).body( equalTo("User registered successfully."));
    }
    @Test(description = "verify Registration with existing username" )
    public void VerifyRegisterExistsUserName(){
        given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"username\": \"string1\",\n" +
                        "  \"password\": \"string1\"\n" +
                        "}")
                .when()
                .post("/api/Auth/register")
                .then()
                .statusCode(400).body(equalTo("Username already exists."));
    }
}