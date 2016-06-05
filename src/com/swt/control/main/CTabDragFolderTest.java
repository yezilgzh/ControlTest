package com.swt.control.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

class CTabDragFolderTest {
	public static void main(String[] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		final CTabFolder tabFolder = new CTabFolder(shell, SWT.CLOSE | SWT.BORDER | SWT.FLAT);
		tabFolder.setSimple(false);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		for (int i = 0; i < 5; i++) {
			CTabItem item = new CTabItem(tabFolder, SWT.NONE);
			item.setText("item " + i);
			Text text = new Text(tabFolder, SWT.BORDER | SWT.MULTI | SWT.VERTICAL);
			text.setText("Text control for " + i);
			item.setControl(text);
		}
		Listener listener = new Listener() {
			boolean drag = false;
			boolean exitDrag = false;
			CTabItem dragItem;
			Cursor cursorSizeAll = new Cursor(null, SWT.CURSOR_SIZEALL);
			Cursor cursorIbeam = new Cursor(null, SWT.CURSOR_NO);
			Cursor cursorArrow = new Cursor(null, SWT.CURSOR_ARROW);

			public void handleEvent(Event e) {
				Point p = new Point(e.x, e.y);
				if (e.type == SWT.DragDetect) {
					p = tabFolder.toControl(display.getCursorLocation()); // see
																			// eclipse
																			// bug
																			// 43251
				}
				switch (e.type) {
				// 拖拉Tab
				case SWT.DragDetect: {
					CTabItem item = tabFolder.getItem(p);
					if (item == null) {
						return;
					}

					drag = true;
					exitDrag = false;
					dragItem = item;

					// 换鼠标形状
					tabFolder.getShell().setCursor(cursorIbeam);
					break;
				}
					// 鼠标进入区域
				case SWT.MouseEnter:
					if (exitDrag) {
						exitDrag = false;
						drag = e.button != 0;
					}
					break;
				// 鼠标离开区域
				case SWT.MouseExit:
					if (drag) {
						tabFolder.setInsertMark(null, false);
						exitDrag = true;
						drag = false;

						// 换鼠标形状
						tabFolder.getShell().setCursor(cursorArrow);
					}
					break;
				// 松开左键
				case SWT.MouseUp: {
					if (!drag) {
						return;
					}
					tabFolder.setInsertMark(null, false);
					CTabItem item = tabFolder.getItem(new Point(p.x, 1));

					if (item != null) {
						int index = tabFolder.indexOf(item);
						int newIndex = tabFolder.indexOf(item);
						int oldIndex = tabFolder.indexOf(dragItem);
						if (newIndex != oldIndex) {
							boolean after = newIndex > oldIndex;
							index = after ? index + 1 : index/* - 1 */;
							index = Math.max(0, index);

							CTabItem newItem = new CTabItem(tabFolder, SWT.NONE, index);
							newItem.setText(dragItem.getText());

							Control c = dragItem.getControl();
							dragItem.setControl(null);
							newItem.setControl(c);
							dragItem.dispose();

							tabFolder.setSelection(newItem);

						}
					}
					drag = false;
					exitDrag = false;
					dragItem = null;

					// 换鼠标形状
					tabFolder.getShell().setCursor(cursorArrow);
					break;
				}
					// 鼠标移动
				case SWT.MouseMove: {
					if (!drag) {
						return;
					}
					CTabItem item = tabFolder.getItem(new Point(p.x, 2));
					if (item == null) {
						tabFolder.setInsertMark(null, false);
						return;
					}
					Rectangle rect = item.getBounds();
					boolean after = p.x > rect.x + rect.width / 2;
					tabFolder.setInsertMark(item, after);

					// 换鼠标形状
					tabFolder.getShell().setCursor(cursorSizeAll);
					break;
				}
				}
			}
		};
		tabFolder.addListener(SWT.DragDetect, listener);
		tabFolder.addListener(SWT.MouseUp, listener);
		tabFolder.addListener(SWT.MouseMove, listener);
		tabFolder.addListener(SWT.MouseExit, listener);
		tabFolder.addListener(SWT.MouseEnter, listener);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
