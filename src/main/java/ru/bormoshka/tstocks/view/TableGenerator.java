package ru.bormoshka.tstocks.view;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import java.util.List;
import ru.bormoshka.tstocks.DAO.entities.Category;
import ru.bormoshka.tstocks.DAO.entities.Unit;
import ru.bormoshka.tstocks.DAO.entities.UnitType;
import ru.bormoshka.tstocks.local.AppLocale;
import ru.bormoshka.tstocks.view.container.SimpleTable;

public class TableGenerator {
	@Deprecated
	public static Table getUnitsTableComponent(List<Unit> units) {
		BeanContainer<String, Unit> beans = new BeanContainer<String, Unit>(Unit.class);
		beans.setBeanIdProperty("unitId");
		System.out.print(units.toString());
		beans.addAll(units);
		
		SimpleTable table = new SimpleTable("Units", beans);
		table.setPageLength(beans.size());
		table.setSelectable(true);
		table.setColumnCollapsingAllowed(true);
		table.setVisibleColumns(new Object[]{"id", "name", "location.name", "beginDate", "endDate", "status"});
		
		table.setColumnHeader("name", "Name");
		table.setColumnHeader("unitId", "ID");
		table.setColumnHeader("location.name", "Location");
		table.setColumnHeader("beginDate", "Registration Date");
		table.setColumnHeader("endDate", "Write-off date");
		table.setColumnHeader("status", "Current Status");
		
		table.setColumnHeaderMode(Table.ColumnHeaderMode.EXPLICIT);
		table.setSizeFull();
		return table;
	}
	
	public static Table getCategoryTableComponent(List<Category> units) {
		BeanContainer<String, Category> beans = new BeanContainer<>(Category.class);
		beans.setBeanIdProperty("id");
		System.out.print(units.toString());
		beans.addAll(units);
		
		
		
		SimpleTable table = new SimpleTable(localize("table_caption_category"), beans);
		table.setPageLength(beans.size());
		table.setSelectable(true);
		table.setColumnCollapsingAllowed(true);
		table.setVisibleColumns(new Object[]{"id", "name", "description"});
		
		table.setColumnHeader("id", localize("table_bean_id"));
		table.setColumnHeader("name", localize("table_bean_category_name"));
		table.setColumnHeader("description", localize("table_bean_category_description"));
		
		table.setColumnHeaderMode(Table.ColumnHeaderMode.EXPLICIT);
		table.setSizeFull();
		table.setImmediate(true);
		table.setSortContainerPropertyId("id");
		table.setSortAscending(false);
		table.setPageLength(30);
		return table;
	}
	protected static String localize(String string) {
		try {
			return AppLocale.get(string);
		} catch (Exception e) {
			return string;
		}
	}
}


