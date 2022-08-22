import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import common.AppiumManager;
import common.reporting.ExtentReporting;

public class BaseTestClass {
	public AppiumManager appiumManager;
	
	@BeforeSuite(alwaysRun = true)
	public void startTest() {
		ExtentReporting.startReporting();
		appiumManager = new AppiumManager();
		appiumManager.intializeAppium();
		System.out.println("All service started Started Rlog");
	}
	
	@AfterSuite(alwaysRun = true)
	public void stopTest() {
		ExtentReporting.stopReporting();
		appiumManager.stopAppium();
	}
}
