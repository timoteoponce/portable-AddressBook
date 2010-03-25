package org.uagrm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.util.ConfigKeys;
import org.uagrm.addressbook.util.Configuration;

import com.sun.rowset.CachedRowSetImpl;

/**
 * @author Timoteo Ponce
 * 
 */
public class DatabaseHandlerImpl implements DatabaseHandler {

	private static final Logger LOG = Logger
			.getLogger(DatabaseHandlerImpl.class.getSimpleName());

	private Connection connection;

	private static DatabaseHandler instance;

	private DatabaseHandlerImpl() {
	}

	public static DatabaseHandler getInstance() {
		if (instance == null) {
			instance = new DatabaseHandlerImpl();
		}
		return instance;
	}

	public static void setInstance(DatabaseHandler instance) {
		DatabaseHandlerImpl.instance = instance;
	}

	@Override
	public void connect() {
		try {
			final String connectionClass = Configuration
					.getConfigKey(ConfigKeys.DB_CONNECTION_CLASS);
			final String dbLocation = Configuration
					.getConfigKey(ConfigKeys.DB_CONNECTION_LOCATION);
			Class.forName(connectionClass);
			setConnection(DriverManager.getConnection(dbLocation));
		} catch (SQLException e) {
			LOG.error("Database error", e);
		} catch (ClassNotFoundException e) {
			LOG.error("JDBC definition not found", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.uagrm.data.DatabaseHandler#executeQuery(java.lang.String)
	 */
	@Override
	public ResultSet executeQuery(String query) {
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			LOG.debug("Query : " + query);
			validateQuery(query);
			connection = getConnection();
			pstat = connection.prepareStatement(query);
			rs = pstat.executeQuery();

			final CachedRowSet rowSet = new CachedRowSetImpl();
			rowSet.populate(rs);

			return rowSet;
		} catch (SQLException e) {
			LOG.error("Database error", e);
		} finally {
			closeQuietly(connection);
			closeQuietly(rs);
			closeQuietly(pstat);
		}
		throw new RuntimeException();
	}

	private void validateQuery(final String query) throws SQLException {
		for (String item : INVALID_STRINGS) {
			if (query.contains(item)) {
				throw new SQLException("Query contains an invalid string [ "
						+ item + " ]");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.uagrm.data.DatabaseHandler#executeUpdate(java.lang.String)
	 */
	@Override
	public int executeUpdate(String query) {
		PreparedStatement pstat = null;
		try {
			LOG.debug("Update : " + query);
			validateQuery(query);
			connection = getConnection();
			pstat = connection.prepareStatement(query);
			final int result = pstat.executeUpdate();
			return result;
		} catch (SQLException e) {
			LOG.error("Database error", e);
		} finally {
			closeQuietly(connection);
			closeQuietly(pstat);
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
			if (obj != null) {
				if (obj instanceof ResultSet) {
					((ResultSet) obj).close();
				} else if (obj instanceof Connection) {
					((Connection) obj).close();
				} else if (obj instanceof Statement) {
					((Statement) obj).close();
				}
			}
		} catch (SQLException e) {
			LOG.error("Database error", e);
		}
	}
}
