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
	    TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "end.. result："+prop.getProperty(ReservationConstants.KEY_RESERVATION_RESULT, ""), TxtWriter.LOG_INFO);
	    return prop;	    
	}
	
	public String calcTotalAmount(int plan, int adult, int child) {
		String METHOD_NAME = "calcTotalAmount";
		DecimalFormat df = new DecimalFormat("###,###,##0"); 
		String totalAmount = "￥"+df.format((Integer.parseInt(ReservationConstants.PLAN_LIST[plan][1])*adult) + (Integer.parseInt(ReservationConstants.PLAN_LIST[plan][2])*child));		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "end.. totalAmount："+totalAmount, TxtWriter.LOG_INFO);
		return totalAmount;
	}
	
	private String createBody4YourAddress(Properties application){		
		
		StringBuffer buf = new StringBuffer();
		String adultNumber = application.getProperty(ReservationConstants.KEY_APPLICATION_ADULT_NUMBER);
		String childNumber = application.getProperty(ReservationConstants.KEY_APPLICATION_CHILD_NUMBER);
		
		
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANJI)+"様");
		buf.append("\n");
		buf.append("\n");
		buf.append("この度は「フォレストひがし」のツアーにお申し込み頂きありがとうございます。");
		buf.append("\n");
		buf.append("以下の内容でお申し込み承りましたので、ご確認ください。");
		buf.append("\n");
		buf.append("\n");
		buf.append("おなまえ（カナ）：　");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANA));
		buf.append("\n");
		buf.append("おなまえ（漢字）：　");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_NAME_KANJI));
		buf.append("\n");
		buf.append("参加人数：　");
		buf.append("大人"+adultNumber+"名");
		if(adultNumber.equals("9")){
			buf.append("以上");
		} 
		buf.append("、");
		buf.append("こども"+childNumber+"名");
		if(childNumber.equals("9")){
			buf.append("以上、");
		}
		buf.append("\n");
		buf.append("申込プラン：　");
		buf.append(ReservationConstants.PLAN_LIST[Integer.parseInt(application.getProperty(ReservationConstants.KEY_APPLICATION_PLAN))][0]);
		buf.append("\n");
		buf.append("ご希望日：　");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_DATE));
		buf.append("\n");
		buf.append("ご希望時間帯：　");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_TIME));
		buf.append("\n");
		buf.append("お電話番号：　");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_TEL));
		buf.append("\n");
		buf.append("メールアドレス：　");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_MAILADDRESS));
		buf.append("\n");
		buf.append("その他コメント：　");
		buf.append("\n");
		buf.append(application.getProperty(ReservationConstants.KEY_APPLICATION_COMMENT).replace("その他ご要望はこちらに入力して下さい",""));
		buf.append("\n");
		buf.append("合計金額：　");
		buf.append(calcTotalAmount(Integer.parseInt(application.getProperty(ReservationConstants.KEY_APPLICATION_PLAN)),
							Integer.parseInt(application.getProperty(ReservationConstants.KEY_APPLICATION_ADULT_NUMBER)),
							Integer.parseInt(application.getProperty(ReservationConstants.KEY_APPLICATION_CHILD_NUMBER))));
		if(adultNumber.equals("9")|childNumber.equals("9")){
			buf.append("から　（正式な金額は参加人数を確認させて頂いてから提示させて頂きます）");
		}
		buf.append("\n");
		buf.append("\n");
		buf.append("なお、ツアーの詳細な情報（開始時間・終了時間、準備するもの、その他注意事項等）、お支払方法は担当より３日以内に連絡させて頂きます。");
		buf.append("\n");
		buf.append("当日の予約状況からご希望に添えないこともございますので、あらかじめご了承ください。");
		buf.append("\n");
		buf.append("\n");		
		buf.append("--");
		buf.append("\n");
		buf.append("フォレストひがし");
		buf.append("\n");
		buf.append("〒905-1204　沖縄県国頭郡東村字平良498番地");
		buf.append("\n");
		buf.append("TEL：070-5485-9543 MAIL： info@foresthigashi.jp");
		
		return buf.toString();
		
	}
}
