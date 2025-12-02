package Base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import java.util.UUID;
import  static io.restassured.RestAssured.given;
public class BaseTest {
    public static String token;
    public static String InvoiceID;
    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://qa-assignment.sortcrm.com";
        loginAndGetToken();
        InvoiceID();
    }
    public void loginAndGetToken() {
        token = given()
                .header("Content-Type","application/json")
                .body("{\"username\":\"string\",\"password\":\"string\"}")
                .post("/api/Auth/login")
                .then().extract().path("token");
    }
    public void InvoiceID(){
        InvoiceID= UUID.randomUUID().toString();
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{\"id\": \"" + InvoiceID + "\",\n" +
                        "  \"contactId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
                        "  \"createdBy\": \"string\",\n" +
                        "  \"cost\": 0,\n" +
                        "  \"discountPercentage\": 100,\n" +
                        "  \"taxAmount\": 1,\n" +
                        "  \"totalAmount\": 0,\n" +
                        "  \"totalProfit\": 0,\n" +
                        "  \"status\": 0,\n" +
                        "  \"createdDate\": \"2025-11-30T23:07:19.451Z\",\n" +
                        "  \"username\": \"string\"}")
                .post("/api/Invoices")
                .then()
                .statusCode(201);
    }
    public String generateUsername(){
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }
}