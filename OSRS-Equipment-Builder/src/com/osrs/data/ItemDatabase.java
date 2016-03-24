package com.osrs.data;

/**
 * Item Database object for retrieving items from the database
 * @author Landon Reams
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.osrs.npc.Item;

public class ItemDatabase {
	public static final String DB_PATH = "./db/items.db";
	private static final String URL_PREFIX = "jdbc:sqlite:";
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public ItemDatabase(){
		
	}
	
	public Item get(String item){
		if(item == null)
			throw new IllegalArgumentException("String cannot be null.");
		
		Item result = null;
		conn = null; stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL_PREFIX + DB_PATH);
			stmt = conn.prepareStatement("select * from equipment where name = ?");
			stmt.setString(1, item);
			rs = stmt.executeQuery();
		
			if(rs.next()){
				result = new Item();
				String name = rs.getString("name");
				Slot   slot = Slot.fromString(rs.getString("slot"));
				boolean is2h = rs.getBoolean("is2h");
				
				result.setName(name);
				result.setSlot(slot);
				result.set2h(is2h);
				
				for(ArmorStats as : ArmorStats.values()){
					result.set(as, rs.getInt(as.toString()));
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return result;
	}
	
	public Item[] getAll(String[] items){
		if(items == null)
			throw new IllegalArgumentException("Array cannot be null.");
		Item result[] = new Item[items.length];
		conn = null; stmt = null;
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL_PREFIX + DB_PATH);
			stmt = conn.prepareStatement("select * from equipment where name = ?");
			
			for(int i = 0; i < items.length; i++){
				stmt.setString(1, items[i]);
				rs = stmt.executeQuery();
			
				if(rs.next()){
					result[i] = new Item();
					String name = rs.getString("name");
					Slot   slot = Slot.fromString(rs.getString("slot"));
					boolean is2h = rs.getBoolean("is2h");
					
					result[i].setName(name);
					result[i].setSlot(slot);
					result[i].set2h(is2h);
					
					for(ArmorStats as : ArmorStats.values()){
						result[i].set(as, rs.getInt(as.toString()));
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
	public static void main(String[] args){
		ItemDatabase db = new ItemDatabase();
		
		Item i = db.get("Dragon halberd");
		
		System.out.println(i);
	}
	
	
}
