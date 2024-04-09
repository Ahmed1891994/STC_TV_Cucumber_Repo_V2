package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesFileHandler {
    // Load properties file from folder
    public Properties loadProperties(String resourceName) {
        Properties prop = new Properties();
        String filePath = System.getProperty("user.dir") + "/src/main/resources/configurations/" + resourceName + ".properties";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            MyLogger.info("Loaded property file: " + resourceName);
            prop.load(fis);
        } catch (IOException e) {
            MyLogger.error("Error loading property file: " + resourceName, e);
            throw new RuntimeException("Error loading property file: " + resourceName, e);
        }
        return prop;
    }
}