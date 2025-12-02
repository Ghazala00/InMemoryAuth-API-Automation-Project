package Contacts;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import Base.BaseTest;
public class DeleteContactTest extends  BaseTest {
    @Test(description = "Verify Deleting an existing contact and ensuring that the contact got deleted")
    public void DeleteContact() {
      given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("/api/Contacts/cfa33a9a-681f-435c-9d21-290d108862cc" )
                .prettyPeek()
                .then()
                .statusCode(204);
       given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("/api/Contacts/cfa33a9a-681f-435c-9d21-290d108862cc" )
                .then()
                .statusCode(404);

    }
    @Test(description = "Verify deleting contact without token")
    public  void DeleteContactWithoutToken(){
        given()
                .contentType("application/json")
                .when()
                .get("/api/Contacts/cfa33a9a-681f-435c-9d21-290d108862cc" )
                .then()
                .statusCode(401);
    }
}
