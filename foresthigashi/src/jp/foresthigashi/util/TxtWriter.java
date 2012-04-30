package jp.foresthigashi.util;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TxtWriter {
	
	public static final int LOG_DEBUG = 1;
	public static final int LOG_INFO = 2;
	public static final int LOG_ERROR = 3;
	private static final String LOG_PROPERTIES_PATH = "log.properties";
	private static final String CSV_PROPERTIES_PATH = "csv.properties";
	
	public static void writeLog(String classname, String methodname, String message, int level){		
		writeLog(classname, methodname, message, null, level);		
	}
	
	public static void writeLog(String classname, String methodname, Throwable th, int level){
		writeLog(classname, methodname, null, th, level);		
	}
	
	private static void writeLog(String classname, String methodname, String message, Throwable th, int level){		
		Logger logger = Logger.getLogger(TxtWriter.class);		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(LOG_PROPERTIES_PATH);
		PropertyConfigurator.configure(url);		
		if(level==LOG_DEBUG){
			logger.debug(classname+"."+methodname+"(): "+message);			
		} else if(level==LOG_INFO){
			logger.info(classname+"."+methodname+"(): "+message);
		} else if(level==LOG_ERROR){
			logger.error(classname+"."+methodname+"()", th);
		}
	}
	
	public static void writeCSV(String message){		
		Logger logger = Logger.getLogger(TxtWriter.class);
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(CSV_PROPERTIES_PATH);		
		PropertyConfigurator.configure(url);		
		logger.info(message);
	}
}
