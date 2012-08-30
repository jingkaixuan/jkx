package net.jkx.lab.event;

public class StaffLi implements MeetingListener {
	private static StaffLi instance = new StaffLi();
	
	public static StaffLi herself() {
		return instance;
	}
	
	private StaffLi() {}
	
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------老李的反应----------");
		System.out.println("在去" + e.getAddress() + "的路上……");
		System.out.println("思考关于“" + e.getSubject() + "”的事情……");
		System.out.println();

	}

}
