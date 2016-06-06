package com.swt.controls.test;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;  
  
public class ToolBarExample {  
    Display display = new Display();  
    Shell shell = new Shell(display);  
    ToolBar toolBar;  
    Action forwardAction,homeAction;  
    public ToolBarExample() {  
//        MenuManager menuManager = new MenuManager();  
        //1.添加工具栏  
        toolBar = new ToolBar(shell,SWT.FLAT|SWT.WRAP|SWT.RIGHT |SWT.BORDER);  
  
        //2.添加工具项－push  
        ToolItem pushItem = new ToolItem(toolBar,SWT.PUSH);  
        pushItem.setText("Push Item");  
//        Image icon = new Image(shell.getDisplay(),"icons/forward.gif");  
//        pushItem.setImage(icon);  
          
        //3.添加工具项－check,radio,seperator  
        ToolItem checkItem = new ToolItem(toolBar,SWT.CHECK);  
        checkItem.setText("Check Item");  
        ToolItem radioItem1 = new ToolItem(toolBar, SWT.RADIO);  
        radioItem1.setText("RADIO item 1");  
        ToolItem radioItem2 = new ToolItem(toolBar, SWT.RADIO);  
        radioItem2.setText("RADIO item 2");  
        ToolItem separatorItem = new ToolItem(toolBar, SWT.SEPARATOR);  
          
        //4.下拉DROP_DOWN、  
        final ToolItem dropDownItem = new ToolItem(toolBar, SWT.DROP_DOWN);  
        dropDownItem.setText("DROP_DOWN item");  
        dropDownItem.setToolTipText("Click here to see a drop down menu ...");  
        final Menu menu = new Menu(shell, SWT.POP_UP);  
        new MenuItem(menu, SWT.PUSH).setText("Menu item 1");  
        new MenuItem(menu, SWT.PUSH).setText("Menu item 2");  
        new MenuItem(menu, SWT.SEPARATOR);  
        new MenuItem(menu, SWT.PUSH).setText("Menu item 3");  
        // 设置工具项的事件监听器  
        dropDownItem.addListener(SWT.Selection, new Listener() {  
            public void handleEvent(Event event) {  
                if (event.detail == SWT.ARROW) {  
                    Rectangle bounds = dropDownItem.getBounds();  
                    Point point = toolBar.toDisplay(bounds.x, bounds.y + bounds.height);  
                    // 设置菜单的显示位置  
                    menu.setLocation(point);  
                    menu.setVisible(true);  
                }  
            }  
        });  
          
        // 5.事件处理  
        // 设置工具项的事件监听器  
        Listener selectionListener = new Listener() {  
            public void handleEvent(Event event) {  
                ToolItem item = (ToolItem) event.widget;  
                System.out.println(item.getText() + " is selected");  
                if ((item.getStyle() & SWT.RADIO) != 0  
                        || (item.getStyle() & SWT.CHECK) != 0)  
                    System.out.println("Selection status: "  
                            + item.getSelection());  
            }  
        };  
        pushItem.addListener(SWT.Selection, selectionListener);  
        checkItem.addListener(SWT.Selection, selectionListener);  
        radioItem1.addListener(SWT.Selection, selectionListener);  
        radioItem2.addListener(SWT.Selection, selectionListener);  
        dropDownItem.addListener(SWT.Selection, selectionListener);  
          
          
        // 6. 其他组件text 、checkbox  
        ToolItem textItem = new ToolItem(toolBar,SWT.SEPARATOR);  
        Text text = new Text(toolBar, SWT.BORDER | SWT.SINGLE);  
        text.pack();  
        textItem.setWidth(text.getSize().x);  
        textItem.setControl(text);  
          
        ToolItem sep = new ToolItem(toolBar, SWT.SEPARATOR);  
        Combo combo = new Combo(toolBar, SWT.READ_ONLY);  
        for (int i = 0; i < 4; i++) {  
          combo.add("Item " + i);  
        }  
        combo.pack();  
        sep.setWidth(combo.getSize().x);  
        sep.setControl(combo);  
          
        toolBar.pack();  
        shell.addListener(SWT.Resize, new Listener(){  
  
            public void handleEvent(Event arg0) {  
                Rectangle clientArea = shell.getClientArea();  
                toolBar.setSize(toolBar.computeSize(clientArea.width, SWT.DEFAULT));  
                  
            }  
              
        });  
        shell.setSize(660, 356);  
        shell.open();  
        while (!shell.isDisposed()) {  
            if (!display.readAndDispatch()) {  
                display.sleep();  
            }  
        }  
        display.dispose();  
    }  
      
    public static void main(String[] args) {  
        new ToolBarExample();  
    }  
  
}  