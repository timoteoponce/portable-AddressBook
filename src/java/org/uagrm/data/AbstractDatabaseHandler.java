package org.uagrm.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.apache.log4j.Logger;

import com.sun.rowset.CachedRowSetImpl;

/**
 * @author Timoteo Ponce
 * 
 */
public abstract class AbstractDatabaseHandler implements DatabaseHandler {

    private static final Logger LOG = Logger
	    .getLogger(AbstractDatabaseHandler.class);
    private Connection connection;

    @Override
    abstract public void connect();

    /*
     * (non-Javadoc)
     * 
     * @see org.uagrm.data.DatabaseHandler#executeQuery(java.lang.String)
     */
    @Override
    public ResultSet executeQuery(String query) {
	try {
	    LOG.info("Query:" + query);
	    connection = getConnection();
	    final PreparedStatement pstat = connection.prepareStatement(
		    query);
	    final ResultSet rs = pstat.executeQuery();

	    final CachedRowSet rowSet = new CachedRowSetImpl();
	    rowSet.populate(rs);

	    return rowSet;
	} catch (SQLException e) {
	    LOG.error("Database error", e);
	} finally {
	    closeQuietly(connection);
	}
	throw new RuntimeException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.uagrm.data.DatabaseHandler#executeUpdate(java.lang.String)
     */
    @Override
    public int executeUpdate(String sql) {
	try {
	    LOG.info("update:" + sql);
	    connection = getConnection();
	    final PreparedStatement pstat = connection.prepareStatement(
		    sql);
	    final int result = pstat.executeUpdate();
	    return result;
	} catch (SQLException e) {
	    LOG.error("Database error", e);
	} finally {
	    closeQuietly(connection);
	}
	throw new RuntimeException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.uagrm.data.DatabaseHandler#getConnection()
     */
    @Override
    public Connection getConnection() throws SQLException {
	if (connection == null || connection.isClosed()) {
	    connect();
	}
	return connection;
    }

    /**
     * @param connection
     */
    public void setConnection(Connection connection) {
	this.connection = connection;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.uagrm.data.DatabaseHandler#closeQuietly(java.lang.Object)
     */
    @Override
    public void closeQuietly(Object obj) {
	try {
	    if (obj instanceof ResultSet) {
		((ResultSet) obj).close();
	    } else if (obj instanceof Connection) {
		((Connection) obj).close();
	    }
	} catch (SQLException e) {
	    LOG.error("Database error", e);
	}
    }
}
