
package com.app.Service;

import java.util.Properties;
import java.util.Random;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

@Service
public class SendMail {

	// generate vrification code 
	public String getRandom() {
		Random rnd = new Random();

		int number = rnd.nextInt(9999);
		return String.format("%04d", number);
	}

	// send email to the user email 
	public boolean sendEmail(String mailId, int otp){
	boolean test = false;

	String toEmail = mailId;
	final String fromEmail = "rp9322935@gmail.com";
	final String password = "ibntxemqiybqnuee";
	
  
  // your host email smtp server details 
//		Properties properties = new Properties(); 
		Properties prop = System.getProperties();
		
		prop.setProperty("mail.smtp.host", "smtp.gmail.com");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.port", "587");
		
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
  
  //get session to authenticate the host email address and password 
	Session session = Session.getInstance(prop, new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(fromEmail, password);
		}
	});
  
	try {
		// set email message details
		MimeMessage mess = new MimeMessage(session);

		// set from email address
		mess.setFrom(new InternetAddress(fromEmail)); // set to email address or destination email address

		mess.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));

		// set email subject
		mess.setSubject("User Email Verification");

		// set message text
		mess.setText("To reset apssword, Please verify your account using this code: " + otp);
		// send the message
		System.out.println("before transport..");
		Transport.send(mess);
		System.out.println("after transport..");

		test = true;
	
  }catch(Exception e){
		e.printStackTrace();
	}

	return test;
}

}
