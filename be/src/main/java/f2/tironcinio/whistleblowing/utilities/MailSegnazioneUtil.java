package f2.tironcinio.whistleblowing.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class MailSegnazioneUtil {
	
	public static boolean sendMail( String body) {
		
		Properties mailProps = new Properties();
		Properties accountProps = new Properties();
		
		try {
			mailProps.load(new FileInputStream(new File("mail.properties")));
			accountProps.load(new FileInputStream(new File("account.properties")));
			
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(accountProps.getProperty("username"),
							   accountProps.getProperty("password"));		
				}
			};
			
			Session session = Session.getInstance(mailProps, auth);
			
			MimeMessage msg = new MimeMessage(session);
			
			msg.setFrom(accountProps.getProperty("username"));
			msg.setRecipient(RecipientType.TO, new InternetAddress("gab1dibe@gmail.com"));
			msg.setSubject("hai una nuova segnalazione da assegnare");
			
			MimeBodyPart bp = new MimeBodyPart();
			bp.setContent(body, "text/html");
			
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(bp);
			
			msg.setContent(mp);
			
			Transport.send(msg);
			
			return true;
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

}
