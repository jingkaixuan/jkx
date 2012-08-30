package net.jkx.lab.event;

public class StaffJing implements MeetingListener {
	private static StaffJing instance = new StaffJing();
	private StaffJing() {
		
	}
	
	public static StaffJing himself() {
		return instance;
	}
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------老荆的反应----------");
		System.out.println("在去" + e.getAddress() + "的路上……");
		System.out.println("思考关于“" + e.getSubject() + "”的事情……");
		System.out.println();

	}

}
