package ru.bormoshka.tstocks.view;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import java.util.List;
import ru.bormoshka.tstocks.DAO.entities.Unit;
import ru.bormoshka.tstocks.DAO.entities.UnitType;
import ru.bormoshka.tstocks.view.container.UnitTable;

public class TableGenerator {
	
	public static Table getUnitsTableComponent(List<Unit> units) {
		BeanContainer<String, Unit> beans = new BeanContainer<String, Unit>(Unit.class);
		beans.setBeanIdProperty("unitId");
		System.out.print(units.toString());
		beans.addAll(units);
		
		UnitTable table = new UnitTable("Units", beans);
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
}


