package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.actions.ActionType;
import org.uagrm.addressbook.model.Entity;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.util.ConfigKeys;
import org.uagrm.addressbook.util.Configuration;
import org.uagrm.data.DatabaseHandler;
import org.uagrm.data.DatabaseHandlerImpl;

/**
 * @author Timoteo Ponce
 * 
 * @param <T>
 */
public abstract class AbstractSqlDao<T> implements GenericDao<T> {

	public static final String VAR_TABLE = "${table}";
	public static final String VAR_VALUES = "${values}";
	public static final String VAR_CONDITION = "${condition}";
	public static final String VAR_COLUMNS = "${columns}";

	private final DatabaseHandler handler = DatabaseHandlerImpl.getInstance();

	private final Logger log;

	public AbstractSqlDao() {
		log = Logger.getLogger(getClass());
	}

	@Override
	public void create(T entity) {
		final StrBuilder buffer = new StrBuilder();
		buffer.append(Configuration.getConfigKey(ConfigKeys.SQL_INSERT).trim());
		buffer.replaceAll(VAR_TABLE, getTableName());
		buffer.replaceAll(VAR_VALUES, getFields(entity,
				ActionType.CREATE));

		log.info("Query result: " + handler.executeUpdate(buffer.toString()));
		// retrieve the generated ID
		buffer.clear();
		buffer
				.append(Configuration
						.getConfigKey(ConfigKeys.SQL_SELECT_LAST_ID));
		buffer.replaceAll(VAR_TABLE, getTableName());

		ResultSet rs = handler.executeQuery(buffer.toString());
		try {
			rs.next();
			((Entity) entity).setId(rs.getInt(1));
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			handler.closeQuietly(rs);
		}
	}

	@Override
	public void delete(T entity) {
		final StrBuilder buffer = new StrBuilder();
		buffer.append(Configuration.getConfigKey(ConfigKeys.SQL_DELETE).trim());
		buffer.replaceAll(VAR_TABLE, getTableName());
		buffer.replaceAll(VAR_CONDITION, "id = " + ((Entity) entity).getId());

		log.info("Query result: " + handler.executeUpdate(buffer.toString()));
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
		final StrBuilder buffer = new StrBuilder();
		buffer.append(Configuration.getConfigKey(ConfigKeys.SQL_DELETE).trim());
		buffer.replaceAll(VAR_TABLE, ref.getTableName());

		if (StringUtils.isNotEmpty(ref.getSourceColumn())) {
			buffer.replaceAll(VAR_CONDITION, ref.getSourceColumn() + "="
					+ ref.getSourceId());
		} else {
			buffer.replaceAll(VAR_CONDITION, ref.getTargetColumn() + "="
					+ ref.getTargetId());
		}
		handler.executeUpdate(buffer.toString());
	}

	protected void createReference(ReferenceLink ref) {
		log.info("Adding reference: " + ref.toString());
		final StrBuilder buffer = new StrBuilder();
		buffer.append(Configuration.getConfigKey(ConfigKeys.SQL_INSERT).trim());
		buffer.replaceAll(VAR_TABLE, ref.getTableName());
		buffer.replaceAll(VAR_VALUES, "(" + ref.getSourceId() + ","
				+ ref.getTargetId() + ")");


		getDatabaseHandler().executeUpdate(buffer.toString());
	}	

	@Override
	public T read(T entity) {
		final StrBuilder buffer = new StrBuilder();
		buffer.append(Configuration.getConfigKey(ConfigKeys.SQL_SELECT).trim());
		buffer.replaceAll(VAR_TABLE, getTableName());
		buffer.replaceAll(VAR_COLUMNS, "*");
		buffer.replaceAll(VAR_CONDITION, "id = " + ((Entity) entity).getId());

		ResultSet rs = handler.executeQuery(buffer.toString());
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
		Collection<T> resultList = new ArrayList<T>();
		final StrBuilder buffer = new StrBuilder();

		buffer.append(Configuration.getConfigKey(ConfigKeys.SQL_SELECT_ALL)
				.trim());
		buffer.replaceAll(VAR_TABLE, getTableName());
		buffer.replaceAll(VAR_COLUMNS, "*");

		ResultSet rs = handler.executeQuery(buffer.toString());
		try {
			while (rs.next()) {
				T tempEntity = loadValues(rs);
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
		final StrBuilder buffer = new StrBuilder();
		buffer.append(Configuration.getConfigKey(ConfigKeys.SQL_UPDATE).trim());
		buffer.replaceAll(VAR_TABLE, getTableName());
		buffer.replaceAll(VAR_VALUES, getFields(entity, ActionType.UPDATE));
		buffer.replaceAll(VAR_CONDITION, "id = " + ((Entity) entity).getId());




		log.info("Query result: " + handler.executeUpdate(buffer.toString()));
	}


	protected DatabaseHandler getDatabaseHandler() {
		return handler;
	}	
	
	abstract protected T loadValues(ResultSet rs) throws SQLException;

	abstract protected String getFields(T entity, ActionType action);

	abstract protected String getTableName();

	abstract protected void fillValues(T entity, ResultSet rs)
			throws SQLException;

	@Override
	abstract public void loadReferences(T entity, Class<?> clazz);

	abstract protected Collection<ReferenceLink> getReferences(T entity);

}
