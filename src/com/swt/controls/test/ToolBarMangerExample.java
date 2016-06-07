package com.swt.controls.test;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

public class ToolBarMangerExample {
	Display display = new Display();
	Shell shell = new Shell(display);
	Action openAction, exitAction;
	ToolBar toolBar;

	public ToolBarMangerExample() {
		MenuManager menuManager = new MenuManager();

		toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT | SWT.BORDER);
		final ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		initActions();// 初始化Action
		toolBarManager.add(openAction);
		ActionContributionItem item = new ActionContributionItem(exitAction);
		item.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		toolBarManager.add(item);
		toolBarManager.update(true);
		toolBar.pack();

		MenuManager fileMenuManager = new MenuManager("&File");
		fileMenuManager.add(openAction);
		fileMenuManager.add(exitAction);
		menuManager.add(fileMenuManager);
		menuManager.updateAll(true);
		MenuManager fileMenuManager1 = new MenuManager("&File1");
		fileMenuManager1.add(openAction);
		fileMenuManager1.add(exitAction);
		menuManager.add(fileMenuManager1);
		menuManager.updateAll(true);

		shell.setMenuBar(menuManager.createMenuBar((Decorations) shell));
		shell.addListener(SWT.Resize, new Listener() {

			public void handleEvent(Event arg0) {
				Rectangle clientArea = shell.getClientArea();
				toolBar.setSize(toolBar.computeSize(clientArea.width, SWT.DEFAULT));
			}
		});
		shell.setSize(300, 200);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

	private void initActions() { // ImageDescriptor.createFromFile(null,
									// "icons/Open.gif")
		// , ImageDescriptor.createFromFile(null, "icons/Exit.gif")

		openAction = new Action("&openAction") {
			public void run() {
				System.out.println("OPEN");
			}
		};
		openAction.setAccelerator(SWT.CTRL + 'O');

		exitAction = new Action("&Exit") {
			public void run() {
				System.out.println("Exit");
			}
		};
		exitAction.setAccelerator(SWT.CTRL + 'E');
	}

	public static void main(String[] args) {
		new ToolBarMangerExample();
	}
}