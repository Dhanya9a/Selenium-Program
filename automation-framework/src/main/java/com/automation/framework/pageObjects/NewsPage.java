package com.automation.framework.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsPage {
private WebDriver driver;
	
	@FindBy(xpath="//div[@data-testid='post-duration']/preceding-sibling::time/span")
	List<WebElement> videoTime;
	
	@FindBy(xpath="//a[@rowid='2' and contains(@href,'golden-state-warriors-men-jackets')]//div[text()='Jackets']")
	WebElement jackets;
	
	@FindBy(xpath="//div[@class='column']/div[contains(@class,'product-card')]")
	List<WebElement> jacketsListInPage;
	
	@FindBy(xpath="(//ul[@class='pagination-list-container']/li[@class='next-page']/a[@aria-disabled='false'])[2]")
	WebElement next;
	
	
	@FindBy(xpath="(//ul[@class='pagination-list-container']/li[@class='next-page disabled'])[2]")
	WebElement next_disabled;
	
	@FindBy(xpath="//div[@class='column']/div[contains(@class,'product-card')]/div/div[@class='product-card-title']")
	List<WebElement> productTitle;
	
	@FindBy(xpath="//span[@class='lowest']/span[@class='price primary']/span[@class='money-value']/span[@class='sr-only']")
	List<WebElement> productPrice;
	
	
	
	
	
	public NewsPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); 
	    }
	//Actions:
	public String validateNewsPageTitle()
	{
		return driver.getTitle();
	}
	
	public int[] countofVideos()
	{
		List<Integer> totalVideos=new ArrayList<Integer>();
		List<Integer> videosGreaterThan3Days=new ArrayList<Integer>();
		for(int i=0;i<videoTime.size();i++)
		{
			//totalVideos.add(videoTime.get(i).getText());
			int number =Integer.parseInt(videoTime.get(i).getText().replaceAll("[^0-9]", ""));
			String s=videoTime.get(i).getText().replaceAll("[0-9]", "");
			totalVideos.add(number);
			if(number>=3 && s.equalsIgnoreCase("d"))
			{
				videosGreaterThan3Days.add(number);
			}
		}
		
		return new int[] {totalVideos.size(),videosGreaterThan3Days.size()};
	}
	
}
