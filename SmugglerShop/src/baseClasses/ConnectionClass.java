/**
 * 
 */
package baseClasses;

import java.sql.DriverManager;
import java.util.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kaikun
 *
 *         Our own connection class which is already a set up connection and
 *         just needs to be created to directly fetch sqlQueries to the database
 *         server. Possibly the DB-Name/port/localhost must be adjusted.
 *
 */
public class ConnectionClass {

	/* localhost, port and DB-Name may have to be adjusted */
	private static final String connection_url = "jdbc:mysql://localhost:3306/webshopDB";
	/* Connection data, may has to be changed to yours */
	private String username = "root";
	private String password = "sunny";
	private Connection connectionDB;

	/**
	 * Constructor method creates a connection to the database.
	 */
	public ConnectionClass() {
	}

	/**
	 * 
	 * @param sqlQuery
	 * @return ResultSet, which is just readable_forward_Only and is not
	 *         sensitive to updates in the DB.
	 */
	public ResultSet fetch(String sqlQuery) {
		if (connectionDB == null)
			Connection();
		ResultSet rs = null;

		try {
			PreparedStatement stat = connectionDB.prepareStatement(sqlQuery);
			stat.execute();
			rs = stat.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;

	}

	/**
	 * 
	 * @param sqlQuery
	 * @return ResultSet, which is changeable, sensitive to updates in the DB.
	 */
	public ResultSet update(String sqlQuery) {
		if (connectionDB == null)
			Connection();
		ResultSet rs = null;

		try {
			PreparedStatement stat = connectionDB.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stat.execute();
			rs = stat.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * Sets up a connection to the DB defined by the connection_url with the
	 * user / password data.
	 */
	private void Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Properties user = new Properties();
			user.put("user", username);
			user.put("password", password);
			connectionDB = DriverManager.getConnection(connection_url, user);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Closes an open connection
	 */
	public void closeConnection() {
		try {
			connectionDB.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
