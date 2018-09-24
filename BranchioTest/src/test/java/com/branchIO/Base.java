
package com.branchIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Base {
	 

	
	 public  String getpropertyValue(String key) throws IOException{
		String result="";
		 String propertyfileName="locator.properties";
		 Properties prop = new Properties();
		 
		 InputStream inputstream=getClass().getClassLoader().getResourceAsStream(propertyfileName);
		 
		 
		 if(inputstream!=null){
			 prop.load(inputstream);
		 }else{
			 throw new FileNotFoundException("Proeprty file "+propertyfileName+"notFound *****" );
		 }
		result = prop.getProperty(key);
		 
		 
		 return result;
		
	}
	
	
}