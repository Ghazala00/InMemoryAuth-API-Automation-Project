package Invoices;
import Base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReturnInvoiceTest extends BaseTest {
    @Test(description = "Verify retrieving a list of all invoices")
    public void RetrieveAllInvoices(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("/api/Invoices")
                .then()
                .statusCode(200);
    }
    @Test(description = "Verify Retrieving a specific invoice by ID")
    public void RetrieveInvoiceByID(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("/api/Invoices/3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .then()
                .statusCode(200);
    }
    @Test(description = "Verify Marking an existing invoice as returned")
    public void ReturnInvoice(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("/api/Invoices/"+InvoiceID)
                .then()
                .statusCode(200).body( equalTo("Invoice returned successfully."));
    }

}
