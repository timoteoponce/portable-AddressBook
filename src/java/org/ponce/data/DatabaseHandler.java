package org.ponce.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timoteo Ponce
 *
 */
public interface DatabaseHandler {
    void connect();

    ResultSet executeQuery(String query);

    int executeUpdate(String sql);

    Connection getConnection() throws SQLException;

    void closeQuietly(Object obj);

}
