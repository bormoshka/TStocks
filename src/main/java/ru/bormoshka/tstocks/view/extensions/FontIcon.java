/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bormoshka.tstocks.view.extensions;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 *
 * @author 45
 */
public class FontIcon extends Label {

	protected String iconType = "";
	protected String iconStyle = "";
	protected boolean isSpining = false;
	public FontIcon() {
	}

	public FontIcon(String icon) {
		this.iconType = icon;
		getState().text = "<i class=\"icon-dusty " + icon + "\"></i>";
		setContentMode(ContentMode.HTML);
	}

	public FontIcon(String icon, String aClass) {
		this.iconType = icon;
		this.iconStyle = aClass;
		getState().text = "<i class=\"icon-dusty " + icon + " " + aClass + "\"></i>";
		setContentMode(ContentMode.HTML);
	}
	
	public FontIcon(String icon, String aClass, boolean isSpining) {
		this.iconType = icon;
		this.isSpining = isSpining;
		this.iconStyle = aClass;
		String cssClasses;
		if(!isSpining) {
			cssClasses = icon + " " + aClass;
		} else {
			cssClasses = icon + " animate-spin " + aClass;
		}
		getState().text = "<i class=\"icon-dusty " + cssClasses + "\"></i>";
		
		setContentMode(ContentMode.HTML);
	}

	@Override
	public void setValue(String newStringValue) {
		if (getPropertyDataSource() == null) {
			getState().text = "<i class=\"icon-dusty " + newStringValue + "\"></i>";
		} else {
			throw new IllegalStateException(
					"Icon is only a Property.Viewer and cannot update its data source");
		}
	}
	
	public void setSpining(boolean isSpining) {
		
		if (getPropertyDataSource() == null) {
			this.isSpining = isSpining;
			String cssClasses;
			if(!isSpining) {
				cssClasses = iconType + " " + iconStyle;
			} else {
				cssClasses = iconType + " animate-spin " + iconStyle;
			}
			getState().text = "<i class=\"icon-dusty " + cssClasses + "\"></i>";
		} else {
			throw new IllegalStateException(
					"Icon is only a Property.Viewer and cannot update its data source");
		}
	}

	public static class Name {

		public final static String TASKS = "icon-tasks";
		public final static String WRENCH = "icon-wrench";
		public final static String ARCHIVE = "icon-archive";
		public final static String COFFEE = "icon-coffee";
		public final static String EXCHANGE = "icon-exchange";
		public final static String USERS = "icon-users";
		public final static String HOME = "icon-home-1";
		public final static String LIST = "icon-list-alt";
	};
}
