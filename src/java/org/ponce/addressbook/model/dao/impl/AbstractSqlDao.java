package org.ponce.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.ponce.addressbook.controller.actions.CommonActions;
import org.ponce.addressbook.model.Entity;
import org.ponce.addressbook.model.ReferenceLink;
import org.ponce.addressbook.model.dao.GenericDao;
import org.ponce.addressbook.util.Configuration;
import org.ponce.data.DatabaseHandler;
import org.ponce.data.DatabaseHandlerFactory;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public abstract class AbstractSqlDao<T> implements GenericDao<T> {

	private static final Logger log = Logger.getLogger(SqlGroupDao.class);

	private final DatabaseHandler handler = DatabaseHandlerFactory
			.getDatabaseHandler();

	@Override
	public void create(T entity) {
		String query = Configuration.getConfigKey(GenericDao.SQL_INSERT).trim();
		query = query.replace(GenericDao.VAR_TABLE, getTableName());
		query = query.replace(GenericDao.VAR_VALUES, getFields(entity,
				CommonActions.CREATE));
		log.info("Query result: " + handler.executeUpdate(query));
		// retrieve the generated ID
		query = Configuration.getConfigKey(GenericDao.SQL_SELECT_LAST_ID);
		query = query.replace(GenericDao.VAR_TABLE, getTableName());

		ResultSet rs = handler.executeQuery(query);
		try {
			rs.next();
			((Entity)entity).setId(rs.getInt(1));
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			handler.closeQuietly(rs);
		}
	}

	@Override
	public void delete(T entity) {
		String query = Configuration.getConfigKey(GenericDao.SQL_DELETE).trim();
		query = query.replace(GenericDao.VAR_TABLE, getTableName());
		query = query.replace(GenericDao.VAR_CONDITION, "id = "
				+ ((Entity)entity).getId());

		log.info("Query result: " + handler.executeUpdate(query));
		deleteReferences(entity);
	}

	protected void deleteReferences(T entity) {
		log.info("Removing all references.");
		Collection<ReferenceLink> references = getReferences(entity);

		for (ReferenceLink ref : references) {
			deleteReference(ref);
		}
	}

	protected void deleteReference(ReferenceLink ref) {
		log.info("Removing reference: " + ref.toString());
		String query = Configuration.getConfigKey(GenericDao.SQL_DELETE).trim();
		query = query.replace(GenericDao.VAR_TABLE, ref.getTableName());
		if (ref.getSourceColumn() != null) {
			query = query.replace(GenericDao.VAR_CONDITION, ref
					.getSourceColumn()
					+ "=" + ref.getSourceId());
		} else {
			query = query.replace(GenericDao.VAR_CONDITION, ref
					.getTargetColumn()
					+ "=" + ref.getTargetId());
		}
		handler.executeUpdate(query);
	}

	protected void createReference(ReferenceLink ref) {
		log.info("Adding reference: " + ref.toString());
		String query = Configuration.getConfigKey(GenericDao.SQL_INSERT).trim();
		query = query.replace(GenericDao.VAR_TABLE, ref.getTableName());
		query = query.replace(GenericDao.VAR_VALUES, "(" + ref.getSourceId()
				+ "," + ref.getTargetId() + ");");
		getDatabaseHandler().executeUpdate(query);
	}

	@Override
	public T read(T entity) {
		String query = Configuration.getConfigKey(GenericDao.SQL_SELECT).trim();
		query = query.replace(GenericDao.VAR_TABLE, getTableName());
		query = query.replace(GenericDao.VAR_COLUMNS, "*");
		query = query.replace(GenericDao.VAR_CONDITION, "id = "
				+ ((Entity)entity).getId());
		ResultSet rs = handler.executeQuery(query);
		try {
			rs.next();
			fillValues(entity, rs);
		} catch (SQLException e) {
			log.error("Error reading entity: " + ((Entity)entity).getId() + " > " + e);
			entity = null;
		} finally {
			handler.closeQuietly(rs);
		}
		return entity;
	}

	@Override
	public Collection<T> selectAll() {
		Collection<T> resultList = new ArrayList<T>();

		String query = Configuration.getConfigKey(GenericDao.SQL_SELECT_ALL)
				.trim();
		query = query.replace(GenericDao.VAR_TABLE, getTableName());
		query = query.replace(GenericDao.VAR_COLUMNS, "*");
		ResultSet rs = handler.executeQuery(query);
		try {
			while (rs.next()) {
				T tempEntity = loadValues(rs);
				//
				resultList.add(tempEntity);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			handler.closeQuietly(rs);
		}
		return resultList;
	}

	@Override
	public void update(T entity) {
		String query = Configuration.getConfigKey(GenericDao.SQL_UPDATE).trim();
		query = query.replace(GenericDao.VAR_TABLE, getTableName());
		query = query.replace(GenericDao.VAR_VALUES, getFields(entity,
				CommonActions.UPDATE));
		query = query.replace(GenericDao.VAR_CONDITION, "id = "
				+ ((Entity)entity).getId());
		log.info("Query result: " + handler.executeUpdate(query));
	}

	protected DatabaseHandler getDatabaseHandler() {
		return handler;
	}

	protected abstract T loadValues(ResultSet rs) throws SQLException;

	protected abstract String getFields(T entity, CommonActions action);

	protected abstract String getTableName();

	protected abstract void fillValues(T entity, ResultSet rs)
			throws SQLException;

	@Override
	public abstract void loadReferences(T entity, Class<?> clazz);

	protected abstract Collection<ReferenceLink> getReferences(T entity);

}
