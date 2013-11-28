/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import ru.bormoshka.tstocks.view.extensions.FontIcon;

/**
 *
 * @author 45
 */
public class TransactionView extends BasicView {
	
	public TransactionView() {
		super();
		this.viewName = "Transaction View";
		iconStyle = FontIcon.Name.EXCHANGE;
		MenuBar.MenuItem mItem = this.viewMenu.addItem("Show Some T", null);
		mItem.addItem("Nope!", null);
		
		viewContent = new HorizontalLayout();
		viewContent.addComponent(new Label("Som weird shit"));
	}
	
	@Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
		basicInit();
	}
	
}
