package net.jkx.test.event;

public class MeetingExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Boss boss = Boss.himself();
		
		boss.addMeetingListener(boss);
		boss.addMeetingListener(StaffMa.himself());
		boss.addMeetingListener(StaffXu.himself());
		boss.addMeetingListener(StaffChen.himself());
		boss.addMeetingListener(StaffJing.himself());
		boss.addMeetingListener(StaffLi.herself());

		System.out.println("ע�⣡��ʼ�����ˡ���");
		System.out.println("��������ҵķ�Ӧ");
		System.out.println();
		boss.holdAMeeting("318������", "��ȫ����");
	}

}
