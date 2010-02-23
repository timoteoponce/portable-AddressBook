package org.ponce.addressbook.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
    private static final Logger log = Logger.getLogger(ConnectionUtil.class);
    private static ConnectionUtil instance;
    private Connection connection;

    private ConnectionUtil() {

    }

    public static ConnectionUtil getInstance() {
	if (instance == null) {
	    instance = new ConnectionUtil();
	}
	return instance;
    }

    public void connect() {
	try {
	    Class.forName("org.sqlite.JDBC");
	    connection = DriverManager.getConnection("jdbc:sqlite:"
		    + Configuration.getConfigKey("database.location"));
	} catch (SQLException e) {
	    log.error("Database error", e);
	} catch (ClassNotFoundException e) {
	    log.error("JDBC definition not found", e);
	}
    }

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
    

    public int executeUpdate(String sql) {
	try {
	    log.info("update:" + sql);
	    final PreparedStatement pstat = getConnection().prepareStatement(
		    sql);
	    final int result = pstat.executeUpdate();
	    return result;
	} catch (SQLException e) {
	    log.error("Database error", e);
	} finally {
	    releaseConnection();
	}
	throw new RuntimeException();
    }

    private Connection getConnection() throws SQLException {
	if (connection == null || connection.isClosed()) {
	    connect();
	}
	return connection;	
    }

    private void releaseConnection() {
	try {
	    connection.close();
	} catch (SQLException e) {
	    log.error("Database error", e);
	}
    }

    public static void releaseResource(ResultSet res) {
	try {
	    res.close();	    
	} catch (SQLException e) {
	    log.error("Database error", e);
	}
    }
    
    public static void main(String[] args) throws SQLException, IOException {
	ConnectionUtil conn = ConnectionUtil.getInstance();
	// conn.executeUpdate("CREATE TABLE TEST(id int primary key);");
	conn.executeUpdate("INSERT INTO TEST VALUES (1);");
	conn.executeUpdate("INSERT INTO TEST VALUES (2);");
	conn.executeUpdate("INSERT INTO TEST VALUES (3);");

	ResultSet rs = conn.executeQuery("SELECT * FROM TEST;");
	if (rs.next()) {
	    do {
		log.info("val: " + rs.getInt(1));
	    } while (rs.next());
	}
	conn.releaseResource(rs);
	conn.releaseConnection();
	System.in.read();
    }

}
