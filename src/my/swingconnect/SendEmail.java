/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.swingconnect;

/**
 *
 * @author alicegeorge
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.util.Properties;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmail {
	

		
	public static void Mail(){
            System.out.println("Started");

 
		final String username = "photo2drop@gmail.com";
		final String password = "alice@123";
 
		//Mail properties setting//
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		//Mail user authentication//
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
  System.out.println(" auth");
		try {
			//Current time//
			Date date=new Date();
			String rundate=date.toString();
			
  System.out.println("date" + rundate);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("photo2drop@gmail.com"));
			
                        
			//Setting recipient for the report//
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("rinitageorge@gmail.com"));
                        message.addRecipients(Message.RecipientType.TO,
				InternetAddress.parse("pradil90@gmail.com"));
                        message.addRecipients(Message.RecipientType.TO,
				InternetAddress.parse("Crisj.207@gmail.com"));
                        message.addRecipients(Message.RecipientType.TO,
				InternetAddress.parse("radhika_207@yahoo.com"));
			//Setting suject line//
			message.setSubject("Photos shared| " + rundate);
			//Setting the report data//
			message.setText("Dear Team,"
				+ "\n\n This is an automated mail alert from photodrop.\n\n"
                                
                                + "Photos has been uploaded to photo-drop.com. Please click on the following link http://photo-drop.com/photodropgallery.php to view the gallery."
					
					+ "\n\nThanks and Regards" + "\nPhoto-drop Team");
			

			//sending generated Message//
			Transport.send(message);
 
			System.out.println("Successfully sent");
			
			//Error handling//
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

