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
	
	/**
	 * Establishes a connection at creation
	 */
	protected ConnectionClass conn = new ConnectionClass();
	protected ResultSet content;
	
	/**
	 * 
	 * @param sql query to the database e.g. "SELECT * FROM product;" 
	 * @return the ResultSet of the requested rows
	 */
	protected abstract void setContent(String sql);

}
