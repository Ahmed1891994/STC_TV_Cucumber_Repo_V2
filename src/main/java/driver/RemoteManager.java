package driver;

import base.TestSetupContext;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ListenToIPAddress;
import utils.MyLogger;

import java.net.URI;
import java.net.UnknownHostException;

public class RemoteManager {
    private String ip;
    private String port;

    public RemoteManager(TestSetupContext testsetupcontext) {
        try {
            port = testsetupcontext.getEnvironment().getgridport();
            MyLogger.info("get port from Environment variable with value : " + port);
            ip = new ListenToIPAddress().getMyIpAddress();
            MyLogger.info("get port from Environment variable or current Host with value : " + ip);
        } catch (UnknownHostException e) {
            MyLogger.error("there is UnknownHostException issue Please Investegate this");
        }
    }

    public RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            MyLogger.info("Save URL and port for Grid -> " + ip + ":" + port);
            String gridURL = String.format("http://%s:%s/wd/hub", ip, port);
            MyLogger.info("send URL and port and capability to remote driver");
            // Use URI to create the URL
            remoteWebDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), capability);
        } catch (java.net.MalformedURLException e) {
            MyLogger.error("there is java.net.MalformedURLException issue Please Investigate this");
        } catch (IllegalArgumentException e) {
            MyLogger.error("there is IllegalArgumentException issue Please Investigate this");
        }
        return remoteWebDriver;
    }
}