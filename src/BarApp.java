
/*
 * Created on May 22, 2005
 *
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tracker;

/**
 * Example of ToolBar, CoolBar, Link, SashForm and various Dialogs.
 *
 * @author barryf
 */
public class BarApp extends BasicApplication {
	// protected Map controls = new HashMap();
	protected Menu menu1, menu2, menu3, menu4, menu5, menu6, menu7;
	protected Tracker tracker;
	protected SashForm sashForm;

	protected List disposeables = new ArrayList();

	protected class XSelectionListener extends SelectionAdapter {
		public void widgetDefaultSelected(SelectionEvent se) {
			widgetSelected(se);
		}

		public void widgetSelected(SelectionEvent se) {
			System.out.println("*** define action ***");
		}
	}

	/**
	 * Constructor.
	 */
	public BarApp(Shell shell, int style) {
		super(shell, style); // must always supply parent and style
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent de) {
				for (Iterator iter = disposeables.iterator(); iter.hasNext();) {
					Object element = iter.next();
					iter.remove();
					try {
						Method dispose = element.getClass().getMethod("dispose", null);
						dispose.invoke(element, null);
					} catch (Exception e) {
						// ignore
					}
				}
			}
		});
	}

	/** Parse any arguments */
	protected void parseArgs(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.charAt(0) == '-') {
				String s = arg.substring(1);
				throw new IllegalArgumentException("Invalid switch: " + s);
			} else {
				throw new IllegalArgumentException("No argument allowed");
			}
		}
	}

	/** Allow subclasses to complete the GUI */
	protected void completeGui(String[] args) {
		parseArgs(args);
		createGui(this);
		displayTree(this);
	}

	protected Image createImage(Device device, String path) {
		Image i = super.createImage(device, path);
		disposeables.add(i);
		return i;
	}

	protected void createGui(BarApp app) {
		ToolItem ti = null;
		CoolItem ci = null;
		Button b = null;
		Composite c = null;
		Label l;
		MenuItem mi;

		// Composite xbody = new Composite(this, SWT.NONE);
		// xbody.setLayout(new FillLayout());

		SashForm sf = createHSashForm(this);

		Composite sash = new Composite(sf, SWT.BORDER);
		sash.setLayout(new FillLayout());
		sashForm = createHSashForm(sash);
		c = new Composite(sashForm, SWT.BORDER);
		c.setLayout(new FillLayout());
		l = createLabel(c, "Left Pane", null, SWT.LEFT);
		c = new Composite(sashForm, SWT.BORDER);
		c.setLayout(new FillLayout());
		l = createLabel(c, "Center Pane", null, SWT.CENTER);
		c = new Composite(sashForm, SWT.BORDER);
		c.setLayout(new FillLayout());
		l = createLabel(c, "Right Pane", null, SWT.RIGHT);
		sashForm.setWeights(new int[] { 33, 33, 33 });
		// sashForm.SASH_WIDTH = 3;
		// Color sc = sashForm.getDisplay().getSystemColor(SWT.COLOR_CYAN);
		// sashForm.setForeground(sc);

		Composite body = new Composite(sf, SWT.BORDER);
		body.setLayout(new GridLayout(2, false));

		sf.setWeights(new int[] { 20, 80 });

		tracker = createTracker(body, SWT.RESIZE, new ControlAdapter() {
			public void controlMoved(ControlEvent ce) {
				System.out.println("Tracker Moved - " + ce);
			}

			public void controlResized(ControlEvent ce) {
				System.out.println("Tracker resized - " + ce);
			}
		}, new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				System.out.println("Tracker KeyPress - " + ke);
			}

			public void keyReleased(KeyEvent ke) {
				System.out.println("Tracker KeyReleases - " + ke);
			}
		});

		ToolBar vtb1 = createVToolBar(body, SWT.NONE);
		GridData vtb1ld = new GridData(GridData.FILL_VERTICAL);
		vtb1ld.grabExcessVerticalSpace = true;
		vtb1ld.horizontalSpan = 1;
		vtb1ld.verticalSpan = 7;
		vtb1.setLayoutData(vtb1ld);

		ti = createToolItem(vtb1, SWT.PUSH, "Open Tracker", null, "Opens a Tracker", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Open Tracker ToolItem selected - " + ((ToolItem) se.widget).getSelection());
				Rectangle[] rects = new Rectangle[1];
				rects[0] = ((ToolItem) se.widget).getParent().getBounds();
				tracker.setRectangles(rects);
				tracker.setStippled(true);
				tracker.open();
			}
		});

		ti = createToolItem(vtb1, SWT.CHECK, "Check", null, "Example Check Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Check ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(vtb1, SWT.DROP_DOWN, "Drop", null, "Example Drop Down Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Drop ToolItem selected - " + ((ToolItem) se.widget).getSelection());
				// ToolBar tb = ((ToolItem)se.widget).getParent();
				// Display d = tb.getDisplay();
				// Point p = tb.getLocation();
				// Point s = tb.getSize();
				// Point np = d.map(tb, null, 0, p.y);
				// menu1.setLocation(np);
				menu1.setVisible(true);
			}
		});
		Menu m1 = menu1 = new Menu(vtb1);
		mi = createMenuItem(m1, SWT.PUSH, "Item 1", null, '1', true, null);
		mi = createMenuItem(m1, SWT.PUSH, "Item 2", null, '2', true, null);
		mi = createMenuItem(m1, SWT.PUSH, "Item 2", null, '3', true, null);

		ti = createToolItem(vtb1, SWT.SEPARATOR, "Separator", null, "Example Separator Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Separator ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti.setControl(new Label(vtb1, SWT.SEPARATOR | SWT.HORIZONTAL));

		ti = createToolItem(vtb1, SWT.RADIO, "Radio 1", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 1 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(vtb1, SWT.RADIO, "Radio 2", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 2 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(vtb1, SWT.RADIO, "Radio 3", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 3 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		CBanner banner1 = createCBanner(body);
		banner1.setSimple(true);
		GridData b1ld = new GridData(GridData.FILL_HORIZONTAL);
		b1ld.grabExcessHorizontalSpace = true;
		b1ld.horizontalSpan = 1;
		b1ld.verticalSpan = 1;
		banner1.setLayoutData(b1ld);

		ToolBar htb1 = createHToolBar(banner1, SWT.NONE);
		banner1.setLeft(htb1);

		ti = createToolItem(htb1, SWT.PUSH, "Press 1", null, "Example Button Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Press 1 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(htb1, SWT.CHECK, "Check", null, "Example Check Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Check ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(htb1, SWT.DROP_DOWN, "Drop", null, "Example Drop Down Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Drop ToolItem selected - " + ((ToolItem) se.widget).getSelection());
				// ToolBar tb = ((ToolItem)se.widget).getParent();
				// Display d = tb.getDisplay();
				// Point p = tb.getLocation();
				// Point s = tb.getSize();
				// Point np = d.map(tb, null, 0, p.y);
				// menu1.setLocation(np);
				menu2.setVisible(true);
			}
		});
		Menu m2 = menu2 = new Menu(htb1);
		mi = createMenuItem(m2, SWT.PUSH, "Item 1", null, '1', true, null);
		mi = createMenuItem(m2, SWT.PUSH, "Item 2", null, '2', true, null);
		mi = createMenuItem(m2, SWT.PUSH, "Item 2", null, '3', true, null);

		ti = createToolItem(htb1, SWT.SEPARATOR, "Separator", null, "Example Separator Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Separator ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti.setControl(new Label(htb1, SWT.SEPARATOR | SWT.VERTICAL));

		ti = createToolItem(htb1, SWT.RADIO, "Radio 1", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 1 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(htb1, SWT.RADIO, "Radio 2", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 2 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(htb1, SWT.RADIO, "Radio 3", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 3 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ToolBar htb2 = createHToolBar(banner1, SWT.NONE);
		banner1.setRight(htb2);

		ti = createToolItem(htb2, SWT.PUSH, "Push 2", null, "Example Button Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Push ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(htb2, SWT.CHECK, "Check", null, "Example Check Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Check ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(htb2, SWT.DROP_DOWN, "Drop", null, "Example Drop Down Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Drop ToolItem selected - " + ((ToolItem) se.widget).getSelection());
				menu3.setVisible(true);
			}
		});
		Menu m3 = menu3 = new Menu(htb2);
		mi = createMenuItem(m3, SWT.PUSH, "Item 1", null, '1', true, null);
		mi = createMenuItem(m3, SWT.PUSH, "Item 2", null, '2', true, null);
		mi = createMenuItem(m3, SWT.PUSH, "Item 2", null, '3', true, null);

		ti = createToolItem(htb2, SWT.SEPARATOR, "Separator", null, "Example Separator Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Separator ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti.setControl(new Label(htb2, SWT.SEPARATOR | SWT.VERTICAL));

		ti = createToolItem(htb2, SWT.RADIO, "Radio 1", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 1 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(htb2, SWT.RADIO, "Radio 2", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 2 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(htb2, SWT.RADIO, "Radio 3", null, "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 3 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		CBanner banner2 = createCBanner(body);
		banner2.setSimple(false);
		GridData b2ld = new GridData(GridData.FILL_HORIZONTAL);
		b2ld.grabExcessHorizontalSpace = true;
		b2ld.horizontalSpan = 1;
		b2ld.verticalSpan = 1;
		banner2.setLayoutData(b2ld);

		ToolBar htb3 = createHToolBar(banner2, SWT.NONE);
		banner2.setLeft(htb3);

		ti = createToolItem(htb3, SWT.PUSH, "Push 3", createImage(getDisplay(), "icons/barapp1.gif"), "Example Button Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Push ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(htb3, SWT.CHECK, "Check", createImage(getDisplay(), "icons/barapp2.gif"), "Example Check Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Check ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(htb3, SWT.DROP_DOWN, "Drop", createImage(getDisplay(), "icons/barapp3.gif"), "Example Drop Down Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Drop ToolItem selected - " + ((ToolItem) se.widget).getSelection());
				menu4.setVisible(true);
			}
		});
		Menu m4 = menu4 = new Menu(htb3);
		mi = createMenuItem(m4, SWT.PUSH, "Item 1", null, '1', true, null);
		mi = createMenuItem(m4, SWT.PUSH, "Item 2", null, '2', true, null);
		mi = createMenuItem(m4, SWT.PUSH, "Item 2", null, '3', true, null);

		ti = createToolItem(htb3, SWT.SEPARATOR, "Separator", null, "Example Separator Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Separator ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti.setControl(new Label(htb3, SWT.SEPARATOR | SWT.VERTICAL));

		ti = createToolItem(htb3, SWT.RADIO, "Radio 1", createImage(getDisplay(), "icons/barapp4.gif"), "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 1 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(htb3, SWT.RADIO, "Radio 2", createImage(getDisplay(), "icons/barapp5.gif"), "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 2 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(htb3, SWT.RADIO, "Radio 3", createImage(getDisplay(), "icons/barapp6.gif"), "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 3 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ToolBar htb4 = createHToolBar(banner2, SWT.NONE);
		banner2.setRight(htb4);

		ti = createToolItem(htb4, SWT.PUSH, "Push 4", createImage(getDisplay(), "icons/barapp1.gif"), "Example Button Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Push ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(htb4, SWT.CHECK, "Check", createImage(getDisplay(), "icons/barapp2.gif"), "Example Check Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Check ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		ti = createToolItem(htb4, SWT.DROP_DOWN, "Drop", createImage(getDisplay(), "icons/barapp3.gif"), "Example Drop Down Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Drop ToolItem selected - " + ((ToolItem) se.widget).getSelection());
				menu2.setVisible(true);
			}
		});
		Menu m5 = menu2 = new Menu(htb4);
		mi = createMenuItem(m5, SWT.PUSH, "Item 1", null, '1', true, null);
		mi = createMenuItem(m5, SWT.PUSH, "Item 2", null, '2', true, null);
		mi = createMenuItem(m5, SWT.PUSH, "Item 2", null, '3', true, null);

		ti = createToolItem(htb4, SWT.SEPARATOR, "Separator", null, "Example Separator Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Separator ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti.setControl(new Label(htb4, SWT.SEPARATOR | SWT.VERTICAL));

		ti = createToolItem(htb4, SWT.RADIO, "Radio 1", createImage(getDisplay(), "icons/barapp4.gif"), "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 1 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(htb4, SWT.RADIO, "Radio 2", createImage(getDisplay(), "icons/barapp5.gif"), "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 2 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});
		ti = createToolItem(htb4, SWT.RADIO, "Radio 3", createImage(getDisplay(), "icons/barapp6.gif"), "Example Radio Item", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 3 ToolItem selected - " + ((ToolItem) se.widget).getSelection());
			}
		});

		// l = createLabel(banner1, "Bottom Label 1");
		// banner1.setBottom(l);
		// l = createLabel(banner2, "Bottom Label 2");
		// banner2.setBottom(l);

		CoolBar hcb1 = createCoolBar(body, SWT.FLAT);
		GridData hcb1ld = new GridData(GridData.FILL_HORIZONTAL);
		// hcb1ld.grabExcessHorizontalSpace = true;
		// hcb1ld.grabExcessVerticalSpace = true;
		hcb1ld.horizontalSpan = 1;
		hcb1ld.verticalSpan = 1;
		hcb1.setLayoutData(hcb1ld);

		ci = createCoolItem(hcb1, SWT.FLAT, "Drop", null, new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Drop CoolItem selected - " + ((ToolItem) se.widget).getSelection());
				menu6.setVisible(true);
			}
		});
		b = createButton(hcb1, SWT.PUSH, "Press Me 1", null, new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Button 1 CoolItem selected - " + ((Button) se.widget).getText());
				menu6.setVisible(true);
			}
		});
		ci.setControl(b);
		Menu m6 = menu6 = new Menu(hcb1);
		mi = createMenuItem(m6, SWT.PUSH, "Item 1", null, '1', true, null);
		mi = createMenuItem(m6, SWT.PUSH, "Item 2", null, '2', true, null);
		mi = createMenuItem(m6, SWT.PUSH, "Item 2", null, '3', true, null);

		ci = createCoolItem(hcb1, SWT.FLAT, "Drop", null, new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Drop CoolItem selected - " + ((ToolItem) se.widget).getSelection());
				menu7.setVisible(true);
			}
		});
		b = createButton(hcb1, SWT.PUSH, "Press Me 2", null, new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Button 2 CoolItem selected - " + ((Button) se.widget).getText());
				menu7.setVisible(true);
			}
		});
		ci.setControl(b);
		Menu m7 = menu7 = new Menu(hcb1);
		mi = createMenuItem(m7, SWT.PUSH, "Item 1", null, '1', true, null);
		mi = createMenuItem(m7, SWT.PUSH, "Item 2", null, '2', true, null);
		mi = createMenuItem(m7, SWT.PUSH, "Item 2", null, '3', true, null);

		ci = createCoolItem(hcb1, SWT.FLAT, "Drop", null, new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 1 selected - " + se.widget);
			}
		});
		b = createButton(hcb1, SWT.RADIO, "Radio 1", null, (SelectionListener) null);
		ci.setControl(b);

		ci = createCoolItem(hcb1, SWT.FLAT, "Drop", null, new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 2 selected - " + se.widget);
			}
		});
		b = createButton(hcb1, SWT.RADIO, "Radio 2", null, (SelectionListener) null);
		ci.setControl(b);

		ci = createCoolItem(hcb1, SWT.FLAT, "Drop", null, new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Radio 3 selected - " + se.widget);
			}
		});
		b = createButton(hcb1, SWT.RADIO, "Radio 3", null, (SelectionListener) null);
		ci.setControl(b);

		int[] order = new int[] { 2, 3, 4, 0, 1 };
		// int[] wrap = new int[] {};
		Point[] sizes = new Point[] { new Point(50, 25), new Point(50, 25), new Point(50, 25), new Point(50, 25), new Point(50, 25) };
		hcb1.setItemLayout(order, null, sizes);
		System.out.print("Item order ");
		printArray(hcb1.getItemOrder());
		System.out.println();
		System.out.print("Item sizes ");
		printArray(hcb1.getItemSizes());
		System.out.println();
		System.out.print("Wrap indexes ");
		printArray(hcb1.getWrapIndices());
		System.out.println();

		Link l1 = createLink(body, "<a href=\"http://www.somecorp.com\">This is a link!</a>", new XSelectionListener() {
			public void widgetSelected(SelectionEvent se) {
				System.out.println("Link selected - " + ((Link) se.widget).getText() + ";" + se.text);
			}
		});
		GridData l1ld = new GridData(GridData.FILL_HORIZONTAL);
		l1ld.grabExcessHorizontalSpace = true;
		l1ld.horizontalSpan = 1;
		l1ld.verticalSpan = 1;
		l1.setLayoutData(l1ld);

		Composite comp = createComposite(body, new RowLayout(SWT.HORIZONTAL));
		b = createButton(comp, SWT.PUSH, "File", null, new XSelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setText("Sample File Dialog");
				String path = fd.open();
				System.out.println("File Dialog - selected=" + path);
			}
		});
		b = createButton(comp, SWT.PUSH, "Directory", null, new XSelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {
				DirectoryDialog dd = new DirectoryDialog(getShell(), SWT.NONE);
				dd.setText("Sample Directory Dialog");
				String path = dd.open();
				System.out.println("Directory Dialog - selected=" + path);
			}
		});
		b = createButton(comp, SWT.PUSH, "Color", null, new XSelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {
				ColorDialog cd = new ColorDialog(getShell(), SWT.NONE);
				cd.setText("Sample Color Dialog");
				RGB rgb = cd.open();
				System.out.println("Color Dialog - selected=" + rgb);
			}
		});
		b = createButton(comp, SWT.PUSH, "Font", null, new XSelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {
				FontDialog fd = new FontDialog(getShell(), SWT.NONE);
				fd.setText("Sample Font Dialog");
				FontData d = fd.open();
				System.out.println("Font Dialog - selected=" + d);
			}
		});

	}

	protected void printArray(int[] ia) {
		if (ia == null) {
			System.out.println("null");
			return;
		}
		for (int i = 0; i < ia.length; i++) {
			int j = ia[i];
			if (i > 0) {
				System.out.print(',');
			}
			System.out.print(j);
		}
	}

	protected void printArray(Point[] ia) {
		if (ia == null) {
			System.out.println("null");
			return;
		}
		for (int i = 0; i < ia.length; i++) {
			Point p = ia[i];
			if (i > 0) {
				System.out.print(',');
			}
			System.out.print("[" + p.x + ',' + p.y + "]");
		}
	}

	/** Allow subclasses to initialize the GUI */
	protected void initGui() {
	}

	/** Main driver */
	public static void main(String[] args) {
		run(BarApp.class.getName(), "BarApp Example", SWT.NONE, 600, 300, args);
	}
}