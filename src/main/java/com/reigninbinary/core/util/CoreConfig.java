package com.reigninbinary.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// config file params can be overriden with environment variables of the same name.
// this code checks for the config param name in the system environment first and
// returns the config file value only if the environment variable does not exist.

public class CoreConfig {

	private static final String CONFIG_FILE_ENVPARAM = "CONFIG_FILE";
	private static final String CONFIG_FILE_DEFAULT = "config.properties";
	
	private static final String NOFILE_ERROR = "Unable to load properties file: %s";
	
	private static Properties properties = new Properties();
		
	static {
	
		String configFile = getConfigFilename();

		InputStream inputStream = null;
		try {

			inputStream = CoreConfig.class.getClassLoader().getResourceAsStream(configFile);
			if (inputStream == null) {
		        throw new Exception(String.format(NOFILE_ERROR, configFile));
			}
			properties.load(inputStream);
		}
		catch (Exception e) {
			final String ERRFMT = "config file '%s' is missing. if no config file required then ignore this msg.";
			CoreLogging.logSevere(String.format(ERRFMT, configFile), e);
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					final String ERRFMT = "unable to close input stream for config file '%s'"; 
					CoreLogging.logSevere(String.format(ERRFMT, configFile), e);
				}
			}
		}
	}
	
	public static String getConfigParam(String paramName, String defaultValue) {
		
		String paramValue = getSystemEnvParam(paramName);
		if (paramValue == null || paramValue.isEmpty()) {
			paramValue = getProperty(paramName, defaultValue);
		}
		return paramValue;
	}
	
	public static int getConfigParam(String paramName, int defaultValue) {
		
		String paramValue = getSystemEnvParam(paramName);
		if (paramValue == null || paramValue.isEmpty()) {
			return getProperty(paramName, defaultValue);
		}
		return Integer.parseInt(paramValue);
	}
	
	public static boolean getConfigParam(String paramName, boolean defaultValue) {
		
		String paramValue = getSystemEnvParam(paramName);
		if (paramValue == null || paramValue.isEmpty()) {
			return getProperty(paramName, defaultValue);
		}
		return Boolean.parseBoolean(paramValue);
	}
	
	private static String getSystemEnvParam(String paramName) {
		
		return System.getenv(paramName);
	}

	private static String getProperty(String propertyName, String defaultValue) {
		
		String propertyValue = properties.getProperty(propertyName);
		if (propertyValue == null) {
			propertyValue = defaultValue;
		}		
		return propertyValue;
	}
		
	private static int getProperty(String propertyName, int defaultValue) {
		
		String propertyValue = properties.getProperty(propertyName);
		if (propertyValue != null) {
			return Integer.parseInt(propertyValue);
		}		
		return defaultValue;
	}
		
	private static boolean getProperty(String propertyName, boolean defaultValue) {
		
		String propertyValue = properties.getProperty(propertyName);
		if (propertyValue != null) {
			return Boolean.parseBoolean(propertyValue);
		}		
		return defaultValue;
	}
	
	private static String getConfigFilename() {
		
		String configFile = System.getenv(CONFIG_FILE_ENVPARAM);
		if (configFile == null || configFile.isEmpty()) {
			configFile = CONFIG_FILE_DEFAULT;
		}
		return configFile;
	}
}
