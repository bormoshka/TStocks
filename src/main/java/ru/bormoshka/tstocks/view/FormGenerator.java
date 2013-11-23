/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.view;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;

import com.vaadin.ui.TextField;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import ru.bormoshka.tstocks.DAO.entities.Status;
import ru.bormoshka.tstocks.DAO.entities.Unit;
import ru.bormoshka.tstocks.DAO.entities.UnitType;
import ru.bormoshka.tstocks.controller.StockController;
import ru.bormoshka.tstocks.controller.exception.SavingException;

/**
 *
 * @author 45
 */
public class FormGenerator {
	
	public static Component getAddUnitFormBean(StockController stock) {		
		FormLayout form = new FormLayout();
		
		BeanItem<UnitType> beanItem = new BeanItem<UnitType>(new UnitType());		
		BeanFieldGroup beanBinder = new BeanFieldGroup (UnitType.class);	
		
		beanBinder.setItemDataSource(beanItem);
		
		form.addComponent(beanBinder.buildAndBind("Unit Name", "name"));
		form.addComponent(beanBinder.buildAndBind("Location", "location"));
		form.addComponent(beanBinder.buildAndBind("Date Begin", "beginDate"));
		
		form.addComponent(beanBinder.buildAndBind("Status", "status", NativeSelect.class));
		/*
		NativeSelect status = new NativeSelect("Status");
		status.removeAllItems();
		String[] names = Status.names();
		int i = 0;
		
        for (String n: names) {
            status.addItem(i);
            status.setItemCaption(i++, n);
        }

        status.setNullSelectionAllowed(false);
		form.addComponent(status);*/
		
		form.setImmediate(false);
		Button submit = new Button("Add Unit");
		
		submit.addClickListener(new SaveUnitListener(beanBinder, stock));
		
		form.addComponent(submit);
		
		
		
		return form;
	}
}

class SaveUnitListener implements Button.ClickListener {	
	BeanFieldGroup beanBinder;
	StockController stock;
	
	SaveUnitListener(BeanFieldGroup beanBinder, StockController stock) {
		this.beanBinder = beanBinder;
		this.stock = stock;
	}
			@Override
	public void buttonClick(ClickEvent event) {
		
		try {
			beanBinder.commit();
		} catch (FieldGroup.CommitException ex) {
			Notification.show("Commit error", Notification.Type.ERROR_MESSAGE);
		}
		
		BeanItem unitWrap = beanBinder.getItemDataSource();
		
		if(beanBinder.isValid()) {
			try {
				Unit unit = (Unit) unitWrap.getBean();
				stock.saveUnit(unit);
				Notification.show("Unit Saved!");				
			} catch (SavingException ex) {
				Notification.show("Error occured: ", Notification.Type.ERROR_MESSAGE);
			}
		} else {
			Notification.show("Not Valid Form", Notification.Type.ERROR_MESSAGE);
		}
		
	}
}
