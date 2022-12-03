/*Author :  Venkatramana Reddy Araveeti
 * 
 */
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

public class ToysSecetionPage extends SeleniumMethods {

	// Creating the Generic Driver Instance.

	private WebDriver driver;
	private CustomAssersion assersion;

	@FindBy(xpath = "//span[text()='Outdoor Play']")
	private WebElement lnkOutDoorPlay;

	@FindBy(xpath = "//span[text()='Swimming Pools & Waterslides']")
	private WebElement lnkSwimming;

	@FindBy(xpath = "//span[text()='Easy Set Swimming Pools']")
	private WebElement lnkEasySet;

	private String aLocator = "//h2[text()='DUMMY']";

	@FindBy(linkText = "Price")
	private WebElement lnkPrice;

	@FindBy(xpath = "//div[@class='price-current']/div")
	private List<WebElement> txtPrice;
	
	// Initialize the Elements using Page Factory

	public ToysSecetionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		assersion = new CustomAssersion(driver);
	}

	// Navigate to Out Door Play Page
	public ToysSecetionPage browserToOutDoorPlay() {
		assersion.assertTrue(click(lnkOutDoorPlay));
		return this;
	}

	public ToysSecetionPage toSwimmingPools() {
		assersion.assertTrue(click(lnkSwimming));
		return this;
	}

	public ToysSecetionPage toEasySetPools() {
		assersion.assertTrue(click(lnkEasySet));
		return this;
	}

	public ItemDetailsPage selectItem(String aItem) {
		WebElement element = fluentWait(By.xpath(aLocator.replaceAll("DUMMY", aItem)), driver);
		assersion.assertTrue(click(element));
		return new ItemDetailsPage(driver);
	}

	public ToysSecetionPage clickPriceLink() {
		assersion.assertTrue(click(lnkPrice));
		return this;
	}

	public ToysSecetionPage verifyItemsPrice() {
		List<Integer> actualItems = new ArrayList<Integer>();
		List<Integer> Expected;		
		for (int i = 0; i < txtPrice.size(); i++) {
			if(!txtPrice.get(i).getText().equals("")) {
			actualItems.add(Integer.parseInt(txtPrice.get(i).getText().replaceAll("\n", "").replaceAll("\r", "").replaceAll("[$,]", "").split("\\.", 2)[0]));
			}
		}

		Expected = new ArrayList<Integer>(actualItems);
		Collections.sort(Expected);
		assersion.assertTrue(Expected.equals(actualItems));
		log.info("The Items are displayed in Ascending Order by Price and the Test Passed");
		return this;

	}

	public ToysSecetionPage verifyItemsPriceAscendingOrder() {
		clickPriceLink();
		waitForAjaxToComplete();
		verifyItemsPrice();
		return this;
	}

}
