/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.walmart.ecom.ui.product.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.walmart.ecom.ui.core.CustomAssersion;
import com.walmart.ecom.ui.core.SeleniumMethods;

public class OutDoorLivingPage extends SeleniumMethods{
	
	//Creating the Generic Driver Instance.
	
	 private WebDriver driver;
	 private CustomAssersion assersion;
	 
	 @FindBy(xpath="//a[contains(@href,swimming-pools--accessories]")
	 private WebElement lnkswmmingAccessories;
			 
	    
	    //Initialize the Elements using Page Factory
	    
	    public OutDoorLivingPage(WebDriver driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        assersion = new CustomAssersion(driver);
	    }
	    
	   	    
	   
}

