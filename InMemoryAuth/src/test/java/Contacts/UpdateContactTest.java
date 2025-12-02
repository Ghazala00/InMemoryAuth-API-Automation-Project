package Contacts;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import Base.BaseTest;

public class UpdateContactTest extends  BaseTest {
    @Test(description = "Verify Updating an existing contact")
    public void UpdateExistedContact() {
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{ \"id\": \"cfa33a9a-681f-435c-9d21-290d108862cc\",\n" +
                        "    \"firstName\": \"seif\",\n" +
                        "    \"lastName\": \"marwan\",\n" +
                        "    \"email\": \"Seif1wmarw@example.com\",\n" +
                        "    \"phone\": \"0111234867\",\n" +
                        "    \"balance\": 10000,\n" +
                        "    \"username\": \"string\"}")
                .when()
                .put("/api/Contacts/cfa33a9a-681f-435c-9d21-290d108862cc" )
                .then()
                .statusCode(200);
    }
    @Test(description = "Verify updating contact with invalid ID")
    public void UpdateContactInvalidID() {
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{\"id\": \"cfa33a9a-681f-435c-9d21-290d108862c2\",\n" +
                        "    \"firstName\": \"seif\",\n" +
                        "    \"lastName\": \"marwan\",\n" +
                        "    \"email\": \"Seif1wmarw@example.com\",\n" +
                        "    \"phone\": \"0111234867\",\n" +
                        "    \"balance\": 500,\n" +
                        "    \"username\": \"string\"\n}")
                .when()
                .put("/api/Contacts/cfa33a9a-681f-435c-9d21-290d108862c2" )
                .then()
                .statusCode(404).body(equalTo("Contact with ID cfa33a9a-681f-435c-9d21-290d108862c2 not found."));
}
@Test(description = "Verify Updating Contact Balance by Contact ID")
    public void UpdateContactBalance() {
        given()
                .queryParam("id", "33df2352-a0e0-4d3c-bc50-670be7731baa")
                .queryParam("amount", "2000")
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("api/Contacts/tipup?id=33df2352-a0e0-4d3c-bc50-670be7731baa&amount=2000" )
                .then()
                .statusCode(200);
    }

}

