package com.ocphase1.productorder;


import static org.hamcrest.Matchers.containsString;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URISyntaxException;

import java.util.Properties;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


//import framework.rest_assured_helper.Get_Element_In_Response;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;





public class ProductOrder extends Base{

	
	// Story-CROSS-3456


//@Test(priority=1)

 public void TC001_CreateEmptyOrder (ITestContext context) throws URISyntaxException{
	 
	System.out.println("This is TC001_CreateEmptyOrder-----------");
	
	String paylaod="{}";	
	
	String URI="http://10.204.8.49:5292/shoppingCart/v1/shoppingCart";
 String auth=Bearer eyJraWQiOiJ5eGZ5Y3V3MFovUDM5N0dydGRpaDdaa1dRTTA9IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZ3dBcHAiLCJyb2xlcyI6IkFzbVNlcnZpY2VzSXNzdWVUb2tlblJvbGU7QXNtU2VydmljZXNBcHBJZGVudGl0eTtBc21TZXJ2aWNlc1ZhbGlkYXRlVG9rZW5Sb2xlO0FzbVNlcnZpY2VzUmVuZXdUb2tlblJvbGUiLCJpc3MiOiJAQTNTLmFtZG9jcy5jb20iLCJvaXQiOiIxNDkyMDY5OTUxOTA4IiwiZXhwIjoiMTQ5MjA3MzU1MTkwOCIsImlhdCI6IjE0OTIwNjk5NTE5MDgiLCJqdGkiOiIzZmM5Mzk5NWRjZWZmOWRjNTFhMzkxZDE1NTdiY2FiMzAyNDEyMGIxMmRiMzExODM1M2Q5NzNkNjRiY2NjMDYzIn0.MYBdnlXR8fmMdjuNHzOzHwvk5PYRAAbwJEIpe6Z-jNso0ssoeYknkdhmCPmFIjDBtAHnnb-8yNKSAT39pT9Gr9ymu5Ve0F5nX8IjS49jiS6pTO5GmkJaou4REETQE25UyWA4p8c26z3Ilvo8HwIFg79_XTyf3YoOv1PUpCxf-fT0tqmdGmSuQg7qMxg9Q8UgJ2QgeXYKAm0Dx-k_Ict7dxiNuQC_oSsEzktRA2ohXvUduMWP9QZk3JRxDa-lbJwbzym1yxZIaolYkusPJuPUYRHhf18hamGCDdiZs_bJGdmMeiPChdZrnHvJfaDuaBCzbHlmycCgmE8iohcBKFLvPw"
	Response response	=given().body(Payload_OnePlan.toString()).with().contentType("application/json")
			.and().given().header("Authorization", auth)
			.then().expect().statusCode(201).log().all().and().when().post(URI);
	
	 

}
	 
@Test(priority=2)

public void TC002_create_order_throws_400_BadRequest () throws URISyntaxException{
	 
	System.out.println("This is TC002_create_order_throws_400_BadRequest-----------");
	
	
	String malformedPayload="";

	Response response	=given().body(malformedPayload).with().contentType("application/json").then().expect().statusCode(400)
			.log().all().when().post(prop.getProperty("URI"));
	
		
	

}
	
//Story-CROOS-2907

//@Test(priority=3)

public void TC001_CreateProductorder_OnlyOneOffer (ITestContext context) throws URISyntaxException{
	 
	System.out.println("This is TC001_CreateProductorder_OnlyOneOffer-----------");
	
	
	

	Response response	=given().body(prop.getProperty("OC_Phase1_OneOfferPayload")).with().contentType("application/json").then()
	.expect().statusCode(201).log().all().and().body(containsString(prop.getProperty("State"))).when().post(prop.getProperty("URI"));
	
		
	String ProductOrderID_onePlan=varifyRes.Return_Element_In_Response_(response, "id");
	
	System.out.println("this is orderDi for one plan"+ProductOrderID_onePlan);
	
	context.setAttribute("ProductOrder_ID_onePlan",  ProductOrderID_onePlan);
	
	Assert.assertNotNull(ProductOrderID_onePlan);
	
	String OrderItemID = varifyRes.Return_Element_In_Response_(response, "orderItems.id");
	 
	
	
	Assert.assertNotNull(OrderItemID);
	
	 
}

//@Test(priority=4)

public void TC006_create_order_throws_400_BadRequest () throws URISyntaxException{
	 
	System.out.println("This is TC006_create_order_throws_400_BadRequest-----------");
	
	
	

	Response response	=given().body(prop.getProperty("OC_Phase1_malformedPayload")).with()
.contentType("application/json").then().expect().statusCode(400).log().all().when().post(prop.getProperty("URI"));
	
		
	

}

//@Test(priority=5)

public void TC002_create_order_with_Input_Dates () throws URISyntaxException{
	 
	System.out.println("This is --TC002_create_order_with_Input_Dates-----------");
	
	
	

	Response response	=given().body(prop.getProperty("OC_Phase1_inputs_DatePayload")).with()
	.contentType("application/json").then().expect().statusCode(201).log().all().and().body(containsString(prop.getProperty("State")))
	.when().post(prop.getProperty("URI"));
	
		
	String ProductOrderID=varifyRes.Return_Element_In_Response_(response, "id");
	
	Assert.assertNotNull(ProductOrderID);
	
	String orderDate = varifyRes.Return_Element_In_Response_(response, "orderDate");
	
	String requestedStartDate =varifyRes.Return_Element_In_Response_(response, "requestedStartDate");
	
	String requestedCompletionDate =varifyRes.Return_Element_In_Response_(response, "requestedCompletionDate");
	
	
	String payload_date=prop.getProperty("OC_Phase1_inputs_DatePayload");
	
	//orderDate should be generated by ms
	
	Assert.assertFalse(payload_date.contains(orderDate));
	
	
	
	
	//requestedStartDate  should be honored
	
	
	
	Assert.assertTrue(payload_date.contains(requestedStartDate));
	
	
	//requestedCompletionDate should be honored
	 
	Assert.assertTrue(payload_date.contains(requestedCompletionDate));
	
	

}

//@Test(priority=6)

public void TC003_Create_Order_with_Input_ID () throws URISyntaxException{
	 
	System.out.println("TC003_Create_Order_with_Input_ID----------");
	
	
	

	Response response	=given().body(prop.getProperty("OC_Phase1_input_IDPayload")).with()
			.contentType("application/json").then().expect().statusCode(201).log().all().and()
			.body(containsString(prop.getProperty("State"))).when().post(prop.getProperty("URI"));
	
		
	String ProductOrderID=varifyRes.Return_Element_In_Response_(response, "id");
	
	String payload_inputID=prop.getProperty("OC_Phase1_input_IDPayload");
	
	Assert.assertFalse(payload_inputID.contains(ProductOrderID));
	
	
	
	 
}
//@Test(priority=7)

public void TC004_Create_order_with_Input_price () throws URISyntaxException{
	 
	System.out.println("TC004_Create_order_with_Input_price----------");
	
	
	

	Response response	=given().body(prop.getProperty("OC_Phase1_input_pricePayload")).with().contentType("application/json").then().expect()
			.statusCode(201).log().all().and().body(containsString(prop.getProperty("State"))).when().post(prop.getProperty("URI"));
	
		
	
	
	String OrderItem=varifyRes.Return_Element_In_Response_(response, "orderItems");
	
	
	
	
	
	 Assert.assertFalse(OrderItem.contains("3000"));
}

//@Test(priority=8)

public void TC007_CreateProductorder_relatedpartyID() throws URISyntaxException{
	 
	System.out.println("TC007_CreateProductorder_relatedpartyID----------");
	
	
	

	Response response	=given().body(prop.getProperty("OC_Phase1_input_relatedPartyID")).with().contentType("application/json")
			.then().expect().statusCode(201).log().all().and().body(containsString(prop.getProperty("State"))).when().post(prop.getProperty("URI"));
	
		
	
	
	String herf=varifyRes.Return_Element_In_Response_(response, "relatedParties.href");
	
	
	
	System.out.println("this is response--"+herf);
	
	Assert.assertNotNull(herf);
	 
}


// story-CROSS-2888

	


//@Test(priority = 8)
public void TC001_Retrieve_OnePlan(ITestContext context) {
	
	System.out.println("TC001_Retrieve_OnePlan----------");
	
	String URI=prop.getProperty("URI");
	
	String url=URI+"/"+context.getAttribute("ProductOrder_ID_onePlan").toString();
	
	System.out.println("this is one plan order------"+url);

	  given().when().get(url).then().body(containsString(prop.getProperty("State"))).statusCode(200).log().all();

	  Response response= given().accept(ContentType.JSON).and().given().config(config.sslConfig(new SSLConfig().relaxedHTTPSValidation())).get(url);

	  
	  String OrderItemID = varifyRes.Return_Element_In_Response_(response, "orderItems.id");
		 
	 

Assert.assertNotNull(OrderItemID);

}

@Test(priority = 9)
public void TC002_RetrieveProductOrderDetail_InvalidID() {
	
	System.out.println("TTC002_RetrieveProductOrderDetail_InvalidID----------");
	
	String URI=prop.getProperty("URI");
	
	String url=URI+"/ProductOrder_AAA";
	
	given().when().get(url).then().body(containsString(prop.getProperty("getProductOrder_notFound"))).statusCode(404).log().all();
	
	

	

}
//@Test(priority = 10)
public void TC003_Retrieve_EmptyOrder(ITestContext context) {
	
	System.out.println("TC004_Retrieve_EmptyOrder----------");
	
	String URI=prop.getProperty("URI");
	
	String url=URI+"/"+context.getAttribute("ProductOrder_ID").toString();
	
	 given().when().get(url).then().body(containsString(prop.getProperty("State"))).statusCode(200).log().all();

	

}


}