
package com.branchIO;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Base {
	 

	JavascriptExecutor js;
	static Properties prop;
	static FileInputStream fis;

	@BeforeMethod
//GitFolder
	public void setupEn() throws Exception {

		String path = System.getProperty("user.home")+File.separator+"GitFolder"+File.separator+"BranchTest"+File.separator+"BranchioTest"+File.separator+"locator.properties";
		File file = new File(path);

		fis = new FileInputStream(file);
		prop = new Properties();
		prop.load(fis);

	}

	

	public  String getProp(String key) {

		return prop.getProperty(key);

	}
}