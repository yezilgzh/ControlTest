package com.swt.composites.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CTableFolderTest {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CTableFolderTest window = new CTableFolderTest();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));
		final CTabFolder tabFolder = new CTabFolder(shell, SWT.NONE | SWT.CLOSE | SWT.BORDER);
		tabFolder.addCTabFolder2Listener(new CTabFolder2Adapter() {
			public void minimize(CTabFolderEvent event) {
				tabFolder.setMinimized(true);
				tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
				shell.layout(true);// 刷新布局
			}

			public void maximize(CTabFolderEvent event) {
				tabFolder.setMaximized(true);
				tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				shell.layout(true);
			}

			public void restore(CTabFolderEvent event) {
				tabFolder.setMinimized(false);
				tabFolder.setMaximized(false);
				tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
				shell.layout(true);
			}
		});
		// tabFolder.setBounds(0, 0, 283, 211);
		tabFolder.setTabHeight(20);
		tabFolder.marginHeight = 5;
		tabFolder.marginWidth = 5;
		tabFolder.setMaximizeVisible(true);
		tabFolder.setMinimizeVisible(true);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		// 下面两个是设置固定的背景色和前景色
		// tabFolder.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
		// tabFolder.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		// 下面是设置渐变色
		Color[] color = new Color[4];
		color[0] = Display.getDefault().getSystemColor(SWT.COLOR_DARK_BLUE);
		color[1] = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
		color[2] = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
		color[3] = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		int[] intArray = new int[] { 25, 45, 100 };
		tabFolder.setSelectionBackground(color, intArray);
		// 这是设置了背景颜色，但是如果同时设置了背景图片的话以背景图片优先
		tabFolder.setSimple(false);// 设置圆角
		tabFolder.setUnselectedCloseVisible(true);
		for (int i = 1; i < 4; i++) {
			CTabItem item = new CTabItem(tabFolder, SWT.None | SWT.MULTI | SWT.V_SCROLL);
			item.setText("选项卡" + i);
			Text t = new Text(tabFolder, SWT.None | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP);
			t.setText("这是选项卡可以控制的文字" + i + "\n\n世界第一等\n\n一路顺风");
			item.setControl(t);

		}
	}
}
