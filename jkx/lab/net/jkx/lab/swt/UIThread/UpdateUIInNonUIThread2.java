package net.jkx.lab.swt.UIThread;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UpdateUIInNonUIThread2 {
	static Display display = null;
	static Shell shell = null;
	static Button btn = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		conditionOne();
//		conditionTwo();
//		conditionThree();
//		conditionFour(); // throw org.eclipse.swt.SWTException: Invalid thread access
//		conditionFive();
		conditionSix();
	}

	/**
	 * �����߳��ﴴ��UI�����̼߳���UI�̡߳�
	 */
	public static void conditionOne() {
		createUI();
		holdUI();
	}

	/**
	 * ���û��Զ����һ���߳��ﴴ��UI
	 */
	public static void conditionTwo() {
		Thread uiThread = new Thread(new Runnable() {
			@Override
			public void run() {
				createUI();
				holdUI();
			}
		});
		uiThread.start();
	}
	
	/**
	 * ��UI�߳������UIʱ��������²������к�ʱ���ش�����ᵼ�½�������Ӧ��
	 */
	public static void conditionThree() {
		Thread uiThread = new Thread(new Runnable() {
			@Override
			public void run() {
				createUI();
				
				updateUI();
				
				holdUI();
			}
		});
		uiThread.start();
	}
	
	/**
	 * ����һ���߳������UIʱ����������϶�����������̷߳����쳣��
	 * ������Ϊ�ڷ�UI�߳������UI����ʹ��Display.syncExec��
	 * conditionFive��һ�ֽ���취�����ᷢ��Ҳ������
	 */
	public static void conditionFour() {
		Thread uiThread = new Thread(new Runnable() {
			@Override
			public void run() {
				createUI();
				
				Thread updateThread = new Thread(new Runnable(){

					@Override
					public void run() {
						updateUI();
						
					}});
				updateThread.start();
				
				holdUI();
			}
		});
		uiThread.start();
	}
	
	/**
	 * ��Ȼdisplay.syncExec������UI�ϲ�����UI�߳��У�������updateUI�Ǹ���ʱ�Ĳ���������UI�̺߳���Ȼ�ᵼ��UI����Ӧ��
	 * ���conditionThree��Ч����һ���ġ�
	 * ԭ������display.syncExec�����ȹ����ˣ���ΪupdateUI��������������UI��ֻ�ǡ�btn.setText"��
	 */
	public static void conditionFive() {
		Thread uiThread = new Thread(new Runnable() {
			@Override
			public void run() {
				createUI();
				
				Thread updateThread = new Thread(new Runnable(){

					@Override
					public void run() {
						display.syncExec(new Runnable(){

							@Override
							public void run() {
								updateUI();								
							}});
					}});
				updateThread.start();
				
				holdUI();
			}
		});
		uiThread.start();
	}
	
	public static void conditionSix() {
		Thread uiThread = new Thread(new Runnable() {
			@Override
			public void run() {
				createUI();
				
				Thread updateThread = new Thread(new Runnable(){

					@Override
					public void run() {
						updateUI2();								
					}});
				updateThread.start();
				
				holdUI();
			}
		});
		uiThread.start();
	}
	

	public static void createUI() {
		System.out.println("Create UI: " + Thread.currentThread().getName());
		display = Display.getDefault();
		shell = new Shell();
		shell.setSize(200, 100);
		shell.setLayout(new FillLayout());

		btn = new Button(shell, SWT.None);
		btn.setText("Fuck");

		shell.layout();
		shell.open();
	}

	public static void holdUI() {
		if (shell != null && display != null) {
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}

			shell.dispose();
			display.dispose();
		}
	}
	
	public static void updateUI() {
		System.out.println("Update UI: " + Thread.currentThread().getName());
		if(btn != null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			btn.setText("You");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			btn.setText("!!!");
		}
	}
	
	public static void updateUI2() {
		if(btn != null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			display.syncExec(new Runnable() {
				@Override
				public void run() {
					System.out.println("Update UI: " + Thread.currentThread().getName());
					btn.setText("You");					
				}});
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			display.syncExec(new Runnable() {
				@Override
				public void run() {
					System.out.println("Update UI: " + Thread.currentThread().getName());
					btn.setText("!!!");				
				}});
		}
	}
}
