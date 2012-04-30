package jp.foresthigashi.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ObjectConverter {
	
	private static final String CLASS_NAME = "ObjectConverter";
	
	public static Properties convert2Prop4HttpRequest(Map target){
		
		final String METHOD_NAME = "convert2Prop4HttpRequest";
		
		Properties prop = new Properties();
		String key = "";
		String [] value = new String[1];
		
		for (Iterator it = target.entrySet().iterator(); it.hasNext();) {
		    Map.Entry entry = (Map.Entry)it.next();
		    key = (String)entry.getKey();
		    value = (String[])entry.getValue();
		    prop.setProperty(key, value[0]);
		}
		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, prop.toString(), TxtWriter.LOG_INFO);
		return prop;
		
	}
	
	public static String convert2JSON(Properties target){
		
		final String METHOD_NAME = "convert2JSON";
		
		StringBuffer buf = new StringBuffer();
		String key = "";
		String value = "";
		
		buf.append("{");
		
		for (Iterator it = target.entrySet().iterator(); it.hasNext();) {
		    Map.Entry entry = (Map.Entry)it.next();
		    key = (String)entry.getKey();
		    value = (String)entry.getValue();
		    buf.append("\"");
		    buf.append(key);
		    buf.append("\"");
		    buf.append(":");
		    buf.append("\"");
		    buf.append(value);
		    buf.append("\"");
		    if(it.hasNext()){
		    	buf.append(",");
		    }		    
		}
		buf.append("}");		
		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, buf.toString(), TxtWriter.LOG_INFO);
		return buf.toString();
	}
	
}
