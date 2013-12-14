package ru.bormoshka.tstocks.view;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bormoshka.tstocks.DAO.AbstractDAO;
import ru.bormoshka.tstocks.DAO.StockManager;
import ru.bormoshka.tstocks.local.L;

public class MenuCommand implements MenuBar.Command {

	protected AbstractLayout viewContent;
	protected Class entity;

	public MenuCommand(AbstractLayout viewContent, Class entity) {
		this.viewContent = viewContent;
		this.entity = entity;
	}

	@Override
	public void menuSelected(MenuBar.MenuItem selectedItem) {
		
		this.viewContent.setSizeFull();
		this.viewContent.setStyleName("view-content");
		this.viewContent.setHeight(100,Sizeable.Unit.PERCENTAGE);
		this.viewContent.removeAllComponents();
		addView(entity, viewContent);
	}
	
	private void addView(Class classname, AbstractLayout content) {
		
		final Table table = TableGenerator.getTableForClass(classname);
		AbstractLayout form = FormGenerator.getFormLayoutForClass(classname, table);

		VerticalLayout tableHolder = new VerticalLayout();
		
		Button saveTable = new Button(L.get("save_changes"), new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				table.commit();
			}			
		});
		Button discardTable = new Button(L.get("cancel_changes"), new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				table.discard();
			}			
		});
		HorizontalLayout buttonsHolder = new HorizontalLayout();
		
		buttonsHolder.addComponent(saveTable);
		buttonsHolder.addComponent(discardTable);
		buttonsHolder.setSpacing(true);
		
		tableHolder.addComponent(table);
		tableHolder.addComponent(buttonsHolder);
		tableHolder.setSpacing(true);
		form.setSizeFull();
		form.setStyleName("new-entity-form");
		table.setWidth(100, Sizeable.Unit.PERCENTAGE);
		table.setHeight(100, Sizeable.Unit.PERCENTAGE);
		tableHolder.setSizeFull();
		
		HorizontalSplitPanel sPanel = new HorizontalSplitPanel(form, tableHolder);
		sPanel.setStyleName("h-split-panel");
		sPanel.setSizeFull();
		content.addComponent(sPanel);
	}
}

