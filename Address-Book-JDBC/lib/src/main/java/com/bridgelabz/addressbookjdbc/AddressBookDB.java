package com.bridgelabz.addressbookjdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressBookDB {
	public Connection connectDb(String dbName, String user,String pass) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(String.format("jdbc:mysql://localhost:3306/%s?useSSL=false",dbName),user,pass);
			return con;
		}catch(SQLException e){ e.printStackTrace();}
		catch(ClassNotFoundException e){e.printStackTrace();}
		return null;
	}
	
	public void getDataFromTable(Connection con) throws SQLException {
		try{
			ResultSet rs;
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "address_book", null);
			if(tables.next()) {
				String tName = tables.getString("TABLE_NAME");
				if(tName != null && tName.equals("address_book")) {
					PreparedStatement stmt=con.prepareStatement("select * from address_book"); 
					rs=stmt.executeQuery();
					printData(rs);}
			}else {
				Statement stmt=con.createStatement(); 
				stmt.execute("CREATE TABLE address_book (ID int unsigned not null auto_increment,firstName varchar(10) not null,lastName varchar(20) not null,"
						+ "address varchar(100) not null,city varchar(10) not null,state varchar(10) not null,zipCode varchar(15) not null,phoneNumber varchar(22) not null,"
						+ " email varchar(50) not null,primary key(id));");
				System.out.println("New Table Created");
		}}catch(SQLException e) {e.printStackTrace();}
		
	}
	
	public void updateContactByName(Connection con,String name,int contact) {
		try {
			String query ="update address_book set phoneNumber=? where firstName=?";
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setString(1, Integer.toString(contact));
			stmt.setString(2, name);
			stmt.executeUpdate();
			System.out.println("Updated !");
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void printData(ResultSet rs) throws SQLException {
		try {
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
							+" "+rs.getString(4)+"  "+rs.getString(5) + " "+rs.getString(6) 
							+" "+rs.getString(7)+"  "+rs.getString(8) + " "+rs.getString(9) + rs.getString(10));
			}catch(SQLException e){ e.printStackTrace();}
	}

	public void getContactsWithParticularPeriod(Connection con, String startdate)throws SQLException {
		try {
			String query ="select phoneNumber from address_book where startdate between CAST(? AS DATE) AND DATE(NOW()) ";
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setString(1, startdate);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) System.out.println(rs.getString(1));
		}catch(SQLException e){ e.printStackTrace();}
	}
}
