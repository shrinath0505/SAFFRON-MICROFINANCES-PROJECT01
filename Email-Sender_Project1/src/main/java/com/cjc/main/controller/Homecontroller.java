package com.cjc.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cjc.main.model.EmailSender;
import com.cjc.main.serviceaimpl.EmailSenderImpl;

@RestController
@CrossOrigin("*")

public class Homecontroller {
	
	@Autowired
	EmailSenderImpl si;
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	
	@PostMapping("/emailsend")
	public String sendEmail(@RequestBody EmailSender e) {
		e.setFromEmail(fromEmail);
		try {
			si.sendEmail(e);
		} catch (Exception e2) {
			e2.printStackTrace();
			return "Email cann't Be Send";
		}
		
		
		return "Email send SUESSFULLY";
	}
	
	@PostMapping("/emailsendwithattachment")
	public String emailsendwithattachment(@RequestBody EmailSender e) {
		
		e.setFromEmail(fromEmail);
		String master=e.getPath();
		String target="C:\\dell";
		String replacement="C:\\Users\\dell\\Downloads";
		String processed=master.replace(target, replacement);
		
		System.out.println(processed);
		e.setPath(processed);
		
		try {
			si.sendEmailWithAttachment(e);
			System.out.println("Email Sent With Attachment");
		} catch (Exception e2) {
			e2.printStackTrace();
			return "email can not be send";
		}
		
		
		return "email send successfully With Attachment";
	}
	
	@PostMapping("/emailsendwithPhoto")
	public String emailsendwithPhoto(@RequestBody EmailSender e) {
		e.setFromEmail(fromEmail);
		try {
			si.sendEmailWithPhoto(e);
		} catch (Exception e2) {
			e2.printStackTrace();
			return "email can not be send";
		}
		
		
		return "email send successfully with JPG photo";
	}

}
