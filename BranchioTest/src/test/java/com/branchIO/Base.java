
package com.branchIO;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Base {
	  WebDriver driver;

	JavascriptExecutor js;
	static Properties prop;
	static FileInputStream fis;

	@BeforeClass

	public void setupEn() throws Exception {

		String path = System.getProperty("user.home")+File.separator+"GitFolder"+File.separator+"BranchioTest"+File.separator+"locator.properties";
		File file = new File(path);

		fis = new FileInputStream(file);
		prop = new Properties();
		prop.load(fis);

	}

	@AfterClass
	public  void teardown() throws Exception {
		driver.quit();
		

	}

	public  String getProp(String key) {

		return prop.getProperty(key);

	}
}