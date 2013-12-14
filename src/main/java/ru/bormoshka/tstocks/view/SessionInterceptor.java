/*
 * Copyright (C) 2013 45
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.bormoshka.tstocks.view;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author 45
 */
@WebFilter(urlPatterns = {"/app/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR})
public class SessionInterceptor implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	private static SessionFactory sessionFactory;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		sessionFactory = (SessionFactory) WebApplicationContextUtils.
				getRequiredWebApplicationContext(filterConfig.getServletContext()).
				getBean("sessionFactory");
	}

	@Override
	public void destroy() {
		final Session session = sessionFactory.getCurrentSession();

		if (session.getTransaction().isActive()) {
			session.getTransaction().commit();
		}

		if (session.isOpen()) {
			session.close();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final Session session = sessionFactory.getCurrentSession();
		try {
			if (!session.getTransaction().isActive()) {
				session.beginTransaction();
			}

			chain.doFilter(request, response);

			if (session.getTransaction().isActive()) {
				session.getTransaction().commit();
			}
		} catch (StaleObjectStateException e) {
			logger.error(e.toString());

			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}

			throw e;
		} catch (IOException | ServletException | HibernateException e) {
			logger.error(e.toString());

			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}

			throw new ServletException(e);
		}
	}

}
