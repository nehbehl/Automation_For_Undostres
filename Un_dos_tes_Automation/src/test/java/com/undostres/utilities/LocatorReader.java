package com.undostres.utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


/*
 * @Desc: This class holds code for reading locator values from the ApplicationLocators.xml file
 */

public class LocatorReader {
	private Document doc;

	
	/**
	 * Read Locator from file
	 * 
	 * @param locatorFile: passing locator file from folder repository
	 */
	public LocatorReader(String locatorFile) {
		SAXReader reader = new SAXReader();
		try {
			String locatorFilePath = System.getProperty(locatorFile);
			InputStream resourceStream;

			if (locatorFilePath == null) {
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				resourceStream = loader.getResourceAsStream("Repository/" + locatorFile);
			} else {

				resourceStream = new FileInputStream(locatorFilePath);
			}
			doc = reader.read(resourceStream);
		} catch (DocumentException e) {
			TestListeners.extentLogger.fail(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLocator(String locator) {
		return doc.selectSingleNode("//" + locator.replace('.', '/')).getText();
	}

}
