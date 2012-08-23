package net.jkx.test.event;

public class StaffWang implements MeetingListener {
	private static StaffWang instance = new StaffWang();
	
	public static StaffWang herself() {
		return instance;
	}
	
	private StaffWang(){}
	
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------老王的反应----------");
		System.out.println("在去" + e.getAddress() + "的路上……");
		System.out.println("思考关于“" + e.getSubject() + "”的事情……");
		System.out.println();

	}

}
