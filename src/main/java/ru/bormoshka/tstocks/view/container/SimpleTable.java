/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.view.container;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 45
 */
public class SimpleTable extends Table{

	public SimpleTable() {
	}

	public SimpleTable(String caption) {
		super(caption);
	}

	public SimpleTable(String caption, Container dataSource) {
		super(caption, dataSource);
	}
	
    @Override
    protected String formatPropertyValue(Object rowId,
            Object colId, Property property) {
        // Format by property type
        if (property.getType() == Date.class && property.getValue() != null) {
            SimpleDateFormat df =
                new SimpleDateFormat("dd.MM.yyyy");
            return df.format((Date)property.getValue());
        }

        return super.formatPropertyValue(rowId, colId, property);
    }
};
