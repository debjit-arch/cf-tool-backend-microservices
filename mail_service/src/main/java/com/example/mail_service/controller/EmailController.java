package com.example.mail_service.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mail_service.service.SendEmailService;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {

	@Autowired
	private SendEmailService sendEmailService;

	@GetMapping("sendEmail")
	public String sendEmail() throws UnsupportedEncodingException, MessagingException {
		sendEmailService.sendEmail("debjit@consultantsfactory.com", "Test Body", "Test Subject");
		return "Mail Sent";
	}

	@PostMapping("send-email")
	public String customEmail(@RequestBody MailRequest request)
			throws UnsupportedEncodingException, MessagingException {
		sendEmailService.sendEmail(request.getReceiver(), request.getBody(), request.getSubject());
		return "Mail Sent";
	}
}
