package Contacts;
import Base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class CreateContactTest extends BaseTest {
    @Test(description = "Verfiy creating new contact")
    public void CreateContact() {
        String ID = UUID.randomUUID().toString();
        String email = ID + "@mail.com";
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{\n" +
                        "  \"id\": \"" + ID + "\",\n" +
                        "  \"firstName\": \"SEQ\",\n" +
                        "  \"lastName\": \"marwan\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"phone\": \"0111234867\",\n" +
                        "  \"balance\": 10,\n" +
                        "  \"username\": \"string\"\n" +
                        "}")
                .when()
                .post("/api/Contacts")
                .then()
                .statusCode(200);
    }

    @Test(description = "Verfiy creating a contact that already exists with same Email")
    public void CreateExistedContact() {
         Faker faker = new Faker();
         String ID = faker.internet().uuid();
//        String ID = UUID.randomUUID().toString();
//        String email = ID + "@mail.com";
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                //header("Authorization","Bearer "+token)
                .body("{\n" +
                        "  \"id\": \"" + ID + "\",\n" +
                        "  \"firstName\": \"SEQ\",\n" +
                        "  \"lastName\": \"marwan\",\n" +
                        "  \"email\": \"ahmed@example.com\",\n" +
                        "  \"phone\": \"0111234867\",\n" +
                        "  \"balance\": 10,\n" +
                        "  \"username\": \"string\"\n" +
                        "}")
                .when()
                .post("/api/Contacts")
                .then()
                .statusCode(400).body(equalTo("A contact with the same email already exists."));
    }

    @Test(description = "Verfiy creating a contact with invalid Email format")
    public void CreateContactInvalidEmail() {
        String ID = UUID.randomUUID().toString();
        String email = ID + "@mail.com";
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{\n" +
                        "  \"id\": \"" + ID + "\",\n" +
                        "  \"firstName\": \"SEQ\",\n" +
                        "  \"lastName\": \"marwan\",\n" +
                        "  \"email\": \"ahmed$ple.com\",\n" +
                        "  \"phone\": \"0111234867\",\n" +
                        "  \"balance\": 10,\n" +
                        "  \"username\": \"string\"\n" +
                        "}")
                .when()
                .post("/api/Contacts")
                .then()
                .statusCode(400).body("errors.Email[0]", equalTo("Invalid Email Address."));
    }

    @Test(description = "Verfiy creating a contact with missing required field FirstName")
    public void CreateContactMissingFirstName() {
        String ID = UUID.randomUUID().toString();
        String email = ID + "@mail.com";
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{\n" +
                        "  \"id\": \"" + ID + "\",\n" +
                        "  \"lastName\": \"marwan\",\n" +
                        "  \"email\": \"ahmed$ple.com\",\n" +
                        "  \"phone\": \"0111234867\",\n" +
                        "  \"balance\": 10,\n" +
                        "  \"username\": \"string\"\n" +
                        "}")
                .when()
                .post("/api/Contacts")
                .then()
                .statusCode(400).body("errors.FirstName[0]", equalTo("The FirstName field is required."));
    }

    @Test(description = "Verify creating contact without token")
    public void CreateContactWithoutToken() {
        String ID = UUID.randomUUID().toString();
        String email = ID + "@mail.com";
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": \"" + ID + "\",\n" +
                        "  \"firstName\": \"SEQ\",\n" +
                        "  \"lastName\": \"marwan\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"phone\": \"0111234867\",\n" +
                        "  \"balance\": 10,\n" +
                        "  \"username\": \"string\"\n" +
                        "}")
                .when()
                .post("/api/Contacts")
                .then()
                .statusCode(401);
    }
}
