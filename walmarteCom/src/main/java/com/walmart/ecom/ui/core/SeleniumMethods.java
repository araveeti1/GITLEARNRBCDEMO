/*Author :  Venkatramana Reddy Araveeti
 * 
 */

package com.walmart.ecom.ui.core;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;

public class SeleniumMethods {

	// Creating the Generic Driver Instance.

	private WebDriver driver;
	private static final TimeUnit SECONDS = null;

	protected org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

	public CustomAssersion assersion;

	public SeleniumMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		assersion = new CustomAssersion(driver);
	}

	public boolean click(WebElement objElement) {

		boolean isVerify = false;
		try {
			waitForElementClickable(objElement);
			objElement.click();
			log.info("The element" + objElement + " Has been clicked successfully");
			isVerify = true;
		} catch (IllegalArgumentException e) {
			// log.error(" Exception is thrown at run time");
			isVerify = false;

		}
		return isVerify;
	}

	public boolean sendKeys(WebElement objElement, String sTextToSend)
			throws InterruptedException, IOException, TimeoutException {
		boolean isVerify = false;
		try {
			waitForElementClickable(objElement);
			objElement.clear();
			objElement.sendKeys(sTextToSend);
			log.info("The Text is entered Successfully into the Text Box::" + objElement);
			isVerify = true;

		} catch (IllegalArgumentException e) {
			// log.error("An Exception is thrown at run time ");
			throw e;

		} catch (Exception e) {
			// log.error("Time out Exception is thrown at run time ");
			throw e;

		}

		return isVerify;
	}

	public WebElement fluentWait(final By locator, WebDriver driver) {

		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});

		return foo;
	};

	// Quit Browser
	public boolean quitDriver() throws Exception {
		boolean isVerify = false;
		try {
			driver.quit();
			log.info("Successfully closed the Browser ");
			isVerify = true;
		} catch (Exception e) {

			log.error("An Exception is thrown at run time");
			throw e;

		}

		return isVerify;
	}

	// Getting the Page Source

	public String getPageSource() throws InterruptedException {
		return driver.getPageSource();

	}

	/*
	 * Wait for the Element to be click able
	 * 
	 */
	public boolean waitForElementClickable(WebElement objElement) {
		boolean isVerify = false;
		log.info("Waiting for the element ::" + objElement);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(objElement));
			isVerify = true;
		} catch (Exception e) {
			log.error("Exception is thrown at run time and the test Fails");
			isVerify = false;
		}
		return isVerify;

	}

	/*
	 * Check the Presence of the Element with Text
	 * 
	 */

	public boolean verifyElementPresenceWithText(WebElement objElement, String aText) {
		boolean isVerify = false;
		log.info("Verifying the Presence of Element" + objElement);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.textToBePresentInElement(objElement, aText));
			isVerify = true;
		} catch (Exception e) {
			log.error(
					"Exception is thrown at run time while verifying the presence of Element wit Text and the test Fails");
			isVerify = false;
		}
		return isVerify;

	}

	@SuppressWarnings("deprecation")
	public void waitForAjaxToComplete() {
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
		fluentWait.withTimeout(20, TimeUnit.SECONDS).pollingEvery(300, TimeUnit.MILLISECONDS)
				.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver input) {
						Boolean isJqueryComplete = (Boolean) ((JavascriptExecutor) driver)
								.executeScript("return jQuery.active==0");

						if (isJqueryComplete) {
							log.info("The Ajax calls are completed!!!");
							return true;
						} else {
							return false;
						}
					}
				});
	}

	public boolean verifyElementPresence(WebElement objElement, int timeOut) {
		boolean isVerify = false;
		log.info("Verifying the Presence of Element" + objElement);
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(objElement));
			isVerify = true;
		} catch (Exception e) {
			log.error(
					"Exception is thrown at run time while verifying the presence of Element with Text and the test Fails");
			isVerify = false;
		}
		return isVerify;

	}

	public boolean verifyElementPresence(WebElement objElement) {
		boolean isVerify = false;
		log.info("Verifying the Presence of Element" + objElement);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(objElement));
			isVerify = true;
		} catch (Exception e) {
			log.error(
					"Exception is thrown at run time while verifying the presence of Element with Text and the test Fails");
			isVerify = false;
		}
		return isVerify;

	}

	public boolean verifyElementNot(WebElement objElement, int aTimeOut) {
		boolean isVerify = false;
		log.info("Verifying the Presence of Element" + objElement);
		try {
			WebDriverWait wait = new WebDriverWait(driver, aTimeOut);
			wait.until(ExpectedConditions.invisibilityOf(objElement));
			isVerify = true;
		} catch (Exception e) {
			log.error(
					"Exception is thrown at run time while verifying the presence of Element with Text and the test Fails");
			isVerify = false;
		}
		return isVerify;

	}

	public boolean clickByJavaScript(WebElement objElement) {
		boolean isVerify = false;
		log.info("Verifying the Presence of Element" + objElement);
		try {

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", objElement);
			isVerify = true;
		} catch (Exception e) {
			log.error("Exception is thrown at run time while clicking the Element and the Test Fails");
			isVerify = false;
		}
		return isVerify;

	}

	public boolean verifyElementsPresence(WebElement... ele) {

		boolean isVerify = false;
		log.info("The Number of Elements to be verified are ...." + ele.length);
		try {
			for (WebElement element : ele) {
				this.verifyElementPresence(element);
			}

			isVerify = true;
		} catch (Exception e) {
			isVerify = false;
		}
		return isVerify;

	}

}
