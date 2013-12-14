/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bormoshka.tstocks.view;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.hbnutil.HbnContainer;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import javassist.bytecode.SignatureAttribute;
import org.hibernate.SessionFactory;
import ru.bormoshka.tstocks.DAO.AbstractDAO;
import ru.bormoshka.tstocks.DAO.BeanWrapper;
import ru.bormoshka.tstocks.DAO.StockManager;
import ru.bormoshka.tstocks.DAO.entities.*;
import ru.bormoshka.tstocks.controller.StockController;
import ru.bormoshka.tstocks.local.L;
import ru.bormoshka.tstocks.view.container.SimpleTable;

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
	
	public static AbstractLayout getFormLayoutForClass(Class classname, Table table) {
		if(classname.equals(Category.class)) {
			return getAddCategoryFormBean(table);
		} else if(classname.equals(Location.class)){
			return getAddLocationFormBean(table);
		} else if(classname.equals(LocationType.class)){
			return getAddLocationTypeFormBean(table);
		} else if(classname.equals(Address.class)){
			return getAddAddressFormBean(table);
		}
		return null; //throw!!1111
	}

	public static AbstractLayout getAddLocationFormBean(Table table) {
		FormLayout form = new FormLayout();

		BeanItem<Location> beanItem = new BeanItem<>(new Location());
		BeanFieldGroup beanBinder = new BeanFieldGroup(Location.class);
		
		beanItem.addNestedProperty("type.name");

		beanBinder.setItemDataSource(beanItem);
		StockManager stockManager = (StockManager) MainScreen.getSpringHelper().getBean("stockManager");

		ComboBox nSelect = new ComboBox(L.get("bean_field_location_type"), getHbnContainer(LocationType.class));
		nSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		nSelect.setItemCaptionPropertyId("name");
		beanBinder.bind(nSelect, "type");

		form.addComponent(beanBinder.buildAndBind(L.get("bean_field_location_name"), "name"));
		form.addComponent(beanBinder.buildAndBind(L.get("bean_field_location_room"), "room"));
		form.addComponent(nSelect);
		
		
		
		form.setImmediate(false);
		Button submit = new Button(L.get("form_save"));

		submit.addClickListener(new SaveBeanListener<StockManager, Location>(beanBinder, table));

		form.addComponent(submit);

		return form;
	}
	
	public static AbstractLayout getAddLocationTypeFormBean(Table table) {
		FormLayout form = new FormLayout();

		BeanItem<LocationType> beanItem = new BeanItem<>(new LocationType());
		BeanFieldGroup beanBinder = new BeanFieldGroup(LocationType.class);

		beanBinder.setItemDataSource(beanItem);

		form.addComponent(beanBinder.buildAndBind(L.get("table_bean_location_type_name"), "name"));
		form.addComponent(beanBinder.buildAndBind(L.get("table_bean_location_type_description"), "description"));

		form.setImmediate(false);
		Button submit = new Button(L.get("form_save"));

		submit.addClickListener(new SaveBeanListener<StockManager, LocationType>(beanBinder, table));

		form.addComponent(submit);

		return form;
	}
	
	public static AbstractLayout getAddAddressFormBean(Table table) {
		FormLayout form = new FormLayout();

		BeanItem<Address> beanItem = new BeanItem<>(new Address());
		BeanFieldGroup beanBinder = new BeanFieldGroup(Address.class);

		beanBinder.setItemDataSource(beanItem);

		form.addComponent(beanBinder.buildAndBind(L.get("table_bean_address_city"), "city"));
		form.addComponent(beanBinder.buildAndBind(L.get("table_bean_address_district"), "district"));
		form.addComponent(beanBinder.buildAndBind(L.get("table_bean_address_street"), "street"));

		form.setImmediate(false);
		Button submit = new Button(L.get("form_save"));

		submit.addClickListener(new SaveBeanListener<StockManager, Address>(beanBinder, table));

		form.addComponent(submit);

		return form;
	}
		
	public static AbstractLayout getAddCategoryFormBean(Table table) {
		
		FormLayout form = new FormLayout();
		VerticalLayout holder = new VerticalLayout();
		
		BeanItem<Category> beanItem = new BeanItem<>(new Category(new String(), new String()));
		BeanFieldGroup beanBinder = new BeanFieldGroup(Category.class);
		
		
		beanBinder.setItemDataSource(beanItem);

		Label formName = new Label(L.get("new_list_form_label_category"));
		formName.setStyleName("form-name-label");
		holder.addComponent(formName);
		holder.addComponent(form);
		
		form.addComponent(beanBinder.buildAndBind(L.get("bean_field_category_name"), "name"));
		form.addComponent(beanBinder.buildAndBind(L.get("bean_field_category_description"), "description"));

		form.setImmediate(false);
		Button submit = new Button(L.get("form_save"));
		if(table == null) {
			submit.addClickListener(new SaveBeanListener<StockManager, Category>(beanBinder));
		} else {
			submit.addClickListener(new SaveBeanListener<StockManager, Category>(beanBinder, table));			
		}
		form.addComponent(submit);
		form.setImmediate(false);
		return holder;
	}
	
	public static AbstractLayout getAddCategoryFormBean() {		
		AbstractLayout form = getAddCategoryFormBean(null);		
		return form;
	}
	
	private static HbnContainer getHbnContainer(Class classforContainer) {
		SessionFactory sessionFactory = (SessionFactory) MainScreen.getSpringHelper().getBean("sessionFactory");
		
		return new HbnContainer(classforContainer, sessionFactory);
	}
}

class SaveBeanListener<T extends AbstractDAO, B extends AbstractEntity> implements Button.ClickListener {

	private BeanFieldGroup beanBinder;
	private Table table;

	SaveBeanListener(BeanFieldGroup beanBinder) {
		this.beanBinder = beanBinder;
	}
	SaveBeanListener(BeanFieldGroup beanBinder, Table table) {
		this.beanBinder = beanBinder;
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
			if(table != null) {
				HbnContainer bc = (HbnContainer)table.getContainerDataSource();
				bc.saveEntity(bean);
				table.sort();
			} 
			Notification.show("Unit Saved!");
			beanBinder.setItemDataSource(BeanWrapper.produce(bean.getClass()));
			
		} else {
			Notification.show("Not Valid Form", Notification.Type.ERROR_MESSAGE);
		}
		event.getButton().setEnabled(true);
	}
}
