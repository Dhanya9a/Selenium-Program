package com.automation.framework.pageObjects;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.framework.utils.TestUtils;

public class FooterPage {
private WebDriver driver;
	
	@FindBy(xpath="//li[@data-testid='footer-list-item']/a")
	List<WebElement> footerLinks;
	
	@FindBy(xpath="//img[@title='Warner Media Logo']")
	WebElement footer;
	
	
	public FooterPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); 
	    }
	//Actions:
	public String validateFooterPageTitle()
	{
		return driver.getTitle();
	}
	
	public Set<String> duplicateFooterValidation()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		TestUtils.waitForVisibility(driver, footer, 20);
		
		 Set<String> linkSet = new HashSet<>();
	     Set<String> duplicateLinks = new HashSet<>();
	     for (WebElement link : footerLinks) {
	            String href = link.getAttribute("href");
	            if (href != null && !href.isEmpty()) {
	                if (!linkSet.add(href)) { // If add() returns false, it's a duplicate
	                    duplicateLinks.add(href);
	                }
	            }
	        }

	        try {
				TestUtils.writeDataToCSV("data.csv", linkSet);
			} catch (IOException e) {
			}

	        if (!duplicateLinks.isEmpty()) {
	            System.out.println("Duplicate hyperlinks found:");
	            for (String duplicateLink : duplicateLinks) {
	                System.out.println(duplicateLink);
	            }
	        } else {
	            System.out.println("No duplicate hyperlinks found.");
	        }

	        return duplicateLinks;
	    }
}