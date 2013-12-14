package ru.bormoshka.tstocks.view;

import com.vaadin.data.Item;
import com.vaadin.data.hbnutil.HbnContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import org.hibernate.SessionFactory;
import ru.bormoshka.tstocks.DAO.StockManager;
import ru.bormoshka.tstocks.DAO.entities.*;
import ru.bormoshka.tstocks.local.L;
import ru.bormoshka.tstocks.view.container.SimpleTable;

public class TableGenerator {

	public static Table getTableForClass(Class classname) {
				
		if (classname.equals(Category.class)) {
			return getCategoryTableComponent();
		} else if (classname.equals(LocationType.class)) {
			return getLocationTypeTableComponent();
		} else if (classname.equals(Location.class)) {
			return getLocationTableComponent();
		} else if (classname.equals(Address.class)) {
			return getAddressTableComponent();
		} else if (classname.equals(UnitType.class)) {
			return getUnitTypeTableComponent();
		} else if (classname.equals(Property.class)) {
			return getPropertyTableComponent();
		} 
		return null; //throw!!1111
	}

	public static Table getCategoryTableComponent() {

		SimpleTable table = getTableWithContainer(Category.class, L.get("table_caption_category"));

		table.setVisibleColumns(new Object[]{"id", "name", "description"});

		table.setColumnHeader("id", L.get("table_bean_id"));
		table.setColumnHeader("name", L.get("table_bean_category_name"));
		table.setColumnHeader("description", L.get("table_bean_category_description"));

		return table;
	}

	public static Table getLocationTypeTableComponent() {

		SimpleTable table = getTableWithContainer(LocationType.class, L.get("table_caption_location_type"));

		table.setVisibleColumns(new Object[]{"id", "name", "description"});

		table.setColumnHeader("id", L.get("table_bean_id"));
		table.setColumnHeader("name", L.get("table_bean_location_type_name"));
		table.setColumnHeader("description", L.get("table_bean_description"));

		return table;
	}
	public static Table getLocationTableComponent() {

		SimpleTable table = getTableWithContainer(Location.class, L.get("table_caption_location"));	
		
		table.addGeneratedColumn("fulladdress", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				
				HbnContainer.EntityItem eItem = (HbnContainer.EntityItem) source.getContainerDataSource().getItem(itemId);
				
				Location loc = (Location) eItem.getPojo();				
				
				if (loc != null) {
					Address address = loc.getAddress();
					if(address != null) {
						Label label = new Label(address.getFullAddress());

						label.addStyleName("column-type-value");
						label.addStyleName("column-" + (String) columnId);

						return label;
					}
				}
				return null;				
			}
		});
		
		table.setVisibleColumns(new Object[]{"id", "name", "fulladdress", "room"});		

		table.setColumnHeader("id", L.get("table_bean_id"));
		table.setColumnHeader("name", L.get("table_bean_location_name"));
		table.setColumnHeader("fulladdress", L.get("table_bean_location_address.city"));
		table.setColumnHeader("room", L.get("table_bean_location_room"));
		table.setColumnHeader("type.name", L.get("table_bean_location_type.name"));
		
		return table;
	}
	public static Table getAddressTableComponent() {

		SimpleTable table = getTableWithContainer(Address.class, L.get("table_caption_address"));

		table.setVisibleColumns(new Object[]{"id", "city", "district", "street"});

		table.setColumnHeader("id", L.get("table_bean_id"));
		table.setColumnHeader("city", L.get("table_bean_address_city"));
		table.setColumnHeader("district", L.get("table_bean_address_district"));
		table.setColumnHeader("street", L.get("table_bean_address_street"));

		return table;
	}
	public static Table getUnitTypeTableComponent() {

		SimpleTable table = getTableWithContainer(UnitType.class, L.get("table_caption_unit_type"));

		table.setVisibleColumns(new Object[]{"id", "name", "category.name", "properties", "isComposite", "description"});

		table.setColumnHeader("id", L.get("table_bean_id"));
		table.setColumnHeader("name", L.get("table_bean_unit_type_name"));
		table.setColumnHeader("description", L.get("table_bean_description"));
		table.setColumnHeader("properties", L.get("table_bean_unit_type_properties"));
		table.setColumnHeader("category.name", L.get("table_bean_unit_type_category.name"));

		return table;
	}
	
	public static Table getPropertyTableComponent() {

		SimpleTable table = getTableWithContainer(Property.class, L.get("table_caption_unit_type"));

		table.setVisibleColumns(new Object[]{"id", "name", "description"});

		table.setColumnHeader("id", L.get("table_bean_id"));
		table.setColumnHeader("name", L.get("table_bean_property_name"));
		table.setColumnHeader("description", L.get("table_bean_description"));

		return table;
	}

	private static SimpleTable getTableWithContainer(Class classforContainer, String tableCaption) {
		SessionFactory sessionFactory = (SessionFactory) MainScreen.getSpringHelper().getBean("sessionFactory");
		HbnContainer beans = new HbnContainer(classforContainer, sessionFactory);
	
		beans.addContainerProperty("id", Long.class, null);

		SimpleTable table = new SimpleTable(tableCaption);

		table.setContainerDataSource(beans);
		table.setSelectable(true);
		table.setColumnCollapsingAllowed(true);
		table.setColumnHeaderMode(Table.ColumnHeaderMode.EXPLICIT);
		table.setSizeFull();
		table.setEditable(true);
		table.setImmediate(false);
		
		table.setSortContainerPropertyId("id");
		table.setSortAscending(false);

		return table;
	}
}
