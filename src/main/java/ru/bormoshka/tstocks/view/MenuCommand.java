package ru.bormoshka.tstocks.view;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.MenuBar;
import java.lang.reflect.Constructor;
import ru.bormoshka.tstocks.DAO.AbstractDAO;

public class MenuCommand implements MenuBar.Command {

	protected AbstractLayout viewContent;
	protected AbstractLayout newContent;
	protected AbstractDAO model;

	public MenuCommand(AbstractLayout viewContent, AbstractLayout newLayout, AbstractDAO model) {
		this.viewContent = viewContent;
		this.newContent = newLayout;
		this.model = model;
	}

	@Override
	public void menuSelected(MenuBar.MenuItem selectedItem) {
		this.viewContent.removeAllComponents();
		this.viewContent.addComponent(newContent);
	}
}
