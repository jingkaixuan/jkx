package net.jkx.test.jui.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import net.jkx.designpattern.observer.Observer;
import net.jkx.jui.swt.widgets.BrowseComposite;

public class TestBrowseComposite implements Observer {
	BrowseComposite bc = null;
	public TestBrowseComposite(BrowseComposite bc) {
		this.bc = bc;
	}
	@Override
	public void update(Object obj) {
//		System.out.println(bc.getBrowseResult());
		
//		String[] fileNames = bc.getFileNames();
//		for(String name : fileNames) {
//			System.out.print(name + " ");
//		}
//		System.out.println();
		
//		System.out.println(bc.getFilterNames()[0]);
		
		System.out.println(bc.getPath());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setBounds(500, 300, 300, 150);
		shell.setLayout(new FillLayout());

		BrowseComposite bc = new BrowseComposite(shell, SWT.NONE,
				BrowseComposite.FILE | BrowseComposite.OPEN | BrowseComposite.FOLDER);
		bc.setFilterNames(new String[] { "Java Ô´ÎÄ¼þ(*.java)" });
		bc.setFilterExtensions(new String[] { "*.java" });
		bc.registerObserver(new TestBrowseComposite(bc));
		shell.layout();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

}
