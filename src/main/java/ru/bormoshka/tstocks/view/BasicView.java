/**
 * DISCLAIMER
 *
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 *
 * @author jouni@vaadin.com
 *
 */
package ru.bormoshka.tstocks.view;

import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import ru.bormoshka.tstocks.local.L;
import ru.bormoshka.tstocks.view.extensions.FontIcon;

public class BasicView extends VerticalLayout implements View {

	protected Panel topPanel = new Panel();
	protected HorizontalLayout panelLayout = new HorizontalLayout();
	protected FontIcon viewIcon;
	protected String viewName = "Default View";
	protected String iconStyle = FontIcon.Name.HOME;
	protected MenuBar viewMenu = new MenuBar();
	protected AbstractLayout viewContent;

	public BasicView() {
		this.addComponent(topPanel);
		topPanel.setContent(panelLayout);
		topPanel.setStyleName("view-panel");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		basicInit();
		viewIcon.setSpining(true);
	}
	protected void basicInit() {
		viewIcon = new FontIcon(iconStyle, "icon36");
		viewMenu.addStyleName("view-menu");
		
		MenuBar.MenuItem menuHelp = viewMenu.addItem("Help", null);
		menuHelp.addItem("About this section", null);
		menuHelp.addItem("Report an error", null);
		if (viewContent != null) {
			addComponent(viewContent);
			
		}
		setStyleName("main-screen");
		panelLayout.addComponent(viewIcon);
		panelLayout.addComponent(viewMenu);
	}
}
