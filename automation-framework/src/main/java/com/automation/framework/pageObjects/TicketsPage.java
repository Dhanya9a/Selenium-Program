package com.automation.framework.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.framework.utils.TestUtils;

public class TicketsPage {
private WebDriver driver;
	
	@FindBy(xpath="//button[@type='button']/div[contains(@class,'TileHeroStories_tileHeroStoriesButtonTitle')]")
	List<WebElement> slideList;
	
	@FindBy(xpath="//div[contains(@class,'TileHeroStories_tileHeroStoriesButtons')]/button")
	List<WebElement> slideButton;
	
	
	
	public TicketsPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); 
	    }
	//Actions:
	public String validateTicketsPageTitle()
	{
		return driver.getTitle();
	}
	
	public List<String> titleValidation()
	{
		List<String> slideTitle=new ArrayList<String>();
		
		for(int i=0;i<slideList.size();i++)
		{
			slideTitle.add(slideList.get(i).getText());
		}
		return slideTitle;
	}
	
	public List<Integer> timeValidation()
	{
		List<Integer> time=new ArrayList<Integer>();
		TestUtils.waitForAttributeContains(driver,slideButton.get(0),"aria-selected","true",60);
		System.out.println("inside time validation");
		for(int i=0;i<slideButton.size();i++)
		{
			System.out.println("inside for loop");
			if(i!=slideButton.size()-1)
			{
				System.out.println("inside if loop");
				long startTime = System.currentTimeMillis();
				String status=slideButton.get(i+1).getAttribute("aria-selected").toString();
				while(status!="true")
				{
					status=slideButton.get(i+1).getAttribute("aria-selected").toString();
				}
				long endTime = System.currentTimeMillis();
				System.out.println(endTime-startTime);
				time.add((int) (endTime-startTime));
			}
			else
			{
				System.out.println("inside else loop");
				long startTime = System.currentTimeMillis();
				String status=slideButton.get(0).getAttribute("aria-selected").toString();
				while(status!="true")
				{
					status=slideButton.get(0).getAttribute("aria-selected").toString();
				}
				long endTime = System.currentTimeMillis();
				time.add((int) (endTime-startTime));
			}
		}
		
		return time;
	}
	
}