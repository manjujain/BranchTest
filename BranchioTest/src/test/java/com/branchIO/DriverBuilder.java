

package com.branchIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverBuilder {
	 WebDriver driver;
     FileInputStream fis;
     //GitFolder
     String chromeDrPath=System.getProperty("user.home")+File.separator+"GitFolder"+File.separator+"BranchTest"+File.separator+"BranchioTest"+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"resource"+File.separator+"chromedriver";
	 String Firefoxdrpath = System.getProperty("user.home")+File.separator+"GitFolder"+File.separator+"BranchTest"+File.separator+File.separator+"BranchioTest"+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"resource"+File.separator+"gecko.driver";
	  
	  String IEdrpath =  System.getProperty("user.home")+"/GitFolder/BranchTest/BranchioTest/src/test/java/resource/iedriver";
	
	
	  
	  public  WebDriver getDr(String Br) throws IOException {
		String br = Br;

		if (br == "ff") {
			String os=System.getProperty("os.name").toLowerCase();
			if(os.contains("mac")){

			System.setProperty("webdriver.gecko.driver", Firefoxdrpath);

			driver = new FirefoxDriver();
			
		
		}
			else{
				Firefoxdrpath=System.getProperty("users.dir")+"\\GitFolder\\BranchTest\\BranchioTest\\src\\test\\java\\resource\\gecko.driver.exe";
				System.setProperty("webdriver.gecko.driver", Firefoxdrpath);

				driver = new FirefoxDriver();
		
		}
		
		
		} else if (br == "ie") {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			File file = new File(IEdrpath);
			System.setProperty("webdriver.iedriver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}

		else if (br == "ch") {
			String os=System.getProperty("os.name").toLowerCase();
			if(os.contains("mac")){
			
			File file = new File(chromeDrPath);
			
			
			System.out.println("chrome driver path is system in MAC=============="+chromeDrPath);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			
			driver = new ChromeDriver();
			}
			else{
				
				chromeDrPath=System.getProperty("user.home")+
						"\\GitFolder\\BranchTest\\BranchioTest\\src\\test\\java\\resource\\chromedriver.exe";
				
				System.out.println("This is path for chomer driver in WINDOWS ========"+chromeDrPath);
				File file = new File(chromeDrPath);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				
				driver = new ChromeDriver();
			}
			
		}

		else {
			System.out.println("driver not found");
		}

		if (driver == null) {
			driver.close();
		}

		return driver;

	}

}