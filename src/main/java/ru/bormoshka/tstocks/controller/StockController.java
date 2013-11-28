/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.controller;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import ru.bormoshka.tstocks.DAO.StockManager;
import ru.bormoshka.tstocks.DAO.entities.Location;
import ru.bormoshka.tstocks.DAO.entities.Unit;
import ru.bormoshka.tstocks.controller.exception.SavingException;
/**
 *
 * @author 45
 */
public class StockController implements Serializable{
	protected StockManager manager;
		
	public List<Unit> getAllUnits() {
		return manager.getAllUnits();
	}
		
	public void saveUnit(Unit unit) throws SavingException {
		
		if(unit == null || !unit.validate()) {
			throw new SavingException("Not valid Bean");
		}
		
		manager.save(unit, true);
	}

	public StockManager getManager() {
		return manager;
	}

	public void setManager(StockManager manager) {
		this.manager = manager;
	}
	
}
