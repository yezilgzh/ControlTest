package com.swt.composites.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class ScollCompositeTest {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ScollCompositeTest window = new ScollCompositeTest();
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

		final ScrolledComposite sc1 = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridData gd_sc1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_sc1.heightHint = 95;
		sc1.setLayoutData(gd_sc1);
		final Composite c1 = new Composite(sc1, SWT.NONE);
		sc1.setContent(c1);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		c1.setLayout(layout);
		for (int i = 0; i < 100; i++) {
			Button b1 = new Button(c1, SWT.PUSH);
			b1.setText("first button");
		}
		c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		new Label(c1, SWT.NONE);
		new Label(c1, SWT.NONE);
		new Label(c1, SWT.NONE);

		// set the minimum width and height of the scrolled content - method 2
		final ScrolledComposite sc2 = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridData gd_sc2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_sc2.heightHint = 73;
		sc2.setLayoutData(gd_sc2);
		sc2.setExpandHorizontal(true);
		sc2.setExpandVertical(true);
		final Composite c2 = new Composite(sc2, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 4;
		c2.setLayout(layout);
		for (int i = 0; i < 50; i++) {
			Button b2 = new Button(c2, SWT.PUSH);
			b2.setText("first button");
		}
		sc2.setContent(c2);
		sc2.setMinSize(c2.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Button add = new Button(shell, SWT.PUSH);
		add.setText("add children");
		final int[] index = new int[] { 0 };
		add.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				index[0]++;
				Button button = new Button(c1, SWT.PUSH);
				button.setText("button " + index[0]);
				// reset size of content so children can be seen - method 1
				c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				c1.layout();

				button = new Button(c2, SWT.PUSH);
				button.setText("button " + index[0]);
				// reset the minimum width and height so children can be seen
				// method 2
				sc2.setMinSize(c2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				c2.layout();
			}
		});
	}
}
