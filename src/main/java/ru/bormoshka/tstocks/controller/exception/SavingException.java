/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.controller.exception;

/**
 *
 * @author 45
 */
public class SavingException extends Exception {

	/**
	 * Creates a new instance of <code>SavingExeption</code> without detail
	 * message.
	 */
	public SavingException() {
		super("Exeption occured while trying to save Entity");
	}

	/**
	 * Constructs an instance of <code>SavingExeption</code> with the specified
	 * detail message.
	 *
	 * @param msg the detail message.
	 */
	public SavingException(String msg) {
		super(msg);
	}
}
