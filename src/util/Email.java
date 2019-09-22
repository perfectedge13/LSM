package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	private static String recipient = "tom.hobein@gmail.com";
	private static String sender = "alerts@cboe.com";
	private static String host = "127.0.0.1";
	
	public static void sendAlert() {
		
	      // Getting system properties 
	      Properties properties = System.getProperties(); 
	  
	      // Setting up mail server 
	      properties.setProperty("mail.smtp.host", host); 
	  
	      // creating session object to get properties 
	      Session session = Session.getDefaultInstance(properties); 
	  
	      try 
	      { 
	         // MimeMessage object. 
	         MimeMessage message = new MimeMessage(session); 
	  
	         // Set From Field: adding senders email to from field. 
	         message.setFrom(new InternetAddress(sender)); 
	  
	         // Set To Field: adding recipient's email to from field. 
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
	  
	         // Set Subject: subject of the email 
	         message.setSubject("This is Suject"); 
	  
	         // set body of the email. 
	         message.setText("This is a test mail"); 
	  
	         // Send email. 
	         Transport.send(message); 
	         System.out.println("Mail successfully sent"); 
	      } 
	      catch (MessagingException mex)  
	      { 
	         mex.printStackTrace(); 
	      } 
		
	}

}
