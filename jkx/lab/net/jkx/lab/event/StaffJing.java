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
		System.out.println("----------�Ͼ��ķ�Ӧ----------");
		System.out.println("��ȥ" + e.getAddress() + "��·�ϡ���");
		System.out.println("˼�����ڡ�" + e.getSubject() + "�������顭��");
		System.out.println();

	}

}
