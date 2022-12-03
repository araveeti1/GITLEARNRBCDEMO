package com.walmart.ecom.ui.core;

import java.awt.AWTException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LamdaTestDownloadFile {
	
	WebDriver driver;
	//ff
	//IE
	//aedge
	//driver
	//chroe
	//firefox
	//test


		
	
	@Test
	
	    public void fileDownload() throws AWTException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/venkat/Documents/WalmarteCommerceDemo/walmarteCom/chromedriver 2");   

		
		 HashMap<String, Object> chromePrefs = new HashMap<>();
	        chromePrefs.put("profile.default_content_settings.popups", 0);
	        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));

	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("prefs", chromePrefs);

	        WebDriver driver = new ChromeDriver(options);
		
		

		
		
        //ChromeOptions options = new ChromeOptions();

        

        //Map<String, Object> prefs = new HashMap<String, Object>();

       // prefs.put("download.prompt_for_download", false);

       // options.setExperimentalOption("prefs", prefs);

        

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.manage().window().maximize();

	        
	
	        driver.get("https://chromedriver.storage.googleapis.com/index.html?path=79.0.3945.36/");
	
	        Thread.sleep(2000);
	
	        WebElement btnDownload = driver.findElement(By.xpath(".//a[text()='chromedriver_win32.zip']"));
	
	        btnDownload.click();
	
	        
	
	        Thread.sleep(7000);
	        File folder = new File(System.getProperty("user.dir"));

	
	        File[] listOfFiles = folder.listFiles();

	        boolean found = false;
	        File f = null;

	        //Look for the file in the files
	        // You should write smart REGEX according to the filename
	        for (File listOfFile : listOfFiles) {
	            if (listOfFile.isFile()) {
	                String fileName = listOfFile.getName();
	                System.out.println("File " + listOfFile.getName());
	                if (fileName.matches("chromedriver_win32.zip" )) {
	                    f = new File(fileName);
	                    found = true;
	                }

	            }
	        }

	       // Assertions.assertTrue(found, "Downloaded document is not found");
	        f.deleteOnExit();
	        driver.close();
	
	    }
	


}
