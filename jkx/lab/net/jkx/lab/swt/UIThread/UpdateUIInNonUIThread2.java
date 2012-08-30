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
	 * 在主线程里创建UI，主线程既是UI线程。
	 */
	public static void conditionOne() {
		createUI();
		holdUI();
	}

	/**
	 * 在用户自定义的一个线程里创建UI
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
	 * 在UI线程里更新UI时，如果更新操作中有耗时因素存在则会导致界面无响应。
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
	 * 在另一个线程里更新UI时，界面可以拖动，但会出现线程访问异常。
	 * 这是因为在非UI线程里更新UI必须使用Display.syncExec。
	 * conditionFive是一种解决办法。但会发现也不合理。
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
	 * 虽然display.syncExec将更新UI合并到了UI线程中，但由于updateUI是个耗时的操作，并入UI线程后仍然会导致UI无响应。
	 * 这和conditionThree的效果是一样的。
	 * 原因在于display.syncExec的粒度过大了，因为updateUI函数中真正更新UI的只是“btn.setText"。
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
