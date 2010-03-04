package org.uagrm.addressbook.model.dao;

import java.util.Collection;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface GenericDao<T> {	

	void create(T entity);

	T read(T entity);

	void update(T entity);

	void delete(T entity);

	void loadReferences(T entity, Class<?> clazz);

	Collection<T> selectAll();
}
