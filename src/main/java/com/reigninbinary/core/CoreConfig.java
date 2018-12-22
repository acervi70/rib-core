package com.reigninbinary.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

// config file params can be overriden with environment variables of the same name.
// this code checks for the config param name in the system environment first and
// returns the config file value only if the environment variable does not exist.

public class CoreConfig {

	private static final Properties properties = new Properties();
	
	static {
		String configFile = getConfigFilename();

		InputStream inputStream = null;
		try {
			inputStream = CoreConfig.class.getClassLoader().getResourceAsStream(configFile);
			if (inputStream == null) {
				final String NOFILE_ERROR = "Unable to load properties file: %s";
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
				} 
				catch (IOException e) {
					final String ERRFMT = "unable to close input stream for config file '%s'"; 
					CoreLogging.logSevere(String.format(ERRFMT, configFile), e);
				}
			}
		}
	}	
	
	public static String getConfigParam(String paramName, String defaultValue) {
		
		String paramValue = getSystemEnvParam(paramName);
		if (StringUtils.isNotEmpty(paramValue)) {
			return paramValue;
		}
		return getProperty(paramName, defaultValue);
	}
	
	public static boolean getConfigParam(String paramName, boolean defaultValue) {
		
		String paramValue = getSystemEnvParam(paramName);
		if (StringUtils.isNotEmpty(paramValue)) {
			return Boolean.parseBoolean(paramValue);
		}
		return getProperty(paramName, defaultValue);
	}

	public static int getConfigParam(String paramName, int defaultValue) {
		
		String paramValue = getSystemEnvParam(paramName);
		if (StringUtils.isNotEmpty(paramValue)) {
			return Integer.parseInt(paramValue);
		}
		return getProperty(paramName, defaultValue);
	}
	
	public static float getConfigParam(String paramName, float defaultValue) {
		
		String paramValue = getSystemEnvParam(paramName);
		if (StringUtils.isNotEmpty(paramValue)) {
			return Float.parseFloat(paramValue);
		}
		return getProperty(paramName, defaultValue);
	}
	
	public static double getConfigParam(String paramName, double defaultValue) {
		
		String paramValue = getSystemEnvParam(paramName);
		if (StringUtils.isNotEmpty(paramValue)) {
			return Double.parseDouble(paramValue);
		}
		return getProperty(paramName, defaultValue);
	}
	
	private static String getSystemEnvParam(String paramName) {
		
		return System.getenv(paramName);
	}

	private static String getProperty(String propertyName, String defaultValue) {
		
		String propertyValue = properties.getProperty(propertyName);
		if (StringUtils.isNotEmpty(propertyValue)) {
			return propertyValue;
		}		
		return defaultValue;
	}
		
	private static boolean getProperty(String propertyName, boolean defaultValue) {
		
		String propertyValue = properties.getProperty(propertyName);
		if (StringUtils.isNotEmpty(propertyValue)) {
			return Boolean.parseBoolean(propertyValue);
		}		
		return defaultValue;
	}
	
	private static int getProperty(String propertyName, int defaultValue) {
		
		String propertyValue = properties.getProperty(propertyName);
		if (StringUtils.isNotEmpty(propertyValue)) {
			return Integer.parseInt(propertyValue);
		}		
		return defaultValue;
	}
		
	private static float getProperty(String propertyName, float defaultValue) {
		
		String propertyValue = properties.getProperty(propertyName);
		if (StringUtils.isNotEmpty(propertyValue)) {
			return Integer.parseInt(propertyValue);
		}		
		return defaultValue;
	}
		
	private static double getProperty(String propertyName, double defaultValue) {
		
		String propertyValue = properties.getProperty(propertyName);
		if (StringUtils.isNotEmpty(propertyValue)) {
			return Integer.parseInt(propertyValue);
		}		
		return defaultValue;
	}
		
	private static String getConfigFilename() {
		
		final String CONFIG_FILE 			= "CONFIG_FILE";
		final String CONFIG_FILE_DEFAULT 	= "config.properties";
		
		String configFile = System.getenv(CONFIG_FILE);		
		if (StringUtils.isNotEmpty(configFile)) {
			return configFile;
		}
		return CONFIG_FILE_DEFAULT;
	}
}
