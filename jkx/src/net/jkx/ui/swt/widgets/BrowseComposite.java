package net.jkx.ui.swt.widgets;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

import com.swtdesigner.SWTResourceManager;

import net.jkx.designpattern.observer.Observer;
import net.jkx.designpattern.observer.Subject;
import net.mindview.util.BinaryFile;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ToolBar;

/**
 * </p>BrowseComposite��һ������ļ����ļ��еĻ���swt��С�ؼ������ı�����ʽ���ǽ���������ļ����ļ��е�ȫ·����ʾ��һ���ı����ڡ�
 * </p><img src="doc-files/BC.png"/> </p>��������������������ų����뿴�����ļ����͡�
 * </p>��������������󣬿���ͨ��һЩgetter�õ���������ļ����ļ��С�
 * 
 * @author ������
 * @version 0.1
 * 
 */
public class BrowseComposite extends Composite implements Subject {
	private class FileDialogMonitor extends SelectionAdapter {
		private final BrowseComposite bc = BrowseComposite.this;

		/**
		 * @param dialog
		 */
		private String readConfigurationFile() {
			if (bc.recordLastPath) {
				if (bc.configurationFile.exists()) {
					try {
						byte[] pathInBytes = BinaryFile
								.read(bc.configurationFile);
						String pathStr = new String(pathInBytes);
						return pathStr;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						bc.configurationFile.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			return null;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			Object o = e.getSource();
			String actionCommand = "";
			if (o instanceof Button) {
				Button button = (Button) o;
				actionCommand = button.getText();

			} else if (o instanceof MenuItem) {
				MenuItem menuItem = (MenuItem) o;
				actionCommand = menuItem.getText();
			}
			if (actionCommand.equalsIgnoreCase(ACTION_COMMAND_BROWSE_FILE)) {
				FileDialog dialog = new FileDialog(bc.getShell(), SWT.OPEN
						| SWT.MULTI);
				String filterPath = readConfigurationFile();
				if (filterPath != null) {
					dialog.setFilterPath(filterPath);
				}
				if (null != bc.filterNames) {
					dialog.setFilterNames(bc.filterNames);
				}
				if (null != bc.filterExtensions) {
					dialog.setFilterExtensions(bc.filterExtensions);
				}
				String fileName = dialog.open();
				bc.fileNames = dialog.getFileNames();
				if (null == fileName) {
					return;
				}
				String pathStr = fileName.substring(0,
						fileName.lastIndexOf("\\"));
				writeConfigurationFile(pathStr);
				bc.path = pathStr;
				String displayValue = fileName;
				// ���ѡ���˶���ļ����������ļ�����ʾ��һ���������ڡ�
				if (fileNames.length > 1) {
					// bc.fileNames = new String[fileNames.length];
					displayValue = pathStr;
					displayValue += "[";
					int count = 0;
					for (String name : fileNames) {
						displayValue += (name + ((count == fileNames.length - 1) ? ""
								: ";"));
						bc.fileNames[count++] = name;
					}
					displayValue += "]";
				}
				bc.browseResult.setText(displayValue);
				bc.setChanged();
				bc.notifyObservers();

			} else if (actionCommand
					.equalsIgnoreCase(ACTION_COMMAND_BROWSE_FOLDER)) {
				DirectoryDialog dialog = new DirectoryDialog(bc.getShell());
				String filterPath = readConfigurationFile();
				if (filterPath != null) {
					dialog.setFilterPath(filterPath);
				}
				dialog.setText(ACTION_COMMAND_BROWSE_FOLDER);
				dialog.setMessage(MESSAGE_CHOICE_FOLDER);
				String dir = dialog.open();
				if (dir != null) {
					bc.browseResult.setText(dir);
					writeConfigurationFile(dir);
					bc.path = dir;
					bc.fileNames = null;
					bc.setChanged();
					bc.notifyObservers();
				}
			} else if (actionCommand.equalsIgnoreCase(ACTION_COMMAND_SAVE_FILE)) {
				FileDialog dialog = new FileDialog(bc.getShell(), SWT.SAVE);
				String filterPath = readConfigurationFile();
				if (filterPath != null) {
					dialog.setFilterPath(filterPath);
				}
				if (null != bc.filterNames) {
					dialog.setFilterNames(bc.filterNames);
				}
				if (null != bc.filterExtensions) {
					dialog.setFilterExtensions(bc.filterExtensions);
				}
				String fileName = dialog.open();
				if (null == fileName) {
					return;
				}
				String pathStr = fileName.substring(0,
						fileName.lastIndexOf("\\"));
				writeConfigurationFile(pathStr);
				bc.path = pathStr;
				String displayValue = fileName;
				bc.browseResult.setText(displayValue);
				bc.setChanged();
				bc.notifyObservers();
			}
		}

		/**
		 * @param pathStr
		 */
		private void writeConfigurationFile(String pathStr) {
			if (bc.recordLastPath) {
				RandomAccessFile raf = null;
				try {
					if (bc.configurationFile.exists()) {
						bc.configurationFile.delete();
					}
					raf = new RandomAccessFile(bc.configurationFile, "rw");
					byte[] record = pathStr.getBytes();
					raf.write(record);

				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					if (null != raf) {
						try {
							raf.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}
	static final String ACTION_COMMAND_BROWSE_FILE = "���ļ�";
	static final String ACTION_COMMAND_SAVE_FILE = "����";
	static final String ACTION_COMMAND_BROWSE_FOLDER = "���Ŀ¼";

	static final String MESSAGE_CHOICE_FOLDER = "��ѡ��һ��Ŀ¼";
	
	/**
	 * ����ļ�ģʽ����ģʽ�½�������ļ���
	 */
	public static final int FILE = 0x01;// 0000 0101
	
	/**
	 * ����ļ���ģʽ����ģʽ�½�������ļ��С�
	 */
	public static final int FOLDER = 0x02; // 0000 0110
	
	/**
	 * ����ļ����ļ���ģʽ����ģʽ�¿���ͬʱ����ļ����ļ��С�
	 */
	public static final int FILE_FOLDER = FILE | FOLDER;
	
	/**
	 * ���ʱ�򿪵��ǡ��򿪡��Ի���
	 */
	public static final int OPEN = 0x04;

	/**
	 * ���ʱ�򿪵��ǡ����桱�Ի���
	 */
	public static final int SAVE = 0x08;

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setLayout(new FillLayout());

		BrowseComposite bc = new BrowseComposite(shell, SWT.NONE,
				BrowseComposite.FILE | OPEN | FOLDER);
		bc.setFilterNames(new String[] { "Autodesk 3ds�ļ�(*.3ds)" });
		bc.setFilterExtensions(new String[] { "*.3ds" });
		shell.layout();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Text�ؼ���������ʾ��������
	 */
	private Text browseResult;

	/**
	 * �ļ����͹����ַ�������{*.java, *.JAVA, *.class}
	 */
	private String[] filterExtensions = null;
	
	/**
	 * �ļ����͹����ַ�������������{"JavaԴ�ļ�(*.java)", "JavaԴ�ļ�(*.JAVA)",
	 * "Java�ֽ����ļ�(*.class)"}
	 */
	private String[] filterNames = null;

	/**
	 * ָ��һ�������ļ����洢�ÿؼ����һ�������λ�á�
	 */
	File configurationFile = new File(System.getProperties().getProperty(
			"user.dir")
			+ "\\lp.cfg");
	
	/**
	 * ��ʶ�Ƿ��¼���һ�������λ�á�
	 */
	boolean recordLastPath = true;

	/**
	 * ���λ��������Ŀ¼
	 */
	private String path;
	
	/**
	 * ����������Ϊ�ļ����ô��洢��������ļ�����
	 */
	private String[] fileNames;

	/**
	 * ��ʶ״̬�Ƿ�ı䣨Subject��
	 */
	private boolean changed = false;

	/**
	 * �۲����б���Subject��
	 */
	private Vector<Observer> obs;

	/**
	 * ���ģʽ��Ĭ��Ϊ����ļ��С�
	 */
	private int mode = BrowseComposite.FILE_FOLDER;

	/**
	 * ����һ��BrowseComposite��
	 * 
	 * @param parent �ÿؼ��ĸ��ؼ���
	 * @param style �ؼ���ʽ��
	 * @param mode ���ģʽ
	 */
	public BrowseComposite(Composite parent, int style, int mode) {
		super(parent, style);
		this.mode = mode;
		obs = new Vector<Observer>();
		setLayout(new GridLayout(2, false));
		{
			browseResult = new Text(this, SWT.BORDER);
			browseResult.setEditable(false);
			browseResult.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
					true, 1, 1));
		}
		if ((FILE == (this.mode & FILE)) && (FOLDER == (this.mode & FOLDER))) {
			final ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
			final ToolItem dropdown = new ToolItem(toolBar, SWT.DROP_DOWN);
			dropdown.setImage(SWTResourceManager.getImage(
					BrowseComposite.class, "/images/folder-white.png"));

			final Menu menu = new Menu(this.getShell(), SWT.POP_UP);
			MenuItem browseFile = new MenuItem(menu, SWT.PUSH);
			FileDialogMonitor ofdm = new FileDialogMonitor();
			browseFile.setText(ACTION_COMMAND_BROWSE_FILE);
			browseFile.addSelectionListener(ofdm);
			MenuItem browseFolder = new MenuItem(menu, SWT.PUSH);
			browseFolder.setText(ACTION_COMMAND_BROWSE_FOLDER);
			browseFolder.addSelectionListener(ofdm);

			dropdown.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					if (event.detail == SWT.ARROW) {
						Rectangle rect = dropdown.getBounds();
						Point pt = new Point(rect.x, rect.y + rect.height);
						pt = toolBar.toDisplay(pt);
						menu.setLocation(pt.x, pt.y);
						menu.setVisible(true);
					}
				}
			});
		} else if (FILE == (this.mode & FILE)) {
			if (SAVE == (this.mode & SAVE)) {
				Button browseFileBtn = new Button(this, SWT.NONE);
				browseFileBtn.setText(ACTION_COMMAND_SAVE_FILE);
				FileDialogMonitor ofdm = new FileDialogMonitor();
				browseFileBtn.addSelectionListener(ofdm);

			} else {
				Button browseFileBtn = new Button(this, SWT.NONE);
				browseFileBtn.setText(ACTION_COMMAND_BROWSE_FILE);
				FileDialogMonitor ofdm = new FileDialogMonitor();
				browseFileBtn.addSelectionListener(ofdm);
			}
		} else if (FOLDER == (this.mode & FOLDER)) {
			Button browseFileBtn = new Button(this, SWT.NONE);
			browseFileBtn.setText(ACTION_COMMAND_BROWSE_FOLDER);

			FileDialogMonitor ofdm = new FileDialogMonitor();
			browseFileBtn.addSelectionListener(ofdm);
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	protected synchronized void clearChanged() {
		changed = false;
	}

	@Override
	public int countOfObservers() {
		return obs.size();
	}

	/**
	 * ������������
	 * @return ����ļ�ʱ������������ļ�ȫ·��������ļ���ʱ�����ļ��е�ȫ·����
	 */
	public String getBrowseResult() {
		return browseResult.getText();
	}

	/**
	 * ������������ļ���������·������
	 * @return ��������ļ�����ɵ����顣
	 */
	public String[] getFileNames() {
		return fileNames == null ? new String[]{} : fileNames;
	}

	/**
	 * �����ļ����͹����ַ������顣
	 * @return
	 */
	public String[] getFilterExtensions() {
		return filterExtensions == null ? new String[]{} : filterExtensions;
	}

	/**
	 * �����ļ����͹���˵���ַ�����
	 * @return
	 */
	public String[] getFilterNames() {
		return filterNames == null ? new String[]{} : filterNames;
	}

	/**
	 * ������������·�����������ļ�����
	 * @return
	 */
	public String getPath() {
		return path;
	}

	@Override
	public boolean hasChanaged() {
		return this.changed;
	}

	/**
	 * �Ƿ��¼����򿪵�·����
	 * @return
	 */
	public boolean isRecordLastPath() {
		return recordLastPath;
	}

	@Override
	public void notifyObservers() {
		Object[] arrLocal;

		synchronized (this) {
			if (!changed)
				return;
			arrLocal = obs.toArray();
			clearChanged();
		}

		for (int i = arrLocal.length - 1; i >= 0; i--)
			((Observer) arrLocal[i]).update(this);

	}

	@Override
	public void registerObserver(Observer o) {
		if (o == null)
			throw new NullPointerException();
		if (!obs.contains(o)) {
			obs.addElement(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		obs.removeElement(o);
	}

	protected synchronized void setChanged() {
		changed = true;
	}

	/**
	 * �����ļ����͹����ַ�������
	 * @param filterExtensions �ļ����͹����ַ�������
	 */
	public void setFilterExtensions(String[] filterExtensions) {
		this.filterExtensions = filterExtensions;
	}

	/**
	 * �����ļ����͹��������ַ�������
	 * @param filterNames �ļ����͹��������ַ�������
	 */
	public void setFilterNames(String[] filterNames) {
		this.filterNames = filterNames;
	}

	/**
	 * �����Ƿ��¼����򿪵��ļ�λ�á�
	 * @param recordLastPath true����¼��false������¼��
	 */
	public void setRecordLastPath(boolean recordLastPath) {
		this.recordLastPath = recordLastPath;
	}

}