package com.swt.layout.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.swt.composites.test.CBannerTest;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.StackLayout;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.FlowLayout;
import swing2swt.layout.BorderLayout;

public class BorderLayoutTest {
	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BorderLayoutTest window = new BorderLayoutTest();
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
		shell.setSize(729, 468);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));

		Group group = new Group(shell, SWT.NONE);
		group.setLayout(new BorderLayout(0, 0));
		GridData gd_group = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_group.heightHint = 318;
		group.setLayoutData(gd_group);

		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setLayoutData(BorderLayout.WEST);
		btnNewButton.setText("New Button");

		Button btnNewButton_1 = new Button(group, SWT.NONE);
		btnNewButton_1.setLayoutData(BorderLayout.NORTH);
		btnNewButton_1.setText("New Button");

		Button btnNewButton_3 = new Button(group, SWT.NONE);
		btnNewButton_3.setLayoutData(BorderLayout.CENTER);
		btnNewButton_3.setText("New Button");

		Button btnNewButton_2 = new Button(group, SWT.NONE);
		btnNewButton_2.setLayoutData(BorderLayout.EAST);
		btnNewButton_2.setText("New Button");

		Button btnNewButton_4 = new Button(shell, SWT.NONE);
		btnNewButton_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_4.setText("New Button");

	}
}
