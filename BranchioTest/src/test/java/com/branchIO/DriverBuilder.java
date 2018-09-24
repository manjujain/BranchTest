

package com.branchIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverBuilder {
	WebDriver driver;
	
	
     //GitFolder
      String chromeDrPath=System.getProperty("user.home")+File.separator+"testGit"+File.separator+"BranchTest"+File.separator+"BranchioTest"+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"resource"+File.separator+"chromedriver";
	
        
         String  Firefoxdrpath =System.getProperty("user.home")+File.separator+"testGit"+File.separator+"BranchTest"+File.separator+"BranchioTest"+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"resource"+File.separator+"geckodriver";
	  
	  String IEdrpath =  System.getProperty("user.home")+"/GitFolder/BranchTest/BranchioTest/src/test/java/resource/iedriver";
	

 
	  public  WebDriver getDr( String br) throws IOException {
		

		if (br.equals("ff") ) {
			String os=System.getProperty("os.name").toLowerCase();
			if(os.contains("mac")){

			//System.setProperty("webdriver.gecko.driver", Firefoxdrpath);
				FirefoxDriverManager.getInstance().setup();
			driver = new FirefoxDriver();
			
		
		}
			else{
				Firefoxdrpath=System.getProperty("users.dir")+"\\GitFolder\\BranchTest\\BranchioTest\\src\\test\\java\\resource\\gecko.driver.exe";
				System.setProperty("webdriver.gecko.driver", Firefoxdrpath);

				driver = new FirefoxDriver();
		
		}
		
		
		} else if (br.equals("ie")) {
			//DesiredCapabilities cap = new DesiredCapabilities();
			//cap = DesiredCapabilities.internetExplorer();
			//cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			File file = new File(IEdrpath);
			System.setProperty("webdriver.iedriver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}

		else if (br.equals("ch")) {
			
			ChromeDriverManager.getInstance().setup();
			 
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
			
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