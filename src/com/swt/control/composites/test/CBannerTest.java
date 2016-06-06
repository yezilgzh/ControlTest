package com.swt.control.composites.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CBannerTest {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CBannerTest window = new CBannerTest();
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

		CBanner banner = new CBanner(shell, SWT.NONE);
		GridData gd_banner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_banner.heightHint = 126;
		gd_banner.widthHint = 332;
		banner.setLayoutData(gd_banner);

		Composite composite = new Composite(banner, SWT.NONE);
		banner.setLeft(composite);

		Composite composite_1 = new Composite(banner, SWT.NONE);
		banner.setRight(composite_1);

		Composite composite_2 = new Composite(banner, SWT.NONE);
		banner.setBottom(composite_2);
		banner.setRightWidth(200);

	}
}
