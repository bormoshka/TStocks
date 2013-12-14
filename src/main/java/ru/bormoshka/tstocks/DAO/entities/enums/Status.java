/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bormoshka.tstocks.DAO.entities.enums;

/**
 *
 * @author 45
 */
public enum Status {

	ORDERED(0), IN_STOCK(1), IN_USE(2), OUT_OF_ORDER(3), DEACTIVATED(4), UTILIZED(5), UNDEFINED(45);
	
	private final int id;
	
	private Status(int id)
    {
        this.id = id;
    }

	public static String[] names() {
		Status[] states = values();
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
