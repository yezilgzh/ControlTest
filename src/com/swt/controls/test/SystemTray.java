package com.swt.controls.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

public class SystemTray extends Shell {

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			SystemTray shell = new SystemTray(display, SWT.SHELL_TRIM);
			shell.createSystemTray(shell);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell
	 * 
	 * @param display
	 * @param style
	 */
	public SystemTray(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(500, 375);

	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void createSystemTray(final Shell shell) {
		// 下面两句的效果是：在任务栏不显示
		final int hWnd = shell.handle;
		OS.SetWindowLong(hWnd, OS.GWL_EXSTYLE, OS.WS_EX_CAPTIONOKBTN);
		shell.addShellListener(new ShellListener() {
			public void shellDeactivated(org.eclipse.swt.events.ShellEvent e) {
				System.out.println("shellDeactivated");
			}

			public void shellActivated(org.eclipse.swt.events.ShellEvent e) {
				System.out.println("shellActivated");
			}

			public void shellClosed(org.eclipse.swt.events.ShellEvent e) {
				System.out.println("shellClosed");
			}

			/**
			 * Sent when a shell is un-minimized.
			 */
			public void shellDeiconified(org.eclipse.swt.events.ShellEvent e) {
				System.out.println("shellDeiconified");
			}

			/**
			 * Sent when a shell is minimized. Shell 最小化后事件
			 */
			public void shellIconified(org.eclipse.swt.events.ShellEvent e) {
				// 最小化时不显示在任务栏
				shell.setVisible(false);
				System.out.println("shell.setVisible(false);");
			}

		});

		Display display = shell.getDisplay();
		// Image image = new Image (display, 16, 16);
		Image image = new Image(display, "icons/barapp1.gif");
		final Tray tray = display.getSystemTray();
		final TrayItem trayItem = new TrayItem(tray, SWT.NONE);
		trayItem.setToolTipText("SWT TrayItem");
		trayItem.addListener(SWT.Show, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("show");
			}
		});
		trayItem.addListener(SWT.Hide, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("hide");
			}
		});

		// 托盘单击事件
		trayItem.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				System.out.println("selection");
				// shell.setVisible(true);
				// shell.setMinimized(false);
			}
		});

		// 托盘双击事件
		trayItem.addListener(SWT.DefaultSelection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("default selection");
				shell.setVisible(true);
				shell.setMinimized(false);
			}
		});

		// 添加托盘右键菜单
		final Menu menu = new Menu(shell, SWT.POP_UP);

		MenuItem menuItemMaximize = new MenuItem(menu, SWT.PUSH);// 最大化菜单
		menuItemMaximize.setText("Maximize");
		menuItemMaximize.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(true);
				shell.setMaximized(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// widgetSelected(e);
			}

		});

		MenuItem menuItemMinimize = new MenuItem(menu, SWT.PUSH);// 最小化菜单
		menuItemMinimize.setText("Minimize");
		menuItemMinimize.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				shell.setMinimized(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// widgetSelected(e);
			}
		});

		new MenuItem(menu, SWT.SEPARATOR);// 分割条

		MenuItem menuItemClose = new MenuItem(menu, SWT.PUSH);// 关闭菜单
		menuItemClose.setText("Close");
		menuItemClose.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				Display.getCurrent().close();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});

		trayItem.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("shell.isVisible():" + shell.isVisible());
				menu.setVisible(true);
				if (shell.isVisible()) {
					menu.getItem(0).setEnabled(true);
					menu.getItem(1).setEnabled(true);
				} else {
					menu.getItem(0).setEnabled(true);
					menu.getItem(1).setEnabled(false);
				}
			}
		});
		trayItem.setImage(image);

	}

}