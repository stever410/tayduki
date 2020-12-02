/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author ngota
 */
public class PropertiesUtils implements Serializable {

    private static final String FILE_CONFIG = "dispatcher.properties";

    public String getProperties(String resource) throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream(FILE_CONFIG));
        return properties.getProperty(resource);
    }
}
