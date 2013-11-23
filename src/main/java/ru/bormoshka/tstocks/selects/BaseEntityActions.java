/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.selects;


/**
 *
 * @author 45
 */
public enum BaseEntityActions {
	EDIT(1), DELETE(2);
	
	private final int id;
	
	private BaseEntityActions(int id)
    {
        this.id = id;
    }

	public static String[] names() {
		BaseEntityActions[] states = values();
		String[] names = new String[states.length];

		for (int i = 0; i < states.length; i++) {
			names[i] = states[i].name();
		}

		return names;
	}

	public int getId() {
		return this.id;
	}
}
