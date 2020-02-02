package com.undostres.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Listeners;
import com.undostres.utilities.TestListeners;

/**
 * @Desc: This class holds code specific to file loading
 * 
 */

@Listeners(TestListeners.class)
public class JavaUtilities {

	/**
	 * Load properties file.
	 * 
	 * @param fileName
	 *            the file name
	 * @return the properties
	 */
	public static Properties loadPropertiesFile(String fileName) {
		String user_dir = System.getProperty("user.dir");
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(user_dir + "//src//test//resources//Properties//" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}