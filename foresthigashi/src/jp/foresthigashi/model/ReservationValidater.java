package jp.foresthigashi.model;

import java.util.Properties;

import jp.foresthigashi.constants.ReservationConstants;

public class ReservationValidater {
	
	private static final String P_FULL_KANA = "^[\\u30A0-\\u30FF]+$";
	private static final String P_ASCII = "^[\\u0020-\\u007E]+$";
	private static final String P_HALF_NUMBER = "^[0-9]+$";
	
	public Properties validate(Properties target){
		
		Properties prop = new Properties();
		String message = "";
		
		String nameKana = target.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANA, "");
		String nameKanji = target.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANJI, "");
		String adultNumber = target.getProperty(ReservationConstants.KEY_APPLICATION_ADULT_NUMBER, "");
		String childNumber = target.getProperty(ReservationConstants.KEY_APPLICATION_CHILD_NUMBER, "");
		String date = target.getProperty(ReservationConstants.KEY_APPLICATION_DATE, "");
		String tel = target.getProperty(ReservationConstants.KEY_APPLICATION_TEL, "");
		String mailAddress = target.getProperty(ReservationConstants.KEY_APPLICATION_MAILADDRESS, "");
		String mailAddress2 = target.getProperty(ReservationConstants.KEY_APPLICATION_MAILADDRESS2, "");
		
		if(nameKana.equals("")){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_001;
		} else if(!isFullKana(nameKana.trim().replace("Å@", "").replace(" ",""))){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_002;
		} else if(nameKanji.equals("")){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_003;
		} else if(adultNumber.equals("0")&&childNumber.equals("0")){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_004;
		} else if(date.equals("")){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_005;
		} else if(!(isHalfNumber(date.trim().replace("/", ""))&&date.trim().replace("/", "").length()==8)){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_006;
		} else if(tel.equals("")){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_007;
		} else if(!isHalfNumber(tel.trim().replace("-", ""))){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_008;
		} else if(mailAddress.equals("")){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_009;
		} else if(!(mailAddress.equals(mailAddress2))){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_010;
		} else if(!isACII(mailAddress)){
			message = ReservationConstants.RESERVATION_CONFIRM_ERROR_011;
		} else{
			message = "";
			StringBuffer totalAmount = new StringBuffer();
			totalAmount.append(new ReservationAgent().calcTotalAmount(Integer.parseInt(target.getProperty(ReservationConstants.KEY_APPLICATION_PLAN)),
					Integer.parseInt(target.getProperty(ReservationConstants.KEY_APPLICATION_ADULT_NUMBER)),
					Integer.parseInt(target.getProperty(ReservationConstants.KEY_APPLICATION_CHILD_NUMBER))));
			if(adultNumber.equals("9")|childNumber.equals("9")){
				totalAmount.append("Å`");
			}
			prop.setProperty(ReservationConstants.KEY_RESERVATION_TOTAL_AMOUNT, totalAmount.toString());
		}		
		
		prop.setProperty(ReservationConstants.KEY_RESERVATION_CHECK_RESULT, message);
		
		return prop;
		
	}
	
	private boolean isFullKana(String target){
		return target.matches(P_FULL_KANA);
	}
	
	private boolean isACII(String target){
		return target.matches(P_ASCII);
	}
	
	private boolean isHalfNumber(String target){
		return target.matches(P_HALF_NUMBER);
	}

}
