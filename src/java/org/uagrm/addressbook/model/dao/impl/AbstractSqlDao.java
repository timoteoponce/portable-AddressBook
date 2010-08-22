package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.actions.ActionType;
import org.uagrm.addressbook.model.Entity;
import org.uagrm.addressbook.model.dao.Home;
import org.uagrm.addressbook.model.dao.ReferenceLink;
import org.uagrm.addressbook.model.dao.SqlOperation;
import org.uagrm.data.DatabaseHandler;
import org.uagrm.data.DatabaseHandlerImpl;

/**
 * @author Timoteo Ponce
 * 
 * @param <T>
 */
public abstract class AbstractSqlDao<T> implements Home<T> {

	public static final String VAR_TABLE = "${table}";
	public static final String VAR_VALUES = "${values}";
	public static final String VAR_CONDITION = "${condition}";
	public static final String VAR_COLUMNS = "${columns}";

	private T instance;

	private final Class<T> entityClass;

	private final DatabaseHandler handler = DatabaseHandlerImpl.getInstance();

	private final Logger log;

	public AbstractSqlDao(final Class<T> entityClass) {
		log = Logger.getLogger(getClass());
		this.entityClass = entityClass;
	}

	@Override
	public void setInstance(T instance) {
		this.instance = instance;
	}

	@Override
	public T getInstance() {
		if (this.instance == null) {
			setInstance(newInstance());
		}
		return this.instance;
	}

	@Override
	public void clearInstance() {
		setInstance(null);
	}

	@Override
	public T newInstance() {
		T newInstance = null;
		try {
			newInstance = entityClass.newInstance();
		} catch (Exception e) {
			log.error(e, e);
		}
		return newInstance;
	}

	@Override
	public void persist() {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_INSERT);
		builder.setVariable(VAR_TABLE, getTableName());
		builder.setVariable(VAR_VALUES, getFields(ActionType.CREATE));
		log.info("Query result: " + handler.executeUpdate(builder.getQuery()));
		// retrieve the generated ID
		builder.init(SqlOperation.SQL_SELECT_LAST_ID);
		builder.setVariable(VAR_TABLE, getTableName());
		ResultSet rs = handler.executeQuery(builder.getQuery());
		try {
			rs.next();
			((Entity) instance).setId(rs.getInt(1));
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			handler.closeQuietly(rs);
		}
	}

	@Override
	public void delete() {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_DELETE);
		builder.setVariable(VAR_TABLE, getTableName());
		builder.setVariable(VAR_CONDITION, "id = " + ((Entity) instance).getId());

		log.info("Query result: " + handler.executeUpdate(builder.getQuery()));
		deleteReferences();
	}

	protected void deleteReferences() {
		log.info("Removing all references.");
		Collection<ReferenceLink> references = getReferences();

		for (ReferenceLink ref : references) {
			deleteReference(ref);
		}
	}

	protected void deleteReference(ReferenceLink ref) {
		log.info("Removing reference: " + ref.toString());
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_DELETE);
		builder.setVariable(VAR_TABLE, ref.getTableName());

		if (StringUtils.isNotEmpty(ref.getSourceColumn())) {
			builder.setVariable(VAR_CONDITION, ref.getSourceColumn() + "="
					+ ref.getSourceId());
		} else {
			builder.setVariable(VAR_CONDITION, ref.getTargetColumn() + "=" + ref.getTargetId());
		}
		handler.executeUpdate(builder.getQuery());
	}

	protected void createReference(ReferenceLink ref) {
		log.info("Adding reference: " + ref.toString());
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_INSERT);
		builder.setVariable(VAR_TABLE, ref.getTableName());
		builder.setVariable(VAR_VALUES, "(" + ref.getSourceId() + ","
				+ ref.getTargetId() + ")");

		getDatabaseHandler().executeUpdate(builder.getQuery());
	}	

	@Override
	public T find(T entity) {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_SELECT);
		builder.setVariable(VAR_TABLE, getTableName());
		builder.setVariable(VAR_COLUMNS, "*");
		builder.setVariable(VAR_CONDITION, "id = " + ((Entity) entity).getId());

		ResultSet rs = handler.executeQuery(builder.getQuery());
		try {
			if (rs.next()) {
				fillValues(entity, rs);
			} else {
				entity = null;
			}
		} catch (SQLException e) {
			log.error("Error reading entity: " + ((Entity) entity).getId()
					+ " > " + e);
			entity = null;
		} finally {
			handler.closeQuietly(rs);
		}
		return entity;
	}

	@Override
	public Collection<T> selectAll() {
		final Collection<T> resultList = new ArrayList<T>();
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_SELECT_ALL);
		builder.setVariable(VAR_TABLE, getTableName());
		builder.setVariable(VAR_COLUMNS, "*");

		ResultSet rs = handler.executeQuery(builder.getQuery());
		try {
			while (rs.next()) {
				T tempEntity = loadValues(rs);
				resultList.add(tempEntity);
			}
			clearInstance();
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			handler.closeQuietly(rs);
		}
		return resultList;
	}

	@Override
	public void update() {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_UPDATE);
		builder.setVariable(VAR_TABLE, getTableName());
		builder.setVariable(VAR_VALUES, getFields(ActionType.UPDATE));
		builder.setVariable(VAR_CONDITION, "id = " + ((Entity) instance).getId());

		log.info("Query result: " + handler.executeUpdate(builder.getQuery()));
	}


	protected DatabaseHandler getDatabaseHandler() {
		return handler;
	}	

	protected T loadValues(ResultSet rs) throws SQLException {
		T newInstance = newInstance();
		fillValues(newInstance, rs);
		loadReferences(newInstance);

		return newInstance;
	}

	@Override
	public Number getId() {
		return ((Entity) getInstance()).getId();
	}

	abstract protected String getFields(ActionType action);

	abstract protected String getTableName();

	abstract protected void fillValues(T instance, ResultSet rs)
			throws SQLException;
	
	abstract public void loadReferences(T instance);

	abstract protected Collection<ReferenceLink> getReferences();

}
