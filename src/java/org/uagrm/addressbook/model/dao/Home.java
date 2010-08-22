package org.uagrm.addressbook.model.dao;

import java.util.Collection;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface Home<T> {	

	Number getId();

	void setInstance(T instance);

	T getInstance();

	void clearInstance();

	T newInstance();

	void persist();

	T find(T entity);

	void update();

	void delete();

	Collection<T> selectAll();
}
