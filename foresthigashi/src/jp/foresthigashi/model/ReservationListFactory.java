package jp.foresthigashi.model;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import jp.foresthigashi.util.TxtWriter;

public class ReservationListFactory {
	
	private static final String CLASS_NAME = "ReservationListFactory";
	
	public IReservationList createReservationList(){
		
		final String METHOD_NAME = "ReservationListFactory";
		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "start..", TxtWriter.LOG_INFO);
		
		IReservationList list = null;
		ResourceBundle bundle;
		final String KEY_LIST_TYPE = "ListType";
		final String VALUE_CVS = "0";
		final String VALUE_DB = "1";
		final String PROPERTIES_PATH = "ReservationList";
		
        try {
            bundle = ResourceBundle.getBundle(PROPERTIES_PATH);
        } catch (MissingResourceException e) {
        	TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, e, TxtWriter.LOG_ERROR);
            return list;
        }

        String value = null;
        try {
            value = bundle.getString(KEY_LIST_TYPE);
        } catch (MissingResourceException e) {
        	TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, e, TxtWriter.LOG_ERROR);
            return list;
        }
		
		if(value.equals(VALUE_CVS)){
			list = new ReservationCVSList();
		} else if(value.equals(VALUE_DB)) {
			list =  new ReservationDBList();
		}
		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "end.. value:"+value, TxtWriter.LOG_INFO);
		
		return list;
		
	}
	
}
