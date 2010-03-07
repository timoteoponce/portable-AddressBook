package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.actions.CommonActions;
import org.uagrm.addressbook.model.Entity;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.util.ConfigKeys;
import org.uagrm.addressbook.util.Configuration;
import org.uagrm.data.DatabaseHandler;
import org.uagrm.data.DatabaseHandlerFactory;

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

    private static final Logger LOG = Logger.getLogger(AbstractSqlDao.class.getName());

    private final DatabaseHandler handler = DatabaseHandlerFactory
	    .getDatabaseHandler();

    @Override
    public void create(T entity) {

	String query = Configuration.getConfigKey(ConfigKeys.SQL_INSERT).trim();
	query = query.replace(VAR_TABLE, getTableName());
	query = query.replace(VAR_VALUES, getFields(entity,
		CommonActions.CREATE));
	LOG.info("Query result: " + handler.executeUpdate(query));
	// retrieve the generated ID
	query = Configuration.getConfigKey(ConfigKeys.SQL_SELECT_LAST_ID);
	query = query.replace(VAR_TABLE, getTableName());

	ResultSet rs = handler.executeQuery(query);
	try {
	    rs.next();
	    ((Entity) entity).setId(rs.getInt(1));
	} catch (SQLException e) {
	    LOG.error(e, e);
	} finally {
	    handler.closeQuietly(rs);
	}
    }

    @Override
    public void delete(T entity) {
	String query = Configuration.getConfigKey(ConfigKeys.SQL_DELETE).trim();
	query = query.replace(VAR_TABLE, getTableName());
	query = query.replace(VAR_CONDITION, "id = "
		+ ((Entity) entity).getId());

	LOG.info("Query result: " + handler.executeUpdate(query));
	deleteReferences(entity);
    }

    protected void deleteReferences(T entity) {
	LOG.info("Removing all references.");
	Collection<ReferenceLink> references = getReferences(entity);

	for (ReferenceLink ref : references) {
	    deleteReference(ref);
	}
    }

    protected void deleteReference(ReferenceLink ref) {
	LOG.info("Removing reference: " + ref.toString());
	String query = Configuration.getConfigKey(ConfigKeys.SQL_DELETE).trim();
	query = query.replace(VAR_TABLE, ref.getTableName());

	if (ref.getSourceColumn() != null) {
	    query = query.replace(VAR_CONDITION, ref.getSourceColumn() + "="
		    + ref.getSourceId());
	} else {
	    query = query.replace(VAR_CONDITION, ref.getTargetColumn() + "="
		    + ref.getTargetId());
	}
	handler.executeUpdate(query);
    }

    protected void createReference(ReferenceLink ref) {
	LOG.info("Adding reference: " + ref.toString());
	String query = Configuration.getConfigKey(ConfigKeys.SQL_INSERT).trim();
	query = query.replace(VAR_TABLE, ref.getTableName());
	query = query.replace(VAR_VALUES, "(" + ref.getSourceId() + ","
		+ ref.getTargetId() + ");");
	getDatabaseHandler().executeUpdate(query);
    }

    @Override
    public T read(T entity) {
	String query = Configuration.getConfigKey(ConfigKeys.SQL_SELECT).trim();
	query = query.replace(VAR_TABLE, getTableName());
	query = query.replace(VAR_COLUMNS, "*");
	query = query.replace(VAR_CONDITION, "id = "
		+ ((Entity) entity).getId());
	ResultSet rs = handler.executeQuery(query);
	try {
	    if (rs.next()) {
		fillValues(entity, rs);
	    } else {
		entity = null;
	    }
	} catch (SQLException e) {
	    LOG.error("Error reading entity: " + ((Entity) entity).getId()
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

	String query = Configuration.getConfigKey(ConfigKeys.SQL_SELECT_ALL)
		.trim();
	query = query.replace(VAR_TABLE, getTableName());
	query = query.replace(VAR_COLUMNS, "*");
	ResultSet rs = handler.executeQuery(query);
	try {
	    while (rs.next()) {
		T tempEntity = loadValues(rs);
		//
		resultList.add(tempEntity);
	    }
	} catch (SQLException e) {
	    LOG.error(e, e);
	} finally {
	    handler.closeQuietly(rs);
	}
	return resultList;
    }

    @Override
    public void update(T entity) {
	String query = Configuration.getConfigKey(ConfigKeys.SQL_UPDATE).trim();
	query = query.replace(VAR_TABLE, getTableName());
	query = query.replace(VAR_VALUES, getFields(entity,
		CommonActions.UPDATE));
	query = query.replace(VAR_CONDITION, "id = "
		+ ((Entity) entity).getId());
	LOG.info("Query result: " + handler.executeUpdate(query));
    }

    protected DatabaseHandler getDatabaseHandler() {
	return handler;
    }

    abstract protected T loadValues(ResultSet rs) throws SQLException;

    abstract protected String getFields(T entity, CommonActions action);

    abstract protected String getTableName();

    abstract protected void fillValues(T entity, ResultSet rs)
	    throws SQLException;

    @Override
    abstract public void loadReferences(T entity, Class<?> clazz);

    abstract protected Collection<ReferenceLink> getReferences(T entity);

}
