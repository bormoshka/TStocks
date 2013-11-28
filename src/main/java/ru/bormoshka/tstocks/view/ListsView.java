/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bormoshka.tstocks.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import ru.bormoshka.tstocks.DAO.StockManager;
import ru.bormoshka.tstocks.DAO.entities.Category;
import static ru.bormoshka.tstocks.view.MainScreen.helper;
import ru.bormoshka.tstocks.view.extensions.FontIcon;
import ru.bormoshka.tstocks.view.forms.NewCategoryLayout;

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
		
		viewContent = new HorizontalLayout();
		viewContent.addComponent(new Label("dat content"));
		
		buildMenu();
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		basicInit();
	}

	protected void buildMenu() {

		MenuBar.MenuItem createItem = this.viewMenu.addItem(localize("list_new"), null);
		createItem.addItem(localize("list_new_unit_type"), getDummyCommand());
		createItem.addItem(localize("list_new_location"), getDummyCommand());
		createItem.addItem(localize("list_new_location_type"), getDummyCommand());
		createItem.addItem(localize("list_new_category"), getCommand(new NewCategoryLayout(model)));

		MenuBar.MenuItem managementItem = this.viewMenu.addItem(localize("list_edit"), null);
		managementItem.addItem(localize("list_edit_unit_type"), getDummyCommand());
		managementItem.addItem(localize("list_edit_location"), getDummyCommand());
		managementItem.addItem(localize("list_edit_location_type"), getDummyCommand());
		managementItem.addItem(localize("list_edit_category"), getDummyCommand());
	}

	protected MenuBar.Command getCommand(AbstractLayout newContent) {
		return new MenuCommand(viewContent, newContent, model);
	}

	protected MenuBar.Command getDummyCommand() {
		return new MenuBar.Command() {
			public void menuSelected(MenuItem selectedItem) {
				Notification.show("Yep I see you've choosed: " + selectedItem.getText());
			}
		};
	}

}
