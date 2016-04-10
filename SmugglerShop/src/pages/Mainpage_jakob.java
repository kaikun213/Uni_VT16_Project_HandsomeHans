/**
 * 
 */
package pages;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import baseClasses.Page;

/**
 * @author kaikun
 *
 */

@Named
@SessionScoped
public class Mainpage_jakob extends Page implements Serializable {

	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;

	public void setContent(String sql) {
		content = conn.fetch(sql);
	}
	
	public ResultSet getContent(){
		return content;
	}

}
