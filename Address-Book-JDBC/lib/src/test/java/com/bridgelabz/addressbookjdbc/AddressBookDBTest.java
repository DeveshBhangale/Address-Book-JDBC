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
	
	@Test // UC17
	public void updateContact() throws Exception{
		try {
			Connection con = addressBookDB.connectDb("address_book","root","database");
			addressBookDB.updateContactByName(con, "Lando",345353535);
		}catch(SQLException e){e.printStackTrace();} 
	}
	
	@Test // UC18
	public void getContactsWithParticularPeriod() throws Exception{
		try {
			Connection con = addressBookDB.connectDb("address_book","root","database");
			addressBookDB.getContactsWithParticularPeriod(con,"2021-09-01");
		}catch(SQLException e){e.printStackTrace();} 
	}
	
	@Test // UC19
	public void getContactsByCityOrState() throws Exception{
		try {
			Connection con = addressBookDB.connectDb("address_book","root","database");
			addressBookDB.getContactsByCityOrState(con,"london","UK");
		}catch(SQLException e){e.printStackTrace();} 
	}
	
//	@Test // UC20
//	public void addContact() throws Exception{
//		try {
//			Connection con = addressBookDB.connectDb("address_book","root","database");
//			addressBookDB.addContact(con,"Parth","Bhangale","New Satara","kharghar","MH",410210,7867868,"abc@gmail.com","family","2021-09-20");
//		}catch(SQLException e){e.printStackTrace();} 
//	}
	
	
}
