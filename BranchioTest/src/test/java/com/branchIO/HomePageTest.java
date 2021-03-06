package com.branchIO;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static com.branchIO.MethodLibrary.*;
import static com.branchIO.DriverBuilder.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageTest extends Base{
	
   
	JavascriptExecutor js;
	int TotlaNoOFEmployee = 0;
	int count = 0;
	
	 static WebDriver driver;
	ArrayList<String> allEmployee;
	ArrayList<String> Engineer;
	ArrayList<String> Data ;
	ArrayList<String> Product ;
	ArrayList<String> PartnerGrowth ;
	ArrayList<String> Marketing ;
	ArrayList<String> Operations ;
	ArrayList<String> Recruiting ;
	DriverBuilder builder;
	String locatorValue=null;
	
	@Parameters({"Browser"})
	@BeforeClass
	public void launchBrowser(String br) throws IOException {
		 locatorValue=getpropertyValue("HomePage_team");
		
		builder=new DriverBuilder()	;
	
	
	
     driver=builder.getDr(br);
		

	}

	@Parameters({ "HOMEPageURL" })
	@Test(priority = 1)
	public void launchHomePage(String URL) throws InterruptedException {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
		

	}

	@Test(priority = 2)
	public void HomePage_ScrollDownToTeam() throws InterruptedException, AWTException, IOException {
		System.out.println("pageload");
		System.setProperty("java.awt.headless", "false");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);

		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);

		Thread.sleep(5000);
		
		 locatorValue=getpropertyValue("HomePage_team");
		 
		 System.out.println("Property file loaded sucessfully========"+locatorValue);
		 WebElement team=getElement( driver, "xpath",locatorValue );
		 
		 team.sendKeys(Keys.ENTER);
	}

	@Test(priority = 3)
	public void varifyEmployeeList() throws InterruptedException, IOException {
		SoftAssert softAssert= new SoftAssert();
		Thread.sleep(5000);

		 allEmployee = new ArrayList<String>();
		 Engineer = new ArrayList<String>();
		 Data = new ArrayList<String>();
		 Product = new ArrayList<String>();
		 PartnerGrowth = new ArrayList<String>();
		 Marketing = new ArrayList<String>();
		 Operations = new ArrayList<String>();
		 Recruiting = new ArrayList<String>();
       
		// adding all employee in all employee list
		 locatorValue=getpropertyValue("AllEmployee_classLoc");
		 
		 System.out.println("Property file loaded sucessfully 2========"+locatorValue);
		List<WebElement> list = driver.findElements(By.className(locatorValue));
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).getAttribute("id");
			name = name.replaceAll("-", " ");
			name = name.toUpperCase();
			allEmployee.add(name);

		}

		TotlaNoOFEmployee = list.size();
        
		for (int n = 1; n <= TotlaNoOFEmployee; n++) {
			
			 String xpath_employeeName= "html/body/div[1]/div/section[2]/div/div[2]/div/div["+
					 n
					+"]/div[2]/div/div[2]/h2";
					 
			
			String xpath_catagary="html/body/div[1]/div/section[2]/div/div[2]/div/div["
					+n
					+"]/div[2]/div/div[2]/h4";
			 
			WebElement ele_name = driver.findElement(By.xpath(xpath_employeeName));
			
			WebElement ele_catagory = driver.findElement(By.xpath(xpath_catagary));
			
			String EmployeeName = ele_name.getText();
			EmployeeName =EmployeeName.toUpperCase();
			String EmployeeCatagory = ele_catagory.getText();
					
		// sorting employee according to category	
			
			if (EmployeeCatagory.equals("Engineering")) {
				Engineer.add(EmployeeName);
			}

			

			if (EmployeeCatagory.equals("Data")) {
				Data.add(EmployeeName);
			}

			if (EmployeeCatagory.equals("Product")) {
				Product.add(EmployeeName);
			}
			if (EmployeeCatagory.equals("Partner Growth")) {
				PartnerGrowth.add(EmployeeName);
			}
			if (EmployeeCatagory.equals("Marketing")) {
				Marketing.add(EmployeeName);
			}

			if (EmployeeCatagory.equals("Operations")) {
				Operations.add(EmployeeName);
			}
			if (EmployeeCatagory.equals("Recruiting")) {
				Recruiting.add(EmployeeName);
			}
			
		}
		
		// sorting all the list
		Collections.sort(allEmployee);
		Collections.sort(Engineer);
		Collections.sort(Recruiting);
		Collections.sort(Data);
		Collections.sort(Product);
		Collections.sort(PartnerGrowth);
		Collections.sort(Marketing);
		Collections.sort(Operations);
		
		
		System.out.println("Engineer Employee:  *******");
				
		
		softAssert.assertTrue(compareList(allEmployee, Engineer));
		
		
		softAssert.assertTrue(compareList(allEmployee, Recruiting));
		
		System.out.println("Data Employee:  *******");
		softAssert.assertTrue(compareList(allEmployee, Data));
		
		System.out.println("Product Employee:  *******");
		softAssert.assertTrue(compareList(allEmployee, Product));
		
		System.out.println("PartnerGrowth Employee :  *******");
		softAssert.assertTrue(compareList(allEmployee, PartnerGrowth));
		
		System.out.println("Marketing Employee:  *******");
		softAssert.assertTrue(compareList(allEmployee, Marketing));
		
		softAssert.assertTrue(compareList(allEmployee, Operations));

		Assert.assertTrue(compareList(allEmployee, Engineer));
	
	
	}

	@Test(priority = 4)

	public void validateNumberOfEmployee() {
		
		//sum all category employee 
		
		count += Data.size();
		
		count += Engineer.size();
		
		
		count += Marketing.size();
		
		
		count += Operations.size();
		
		count += PartnerGrowth.size();
		
		
		count += Product.size();
		
		count += Recruiting.size();
		
		System.out.println("**************Total count of employee: " + TotlaNoOFEmployee);

		System.out.println("**************Totla count of each Department: " + count);

		Assert.assertEquals(count, TotlaNoOFEmployee);

	}
	@Parameters({ "GoogleURL" })
	@Test(priority = 5)
	public void navigateHomePageWithGoogle(String URL) throws InterruptedException{
		driver.get(URL);
		driver.findElement(By.name("q")).sendKeys("Branch");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.findElement(By.partialLinkText("Branch - A mobile linking platform powering deep links and mobile")).click();
		driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
		
		
	}
	@Parameters({ "HOMEPageURL" })
	@Test(priority = 6)
	public void openNewtab(String URL){
		driver.get(URL);
		String selectAll = Keys.chord(Keys.SHIFT,Keys.RETURN);
		 driver.findElement(By.className("bds-btn-tertiary")).sendKeys(selectAll);
		   
		driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
	}

@AfterClass
	public void driverclose(){
		driver.quit();
	}

	
}
