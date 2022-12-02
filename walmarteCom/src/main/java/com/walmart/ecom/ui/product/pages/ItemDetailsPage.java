package com.walmart.ecom.ui.product.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.walmart.ecom.ui.core.CustomAssersion;
import com.walmart.ecom.ui.core.SeleniumMethods;

public class ItemDetailsPage extends SeleniumMethods {

	private WebDriver driver;
	private CustomAssersion assersion;

	private String aLocator = "//h1[text()='DUMMY']";
	
	@FindBy(xpath="//button[@class='button add-to-cart-btn available']")
	private WebElement btnAddToCart;
	
	 @FindBy(xpath="//h2[text()='Description & Features']")
	 private WebElement txtDescription;
		
	
	 @FindBy(xpath="//span[text()='Shoppers who viewed this item also viewed']")
	 private WebElement txtViewedItems;
	 
	 @FindBy(xpath="//span[text()='People with similar interests also bought']")
	 private WebElement txtSimilarItems;
	 
	// Initialize the Elements using Page Factory
	
	

	public ItemDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		assersion = new CustomAssersion(driver);
	}

	public ItemDetailsPage verifyItemName(String aName) {
		assersion.assertTrue(verifyElementPresence(fluentWait(By.xpath(aLocator.replaceAll("DUMMY", aName)), driver)));
		return this;
	}
	
	public ItemDetailsPage verifyItemDetails() {
		assersion.assertTrue(verifyElementsPresence(btnAddToCart,txtDescription,txtViewedItems,txtSimilarItems));
		return this;
	}
	

}
