package net.jkx.test.swt.UIThread;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * ʹ��Main�߳���ΪUI�̡߳�
 * @author jkx
 *
 */
public class MainThreadAsUIThread {
	static Button btn = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = Display.getDefault();  
	    final Shell shell = new Shell();  
	    shell.setSize(500, 375);  
	    shell.setText("Use Main Thread as UI Thread");  
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

	/**
	 * Ϊbtn��Ӽ�����
	 */
	private static void registerAction() {
		btn.addMouseListener(new MouseListener() {  
	        @Override  
	        public void mouseDoubleClick(MouseEvent e) {  
	            // TODO Auto-generated method stub  
	        }  
	        @Override  
	        public void mouseDown(MouseEvent e) {  
	            methodA(10);  
	        }  
	        @Override  
	        public void mouseUp(MouseEvent e) {  
	        }  
	    });
		
	}

	/**
	 * ��ӡ���������̲߳����°�ť��ֵ��
	 * @param count
	 */
	protected static void methodA(int count) {
		for (int i = 0; i < count; i++) {  
	        haveArest(300);  
	        System.out.println("MethodA:" + Thread.currentThread().getName());  
	        btn.setText(i + "");  
	    }
	}
	
	/**
	 * ʹ���������߳�˯sleepTime���롣
	 * @param sleepTime
	 */
	private static void haveArest(int sleepTime) {
		try {  
	        Thread.sleep(sleepTime);  
	    } catch (InterruptedException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	}
}
