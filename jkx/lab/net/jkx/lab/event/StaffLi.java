package net.jkx.lab.event;

public class StaffLi implements MeetingListener {
	private static StaffLi instance = new StaffLi();
	
	public static StaffLi herself() {
		return instance;
	}
	
	private StaffLi() {}
	
	@Override
	public void makingReaction(MeetingEvent e) {
		System.out.println("----------����ķ�Ӧ----------");
		System.out.println("��ȥ" + e.getAddress() + "��·�ϡ���");
		System.out.println("˼�����ڡ�" + e.getSubject() + "�������顭��");
		System.out.println();

	}

}
