/*
 * Copyright (C) 2013 45
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.bormoshka.tstocks.view.forms;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import ru.bormoshka.tstocks.DAO.StockManager;
import ru.bormoshka.tstocks.view.FormGenerator;
import ru.bormoshka.tstocks.view.TableGenerator;

/**
 *
 * @author 45
 */
public class NewCategoryLayout extends HorizontalLayout {

	protected FormLayout form;
	protected Table table;
	protected StockManager manager;

	public NewCategoryLayout(StockManager manager) {

		table = TableGenerator.getCategoryTableComponent(manager.getAllCategories());
		form = FormGenerator.getAddCategoryFormBean(manager, table);

		form.setSizeFull();
		
		table.setSizeFull();

		addComponent(form);
		addComponent(table);
		setSizeFull();
		setWidth("100%");
		setSpacing(true);
	}
	/*
	public void rebuildForm() {
		FormLayout newForm = FormGenerator.getAddCategoryFormBean(manager, table);
		replaceComponent(form, newForm);
		form = newForm;
	}
*/
}
