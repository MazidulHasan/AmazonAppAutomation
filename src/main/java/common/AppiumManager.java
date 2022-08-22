package common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import common.reporting.ExtentReporting;
import common.utilityFunctions.ReadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import static common.AutomationConstants.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class AppiumManager {
	private static final Logger LOGGER = Logger.getLogger(AppiumManager.class.getName());
	
	private AppiumDriverLocalService localService;
	private AppiumServiceBuilder appiumServiceBuilder;
	private DesiredCapabilities desiredCapabilities;
	public static AppiumDriver moblieDriver;
	ReadProperties readProperties;
	final static int LAUNCH_TIME_OUT = 9999;
	
	public void intializeAppium() {
        startAppiumService();
        startDriver();
    }
	
	public void startAppiumService() {
        readProperties = new ReadProperties();
//        appiumServiceBuilder = new AppiumServiceBuilder()
//                            .usingDriverExecutable(new File(readProperties.getProperties(AutomationConstants.GLOBAL_PROPERTIES_PATH).getProperty(NODE_PATH_KEY)))
//                            .withIPAddress(readProperties.getProperties(AutomationConstants.GLOBAL_PROPERTIES_PATH).getProperty(IP_ADDRESS_KEY))
//                            .usingAnyFreePort()
//                            .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                            .withArgument(GeneralServerFlag.LOG_LEVEL, "error");
       
        appiumServiceBuilder = new AppiumServiceBuilder()
					        .withAppiumJS(new File("C:\\Users\\ThunderStrike\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					        .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
					        .withIPAddress("127.0.0.1")
					        .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
					        .usingPort(4723);

        localService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        localService.start();
        LOGGER.info("Appium Services Started");
        System.out.println("Appium Services Started Rlog");
        ExtentReporting.logInfo("Appium Services Started");
    }
	
	public void startDriver() {
        try {

            if (readProperties.getProperties(AutomationConstants.GLOBAL_PROPERTIES_PATH).getProperty(AutomationConstants.PLATFORM_NAME_KEY).equals("android")) {
            	System.out.println("Selected android driver Rlog");
            	moblieDriver = new AndroidDriver(localService.getUrl(), setCapabilities());
                LOGGER.info("Android Driver Started");
                System.out.println("Mobile driver started Rlog");
                ExtentReporting.logInfo("Android Driver Started");
                moblieDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIME_OUT, TimeUnit.SECONDS);
                System.out.println("Done with start driver Rlog");
            } else {
            	moblieDriver = new IOSDriver(localService.getUrl(), setCapabilities());
                LOGGER.info("iOS Driver Started");
                ExtentReporting.logInfo("iOS Driver Started");
                moblieDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIME_OUT, TimeUnit.SECONDS);
            }
        } catch (WebDriverException e) {
        	System.out.println("Server Not Started Rlog");
        	System.out.println(e);
            LOGGER.error("Server Not Started", e);
        }
        System.out.println("Driver Started Rlog");
    }
	
	public Capabilities setCapabilities() {
        if (readProperties.getProperties(AutomationConstants.GLOBAL_PROPERTIES_PATH).getProperty(PLATFORM_NAME_KEY).equals("android")) {
        	System.out.println("Android capabilities Rlog");
            getAndroidCaps();
            LOGGER.info("Android Capabilities Obtained");
            ExtentReporting.logInfo("Android Capabilities Obtained");
        } else {
            getIOSCaps();
            LOGGER.info("Android Capabilities Obtained");
            ExtentReporting.logInfo("iOS Capabilities Obtained");
        }
        return desiredCapabilities;
    }
	
	public void getAndroidCaps() {
		System.out.println("Inside get androidCaps Rlog");
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, readProperties.getProperties(GLOBAL_PROPERTIES_PATH).getProperty(PLATFORM_NAME_KEY));
        LOGGER.info("Platform Name is " + readProperties.getProperties(GLOBAL_PROPERTIES_PATH).getProperty(PLATFORM_NAME_KEY));
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(DEVICE_ID));
        LOGGER.info("UDID is " + readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(DEVICE_ID));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(DEVICE_NAME));
        LOGGER.info("Device Name is " + readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(DEVICE_NAME));
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, NO_RESET);
        LOGGER.info("No Rest is set to" + NO_RESET);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, NO_RESET);
        LOGGER.info("Auto Grant Permission is " + NO_RESET);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(AUTOMATION_NAME));
        LOGGER.info("Automation Name is " + readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(AUTOMATION_NAME));
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(APP_PACKAGE_ANDROID));
        LOGGER.info("App Package is " + readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(APP_PACKAGE_ANDROID));
        
        desiredCapabilities.setCapability(MobileCapabilityType.APP, readProperties.getProperties(GLOBAL_PROPERTIES_PATH).getProperty(APP_PATH));
        
//        LOGGER.info("App path is set to " + readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(APP_PATH));
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, LAUNCH_TIME_OUT);
        LOGGER.info("Launch time is set to " + LAUNCH_TIME_OUT);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, LAUNCH_TIME_OUT);
        LOGGER.info("App wait is set to " + LAUNCH_TIME_OUT);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(APP_ACTIVITY_ONE));
        LOGGER.info("APP wait Activity is  " + readProperties.getProperties(ANDROID_TEST_DATA_PROPERTIES).getProperty(APP_ACTIVITY_ONE));
        System.out.println("Done with androidCaps Rlog");
    }
	
	public void getIOSCaps() {
        desiredCapabilities.setCapability(PLATFORM_NAME, readProperties.getProperties(GLOBAL_PROPERTIES_PATH).getProperty(PLATFORM_NAME_KEY));
        LOGGER.info("Platform Name is " + readProperties.getProperties(GLOBAL_PROPERTIES_PATH).getProperty(PLATFORM_NAME_KEY));
        desiredCapabilities.setCapability(AUTOMATION_NAME, readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(AUTOMATION_NAME_IOS));
        LOGGER.info("Automation Name is " + readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(AUTOMATION_NAME));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(DEVICE_NAME_IOS));
        LOGGER.info("Device Name is " + readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(DEVICE_NAME));
        desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(BUNDLE_ID_IOS));
        LOGGER.info("Bundle Id is " + readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(BUNDLE_ID_IOS));
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(DEVICE_ID_IOS));
        LOGGER.info("UDID is " + readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(DEVICE_ID_IOS));
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(DEVICE_VERSION_IOS));
        LOGGER.info("Device Version is " + readProperties.getProperties(IOS_TEST_DATA_PROPERTIES).getProperty(DEVICE_VERSION_IOS));
    }
	
	public void stopAppium() {
        stopDriver();
        stopAppiumService();
    }
	
	public void stopDriver() {
        ExtentReporting.logInfo("Drivers Stopped");
        LOGGER.info("Drivers Stopped");
    }
	
	public void stopAppiumService() {
        if (localService.isRunning()) {
            localService.stop();
        }
    }
}
