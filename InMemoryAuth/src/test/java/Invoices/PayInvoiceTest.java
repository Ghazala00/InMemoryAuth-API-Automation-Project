package Invoices;
import Base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PayInvoiceTest extends  BaseTest {
    @Test (description = "Verify Paying an invoice amount")
    public void PayInvoiceAmount(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{ \"invoiceId\": \""+InvoiceID+"\",\n" +
                        "        \"amount\": 0}")
                .post("/api/Invoices/pay")
                .then()
                .statusCode(200).body( equalTo("Invoice paid successfully."));
    }
    @Test(description = "Verify paying already paid invoice")
    public void PaidInvoice(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body("{ \"invoiceId\": \"8f72beff-1d68-4447-a088-faa8cf771940\",\n" +
                        "        \"amount\": 0}")
                .post("/api/Invoices/pay")
                .then()
                .statusCode(400).body( equalTo("Invoice is already paid."));
    }

}
