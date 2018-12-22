package com.reigninbinary.core;

import java.util.logging.Level;
import java.util.logging.Logger;


public class CoreLogging {

	private static final Logger logger = Logger.getLogger(CoreLogging.class.getName());

	public static boolean isLogInfoEnabled() {
		
		final String 	LOGINFO_ENABLED 		= "LOGINFO_ENABLED";
		final boolean 	LOGINFO_ENABLED_DEFAULT = false;
		
		return CoreConfig.getConfigParam(LOGINFO_ENABLED, LOGINFO_ENABLED_DEFAULT);
	}
	
	public static boolean isLogWarnEnabled() {
		
		final String 	LOGWARN_ENABLED 		= "LOGWARN_ENABLED";
		final boolean 	LOGWARN_ENABLED_DEFAULT = false;
		
		return CoreConfig.getConfigParam(LOGWARN_ENABLED, LOGWARN_ENABLED_DEFAULT);
	}

	public static void logInfo(String msg) {	
		
		if (isLogInfoEnabled()) {			
			logger.info(msg);
		}
	}
	
	public static void logWarning(String msg) {
		
		if (isLogWarnEnabled()) {			
			logger.warning(msg);
		}
	}
	
	public static void logSevere(String msg) {
		
		logger.severe(msg);
	}
	
	public static void logSevere(String msg, Exception e) {
		
		logger.log(Level.SEVERE, msg, e);
	}
}
