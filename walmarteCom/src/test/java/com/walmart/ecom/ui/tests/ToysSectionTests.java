/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.walmart.ecom.ui.tests;

import java.util.Properties;
import org.testng.annotations.Test;

import com.walmart.ecom.ui.core.SeleniumMethods;
import com.walmart.ecom.ui.core.WalmartTestBase;
import com.walmart.ecom.ui.home.page.HomePage;

public class ToysSectionTests extends WalmartTestBase {

	@Test(dataProvider = "loadTestData")
	public void verifyProductDetails(Properties aProperties) {
		new HomePage(driver).loadUrl(walmartUrl).browserToToys().browserToOutDoorPlay().
		toSwimmingPools().toEasySetPools().selectItem(aProperties.getProperty("itemName"))
			.verifyItemName(aProperties.getProperty("itemName")).verifyItemDetails();
	}
	
	@Test(dataProvider = "loadTestData")
	public void verifyItemsDisplayedByPrice(Properties aProperties) {
		new HomePage(driver).loadUrl(walmartUrl).browserToToys().browserToOutDoorPlay().
		toSwimmingPools().toEasySetPools().verifyItemsPriceAscendingOrder();
	}

}
