package com.walmart.ecom.ui.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

import com.walmart.ecom.ui.core.ExcelHandler;

public class WalmartTestBase {

	public String aTestDataPath;

	// Properties aConfFile;

	public boolean isCloseBrowserForEachTest = true;
	public boolean isBrowserAlive = false;
	public String quitBrowserForEachTest = null;
	public static Properties aLocatorsData = new Properties();

	public static Properties aTestData = new Properties();
	public Properties aConfFile = new Properties();

	public WalmartTestBase testBase;
	public Properties aConfile;

	public static WebDriver driver = null;
	public String walmartUrl = null;
	
	CustomAssersion customAssert = null;
	
	

	public Properties getPropertiesFromFile(String fileName) throws IOException {

		String filetoBeLoaded = getBaseFolder() + File.separator + fileName;
		FileInputStream fin = null;
		try {

			fin = new FileInputStream(new File(filetoBeLoaded));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("This is the Class for handling the Properties files" + fileName);

		Properties aPropConf = new Properties();
		aPropConf.load(fin);

		return aPropConf;
	}

	public String getBaseFolder() {
		String homePath = System.getProperty("user.dir");
		return homePath;

	}

	@Parameters(value = { "config-file" })
	@BeforeSuite
	public void loadClassConfigurations(String configfile) throws IOException {
		aConfile = this.getPropertiesFromFile(configfile);
		aTestDataPath = getBaseFolder()+File.separator+"TestData"+File.separator+aConfile.getProperty("TEST_DATA_PATH");
		walmartUrl = aConfile.getProperty("BASE_URL");
		quitBrowserForEachTest = aConfile.getProperty("CLOSE_BROWSER_FOREACH_TEST").toLowerCase();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeEachTest() throws Exception {
		DesiredCapabilities capabilities = null;

		if (!isBrowserAlive) {

			if (aConfile.getProperty("EXECUTION").toLowerCase().contains("local")) {

				String aBrowser = aConfile.getProperty("BROWSER_NAME").toLowerCase();
				System.out.println(aBrowser);

				switch (aBrowser) {

				case "chrome":
					System.setProperty("webdriver.chrome.driver", getBaseFolder() + File.separator + "resources"
							+ File.separator + "chrome" + File.separator + "mac" + File.separator + "chromedriver 3");
					driver = new ChromeDriver();
					isBrowserAlive = true;
					break;
					
				case "firefox":
					System.setProperty("webdriver.gecko.driver", getBaseFolder() + File.separator + "resources"
							+ File.separator + "firefox" + File.separator + "mac" + File.separator + "geckodriver");
					driver = new FirefoxDriver();
					isBrowserAlive = true;
					break;
				default:
					isBrowserAlive = false;
	                throw new Exception("unknown Browser name is Given and the test Fails.Please check the browser name in the config file for the same.");
				}

			}
			
			if(isBrowserAlive) {
				customAssert = new CustomAssersion(driver);
			}

		}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		if (quitBrowserForEachTest.contains("yes")) {

			try {
				driver.quit();
				isBrowserAlive = false;

			} catch (Exception e) {

			}

		}
		
		customAssert.getAssertMessages();

	}
	
	

	@DataProvider(name = "loadTestData")
	public Iterator<Object[]> loadData(Method m) throws IOException {

		return new TestDataFaactory(this, m.getName());
	}

	//Returning  List of Properties for each Execution

	public class TestDataFaactory implements Iterator<Object[]> {

		public WalmartTestBase testBase;
		public Properties aporp;
		LinkedList<Properties> aTestDataSets;
		String CurentMetho;

		int counter;

		public TestDataFaactory(WalmartTestBase testBase, String Method) throws IOException {
			this.testBase = testBase;
			this.CurentMetho = Method;

			ExcelHandler handler = new ExcelHandler();
			aTestDataSets = handler.readProperties(Method,aTestDataPath);
			counter = 0;
		}

		public boolean hasNext() {
			return counter < aTestDataSets.size();
		}

		public Object[] next() {
			// TODO Auto-generated method stub
			// Properties params = new Properties();

			Properties params = aTestDataSets.get(counter);

			Object[] params1 = { params };
			counter++;

			return params1;
		}

		public void remove() {
			// TODO Auto-generated method stub

		}

	}

}