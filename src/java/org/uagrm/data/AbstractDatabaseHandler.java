package org.uagrm.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author Timoteo Ponce
 * 
 */
public abstract class AbstractDatabaseHandler implements DatabaseHandler {

	private static final Logger log = Logger
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
			log.info("Query:" + query);
			final PreparedStatement pstat = getConnection().prepareStatement(
					query);
			final ResultSet rs = pstat.executeQuery();
			return rs;
		} catch (SQLException e) {
			log.error("Database error", e);
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
			log.info("update:" + sql);
			final PreparedStatement pstat = getConnection().prepareStatement(
					sql);
			final int result = pstat.executeUpdate();
			return result;
		} catch (SQLException e) {
			log.error("Database error", e);
		}// finally {
		// closeQuietly(connection);
		// }
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
			log.error("Database error", e);
		}
	}
}
