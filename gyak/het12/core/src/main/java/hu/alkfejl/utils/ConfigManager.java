package hu.alkfejl.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private Properties props = new Properties();

    public ConfigManager(Class<?> c) {
        try {
            props.load(c.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key){
        return props.getProperty(key);
    }
}
