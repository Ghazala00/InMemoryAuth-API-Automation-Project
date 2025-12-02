package Contacts;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import Base.BaseTest;

public class GetContactsTest extends BaseTest{
    @Test(description = "Verify Retrieving all contacts with optional pagination and search")
    public void RetrieveAllContacts() {
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("/api/Contacts?pageNumber=1&pageSize=10&searchTerm=SEQ")
                .then()
                .statusCode(200);
    }
    @Test(description = "Verify getting contacts with invalid page number")
    public void RetrieveContactsInvalidPageNumber() {
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("/api/Contacts?pageNumber=-1&pageSize=10&searchTerm=SEQ")
                .then()
                .statusCode(400).body(equalTo("Page number and page size must be greater than zero."));
    }
    @Test(description = "Verify Retrieving a specific contact by ID.")
    public void RetrieveContactByID() {
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("/api/Contacts/3fa85f64-5717-4562-b3fc-2c963f66afa6" )
                .then()
                .statusCode(200);
    }
    @Test(description = "Verify getting contact with wrong ID")
    public void RetrieveContactByInvalidID() {
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("/api/Contacts/756b02fd-a1c2-46de-96e4-53454c8f80ca" )
                .then()
                .statusCode(404).body(equalTo("Contact with ID 756b02fd-a1c2-46de-96e4-53454c8f80ca not found."));
    }
}
