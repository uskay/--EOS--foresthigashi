package jp.foresthigashi.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jp.foresthigashi.constants.MessengerConstants;

public class Messenger {
	
	private static final String CLASS_NAME = "Messenger";
	
    public int sendMail(String to, String[] bcc, String subject, String body){
    
    			String METHOD_NAME = "sendMail";
    			
    			TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "start..", TxtWriter.LOG_INFO);
    			
    			int result = MessengerConstants.SEND_SUCCESS;
	            Transport transport = null;
	            Properties prop = new Properties();
	            prop.put(MessengerConstants.SMTP_HOST_KEY, MessengerConstants.SMTP_HOST_VALUE);
	            prop.put(MessengerConstants.SMTP_PORT_KEY, MessengerConstants.SMTP_PORT_VALUE);
	            prop.put(MessengerConstants.SMTP_AUTH_KEY, MessengerConstants.SMTP_AUTH_VALUE);
	            prop.put(MessengerConstants.SMTP_STARTTLS_KEY, MessengerConstants.SMTP_STARTTLS_VALUE);
	            try {
	            	Address[] bccList = new Address[bcc.length];
	                Session sess = Session.getInstance(prop);
	                MimeMessage mm = new MimeMessage(sess);
	                mm.setFrom(new InternetAddress(MessengerConstants.GMAIL_USER));
	                mm.setSubject(subject);
	                mm.setRecipient(
	                    Message.RecipientType.TO, new InternetAddress(to));
	                for(int i=0;i<bcc.length;i++){	
	                	bccList[i] = new InternetAddress(bcc[i]);
	                }
	                mm.setRecipients(
		                    Message.RecipientType.BCC, bccList);
	                mm.setContent(body, "text/plain; charset=iso-2022-jp");
	                mm.setHeader("Content-Transfer-Encoding", "7bit");
	                transport = sess.getTransport("smtp");
	                transport.connect(MessengerConstants.GMAIL_USER, MessengerConstants.GMAIL_PASSWORD);
	                transport.sendMessage(mm, mm.getAllRecipients());
	            } catch(SendFailedException e1){
	            	TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, e1, TxtWriter.LOG_ERROR);
	            	if(!(e1.getInvalidAddresses()==null)){
	            		if(!(e1.getInvalidAddresses().equals(MessengerConstants.GMAIL_USER))){
	            			result = MessengerConstants.SEND_FAIL_INVALID_ADDRESS_TO;
	            		} else {
	            			result = MessengerConstants.SEND_FAIL_INVALID_ADDRESS_BCC;
	            		}
	            	} else{
	            		result = MessengerConstants.SEND_FAIL_OTHER;
	            	}	
	            } catch(Exception e2){
	            	TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, e2, TxtWriter.LOG_ERROR);
	            	result = MessengerConstants.SEND_FAIL_OTHER;	            	
	            } finally {
	                if (transport != null) {
	                    try {
							transport.close();
						} catch (MessagingException e3) {
							TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, e3, TxtWriter.LOG_ERROR);
							result = MessengerConstants.SEND_FAIL_OTHER;
						}
	                }
	            }
	            
	            TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "end.. result:"+Integer.toString(result), TxtWriter.LOG_INFO);
	            
	            return result;
	            
    		}
    
}
