package com.swt.conrol.layout.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.swt.control.composites.test.CBannerTest;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.StackLayout;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.FlowLayout;

public class LayoutTest {
	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LayoutTest window = new LayoutTest();
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
		shell.setSize(729, 767);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));

		Group grpFilllaytou = new Group(shell, SWT.NONE);
		grpFilllaytou.setText("filllaytout");
		grpFilllaytou.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_grpFilllaytou = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_grpFilllaytou.heightHint = 79;
		grpFilllaytou.setLayoutData(gd_grpFilllaytou);

		Button btnRadioButton = new Button(grpFilllaytou, SWT.RADIO);
		btnRadioButton.setText("Radio Button");

		Button btnNewButton_1 = new Button(grpFilllaytou, SWT.NONE);
		btnNewButton_1.setText("New Button");

		Button btnNewButton = new Button(grpFilllaytou, SWT.NONE);
		btnNewButton.setText("New Button");

		Group grpAbsoluter = new Group(shell, SWT.NONE);
		grpAbsoluter.setText("absolute");
		grpAbsoluter.setLayout(null);
		GridData gd_grpAbsoluter = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpAbsoluter.heightHint = 74;
		grpAbsoluter.setLayoutData(gd_grpAbsoluter);

		Button btnNewButton_2 = new Button(grpAbsoluter, SWT.NONE);
		btnNewButton_2.setBounds(21, 30, 80, 27);
		btnNewButton_2.setText("New Button");

		Button btnNewButton_3 = new Button(grpAbsoluter, SWT.NONE);
		btnNewButton_3.setBounds(149, 30, 80, 27);
		btnNewButton_3.setText("New Button");

		Button btnNewButton_4 = new Button(grpAbsoluter, SWT.NONE);
		btnNewButton_4.setBounds(266, 30, 80, 27);
		btnNewButton_4.setText("New Button");

		text = new Text(grpAbsoluter, SWT.BORDER);
		text.setBounds(372, 30, 73, 23);

		Group grpRowlayout = new Group(shell, SWT.NONE);
		grpRowlayout.setText("rowlayout");
		// 自动换行
		grpRowlayout.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_grpRowlayout = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_grpRowlayout.heightHint = 59;
		grpRowlayout.setLayoutData(gd_grpRowlayout);
		Button btnNewButton_5 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_5.setText("New Button");

		Button btnNewButton_6 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_6.setText("New Button");

		Button btnNewButton_7 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_7.setText("New Button");

		Button btnNewButton_8 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_8.setText("New Button");

		Button btnNewButton_9 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_9.setText("New Button");

		Button btnNewButton_24 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_24.setText("New Button");

		Button btnNewButton_25 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_25.setText("New Button");

		Button btnNewButton_31 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_31.setText("New Button");

		Button btnNewButton_32 = new Button(grpRowlayout, SWT.NONE);
		btnNewButton_32.setText("New Button");

		Group grpFormlayout = new Group(shell, SWT.NONE);
		grpFormlayout.setText("formlayout");
		grpFormlayout.setLayout(new FormLayout());
		GridData gd_grpFormlayout = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpFormlayout.heightHint = 81;
		grpFormlayout.setLayoutData(gd_grpFormlayout);

		Button btnNewButton_10 = new Button(grpFormlayout, SWT.NONE);
		FormData fd_btnNewButton_10 = new FormData();
		fd_btnNewButton_10.top = new FormAttachment(0);
		fd_btnNewButton_10.left = new FormAttachment(0, 10);
		btnNewButton_10.setLayoutData(fd_btnNewButton_10);
		btnNewButton_10.setText("New Button");

		Button btnNewButton_11 = new Button(grpFormlayout, SWT.NONE);
		FormData fd_btnNewButton_11 = new FormData();
		fd_btnNewButton_11.bottom = new FormAttachment(100, -34);
		fd_btnNewButton_11.left = new FormAttachment(0, 98);
		btnNewButton_11.setLayoutData(fd_btnNewButton_11);
		btnNewButton_11.setText("New Button");

		Button btnNewButton_12 = new Button(grpFormlayout, SWT.NONE);
		FormData fd_btnNewButton_12 = new FormData();
		fd_btnNewButton_12.bottom = new FormAttachment(btnNewButton_11, 0, SWT.BOTTOM);
		fd_btnNewButton_12.left = new FormAttachment(btnNewButton_11, 54);
		btnNewButton_12.setLayoutData(fd_btnNewButton_12);
		btnNewButton_12.setText("New Button");

		Button btnNewButton_13 = new Button(grpFormlayout, SWT.NONE);
		FormData fd_btnNewButton_13 = new FormData();
		fd_btnNewButton_13.top = new FormAttachment(btnNewButton_10, 0, SWT.TOP);
		fd_btnNewButton_13.right = new FormAttachment(btnNewButton_12, 0, SWT.RIGHT);
		btnNewButton_13.setLayoutData(fd_btnNewButton_13);
		btnNewButton_13.setText("New Button");

		Button btnNewButton_14 = new Button(grpFormlayout, SWT.NONE);
		FormData fd_btnNewButton_14 = new FormData();
		fd_btnNewButton_14.bottom = new FormAttachment(100, -22);
		fd_btnNewButton_14.left = new FormAttachment(btnNewButton_10, 0, SWT.LEFT);
		btnNewButton_14.setLayoutData(fd_btnNewButton_14);
		btnNewButton_14.setText("New Button");

		Group grpStacklayout = new Group(shell, SWT.NONE);
		grpStacklayout.setText("stacklayout");
		grpStacklayout.setLayout(new StackLayout());
		GridData gd_grpStacklayout = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpStacklayout.heightHint = 68;
		grpStacklayout.setLayoutData(gd_grpStacklayout);

		Button btnNewButton_17 = new Button(grpStacklayout, SWT.NONE);
		btnNewButton_17.setText("New Button1");

		Button btnNewButton_16 = new Button(grpStacklayout, SWT.NONE);
		btnNewButton_16.setText("33333");

		Button btnNewButton_15 = new Button(grpStacklayout, SWT.NONE);
		btnNewButton_15.setText("33333");
		Group grpFlowlayout = new Group(shell, SWT.NONE);
		grpFlowlayout.setText("flowLayout");
		// 自动换行，切可以设置组件行间距和纵间距
		FlowLayout fl_grpFlowlayout = new FlowLayout(FlowLayout.LEFT, 50, 5);
		grpFlowlayout.setLayout(fl_grpFlowlayout);
		GridData gd_grpFlowlayout = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_grpFlowlayout.heightHint = 87;
		grpFlowlayout.setLayoutData(gd_grpFlowlayout);
		Button btnNewButton_19 = new Button(grpFlowlayout, SWT.NONE);
		btnNewButton_19.setText("New Button1");

		Button btnNewButton_18 = new Button(grpFlowlayout, SWT.NONE);
		btnNewButton_18.setText("New Button2");

		Button btnNewButton_20 = new Button(grpFlowlayout, SWT.NONE);
		btnNewButton_20.setText("New Button3");

		Button btnNewButton_21 = new Button(grpFlowlayout, SWT.NONE);
		btnNewButton_21.setText("New Button");

		Button btnNewButton_22 = new Button(grpFlowlayout, SWT.NONE);
		btnNewButton_22.setText("New Button");

		Button btnNewButton_23 = new Button(grpFlowlayout, SWT.NONE);
		btnNewButton_23.setText("New Button");

		Button btnNewButton_26 = new Button(grpFlowlayout, SWT.NONE);
		btnNewButton_26.setText("New Button");

		Group grpBoxlayout = new Group(shell, SWT.NONE);
		grpBoxlayout.setText("BoxLayout");
		// 不会自动换行的行水平排列或者垂直排列
		grpBoxlayout.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		GridData gd_grpBoxlayout = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpBoxlayout.heightHint = 80;
		grpBoxlayout.setLayoutData(gd_grpBoxlayout);

		Button btnNewButton_27 = new Button(grpBoxlayout, SWT.NONE);
		btnNewButton_27.setText("New Button");

		Button btnNewButton_28 = new Button(grpBoxlayout, SWT.NONE);
		btnNewButton_28.setText("New Button");

		Button btnNewButton_29 = new Button(grpBoxlayout, SWT.NONE);
		btnNewButton_29.setText("New Button");

		Button btnNewButton_30 = new Button(grpBoxlayout, SWT.NONE);
		btnNewButton_30.setText("New Button");

		Button button = new Button(grpBoxlayout, SWT.NONE);
		button.setText("New Button");

		Button button_1 = new Button(grpBoxlayout, SWT.NONE);
		button_1.setText("New Button");

		Button button_5 = new Button(grpBoxlayout, SWT.NONE);
		button_5.setText("New Button");

		Button button_2 = new Button(grpBoxlayout, SWT.NONE);
		button_2.setText("New Button");

		Button button_3 = new Button(grpBoxlayout, SWT.NONE);
		button_3.setText("New Button");

		Button button_4 = new Button(grpBoxlayout, SWT.NONE);
		button_4.setText("New Button");

	}
}
