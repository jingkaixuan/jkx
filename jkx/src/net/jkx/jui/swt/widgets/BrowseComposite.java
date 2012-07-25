package net.jkx.jui.swt.widgets;

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
 * </p>BrowseComposite是一个浏览文件或文件夹的基于swt的小控件，它的表现形式就是将浏览到的文件或文件夹的全路径显示在一个文本框内。
 * </p><img src="doc-files/BC.png"/> </p>可以设置浏览过滤器来排除不想看见的文件类型。
 * </p>当浏览操作结束后，可以通过一些getter拿到浏览过的文件及文件夹。
 * 
 * @author 荆凯旋
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
				// 如果选择了多个文件，将所有文件名显示在一个中括号内。
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
	static final String ACTION_COMMAND_BROWSE_FILE = "打开文件";
	static final String ACTION_COMMAND_SAVE_FILE = "保存";
	static final String ACTION_COMMAND_BROWSE_FOLDER = "浏览目录";

	static final String MESSAGE_CHOICE_FOLDER = "请选择一个目录";
	
	/**
	 * 浏览文件模式，此模式下仅能浏览文件。
	 */
	public static final int FILE = 0x01;// 0000 0101
	
	/**
	 * 浏览文件夹模式，此模式下仅能浏览文件夹。
	 */
	public static final int FOLDER = 0x02; // 0000 0110
	
	/**
	 * 浏览文件和文件夹模式，此模式下可以同时浏览文件和文件夹。
	 */
	public static final int FILE_FOLDER = FILE | FOLDER;
	
	/**
	 * 浏览时打开的是“打开”对话框。
	 */
	public static final int OPEN = 0x04;

	/**
	 * 浏览时打开的是“保存”对话框。
	 */
	public static final int SAVE = 0x08;

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setLayout(new FillLayout());

		BrowseComposite bc = new BrowseComposite(shell, SWT.NONE,
				BrowseComposite.FILE | OPEN | FOLDER);
		bc.setFilterNames(new String[] { "Autodesk 3ds文件(*.3ds)" });
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
	 * Text控件，用于显示浏览结果。
	 */
	private Text browseResult;

	/**
	 * 文件类型过滤字符串，如{*.java, *.JAVA, *.class}
	 */
	private String[] filterExtensions = null;
	
	/**
	 * 文件类型过滤字符串的描述，如{"Java源文件(*.java)", "Java源文件(*.JAVA)",
	 * "Java字节码文件(*.class)"}
	 */
	private String[] filterNames = null;

	/**
	 * 指定一个配置文件，存储该控件最近一次浏览的位置。
	 */
	File configurationFile = new File(System.getProperties().getProperty(
			"user.dir")
			+ "\\lp.cfg");
	
	/**
	 * 标识是否记录最近一次浏览的位置。
	 */
	boolean recordLastPath = true;

	/**
	 * 浏览位置所处的目录
	 */
	private String path;
	
	/**
	 * 如果浏览类型为文件，该处存储浏览到的文件名。
	 */
	private String[] fileNames;

	/**
	 * 标识状态是否改变（Subject）
	 */
	private boolean changed = false;

	/**
	 * 观察者列表（Subject）
	 */
	private Vector<Observer> obs;

	/**
	 * 浏览模式，默认为浏览文件夹。
	 */
	private int mode = BrowseComposite.FILE_FOLDER;

	/**
	 * 创建一个BrowseComposite。
	 * 
	 * @param parent 该控件的父控件。
	 * @param style 控件样式。
	 * @param mode 浏览模式
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
	 * 返回浏览结果。
	 * @return 浏览文件时返回浏览到的文件全路径；浏览文件夹时返回文件夹的全路径。
	 */
	public String getBrowseResult() {
		return browseResult.getText();
	}

	/**
	 * 返回浏览到的文件名（不含路径名）
	 * @return 浏览到的文件名组成的数组。
	 */
	public String[] getFileNames() {
		return fileNames == null ? new String[]{} : fileNames;
	}

	/**
	 * 返回文件类型过滤字符串数组。
	 * @return
	 */
	public String[] getFilterExtensions() {
		return filterExtensions == null ? new String[]{} : filterExtensions;
	}

	/**
	 * 返回文件类型过滤说明字符串。
	 * @return
	 */
	public String[] getFilterNames() {
		return filterNames == null ? new String[]{} : filterNames;
	}

	/**
	 * 返回浏览结果的路径名（不含文件名）
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
	 * 是否记录最近打开的路径。
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
	 * 设置文件类型过滤字符串数组
	 * @param filterExtensions 文件类型过滤字符串数组
	 */
	public void setFilterExtensions(String[] filterExtensions) {
		this.filterExtensions = filterExtensions;
	}

	/**
	 * 设置文件类型过滤描述字符串数组
	 * @param filterNames 文件类型过滤描述字符串数组
	 */
	public void setFilterNames(String[] filterNames) {
		this.filterNames = filterNames;
	}

	/**
	 * 设置是否记录最近打开的文件位置。
	 * @param recordLastPath true：记录；false：不记录。
	 */
	public void setRecordLastPath(boolean recordLastPath) {
		this.recordLastPath = recordLastPath;
	}

}
