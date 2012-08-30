package net.jkx.lab.swt.UIThread;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class AsyncExecAndSyncExec {
	static Button btn = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	        	System.out.println("Create UI in " + Thread.currentThread().getName());
	            createUI();  
	        }  
	    });  
	    t.start(); 
	    
	    Display.getDefault().syncExec(new Runnable() {  
	        @Override  
	        public void run() { 
	        	System.out.println("syncExec: " + Thread.currentThread().getName() + " sleep 3 second.");
	            try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            System.out.println("endline of syncExec.");// 好疑惑，加上这句就会出现非法的线程访问异常。
	        }  
	    }); 
	    
	    Display.getDefault().asyncExec(new Runnable() {  
	        @Override  
	        public void run() { 
	        	System.out.println("asyncExec: " + Thread.currentThread().getName() + " sleep 3 second.");
	            try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            System.out.println("endline of asyncExec.");
	        }  
	    });  
	}
	
	public static void createUI() {
		final Display display = Display.getDefault();  
	    final Shell shell = new Shell();  
	    shell.setSize(500, 375);  
	    shell.setText("Use Main Thread as UI Thread");  
	    shell.setLayout(new FillLayout());  
	    btn = new Button(shell, SWT.NULL);  
	    btn.setText("asyncExec and syncExec");  
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
