package net.jkx.test.swt.UIThread;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 在一个单独的线程里创建UI，则该单独的线程就是UI线程。
 * 于是有如下结论：UI在哪个线程里启动，哪个线程就是UI线程。
 * @author zhangshuai
 *
 */
public class SeparateThreadAsUIThread {

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
	}
	
	private static void createUI()  
	{  
	    System.out.println(Thread.currentThread().getName());  
	    final Display display = Display.getDefault();  
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
