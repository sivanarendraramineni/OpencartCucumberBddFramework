package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;
    public static Properties initproperties() throws FileNotFoundException {
        prop= new Properties();
        try{
            FileInputStream ip= new FileInputStream("src/test/resources/config.properties");
            prop.load(ip);
        }catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }

    public static String getProperty(String key) throws FileNotFoundException {
        if (prop == null) {
            initproperties();
        }
        return prop.getProperty(key);
    }

}
