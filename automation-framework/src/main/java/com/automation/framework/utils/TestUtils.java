package com.automation.framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class TestUtils {

	public static void waitForButtonClickable(WebDriver driver,WebElement e,long durationInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(e));
		
	}
	
	public static void frameToBeAvailableAndSwitchToIt(WebDriver driver,WebElement e,long durationInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(e));
		
	}
	
	public static void waitForVisibility(WebDriver driver,WebElement e,long durationInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.visibilityOf(e));
		
	}
	public static void waitForElementToBeClickable(WebDriver driver,WebElement e,long durationInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	
	public static List<WebElement> waitForVisibilityOfAllElements(WebDriver driver,WebElement e,long durationInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
		List<WebElement> elements=wait.until(ExpectedConditions.visibilityOfAllElements(e));
		return elements;
	}
	
	public static void waitForStalenessOf(WebDriver driver,WebElement e,long durationInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.stalenessOf(e));
	}
	
	public static List<WebElement> waitForPresenceOfAllElements(WebDriver driver,WebElement e,long durationInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
		List<WebElement> elements=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) e));
		return elements;
	}
	public static void waitForInvisibility(WebDriver driver,WebElement e,long durationInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.invisibilityOf(e));
		
	}
	public static void waitForAttributeContains(WebDriver driver,WebElement e,String attribute, String value,long durationInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.attributeContains(e,attribute,value));
		
	}
	public static void moveToElementAction(WebDriver driver,WebElement e)
	{
		Actions a=new Actions(driver);
		a.moveToElement(e).build().perform();
	}
	public static Object[][] getTestData(String TESTDATA_SHEET_PATH,String sheetName)
	{
		Workbook book = null;
		Sheet sheet;
		FileInputStream file=null;
		try {
			file=new FileInputStream(TESTDATA_SHEET_PATH);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book=WorkbookFactory.create(file);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++)
			{
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
				
			}
		}
		return data;
	}

	public static List<String> getColumnTestData(String TESTDATA_SHEET_PATH,String sheetName)
	{
		Workbook book = null;
		Sheet sheet;
		FileInputStream file=null;
		try {
			file=new FileInputStream(TESTDATA_SHEET_PATH);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book=WorkbookFactory.create(file);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		List<String> data=new ArrayList<String>();
		for(int k=1;k<=sheet.getLastRowNum();k++)
		{
			data.add(sheet.getRow(k).getCell(0).toString());
				
		}
		return data;
	}
	
	public static void writeToFile(String filePath, List<String> data) {
		Path file = Paths.get(filePath);
		try {
			Files.write(file, data, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	//Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
	}
    
    public  static void attachTextFileToReport(String filePath) throws InterruptedException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            Reporter.log(line + "<br>", true);  // Log the content to TestNG report
        }
        reader.close();
    }
    public  static void attachTextToReport(String text) throws InterruptedException, IOException {
        
            Reporter.log(text , true);  // Log the content to TestNG report
    }

	public static boolean compareLists(List<String> list1, List<String> list2) {
		boolean areEqual = true;

	
        if (list1.size() != list2.size()) {
            areEqual = false;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                if (!list1.get(i).equals(list2.get(i))) {
                	System.out.println("not equal "+list1.get(i) + list2.get(i));
                    areEqual = false;
                    break;
                }
            }
        }
        return areEqual;
	}
	
	 public static void writeDataToCSV(String filePath, Set<String> data) throws IOException {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	            writer.write("HyperLinks");
	            writer.newLine(); 

	            // Write each data item
	            for (String record : data) {
	                writer.write(record);
	                writer.newLine();
	            }
	        }
	    }
}
