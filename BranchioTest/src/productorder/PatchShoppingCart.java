package shoppingcart;

import static framework.rest_assured_helper.Response_Validation.returnElementInResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static framework.rest_assured_helper.TestPatchMethod.testPatchMethod;
import static io.restassured.RestAssured.given;
import static shoppingcart.ShoppingCartPayloadGenerator.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PatchShoppingCart {
	Properties prop;
	FileInputStream fis;
	
	

	/*
	 * This method will load the properties file
	 */

	@BeforeClass
	public void setup() throws IOException {
		System.out.println("this is before methodShopppingCart");
		File file = new File(
				"..\\order_capture_test\\src\\test\\resources\\propertyFiles\\PatchShoppingCart.properties");
		fis = new FileInputStream(file);
		prop = new Properties();
		prop.load(fis);
	}

	/*
	 * Cleanup environment
	 */
	@AfterClass
	public void clean() throws IOException {
		fis.close();
	}

@Test
public void tC01_add_relatedParty_emptyCart(ITestContext context) throws URISyntaxException, IOException {
	
//String cartID=returnElementInResponse((Response) context.getAttribute("ShoppingCartID_Empty"), "id"); 
//System.out.println("This is emptyShoppingcartID=========="+cartID);

//RestAssured.baseURI = prop.getProperty("baseURI");

//String URI = RestAssured.baseURI + "/shoppingCart/v1/shoppingCart/" + cartID;

JSONObject value = createrelPartyObj("123","james", "customer");
JSONArray patch_array = createPayload_patch_add("/relatedParty",  value  );

String payload = patch_array.toString();

System.out.println("This is paylaod for patch op========: "+payload);



//Response response	=given().body(payload).with().contentType("application/json")
//.then().expect().statusCode(200).log().all().and().when().patch(prop.getProperty("ShoppingCartBaseURI"));



}



}
