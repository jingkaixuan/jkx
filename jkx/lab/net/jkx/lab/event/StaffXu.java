package net.jkx.lab.event;

public class StaffXu implements MeetingListener {
	private static StaffXu instance = new StaffXu();
	private StaffXu(){}
	
	public static StaffXu himself() {
		return instance;
	}
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------����ķ�Ӧ----------");
		System.out.println("����!");
		System.out.println();
	}

}
