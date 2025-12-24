package com.example.mail_service.controller;

public class MailRequest {
	private String receiver;
    private String subject;
    private String body;
	public MailRequest() {
		super();
	}
	public MailRequest(String receiver, String subject, String body) {
		super();
		this.receiver = receiver;
		this.subject = subject;
		this.body = body;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "MailRequest [receiver=" + receiver + ", subject=" + subject + ", body=" + body + "]";
	}
	
}
