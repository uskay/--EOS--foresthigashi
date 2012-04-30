package jp.foresthigashi.constants;

public interface MessengerConstants {
	
	public static final String SMTP_HOST_KEY = "mail.smtp.host";
	public static final String SMTP_PORT_KEY = "mail.smtp.port";
	public static final String SMTP_AUTH_KEY = "mail.smtp.auth";
	public static final String SMTP_STARTTLS_KEY = "mail.smtp.starttls.enable";
	
	public static final String SMTP_HOST_VALUE = "smtp.gmail.com";
	public static final String SMTP_PORT_VALUE = "587";
	public static final String SMTP_AUTH_VALUE = "true";
	public static final String SMTP_STARTTLS_VALUE = "true";
	
	public static final String GMAIL_USER = "info@foresthigashi.jp";
	public static final String GMAIL_PASSWORD = "higashison";
	
	public static final String[] BCC = {
		"forest-higashi-sige@docomo.ne.jp",
		"forest@tutuji.jp"
		};
		
	public static final int SEND_SUCCESS = 0;
	public static final int SEND_FAIL_INVALID_ADDRESS_TO = 1;
	public static final int SEND_FAIL_INVALID_ADDRESS_BCC = 2;
	public static final int SEND_FAIL_OTHER = 9;

}
