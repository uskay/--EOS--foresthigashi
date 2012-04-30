package jp.foresthigashi.constants;

public interface ReservationConstants {
	
	public static final String KEY_RESERVATION_RESULT 		= "RESERVATION_RESULT";
	public static final String KEY_RESERVATION_CHECK_RESULT	= "RESERVATION_CHECK_RESULT";
	public static final String RESERVATION_COMPLETE 	= "";
	public static final String RESERVATION_ERROR_INVALID_MAIL_ADDRESS = "メールアドレスが無効です";
	public static final String RESERVATION_ERROR_SEND_FAIL = "（障害発生）お電話にてお問い合わせください";
	
	public static final String KEY_APPLICATION_NAME_KANA 	= "NAME_KANA";
	public static final String KEY_APPLICATION_NAME_KANJI 	= "NAME_KANJI";
	public static final String KEY_APPLICATION_ADULT_NUMBER	= "ADULT_NUMBER";
	public static final String KEY_APPLICATION_CHILD_NUMBER	= "CHILD_NUMBER";
	public static final String KEY_APPLICATION_PLAN 		= "PLAN";
	public static final String KEY_APPLICATION_DATE 		= "DATE";
	public static final String KEY_APPLICATION_TIME 		= "TIME";
	public static final String KEY_APPLICATION_TEL 			= "TEL";
	public static final String KEY_APPLICATION_MAILADDRESS 	= "MAILADDRESS";
	public static final String KEY_APPLICATION_MAILADDRESS2 = "MAILADDRESS2";
	public static final String KEY_APPLICATION_COMMENT 		= "COMMNET";
	
	public static final String MAIL_SUBJECT				= "［フォレストひがし］お申し込み受付のお知らせ";
	
	public static final String RESERVATION_CONFIRM_ERROR_001 = "おなまえ（全角カナ）を入力してください";
	public static final String RESERVATION_CONFIRM_ERROR_002 = "おなまえ（全角カナ）は全角カナで入力してください";
	public static final String RESERVATION_CONFIRM_ERROR_003 = "おなまえ（漢字）を入力してください";
	public static final String RESERVATION_CONFIRM_ERROR_004 = "参加人数を選択してください";
	public static final String RESERVATION_CONFIRM_ERROR_005 = "ご希望日を入力してください";
	public static final String RESERVATION_CONFIRM_ERROR_006 = "ご希望日はYYYY/MM/DD形式で入力してください";
	public static final String RESERVATION_CONFIRM_ERROR_007 = "お電話番号を入力してください";
	public static final String RESERVATION_CONFIRM_ERROR_008 = "お電話番号は半角数字で入力してください";
	public static final String RESERVATION_CONFIRM_ERROR_009 = "メールアドレスを入力してください";
	public static final String RESERVATION_CONFIRM_ERROR_010 = "メールアドレスと再入力が一致しません";
	public static final String RESERVATION_CONFIRM_ERROR_011 = "メールアドレスは半角英数字で入力してください";
	
	public static final String KEY_RESERVATION_TOTAL_AMOUNT = "TOTAL_AMOUNT";
	public static final String[][] PLAN_LIST = {{"慶佐次川マングローブカヌー体験","6000","4000"},
												{"慶佐次川ナイトカヌー体験","6000","4000"},
												{"太平洋＆マングローブカヌー体験","9000","6000"},
												{"慶佐次川マングローブトレッキング","2000","1500"},
												{"やんばるの森トレッキング","6000","4000"},
												{"新川川リバートレッキング","7000","5000"}};
	
	public static final int WRITE_CSV_SUCCESS = 0;
	public static final int WRITE_CSV_FAIL_OTHER = 9;
}
