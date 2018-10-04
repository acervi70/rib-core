package com.reigninbinary.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreLogging {

	private static final Logger logger = Logger.getLogger(CoreLogging.class.getName());

	private static final String LOGINFO_ENABLED = "LOGINFO_ENABLED";
	private static final boolean  LOGINFO_ENABLED_DEFAULT = false;
	
	private static final String LOGWARN_ENABLED = "LOGWARN_ENABLED";
	private static final boolean  LOGWARN_ENABLED_DEFAULT = false;
	
	public static boolean isLogInfoEnabled() {
		
		return CoreConfig.getConfigParam(LOGINFO_ENABLED, LOGINFO_ENABLED_DEFAULT);
	}
	
	public static boolean isLogWarnEnabled() {
		
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
