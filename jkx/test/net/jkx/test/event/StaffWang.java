package net.jkx.test.event;

public class StaffWang implements MeetingListener {
	private static StaffWang instance = new StaffWang();
	
	public static StaffWang herself() {
		return instance;
	}
	
	private StaffWang(){}
	
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------�����ķ�Ӧ----------");
		System.out.println("��ȥ" + e.getAddress() + "��·�ϡ���");
		System.out.println("˼�����ڡ�" + e.getSubject() + "�������顭��");
		System.out.println();

	}

}
