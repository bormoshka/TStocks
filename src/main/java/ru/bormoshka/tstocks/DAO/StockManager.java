/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.bormoshka.tstocks.DAO;

import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bormoshka.tstocks.DAO.entities.AbstractEntity;
import ru.bormoshka.tstocks.DAO.entities.Composite;
import ru.bormoshka.tstocks.DAO.entities.Location;
import ru.bormoshka.tstocks.DAO.entities.Unit;

/**
 *
 * @author 45
 */
public class StockManager {	
	protected static Configuration config;
	@Autowired
    private SessionFactory sessionFactory;

	public List<Unit> getAllUnits() {
		Session session = sessionFactory.getCurrentSession();		
		session.beginTransaction();
				
		List<Unit> units = session.createQuery("from Unit").list();
				
		session.getTransaction().commit();
		
		return units;
	}
	
	@Deprecated
	public Set<Composite> getAllCompositesInLocation(Location location) {
		Session session = sessionFactory.getCurrentSession();		
		session.beginTransaction();
				
		Set<Composite> composites = location.getComposite();
				
		session.getTransaction().commit();
		
		return composites;
	}
	
	public void changeGoodLocation(String location) {
		Session session = sessionFactory.getCurrentSession();		
		session.beginTransaction();
		
		
		session.getTransaction().commit();
	}
	
	public <T extends AbstractEntity> T save(T entity, boolean evict) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		entity.setId((Long) session.save(entity));
		session.flush();
		if (evict) {
			session.evict(entity);
		}
		session.getTransaction().commit();
		return entity;
	}
	
	public <T extends AbstractEntity> T update(T entity, boolean evict) {
		Session session = sessionFactory.getCurrentSession();
		entity = (T) session.merge(entity);
		session.flush();
		if (evict) {
			session.evict(entity);
		}
		return entity;
	}
	
	public <T extends AbstractEntity> T get(Class<T> type, Long id, boolean readonly) {
		Session session = sessionFactory.getCurrentSession();
		T entity = (T) session.get(type, id);
		if (readonly) {
			session.evict(entity);
		}
		return entity;
	}
	
	public <T extends AbstractEntity> void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}	

	public static Configuration getConfig() {
		return config;
	}

	public static void setConfig(Configuration config) {
		StockManager.config = config;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}
