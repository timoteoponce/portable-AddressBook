package org.uagrm.addressbook.model.dao;

import java.util.Collection;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface GenericDao<T> {

	final String SQL_INSERT = "sql.insert";
	final String SQL_UPDATE_ALL = "sql.update_all";
	final String SQL_UPDATE = "sql.update";
	final String SQL_SELECT_ALL = "sql.select_all";
	final String SQL_SELECT = "sql.select";
	final String SQL_SELECT_LAST_ID = "sql.select.last_id";
	final String SQL_DELETE_ALL = "sql.delete_all";
	final String SQL_DELETE = "sql.delete";
	final String VAR_TABLE = "${table}";
	final String VAR_VALUES = "${values}";
	final String VAR_CONDITION = "${condition}";
	final String VAR_COLUMNS = "${columns}";

	void create(T entity);

	T read(T entity);

	void update(T entity);

	void delete(T entity);

	void loadReferences(T entity, Class<?> clazz);

	Collection<T> selectAll();
}
