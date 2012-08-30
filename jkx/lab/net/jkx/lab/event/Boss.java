package net.jkx.lab.event;

import java.util.ArrayList;

public class Boss implements MeetingListener {
	private static Boss instance = new Boss();
	private Boss(){}
	
	public static Boss himself() {
		return instance;
	}
	
	private ArrayList<MeetingListener> listeners = new ArrayList<MeetingListener>();

	public synchronized void addMeetingListener(MeetingListener listener) {
		if (listener != null) {
			this.listeners.add(listener);
		}
	}
	
	private synchronized void phoneEverybody(MeetingEvent e) {
		for(MeetingListener l : this.listeners) {
			l.makingReaction(e);
		}
	}
	
	public void holdAMeeting(String address, String subject) {
		MeetingEvent e = new MeetingEvent(this, address, subject);
		this.phoneEverybody(e);
	}

	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------老板的反应----------");
		System.out.println(e.getAddress() + "是我每天最想去的地方");
		System.out.println("“" + e.getSubject() + "”这个事情很重要，得再说一下。");
		System.out.println();
	}
}
