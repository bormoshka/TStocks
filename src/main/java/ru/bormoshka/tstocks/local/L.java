/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.local;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author 45
 */
public class L {
	protected final static String defaultLocale = "ru_RU";
	protected final static String localePath = "ru.bormoshka.tstocks.local.base";
	protected final static String webLocalePath = "ru.bormoshka.tstocks.local.web";
	
	protected final static ResourceBundle interfaceBundle = 
			ResourceBundle.getBundle(	localePath, 
										Locale.forLanguageTag(defaultLocale));
	
	protected final static ResourceBundle webInterfaceBundle = 
			ResourceBundle.getBundle(	webLocalePath, 
										Locale.forLanguageTag(defaultLocale));
	
	
	public static String get(String string) {
		try {
			return interfaceBundle.getString(string);
		} catch (Exception e) {
			return string;
		}
	}
	public static String getWeb(String string) {
		try {
			return webInterfaceBundle.getString(string);
		} catch (Exception e) {
			return string;
		}
	}
}
