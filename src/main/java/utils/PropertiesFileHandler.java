package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileHandler {
    //load properties file from folder
    public Properties loadProperties(String resourceName) throws IOException {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configurations/" + resourceName + ".properties")) {
            MyLogger.info("Loaded property file: " + resourceName);
            prop.load(fis);
        } catch (IOException e) {
            MyLogger.error("Error loading property file: " + resourceName);
            throw e;
        }
        return prop;
    }
}