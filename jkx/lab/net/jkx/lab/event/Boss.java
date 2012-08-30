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
		System.out.println("----------�ϰ�ķ�Ӧ----------");
		System.out.println(e.getAddress() + "����ÿ������ȥ�ĵط�");
		System.out.println("��" + e.getSubject() + "������������Ҫ������˵һ�¡�");
		System.out.println();
	}
}
