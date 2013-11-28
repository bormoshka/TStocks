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
public class AppLocale {
	protected final static String defaultLocale = "ru_RU";
	protected final static String localePath = "ru.bormoshka.tstocks.local.base";
	protected final static ResourceBundle interfaceBundle = 
			ResourceBundle.getBundle(	localePath, 
										Locale.forLanguageTag(defaultLocale));
	
	public static String get(String key) {
		return interfaceBundle.getString(key);
	}
}
