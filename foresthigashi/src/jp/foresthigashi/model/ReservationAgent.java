package jp.foresthigashi.model;

import java.text.DecimalFormat;
import java.util.Properties;

import jp.foresthigashi.constants.MessengerConstants;
import jp.foresthigashi.constants.ReservationConstants;
import jp.foresthigashi.util.Messenger;
import jp.foresthigashi.util.TxtWriter;

public class ReservationAgent {
	
	private static final String CLASS_NAME = "ReservationAgent";
	
	public Properties reserve(Properties application){
		
		String METHOD_NAME = "reserve";		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "start..", TxtWriter.LOG_INFO);
		
		Properties prop = new Properties();
			    
	    int result = new Messenger().sendMail(
	    		application.getProperty(ReservationConstants.KEY_APPLICATION_MAILADDRESS),
	    		MessengerConstants.BCC,
	    		ReservationConstants.MAIL_SUBJECT,
	    		createBody4YourAddress(application));
	    
	    if(result==MessengerConstants.SEND_SUCCESS){	    	
	    	new ReservationListFactory().
	    				createReservationList().createReservationList(application);	    	
	    	prop.setProperty(ReservationConstants.KEY_RESERVATION_RESULT, ReservationConstants.RESERVATION_COMPLETE);
	    } else if(result==MessengerConstants.SEND_FAIL_INVALID_ADDRESS_TO){	    	
	    	prop.setProperty(ReservationConstants.KEY_RESERVATION_RESULT, ReservationConstants.RESERVATION_ERROR_INVALID_MAIL_ADDRESS);	    	
	    } else{	    
	    	prop.setProperty(ReservationConstants.KEY_RESERVATION_RESULT, ReservationConstants.RESERVATION_ERROR_SEND_FAIL);	    	
	    }
	    TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "end.. result�F"+prop.getProperty(ReservationConstants.KEY_RESERVATION_RESULT, ""), TxtWriter.LOG_INFO);
	    return prop;	    
	}
	
	public String calcTotalAmount(int plan, int adult, int child) {
		String METHOD_NAME = "calcTotalAmount";
		DecimalFormat df = new DecimalFormat("###,###,##0"); 
		String totalAmount = "��"+df.format((Integer.parseInt(ReservationConstants.PLAN_LIST[plan][1])*adult) + (Integer.parseInt(ReservationConstants.PLAN_LIST[plan][2])*child));		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "end.. totalAmount�F"+totalAmount, TxtWriter.LOG_INFO);
		return totalAmount;
	}
	
	private String createBody4YourAddress(Properties application){		
		
		StringBuffer buf = new StringBuffer();
		String adultNumber = application.getProperty(ReservationConstants.KEY_APPLICATION_ADULT_NUMBER);
		String childNumber = application.getProperty(ReservationConstants.KEY_APPLICATION_CHILD_NUMBER);
		
		
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANJI)+"�l");
		buf.append("\n");
		buf.append("\n");
		buf.append("���̓x�́u�t�H���X�g�Ђ����v�̃c�A�[�ɂ��\�����ݒ������肪�Ƃ��������܂��B");
		buf.append("\n");
		buf.append("�ȉ��̓��e�ł��\�����ݏ���܂����̂ŁA���m�F���������B");
		buf.append("\n");
		buf.append("\n");
		buf.append("���Ȃ܂��i�J�i�j�F�@");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANA));
		buf.append("\n");
		buf.append("���Ȃ܂��i�����j�F�@");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANJI));
		buf.append("\n");
		buf.append("�Q���l���F�@");
		buf.append("��l"+adultNumber+"��");
		if(adultNumber.equals("9")){
			buf.append("�ȏ�");
		} 
		buf.append("�A");
		buf.append("���ǂ�"+childNumber+"��");
		if(childNumber.equals("9")){
			buf.append("�ȏ�A");
		}
		buf.append("\n");
		buf.append("�\���v�����F�@");
		buf.append(ReservationConstants.PLAN_LIST[Integer.parseInt(application.getProperty(ReservationConstants.KEY_APPLICATION_PLAN))][0]);
		buf.append("\n");
		buf.append("����]���F�@");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_DATE));
		buf.append("\n");
		buf.append("����]���ԑсF�@");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_TIME));
		buf.append("\n");
		buf.append("���d�b�ԍ��F�@");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_TEL));
		buf.append("\n");
		buf.append("���[���A�h���X�F�@");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_MAILADDRESS));
		buf.append("\n");
		buf.append("���̑��R�����g�F�@");
		buf.append("\n");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_COMMENT).replace("���̑����v�]�͂�����ɓ��͂��ĉ�����",""));
		buf.append("\n");
		buf.append("���v���z�F�@");
		buf.append(calcTotalAmount(Integer.parseInt(application.getProperty(ReservationConstants.KEY_APPLICATION_PLAN)),
							Integer.parseInt(application.getProperty(ReservationConstants.KEY_APPLICATION_ADULT_NUMBER)),
							Integer.parseInt(application.getProperty(ReservationConstants.KEY_APPLICATION_CHILD_NUMBER))));
		if(adultNumber.equals("9")|childNumber.equals("9")){
			buf.append("����@�i�����ȋ��z�͎Q���l�����m�F�����Ē����Ă���񎦂����Ē����܂��j");
		}
		buf.append("\n");
		buf.append("\n");
		buf.append("�Ȃ��A�c�A�[�̏ڍׂȏ��i�J�n���ԁE�I�����ԁA����������́A���̑����ӎ������j�A���x�����@�͒S�����R���ȓ��ɘA�������Ē����܂��B");
		buf.append("\n");
		buf.append("�����̗\��󋵂��炲��]�ɓY���Ȃ����Ƃ��������܂��̂ŁA���炩���߂��������������B");
		buf.append("\n");
		buf.append("\n");		
		buf.append("--");
		buf.append("\n");
		buf.append("�t�H���X�g�Ђ���");
		buf.append("\n");
		buf.append("��905-1204�@���ꌧ�����S����������498�Ԓn");
		buf.append("\n");
		buf.append("TEL�F070-5485-9543 MAIL�F info@foresthigashi.jp");
		
		return buf.toString();
		
	}
}
