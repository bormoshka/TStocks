package ru.bormoshka.tstocks.DAO;

import com.vaadin.data.util.BeanItem;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bormoshka.tstocks.DAO.entities.AbstractEntity;
import ru.bormoshka.tstocks.DAO.entities.Category;

/**
 *
 * @author 45
 * @param <B>
 */
public class BeanWrapper {
	
	public static BeanItem produce(Class beanClass) {		
		try {	
			return new BeanItem<>(beanClass.newInstance());
		} catch (InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(BeanWrapper.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
}
