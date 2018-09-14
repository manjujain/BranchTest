package com.ocphase1.productorder;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import framework.rest_assured_helper.Get_Element_In_Response;



public class Base {

	Properties prop;
	FileInputStream fis;
	
	Get_Element_In_Response varifyRes = new Get_Element_In_Response();
	
	@BeforeClass
	public  void setup() throws IOException {
		

		System.out.println("this is before method");
		File file = new File("C:\\Users\\manjub\\Git\\order_capture_test\\src_ct\\com\\ocphase1\\productorder\\ocphase1.properties");
		fis = new FileInputStream(file);
	 prop= new Properties();

	prop.load(fis);
		
		
		String port = System.getProperty("server.port");
	    if (port == null) {
	       io.restassured.RestAssured.port = Integer.valueOf(8088);
	    
	      
	    }
	    	 
	    else{
	        io.restassured.RestAssured.port = Integer.valueOf(port);
	    }

	

	String baseHost = System.getProperty("server.host");
	System.out.println("baseHost1 = " + baseHost);
    if(baseHost==null){
        baseHost = prop.getProperty("Mshost");
        System.out.println("baseHost2 = " + baseHost);
    }
    io.restassured.RestAssured.baseURI = baseHost;

}

	@AfterClass
	public void cleanup() throws IOException{
		
	fis.close();


	}	
	
	
}




