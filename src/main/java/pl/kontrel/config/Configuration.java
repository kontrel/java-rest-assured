package pl.kontrel.config;

import java.util.Properties;

public class Configuration {
    
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Configuration.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration properties", e);
        }
    }

    public static String getUrl() {
        return properties.getProperty("url");
    }
}
