/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.DAO.entities;

import com.vaadin.data.util.BeanItem;
import java.io.Serializable;

/**
 *
 * @author 45
 */
public abstract class AbstractEntity implements Serializable {
	abstract public void setId(Long id);	
}
