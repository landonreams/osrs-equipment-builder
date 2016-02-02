package com.osrs.items;

import java.sql.*;

import org.h2.jdbcx.JdbcDataSource;

public class ItemDatabase {
	public static void main(String[] args){
		JdbcDataSource ds = new JdbcDataSource();
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		ds.setURL("jdbc:h2:./com/osrs/items/itemdb");
		ds.setUser("");
		ds.setPassword("");
		try{
			Connection conn = ds.getConnection();
			//String sql = "SELECT * FROM Equipment";
			//Statement stmt = conn.createStatement();
			//ResultSet rs = stmt.executeQuery(sql);
			
			//while(rs.next()){
			//	System.out.println(rs.getInt("PrayerBonus"));
			//}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
