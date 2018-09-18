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
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageTest extends Base{
	

	JavascriptExecutor js;
	int TotlaNoOFEmployee = 0;
	int count = 0;
	DriverBuilder builder;
	WebDriver driver;
	ArrayList<String> allEmployee;
	ArrayList<String> Engineer;
	ArrayList<String> Data ;
	ArrayList<String> Product ;
	ArrayList<String> PartnerGrowth ;
	ArrayList<String> Marketing ;
	ArrayList<String> Operations ;
	ArrayList<String> Recruiting ;

	@Parameters({ "Browser" })
	
	@BeforeClass
	public void launchBrowser(String br) {
		
builder = new DriverBuilder();
		driver =builder.getDr("ch");
		js = (JavascriptExecutor) driver;

	}

	@Parameters({ "HOMEPageURL" })
	@Test(priority = 1)
	public void launchHomePage(String URL) throws InterruptedException {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
		

	}

	@Test(priority = 2)
	public void HomePage_ScrollDownToTeam() throws InterruptedException, AWTException {
		System.out.println("pageload");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);

		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);

		Thread.sleep(5000);
		
		driver.findElement(By.xpath(".//a[contains(text(),'Team')]")).sendKeys(Keys.ENTER);

	}

	@Test(priority = 3)
	public void varifyEmployeeList() throws InterruptedException {
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
		
		List<WebElement> list = driver.findElements(By.className("jump"));
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).getAttribute("id");
			name = name.replaceAll("-", " ");
			name = name.toUpperCase();
			allEmployee.add(name);

		}

		TotlaNoOFEmployee = list.size();

		// sorting employee according to category 
		
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
			String EmployeeCatagory = ele_catagory.getText();
			
			
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
		
		// getting list of employee for all department like-data,marketing,Opertion,
		// Partner Growth, Recruiting

		
		List<WebElement> DataList = getWebElementList(driver, ".//h4[contains(text(),'Data')]");

		count += DataList.size();
		
		List<WebElement> EngineerList = getWebElementList(driver, ".//h4[contains(text(),'Engineering')]");
		count += EngineerList.size();
		
		List<WebElement> MarketingList = getWebElementList(driver, ".//h4[contains(text(),'Marketing')]");
		count += MarketingList.size();
		
		List<WebElement> OpertionList = getWebElementList(driver, ".//h4[contains(text(),'Operations')]");
		count += OpertionList.size();
		
		List<WebElement> PartnerGrowthList = getWebElementList(driver,  getProp("HomePage_PartnerGrowthList"));
		count += PartnerGrowthList.size();
		
		List<WebElement> ProductList = getWebElementList(driver, ".//h4[contains(text(),'Product')]");
		count += ProductList.size();
		
		List<WebElement> RecurtingList = getWebElementList(driver, ".//h4[contains(text(),'Recruiting')]");
		count += RecurtingList.size();

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
	public  void teardown() throws Exception {
		driver.quit();
		

	}

}
