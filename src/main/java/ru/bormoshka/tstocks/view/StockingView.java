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
public class StockingView extends BasicView {
	
	public StockingView() {
		super();
		this.viewName = "Stocking View";
		iconStyle = FontIcon.Name.TASKS;
		MenuBar.MenuItem mItem = this.viewMenu.addItem("Lets Do some stocking", null);
		mItem.addItem("YAY!", null);
		
		viewContent = new HorizontalLayout();
		viewContent.addComponent(new Label("wuuuut"));
	}
	
	@Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
		basicInit();
		viewIcon.setSpining(true);
	}
	
}
