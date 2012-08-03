package net.jkx.test.swt.UIThread;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * �ڷ�UI�߳��и���UI������
 * @author zhangshuai
 *
 */
public class UpdateUIInNonUIThread {
	static Button btn = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = Display.getDefault();  
	    final Shell shell = new Shell();  
	    shell.setSize(500, 375);  
	    shell.setText("SWT Application");  
	    shell.setLayout(new FillLayout());  
	    btn = new Button(shell, SWT.NULL);  
	    btn.setText("shit");  
	    registerAction();  
	    shell.open();  
	    shell.layout();  
	    while (!shell.isDisposed()) {  
	        if (!display.readAndDispatch())  
	            display.sleep();  
	    }  
	    shell.dispose();  
	    display.dispose();

	}

	private static void registerAction() {  
	    btn.addMouseListener(new MouseListener() {  
	        @Override  
	        public void mouseDoubleClick(MouseEvent e) {  
	            // TODO Auto-generated method stub  
	        }  
	        @Override  
	        public void mouseDown(MouseEvent e) {  
	            methodC(10);  
	        }  
	        @Override  
	        public void mouseUp(MouseEvent e) {  
	        }  
	    });  
	}  
	/** 
	 * �������ܶ�, ��ӡ�̵߳�����, ע����ק����, ��������, ֱ������ 
	 */  
	public static void methodA(int count) {  
	    for (int i = 0; i < count; i++) {  
	        haveArest(300);  
	        System.out.println("MethodA:" + Thread.currentThread().getName());  
	        btn.setText(i + "");  
	    }  
	} 
	
	/** 
	 * Ϊ�˽����ק����, ��������, �����߳̿���, ��������Invalid thread access������ 
	 */  
	public static void methodB(final int count) {  
	    Thread t = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	            for (int i = 0; i < count; i++) {  
	                haveArest(300);  
	                System.out.println("MethodB:"  
	                        + Thread.currentThread().getName());  
	                btn.setText(i + "");  
	            }  
	        }  
	    });  
	    t.start();  
	} 
	
	/**
	 * ʹ��display.asyncExec(Runnable runnable)ִ�з�UI�߳��и���UI�Ĵ��롣
	 * @param count
	 */
	public static void methodC(final int count) {  
	    Thread t = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	            for (int i = 0; i < count; i++) {  
	                System.out.println("MethodB Thread:"  
	                        + Thread.currentThread().getName());  
	              
	                haveArest(300);  
	                final Display display = Display.getDefault();  
	                final String s = i + "";  
	                if ((display != null) && (!display.isDisposed())) {  
	                    display.syncExec(new Runnable() {  
	                        @Override  
	                        public void run() {  
	                            System.out.println("MethodB Thread asyncExec:"  
	                                    + Thread.currentThread().getName());  
	                            btn.setText(s);  
	                        }  
	                    });  
	                }  
	            }  
	        }  
	    });  
	    t.start();  
	}  
	  
	public static void haveArest(int sleepTime)  
	{  
	    try {  
	        Thread.sleep(sleepTime);  
	    } catch (InterruptedException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	}  
}
