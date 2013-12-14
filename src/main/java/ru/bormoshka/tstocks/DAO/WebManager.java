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
public class WebManager extends AbstractDAO {

	protected static Configuration config;

	protected SessionFactory sessionFactory;

	public Page getPage(String uri) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}

		Page page = (Page) session.createQuery("from Page where uri = ?")
				.setString(1, uri)
				.uniqueResult();
		if (page == null) {
			throw new Exception("Page not found");
		}
		if (session.getTransaction().isActive()) {
			session.getTransaction().commit();
		}
		return page;
	}

	public List<Page> getAllPages() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		List<Page> pages = session.createQuery("from Page").list();
		if (pages == null || pages.isEmpty()) {
			throw new Exception("None pages was found");
		}
		if (session.getTransaction().isActive()) {
			session.getTransaction().commit();
		}
		return pages;
	}

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

	public static Configuration getConfig() {
		return config;
	}

	public static void setConfig(Configuration config) {
		WebManager.config = config;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
