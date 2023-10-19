package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            String baseDirectory = System.getProperty("user.dir") + "\\config.properties";
            FileInputStream fileInputStream = new FileInputStream(baseDirectory);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean useAuthenticatedProfile() {
        return Boolean.parseBoolean(properties.getProperty("useAuthenticatedProfile"));
    }


    public String getChromeProfileDirectory() {
        return properties.getProperty("chromeUserDataDirectory");
    }

    public String getAppUrl() {
        return properties.getProperty("appUrl");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
