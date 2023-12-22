package com.cjc.main.serviceaimpl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cjc.main.model.EmailSender;

@Service
public class EmailSenderImpl {
	
	@Autowired
	JavaMailSender sender;

	public void sendEmail(EmailSender e) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(e.getToEmail());
		message.setFrom(e.getFromEmail());
	
        message.setSubject(e.getSubject());	
    	message.setText(e.getTextmsg());
        sender.send(message);
        System.out.println("send");
		
		
	}
	
	
	public	void sendEmailWithAttachment(EmailSender e)   {

	      MimeMessage msg=sender.createMimeMessage();
	      
	      
	      try {
			MimeMessageHelper helper=new MimeMessageHelper(msg,true);
			  
			  helper.setTo(e.getToEmail());
			  helper.setFrom(e.getFromEmail());
			  helper.setSubject(e.getSubject());
			  helper.setText(e.getTextmsg());
			   

			    FileSystemResource file = new FileSystemResource(new File(e.getPath()));

			    helper.addAttachment(file.getFilename(),file);
		} catch (MessagingException e1) {
			
			e1.printStackTrace();
			System.out.println("Messaging exception Occured");
		}
	      sender.send(msg);
	    }

	
	public	void sendEmailWithPhoto(EmailSender e) throws MessagingException  {

	      MimeMessage msg=sender.createMimeMessage();
	      
	      MimeMessageHelper helper=new MimeMessageHelper(msg,true);
	      
	      helper.setTo(e.getToEmail());
	      helper.setFrom(e.getFromEmail());
	      helper.setSubject(e.getSubject());
	      helper.setText(e.getTextmsg());
	       

	        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\NAMEET\\Downloads\\Nike and Adidas Wallpaper Good Nike Vs Adidas Wallpaper Hd Best.jpg"));

	        helper.addAttachment(file.getFilename(),file);
	      sender.send(msg);
	    }

}
