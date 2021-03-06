package com.branchIO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MethodLibrary {
	
	// this method will return webelement
	public static  WebElement 	getElement(WebDriver driver, String locaor, String locatorValue){
		WebElement element = null;
		
		switch(locaor){
		case "xpath":
		element =driver.findElement(By.xpath(locatorValue));break;
		case "className":
			element =driver.findElement(By.className(locatorValue));break;
			
		case "id":
			element =driver.findElement(By.id(locatorValue));break;
			
		case "tag":
			element =driver.findElement(By.tagName(locatorValue));break;
		case "css":
			element =driver.findElement(By.cssSelector(locatorValue));break;
		case "name":		
			element =driver.findElement(By.name(locatorValue));break;
			
		default:
			System.out.println("locator not found");
		}
			
		return element;
		
	}	 
	


// this method validate list1 has all element of list2
public static boolean compareList(ArrayList<String>list1, ArrayList<String>list2){
	
	boolean isElementExist=true;
	for(int i=0;i<list2.size();i++){
		String name = list2.get(i);
		
		boolean isFound=false;
		for(int j=0;j<list1.size();j++){

			if(list2.get(i).equals(list1.get(j))){
				isFound=true;
			}
		}
		
		if(isFound==true){
			//System.out.println("Found: " + name);
		}
		else {
			isElementExist=false;
			System.out.println("Not Found: " + name);
		}
			
	}
	return isElementExist;
}
	

public static  List<WebElement> getWebElementList(WebDriver driver,String xpath){
	
List<WebElement> list= driver.findElements(By.xpath(xpath));
	
	
	return list;
	
}
public static WebElement scrollDownToElement(WebDriver driver, By by){
	 
	JavascriptExecutor je = (JavascriptExecutor) driver;
	 
	 
	 
	//Identify the WebElement which will appear after scrolling down
	 
	WebElement element = driver.findElement(by);
	 
	je.executeScript("arguments[0].scrollIntoView(true);",element);
	
	
	return element;
	 	
}
// this method will launch URL on new tab
public  static void OpenNewTab(WebDriver driver,String URL){	
	Set<String> handles	=driver.getWindowHandles();
	String firsthandle = driver.getWindowHandle();
	
	
	Iterator<String > itr=handles.iterator();
	String win = itr.next();
	if(win!=firsthandle){
		String sechandle = win;
	
	driver.switchTo().window(sechandle);
	driver.navigate().to( URL);
	}
}


}
