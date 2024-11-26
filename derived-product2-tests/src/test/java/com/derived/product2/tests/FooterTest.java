package com.derived.product2.tests;

import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.automation.framework.base.BaseTest;
import com.automation.framework.pageObjects.FooterPage;
import com.automation.framework.pageObjects.TicketsPage;
import com.automation.framework.utils.Constants;
import com.automation.framework.utils.PageObjectManager;
import com.automation.framework.utils.TestUtils;

public class FooterTest  extends BaseTest {
    private FooterPage footerPage;
    @BeforeClass
    public void setUp() {
        pageObjectManager = new PageObjectManager(driver);
        footerPage=pageObjectManager.getFooterPage();
    }
	@Test(priority=1)
	public void homePageTitleTest()
	{
		String title=footerPage.validateFooterPageTitle();
		Assert.assertTrue(title.contains(Constants.FOOTER_PAGE_TITLE));
	}
	@Test(priority=2)
	public void duplicateFooterValidation()
	{
		Set<String> titles=footerPage.duplicateFooterValidation();
		
		 Assert.assertTrue(titles.isEmpty(),"Duplicate links are present in the footer.");
	}

    @AfterClass
    public void tearDown() {
    	//driver.close();
        pageObjectManager.resetPageObjects();
    }
}
