package com.derived.product1.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.automation.framework.base.BaseTest;
import com.automation.framework.pageObjects.HomePage;
import com.automation.framework.pageObjects.MenWearPage;
import com.automation.framework.pageObjects.TicketsPage;
import com.automation.framework.utils.Constants;
import com.automation.framework.utils.PageObjectManager;
import com.automation.framework.utils.TestUtils;

public class TicketsTest extends BaseTest {
    private TicketsPage ticketsPage;
    @BeforeClass
    public void setUp() {
        pageObjectManager = new PageObjectManager(driver);
        ticketsPage=pageObjectManager.getTicketsPage();
    }
	@Test(priority=1)
	public void homePageTitleTest()
	{
		String title=ticketsPage.validateTicketsPageTitle();
		Assert.assertTrue(title.contains(Constants.TICKETS_PAGE_TITLE));
	}
	@Test(priority=2)
	public void slideTitleValidation()
	{
		List<String> titles=ticketsPage.titleValidation();
		for(int i=0;i<titles.size();i++)
		{
			System.out.println(titles.get(i));
		}
		List<String> expectedTitles=TestUtils.getColumnTestData(Constants.TEST_DATA_PATH,Constants.SHEET_NAME);
		for(int i=0;i<expectedTitles.size();i++)
		{
			System.out.println(expectedTitles.get(i));
		}
		 Assert.assertTrue(TestUtils.compareLists(titles, expectedTitles),"EXpected titles were not found");
	}
	
	@Ignore
	@Test(priority=3)
	public void slideDurationValidation()
	{
		System.out.println("inside slide duration");
		List<Integer> times=ticketsPage.timeValidation(); 
		for(int i=0;i<times.size();i++) 
		{ 
			System.out.println(times.get(i)); 
		}
	}

    @AfterClass
    public void tearDown() {
    	//driver.close();
        pageObjectManager.resetPageObjects();
    }
}
