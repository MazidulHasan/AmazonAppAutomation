package common.utilityFunctions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.AutomationConstants;
import common.reporting.ExtentReporting;

import static common.AppiumManager.moblieDriver;

import java.time.Duration;

public class Page {
	private static Logger Log = Logger.getLogger(Page.class.getName());
	
	
	public static String getScreenShot() {
        TakesScreenshot takesScreenshots = moblieDriver;
        String dest = takesScreenshots.getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + dest;
    }
	
	public static void getScreenShotAsOutPut() {
//        TakesScreenshot takesScreenshots = moblieDriver;
//        File source = takesScreenshots.getScreenshotAs(OutputType.FILE);
//        String destination = AutomationConstants.SCREENSHOT_PATH;
//        File finalDestination = new File(destination);
//        try {
//            FileUtil.copyFile(source, finalDestination);
//        } catch (IOException e) {
//            Log.error("Could not Capture Screenshot", e);
//        }
    }
	
	protected boolean clickBy(WebElement locator, String buttonName) throws InterruptedException {
		
		System.out.println("Inside clickby Rlog");
		System.out.println("Locator is Rlog"+ locator);
		System.out.println("Button name is Rlog"+ buttonName);
        try {
        	System.out.println("Time out time check: "+ AutomationConstants.WAIT_UNTIL_ELEMENT);
            
        	
        	new WebDriverWait(moblieDriver, AutomationConstants.WAIT_UNTIL_ELEMENT).until(ExpectedConditions.elementToBeClickable(locator)).click();
            
        	
        	
//        	 WebDriverWait wait = new WebDriverWait(moblieDriver, AutomationConstants.WAIT_UNTIL_ELEMENT);
//
//        	 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//        	 element.click();
        	

        	
//        	moblieDriver.findElement(By.xpath(locator));
        	
            
        	System.out.println("Click done");
            ExtentReporting.logInfo("Clicked on " + buttonName);
            Log.info("Clicked on " + buttonName);
        } catch (WebDriverException e) {
            ExtentReporting.logFail("Could not Click " + buttonName, true);
            Log.error("Could not click " + buttonName, e);
            return false;
        }
        return true;
    }

}
