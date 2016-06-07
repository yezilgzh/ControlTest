package com.swt.controls.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class CoolBarExample extends Composite {
	public CoolBarExample(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		CoolBar coolBar = new CoolBar(this, SWT.FLAT);
		coolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		CoolItem coolItem = new CoolItem(coolBar, SWT.NONE);

		ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT | SWT.RIGHT);
		coolItem.setControl(toolBar);

		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		ToolItem tltmNewItem11 = new ToolItem(toolBar, SWT.NONE);
		// tltmNewItem.setImage(SWTResourceManager.getImage(CoolBarExample.class,
		// "/org/eclipse/jface/dialogs/images/message_error.gif"));
		tltmNewItem.setText("New Item");
		tltmNewItem11.setText("sss");

		CoolItem coolItem_1 = new CoolItem(coolBar, SWT.NONE);

		ToolBar toolBar_1 = new ToolBar(coolBar, SWT.FLAT | SWT.RIGHT);
		coolItem_1.setControl(toolBar_1);

		ToolItem tltmNewItem_1 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_1.setText("New Item");

		resize(coolBar);
	}

	/**
	 * 修正coolBar中coolItem的尺寸
	 * 
	 * @param coolBar
	 */
	private void resize(CoolBar coolBar) {

		CoolItem[] coolItems = coolBar.getItems();
		for (int i = 0; i < coolItems.length; i++) {
			CoolItem coolItem = coolItems[i];
			Control control = coolItem.getControl();

			Point controlSize = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			Point coolItemSize = coolItem.computeSize(controlSize.x, controlSize.y);

			if (control instanceof ToolBar) {
				ToolBar toolBar = (ToolBar) control;
				if (toolBar.getItemCount() > 0) {
					controlSize.x = toolBar.getItem(0).getWidth();
				}
			}
			coolItem.setMinimumSize(controlSize);
			coolItem.setPreferredSize(coolItemSize);
			coolItem.setSize(coolItemSize);
		}
	}
}