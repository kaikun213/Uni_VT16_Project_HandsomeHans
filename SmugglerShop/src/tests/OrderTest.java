package tests;


import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import baseClasses.Order;
import baseClasses.Product;
public class OrderTest {

	private static int test_count = 0;

	/* Is executed before every test method (not test case). */
	@Before
	public void setUp() {
		test_count++;
		System.out.println("Test " + test_count);
	}

	/* Is executed after every test method (not test case). */
	@After
	public void tearDown() {
	}

	/* Check size */
	@SuppressWarnings("deprecation")
	@Test
	public void testProductDetails() {
		ArrayList<Order> list = new ArrayList<Order>();
		
		Date date = new Date(2016,4, 18);
		for(int i = 0; i < 10; i++){
		Order order = new Order();
		}
		
	}


	
	
}
