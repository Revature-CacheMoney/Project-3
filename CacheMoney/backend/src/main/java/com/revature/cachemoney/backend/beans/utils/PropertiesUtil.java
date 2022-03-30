package com.revature.cachemoney.backend.beans.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream input = null;
        Properties prop = null;
        try {
            input = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(input);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return prop;
    }

    public static String getProperty(String fileName, String property) {
        try {
            return readPropertiesFile(fileName).getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
