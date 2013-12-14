/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bormoshka.tstocks.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import ru.bormoshka.tstocks.DAO.StockManager;
import ru.bormoshka.tstocks.DAO.entities.*;
import ru.bormoshka.tstocks.local.L;
import ru.bormoshka.tstocks.view.extensions.FontIcon;

/**
 *
 * @author 45
 */
public class ListsView extends BasicView {

	protected StockManager model;

	public ListsView() {
		super();
		model = (StockManager) MainScreen.getSpringHelper().getBean("stockManager");
		viewName = "Lists View";
		iconStyle = FontIcon.Name.LIST;
		
		viewContent = new CssLayout();
		viewContent.addComponent(new Label("dat content"));
		
		buildMenu();
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		basicInit();
	}

	protected void buildMenu() {
		MenuBar.MenuItem managementItem = this.viewMenu.addItem(L.get("list_edit"), null);
		managementItem.addItem(L.get("list_edit_unit_type"), getDummyCommand());
		managementItem.addItem(L.get("list_edit_location"), getCommand(Location.class));
		managementItem.addItem(L.get("list_edit_location_type"), getCommand(LocationType.class));
		managementItem.addItem(L.get("list_edit_address"), getCommand(Address.class));
		managementItem.addItem(L.get("list_edit_category"), getCommand(Category.class));
	}

	protected MenuBar.Command getCommand(Class newContentClass) {
		return new MenuCommand(viewContent, newContentClass);
	}

	protected MenuBar.Command getDummyCommand() {
		return new MenuBar.Command() {
			public void menuSelected(MenuItem selectedItem) {
				Notification.show("Yep I see you've choosed: " + selectedItem.getText());
			}
		};
	}

}
