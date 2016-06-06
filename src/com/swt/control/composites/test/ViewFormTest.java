package com.swt.control.composites.test;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ViewFormTest {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ViewFormTest window = new ViewFormTest();
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

		ViewForm viewForm = new ViewForm(shell, SWT.None);

		ToolBar toolBar = new ToolBar(viewForm, SWT.NONE);
		Text text = new Text(viewForm, SWT.BORDER | SWT.V_SCROLL);
		viewForm.setContent(text);
		viewForm.setTopLeft(toolBar); // 问题在这里

		ToolItem toolItem = new ToolItem(toolBar, SWT.PUSH);
		toolItem.setText("取得");

		ToolItem toolItem2 = new ToolItem(toolBar, SWT.PUSH);
		toolItem2.setText("清除");

		toolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String str = text.getText();
				MessageDialog.openInformation(null, "Text", str);
			}
		});
		toolItem2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
			}
		});

	}
}
