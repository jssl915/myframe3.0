package com.yf.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

/**
 * 读取sys.properities
 */
public class SysProperties {
	static Logger logger = Logger.getLogger(SysProperties.class);
	private static Resource[] locations;
	private static Properties prop;

	public void loadProperties(InputStream is){
		prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initial() throws IOException {
		prop = new Properties();
		for (Resource r : locations) {
			InputStream is = r.getInputStream();
			prop.load(is);
			is.close();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

	public static Properties getProperty() {
		return prop;
	}

	public void setProperty(String key, String value) {
		prop.setProperty(key, value);
		for (Resource r : locations) {
			try {
				OutputStream os = new FileOutputStream(r.getFile());
				prop.store(os, key);
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void setLocations(Resource[] locations) {
		SysProperties.locations = locations;
	}
}
