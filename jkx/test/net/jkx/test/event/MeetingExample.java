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

		System.out.println("注意！开始开会了……");
		System.out.println("来看看大家的反应");
		System.out.println();
		boss.holdAMeeting("318会议室", "安全大检查");
	}

}
