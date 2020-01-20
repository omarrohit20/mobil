package com.picsart.automation.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumServerHelper {
    private static Logger LOGGER = Logger.getLogger(AppiumServerHelper.class.getName());

    static {
        DOMConfigurator.configure("log4j.xml");
    }
    
    private AppiumServerHelper() {}

    public static URL appiumUrl;
    private static AppiumDriverLocalService service;

    public static void startServer() {
        if (System.getProperty("appium.url") == null) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1");
            builder.usingAnyFreePort();
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
            appiumUrl = service.getUrl();
            LOGGER.info("Appium server is running on " + appiumUrl);
        } else {
            try {
                appiumUrl = new URL(System.getProperty("appium.url"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

}
