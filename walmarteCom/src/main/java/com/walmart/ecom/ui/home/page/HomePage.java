/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.walmart.ecom.ui.home.page;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.walmart.ecom.ui.core.CustomAssersion;
import com.walmart.ecom.ui.core.SeleniumMethods;
import com.walmart.ecom.ui.product.pages.OutDoorLivingPage;
import com.walmart.ecom.ui.product.pages.ToysSecetionPage;

public class HomePage extends SeleniumMethods{
	
	@FindBy(id="header-toys")
	private WebElement buttonToys;

	
	
	 private WebDriver driver;
	 private CustomAssersion assersion;

	    
	    //Initialize the Elements using Page Factory
	    
	    public HomePage(WebDriver driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        assersion = new CustomAssersion(driver);
	    }
	    
	    public HomePage loadUrl(String aURL) {  
	    	//log.info("Loading the URL " + aURL);
	    	driver.get(aURL);
	    	//log.info("Successfully loaded the URL:" +aURL);
	    	return this;
	    }
	    
	    
	    public ToysSecetionPage browserToToys() {
	    	assersion.assertTrue(click(buttonToys));
	    	return new ToysSecetionPage(driver);
	    	
	    }
	    
	   
}

