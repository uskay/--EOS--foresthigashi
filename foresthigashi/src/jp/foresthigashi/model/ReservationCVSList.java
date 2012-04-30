package jp.foresthigashi.model;

import java.util.Properties;

import jp.foresthigashi.constants.ReservationConstants;
import jp.foresthigashi.util.TxtWriter;

public class ReservationCVSList implements IReservationList {
	
	private static final String CLASS_NAME = "ReservationCVSList";
	
	public int createReservationList(Properties application) {
		
		String METHOD_NAME = "createReservationList";
		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "start..", TxtWriter.LOG_INFO);
		
		int result = ReservationConstants.WRITE_CSV_SUCCESS;
		StringBuffer buf = new StringBuffer();
		
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANA));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANJI));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_ADULT_NUMBER));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_CHILD_NUMBER));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_PLAN));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_DATE));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_TIME));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_TEL));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_MAILADDRESS));
		buf.append(",");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_COMMENT).
				replace("ÇªÇÃëºÇ≤óvñ]ÇÕÇ±ÇøÇÁÇ…ì¸óÕÇµÇƒâ∫Ç≥Ç¢","").replace("\n", "Å@").replace(",", "ÅC"));
		
		try{
			TxtWriter.writeCSV(buf.toString());
		} catch(Exception e){
			TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, e, TxtWriter.LOG_ERROR);
			result = ReservationConstants.WRITE_CSV_FAIL_OTHER;
		}
		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "end.. result:"+Integer.toString(result), TxtWriter.LOG_INFO);
		
		return result;
	}
}
