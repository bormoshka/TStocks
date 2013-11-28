/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.MenuBar;

/**
 *
 * @author 45
 */
public class DataView extends BasicView {
	
	public DataView() {
		super();
		this.viewName = "Data View";
		MenuBar.MenuItem mItem = this.viewMenu.addItem("Get Some Data", null);
		mItem.addItem("Yo!", null);
	}
	
	@Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
		basicInit();
	}
	
}
