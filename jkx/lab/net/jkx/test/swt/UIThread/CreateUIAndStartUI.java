package net.jkx.test.swt.UIThread;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * ���к�û�е���shell��������д��������з��֣�createUI���߳���û�г��ַǷ������߳��쳣��
 * startUI���߳��������ʹ��syncExec�Ļ��ͻ�����̷߳����쳣��
 * ��֪�����Ƿ�˵������UI���߳���UI�̣߳�new��������ʹ��UI���߳��Ƿ�UI�̡߳��ڷ�UI�߳���ʹ��UI����
 * �ͱ���ʹ��syncExec��
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
