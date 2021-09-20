package com.bridgelabz.addressbookjdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class AddressBookDBTest {
	public static AddressBookDB addressBookDB = new AddressBookDB();
	
	
	@Test //UC16
	public void connection() {
		try{  
			Connection con = addressBookDB.connectDb("address_book","root","database");
			con.close();  
			}catch(Exception e){e.printStackTrace();}  
	}
	
	@Test // UC16
	public void getDataFromTable() throws Exception {
		try {
			Connection con = addressBookDB.connectDb("address_book","root","database");
			addressBookDB.getDataFromTable(con);
			con.close();  
		}catch(SQLException e){e.printStackTrace();} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
