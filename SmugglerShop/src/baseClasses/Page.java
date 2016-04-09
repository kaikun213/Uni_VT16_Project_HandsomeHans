/**
 * 
 */
package baseClasses;

import java.sql.ResultSet;

/**
 * @author kaikun
 * Abstract page class 
 */

/* unfinished ! */
public abstract class Page {
	
	ConnectionClass conn;
	
	/**
	 * Constructor method establishes the connection
	 */
	public Page(){
		conn = new ConnectionClass();
	}
	
	/**
	 * 
	 * @param sql query to the database e.g. "SELECT * FROM product;" 
	 * @return the ResultSet of the requested rows
	 */
	abstract ResultSet fetch(String sql);

}
