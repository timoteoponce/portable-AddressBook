package org.uagrm.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.uagrm.addressbook.util.Configuration;

/**
 * This interface provides a NON secure(for now) way to access 
 * SQL databases, this generic interface has to be implemented 
 * in order to provide database access to other components.
 * @author Timoteo Ponce
 *
 */
public interface DatabaseHandler {	
    /**
     * Connects to the configured database.
     * The configuration is not handled here, that is the 
     * job of {@link DatabaseHandlerFactory} and the application 
     *  properties on {@link Configuration}.
     */
    void connect();

    /**
     * Executes an SQL query and returns the resultant {@link ResultSet}.
     * This method only supports SELECT commands.
     * @param query query to be executed.
     * @return
     */
    ResultSet executeQuery(String query);

    /**
     * Performs a database UPDATE/DELETE using the given SQL string, returns 
     * the operation status.
     * @param sql update to be executed.
     * @return operation status.
     */
    int executeUpdate(String sql);

    /**
     * Returns the current database connection.
     * @return current database connection.
     * @throws SQLException if the database connection is not active.
     */
    Connection getConnection() throws SQLException;

    /**
     * Invokes the 'close' operation in a given resource, common 
     * resource objects are {@link ResultSet} and {@link Connection}.
     * 
     * It's 'Quietly' because all exceptions are swallowed.
     * 
     * @param obj resource to close.
     */
    void closeQuietly(Object obj);

}
