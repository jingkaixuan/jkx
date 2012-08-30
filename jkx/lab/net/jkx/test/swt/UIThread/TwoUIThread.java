package net.jkx.test.swt.UIThread;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 可以存在一个或者多个UI线程,
 * @author zhangshuai
 *
 */
public class TwoUIThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	            createUI();  
	        }  
	    });  
	    t.start();  
	      
	    t = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	            createUI();  
	        }  
	    });  
	    t.start(); 
	}

	private static void createUI() {
		System.out.println(Thread.currentThread().getName());
		final Display display = new Display();
		final Shell shell = new Shell();
		shell.setSize(500, 375);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout());
		Button btn = new Button(shell, SWT.NULL);
		btn.setText("shit");
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		shell.dispose();
		display.dispose();
	}
}
