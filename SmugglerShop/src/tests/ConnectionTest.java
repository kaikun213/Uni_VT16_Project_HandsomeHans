package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import baseClasses.ConnectionClass;
import java.sql.ResultSet;


public class ConnectionTest {

	@Test
	public void testConnectionClass() {
		ConnectionClass conn = new ConnectionClass();
	}

	@Test
	public void testFetch() {
		ConnectionClass conn = new ConnectionClass();
		ResultSet test = conn.fetch("select * from webshopDB.product");
	}

}
