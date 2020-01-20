package com.picsart.automation.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public enum AppiumDriverType implements DriverSetup {

    ANDROID {
        public AppiumDriverType createAppiumObject(URL appiumServerURL, DesiredCapabilities desiredCapabilities) {
            capabilities = desiredCapabilities;
            serverLocation = appiumServerURL;
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            //capabilities.setCapability(MobileCapabilityType.PLATFORM, "ANDROID");
            //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "nexus_5_8.1");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            capabilities.setCapability(MobileCapabilityType.APP, "/root/tmp/sample_apk/picsart.apk");
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.picsart.studio.light");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.socialin.android.photo.picsinphoto.MainPagerActivity");
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 220);
            capabilities.setCapability("deviceReadyTimeout", 5);
            capabilities.setCapability("ignoreUnimportantViews", true);
            capabilities.setCapability("disableAndroidWatchers", true);

            return this;
        }

        public AppiumDriver getAppiumDriver() {
            return new AndroidDriver(serverLocation, capabilities);
        }

    };
    DesiredCapabilities capabilities;
    URL serverLocation;
}