package com.walmart.ecom.ui.core;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Lists;


//Designing the CustomAssersion by extending the Assersion for readability

public class CustomAssersion extends Assertion{
	//123
	//456
	//ready
	//123
	//345

	//exect

	//done
	//done1
	//123
	//456
	//test
	//test1
	//test2
	
	  private List<String> assert_messages = Lists.newArrayList();

	
	 private WebDriver driver;
	 
		protected org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	 	    
	    //Initilize the Elements using Page Factory
	    
	    public CustomAssersion(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    //cutom assersion
	    //Test Data 
	    //git
	    //learn
	    //Test

	    
	    
	
		
		@Override
		public void onBeforeAssert(IAssert a) {
			
		      assert_messages.add("BeforeAssert:" + a.getMessage());
		}

		
		//On Assert Failure it will take the screen shot
		@Override
		public void onAssertFailure(IAssert assertCommand) {
			log.error("The Expected Messsage is:"+assertCommand.getExpected()+" and the Actual Message is +" +assertCommand.getActual());
			assert_messages.add("OnlyOnAssertFailure: The Expected Messsage is:"+assertCommand.getExpected()+" and the Actual Message is +" +assertCommand.getActual());
			takeScreenShot();
		}

		@Override
		public void onAssertSuccess(IAssert a) {
			log.info("The Expected Messsage is:" +a.getExpected()+" and the Actual Message is +" + a.getActual());
			assert_messages.add("OnlyOnAssertSuccess: Expected Messsage is:" +a.getExpected() +" and the Actual Message is::" + a.getActual());

	}
		
		public List<String> getAssertMessages() {
		      return assert_messages;
		    }
	
		public void takeScreenShot() {
			  String destDir = "screenshots";
			  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			  new File(destDir).mkdirs();
			  String destFile = dateFormat.format(new Date(0)) + ".png";
			  try {
			   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		}

}
