package net.jkx.test.event;

public class StaffChen implements MeetingListener {
	private static StaffChen instance = new StaffChen();
	private StaffChen(){}
	
	public static StaffChen himself() {
		return instance;
	}
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------�ϳµķ�Ӧ----------");
		System.out.println("��ȥ" + e.getAddress() + "��·�ϡ���");
		System.out.println("˼�����ڡ�" + e.getSubject() + "�������顭��");
		System.out.println();
	}

}
