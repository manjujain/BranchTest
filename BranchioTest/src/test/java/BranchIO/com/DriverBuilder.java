
package BranchIO.com;

import java.io.File;
import java.io.FileInputStream;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverBuilder {
	static WebDriver driver;
     static FileInputStream fis;
   
     static String Chromerdrpath="/Users/manjujain/Downloads/RestAssured/src/resource/chromedriver";
     
	  static String Firefoxdrpath = System.getProperty("user.home")+File.separator+"Downloads"+File.separator+"src"+File.separator+"resource"+File.separator+"gecko.driver";
	  
	  static String IEdrpath =  System.getProperty("user.home")+"/GitFolder/BranchTestProject/src/resource/iedriver";
	
	
	  
	  public static  WebDriver getDr(String Br) {
		String br = Br;

		if (br == "ff") {
			System.setProperty("webdriver.gecko.driver", Firefoxdrpath);

			driver = new FirefoxDriver();
		} else if (br == "ie") {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			File file = new File(IEdrpath);
			System.setProperty("webdriver.iedriver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}

		else if (br == "ch") {
			File file = new File(Chromerdrpath);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			
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