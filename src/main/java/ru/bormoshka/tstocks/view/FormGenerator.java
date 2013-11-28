/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bormoshka.tstocks.view;

import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;

import com.vaadin.ui.TextField;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import ru.bormoshka.tstocks.DAO.AbstractDAO;
import ru.bormoshka.tstocks.DAO.BeanWrapper;
import ru.bormoshka.tstocks.DAO.StockManager;
import ru.bormoshka.tstocks.DAO.entities.AbstractEntity;
import ru.bormoshka.tstocks.DAO.entities.Category;
import ru.bormoshka.tstocks.DAO.entities.Location;
import ru.bormoshka.tstocks.DAO.entities.Status;
import ru.bormoshka.tstocks.DAO.entities.Unit;
import ru.bormoshka.tstocks.DAO.entities.UnitType;
import ru.bormoshka.tstocks.controller.StockController;
import ru.bormoshka.tstocks.controller.exception.SavingException;
import ru.bormoshka.tstocks.local.AppLocale;

/**
 *
 * @author 45
 */
public class FormGenerator {

	@Deprecated
	public static Component getAddUnitFormBean(StockController stock) {
		/*
		FormLayout form = new FormLayout();

		BeanItem<UnitType> beanItem = new BeanItem<UnitType>(new UnitType());
		BeanFieldGroup beanBinder = new BeanFieldGroup(UnitType.class);

		beanBinder.setItemDataSource(beanItem);

		form.addComponent(beanBinder.buildAndBind("Unit Name", "name"));
		form.addComponent(beanBinder.buildAndBind("Location", "location"));
		form.addComponent(beanBinder.buildAndBind("Date Begin", "beginDate"));

		form.addComponent(beanBinder.buildAndBind("Status", "status", NativeSelect.class));

		form.setImmediate(false);
		Button submit = new Button("Add Unit");

		submit.addClickListener(new SaveBeanListener<>(beanBinder, stock));

		form.addComponent(submit);

		return form;*/
		return null;
	}

	public static FormLayout getAddLocationFormBean(StockManager manager) {
		FormLayout form = new FormLayout();

		BeanItem<Location> beanItem = new BeanItem<>(new Location());
		BeanFieldGroup beanBinder = new BeanFieldGroup(Location.class);

		beanBinder.setItemDataSource(beanItem);

		form.addComponent(beanBinder.buildAndBind(localize("bean_field_location_name"), "name"));
		form.addComponent(beanBinder.buildAndBind(localize("bean_field_location_room"), "room"));
		form.addComponent(beanBinder.buildAndBind(localize("bean_field_location_type"), "type", NativeSelect.class));

		form.setImmediate(false);
		Button submit = new Button(localize("form_save"));

		submit.addClickListener(new SaveBeanListener<StockManager, Location>(beanBinder, manager));

		form.addComponent(submit);

		return form;
	}
	
	public static FormLayout getAddCategoryFormBean(StockManager manager, Table table) {
		FormLayout form = new FormLayout();

		BeanItem<Category> beanItem = new BeanItem<>(new Category(new String(), new String()));
		BeanFieldGroup beanBinder = new BeanFieldGroup(Category.class);
		
		
		beanBinder.setItemDataSource(beanItem);

		form.addComponent(beanBinder.buildAndBind(localize("bean_field_category_name"), "name"));
		form.addComponent(beanBinder.buildAndBind(localize("bean_field_category_description"), "description"));

		form.setImmediate(false);
		Button submit = new Button(localize("form_save"));
		if(table == null) {
			submit.addClickListener(new SaveBeanListener<StockManager, Category>(beanBinder, manager));
		} else {
			submit.addClickListener(new SaveBeanListener<StockManager, Category>(beanBinder, manager, table));			
		}
		form.addComponent(submit);
		form.setImmediate(true);
		return form;
	}
	
	public static FormLayout getAddCategoryFormBean(StockManager manager) {		
		FormLayout form = getAddCategoryFormBean(manager, null);		
		return form;
	}

	protected static String localize(String string) {
		try {
			return AppLocale.get(string);
		} catch (Exception e) {
			return string;
		}
	}
}

class SaveBeanListener<T extends AbstractDAO, B extends AbstractEntity> implements Button.ClickListener {

	private BeanFieldGroup beanBinder;
	private T model;
	private Table table;

	SaveBeanListener(BeanFieldGroup beanBinder, T model) {
		this.beanBinder = beanBinder;
		this.model = model;
	}
	SaveBeanListener(BeanFieldGroup beanBinder, T model, Table table) {
		this.beanBinder = beanBinder;
		this.model = model;
		this.table = table;		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		event.getButton().setEnabled(false);
		try {
			beanBinder.commit();
		} catch (FieldGroup.CommitException ex) {
			Notification.show("Commit error", Notification.Type.ERROR_MESSAGE);
		}

		BeanItem wrapper = beanBinder.getItemDataSource();

		if (beanBinder.isValid()) {
			B bean = (B) wrapper.getBean();
			model.save(bean, true);
			Notification.show("Unit Saved!");
			if(table != null) {
				BeanContainer bc = (BeanContainer)table.getContainerDataSource();
				bc.addBean(bean);
				table.sort();
			}
			beanBinder.setItemDataSource(BeanWrapper.produce(bean.getClass()));
			
		} else {
			Notification.show("Not Valid Form", Notification.Type.ERROR_MESSAGE);
		}
		event.getButton().setEnabled(true);
	}
}
