package net.jkx.lab.event;

public class StaffMa implements MeetingListener {
	private static StaffMa instance = new StaffMa();
	private StaffMa(){}
	
	public static StaffMa himself() {
		return instance;
	}
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------����ķ�Ӧ----------");
		System.out.println("fuc%$#&!��ô����ȥ" + e.getAddress());
		System.out.println("fuc%$#&!��ô���ǹ��ڡ�" + e.getSubject() + "��������");
		System.out.println();
	}

}
