package jp.foresthigashi.constants;

public interface ReservationConstants {
	
	public static final String KEY_RESERVATION_RESULT 		= "RESERVATION_RESULT";
	public static final String KEY_RESERVATION_CHECK_RESULT	= "RESERVATION_CHECK_RESULT";
	public static final String RESERVATION_COMPLETE 	= "";
	public static final String RESERVATION_ERROR_INVALID_MAIL_ADDRESS = "���[���A�h���X�������ł�";
	public static final String RESERVATION_ERROR_SEND_FAIL = "�i��Q�����j���d�b�ɂĂ��₢���킹��������";
	
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
	
	public static final String MAIL_SUBJECT				= "�m�t�H���X�g�Ђ����n���\�����ݎ�t�̂��m�点";
	
	public static final String RESERVATION_CONFIRM_ERROR_001 = "���Ȃ܂��i�S�p�J�i�j����͂��Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_002 = "���Ȃ܂��i�S�p�J�i�j�͑S�p�J�i�œ��͂��Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_003 = "���Ȃ܂��i�����j����͂��Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_004 = "�Q���l����I�����Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_005 = "����]������͂��Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_006 = "����]����YYYY/MM/DD�`���œ��͂��Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_007 = "���d�b�ԍ�����͂��Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_008 = "���d�b�ԍ��͔��p�����œ��͂��Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_009 = "���[���A�h���X����͂��Ă�������";
	public static final String RESERVATION_CONFIRM_ERROR_010 = "���[���A�h���X�ƍē��͂���v���܂���";
	public static final String RESERVATION_CONFIRM_ERROR_011 = "���[���A�h���X�͔��p�p�����œ��͂��Ă�������";
	
	public static final String KEY_RESERVATION_TOTAL_AMOUNT = "TOTAL_AMOUNT";
	public static final String[][] PLAN_LIST = {{"�c������}���O���[�u�J�k�[�̌�","6000","4000"},
												{"�c������i�C�g�J�k�[�̌�","6000","4000"},
												{"�����m���}���O���[�u�J�k�[�̌�","9000","6000"},
												{"�c������}���O���[�u�g���b�L���O","2000","1500"},
												{"���΂�̐X�g���b�L���O","6000","4000"},
												{"�V��샊�o�[�g���b�L���O","7000","5000"}};
	
	public static final int WRITE_CSV_SUCCESS = 0;
	public static final int WRITE_CSV_FAIL_OTHER = 9;
}
