package Invoices;
import Base.BaseTest;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateInvoiceTest extends BaseTest {
    @Test(description = "Verify Creating a new invoice of a spicific Contact ID")
    public void createInvoice(){
        String ID = UUID.randomUUID().toString();
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{\"id\": \"" + ID + "\",\n" +
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
                .when()
                .post("/api/Invoices")
                .then()
                .statusCode(200);

    }
    @Test(description = "Verify creating invoice with invalid contact ID")
    public void createInvoiceInvalidContactID(){
        String ID = UUID.randomUUID().toString();
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{\"id\": \"" + ID + "\",\n" +
                        "  \"contactId\": \"3fa85f64-5717-4562-b3fc-2c963f66afaa\",\n" +
                        "  \"createdBy\": \"string\",\n" +
                        "  \"cost\": 0,\n" +
                        "  \"discountPercentage\": 100,\n" +
                        "  \"taxAmount\": 1,\n" +
                        "  \"totalAmount\": 0,\n" +
                        "  \"totalProfit\": 0,\n" +
                        "  \"status\": 0,\n" +
                        "  \"createdDate\": \"2025-11-30T23:07:19.451Z\",\n" +
                        "  \"username\": \"string\"}")
                .when()
                .post("/api/Invoices")
                .then()
                .statusCode(404).body(equalTo("Contact with ID 3fa85f64-5717-4562-b3fc-2c963f66afaa not found."));
    }
    @Test(description = "Verify creating invoice with negative tax amount")
    public void createInvoiceNegativeTaxAmount(){
        String ID = UUID.randomUUID().toString();
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{\"id\": \"" + ID + "\",\n" +
                        "  \"contactId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
                        "  \"createdBy\": \"string\",\n" +
                        "  \"cost\": 0,\n" +
                        "  \"discountPercentage\": 100,\n" +
                        "  \"taxAmount\": -1,\n" +
                        "  \"totalAmount\": 0,\n" +
                        "  \"totalProfit\": 0,\n" +
                        "  \"status\": 0,\n" +
                        "  \"createdDate\": \"2025-11-30T23:07:19.451Z\",\n" +
                        "  \"username\": \"string\"}")
                .when()
                .post("/api/Invoices")
                .then()
                .statusCode(400).body("errors.TaxAmount[0]", equalTo("Tax amount must be a positive number."));

    }
}
