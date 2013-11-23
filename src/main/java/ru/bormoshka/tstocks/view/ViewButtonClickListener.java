/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.view;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import ru.bormoshka.tstocks.controller.StockController;

public class ViewButtonClickListener implements Button.ClickListener{
	protected AbstractOrderedLayout contentLayout;
	protected StockController stock;

	public ViewButtonClickListener(AbstractOrderedLayout layout, StockController stock) {
		this.contentLayout = layout;
		this.stock = stock;
	}
	
	@Override
	public void buttonClick(Button.ClickEvent event) {
		contentLayout.removeAllComponents();
		
		contentLayout.addComponent(TableGenerator.getUnitsTableComponent(stock.getAllUnits()));

	}
}