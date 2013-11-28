package ru.bormoshka.tstocks.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bormoshka.tstocks.DAO.entities.AbstractEntity;

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

/**
 *
 * @author 45
 */
public abstract class AbstractDAO {
	
	abstract public <T extends AbstractEntity> T save(T entity, boolean evict);
	
	abstract public <T extends AbstractEntity> T update(T entity, boolean evict);
	
	abstract public <T extends AbstractEntity> T get(Class<T> type, Long id, boolean readonly);
	
	abstract public <T extends AbstractEntity> void delete(T entity);
	
	
}
