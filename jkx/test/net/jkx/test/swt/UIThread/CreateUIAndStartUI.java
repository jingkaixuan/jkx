package net.jkx.test.swt.UIThread;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 运行后没有弹出shell，但是在写代码过程中发现，createUI的线程中没有出现非法访问线程异常，
 * startUI的线程中如果不使用syncExec的话就会出现线程访问异常。
 * 不知道这是否说明创建UI的线程是UI线程（new操作），使用UI的线程是非UI线程。在非UI线程里使用UI对象
 * 就必须使用syncExec。
 * @author zhangshuai
 *
 */
public class CreateUIAndStartUI {
	static Display display = null;
	static Shell shell = null;
	static Button btn = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	            createUI();  
	        }  
	    });  
		
		Thread t2 = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	        	startUI();  
	        }  
	    });
	    t1.start();  
	    t2.start();
	}
	
	public static synchronized void createUI() {
		display = Display.getDefault(); 
		shell = new Shell();
		shell.setSize(500, 375);  
	    shell.setText("SWT Application");  
	    shell.setLayout(new FillLayout()); 
	    btn = new Button(shell, SWT.NULL);  
	    btn.setText("shit");
	}
	public static synchronized void startUI() {
		display.syncExec(new Runnable(){
			@Override
			public void run() {
				shell.open();  
			    shell.layout();  
			    while (!shell.isDisposed()) {  
			        if (!display.readAndDispatch())  
			            display.sleep();  
			    }  
			    shell.dispose();  
			    display.dispose();
			}
			
		});
		 
	}

}
