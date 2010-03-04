package org.uagrm.addressbook.util;

/**
 * @author Timoteo Ponce
 * 
 */
public interface ConfigKeys {
    static final String DATASOURCE_HANDLER = "datasource.handler";
    static final String DATASOURCE_LOCATION = "datasource.location";
    static final String DAO_PREFIX = "dao.prefix";    
    // SQL keys
    final String SQL_INSERT = "sql.insert";
    final String SQL_UPDATE_ALL = "sql.update_all";
    final String SQL_UPDATE = "sql.update";
    final String SQL_SELECT_ALL = "sql.select_all";
    final String SQL_SELECT = "sql.select";
    final String SQL_SELECT_LAST_ID = "sql.select.last_id";
    final String SQL_DELETE_ALL = "sql.delete_all";
    final String SQL_DELETE = "sql.delete";    
}
