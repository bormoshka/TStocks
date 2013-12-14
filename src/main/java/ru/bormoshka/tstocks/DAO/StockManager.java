
package ru.bormoshka.tstocks.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bormoshka.tstocks.DAO.entities.*;

/**
 *
 * @author 45
 */
public class StockManager extends AbstractDAO {
	
	protected static Configuration config;
	
    protected SessionFactory sessionFactory;
	
	public <T extends AbstractEntity> T save(T entity, boolean evict) {
		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
		entity.setId((Long) session.save(entity));
		session.flush();
		if (evict) {
			session.evict(entity);
		}
//		session.getTransaction().commit();
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

	public List<Unit> getAllUnits() {
		Session session = sessionFactory.getCurrentSession();		
//		session.beginTransaction();
				
		List<Unit> list = session.createQuery("from Unit").list();
				
//		session.getTransaction().commit();
		
		return list;
	}
	
	public List<LocationType> getAllLocationTypes() {
		Session session = sessionFactory.getCurrentSession();		
				
		List<LocationType> list = session.createQuery("from LocationType").list();
				
		return list;
	}
	
	public List<Category> getAllCategories() {
		Session session = sessionFactory.getCurrentSession();		
//		session.beginTransaction();
				
		List<Category> list = session.createQuery("from Category").list();
				
//		session.getTransaction().commit();
		
		return list;
	}
	
	public Address getLocationAddress(Long locationID) {
		Session session = sessionFactory.getCurrentSession();		
				
		Address address = (Address) session.createQuery("from Address where id = ?")
											.setLong(1, locationID).uniqueResult();

		return address;
	}
			
	public void changeGoodLocation(String location) {
		Session session = sessionFactory.getCurrentSession();		
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
