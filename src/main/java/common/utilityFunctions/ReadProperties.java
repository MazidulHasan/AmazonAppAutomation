package common.utilityFunctions;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ReadProperties {
	Properties properties = new Properties();
	
	private static Logger logger = Logger.getLogger(ReadProperties.class.getName());
	
	
	public Properties getProperties(String path) {
		try {
			FileInputStream fin = new FileInputStream(path);
			properties.load(fin);
		} catch (Exception e) {
			logger.error("Caught an IO exception while reading properties file",e);
		}
		return properties;
	}
}
