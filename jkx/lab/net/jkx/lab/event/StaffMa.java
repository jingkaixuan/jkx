package net.jkx.lab.event;

public class StaffMa implements MeetingListener {
	private static StaffMa instance = new StaffMa();
	private StaffMa(){}
	
	public static StaffMa himself() {
		return instance;
	}
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------老马的反应----------");
		System.out.println("fuc%$#&!怎么还是去" + e.getAddress());
		System.out.println("fuc%$#&!怎么还是关于“" + e.getSubject() + "”的事情");
		System.out.println();
	}

}
