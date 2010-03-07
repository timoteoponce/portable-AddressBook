package org.uagrm.addressbook.util;

/**
 * @author Timoteo Ponce
 * 
 */
public interface ConfigKeys {
    String DB_CONNECTION_CLASS = "db.connection.class";
    String DB_CONNECTION_LOCATION = "db.connection.location";
    String DAO_PREFIX = "dao.prefix";    
    // SQL keys
    String SQL_INSERT = "sql.insert";
    String SQL_UPDATE_ALL = "sql.update_all";
    String SQL_UPDATE = "sql.update";
    String SQL_SELECT_ALL = "sql.select_all";
    String SQL_SELECT = "sql.select";
    String SQL_SELECT_LAST_ID = "sql.select.last_id";
    String SQL_DELETE_ALL = "sql.delete_all";
    String SQL_DELETE = "sql.delete";    
}
