package net.jkx.test.event;

public class StaffChen implements MeetingListener {
	private static StaffChen instance = new StaffChen();
	private StaffChen(){}
	
	public static StaffChen himself() {
		return instance;
	}
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------老陈的反应----------");
		System.out.println("在去" + e.getAddress() + "的路上……");
		System.out.println("思考关于“" + e.getSubject() + "”的事情……");
		System.out.println();
	}

}
