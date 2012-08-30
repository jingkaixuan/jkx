package net.jkx.lab.event;

import java.util.EventObject;

public class MeetingEvent extends EventObject {
	private String address;
	private String subject;
	
	public MeetingEvent(Object source) {
		super(source);
	}
	
	public MeetingEvent(Object source, String address, String subject) {
		super(source);
		this.address = address;
		this.subject = subject;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getSubject() {
		return this.subject;
	}
}
