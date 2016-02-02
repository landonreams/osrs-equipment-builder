package com.osrs.items;

import java.sql.*;
/**
 * Item Database object for retrieving items from the database
 * @author Landon Reams
 */
public class ItemDatabase {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	/**
	 * Gets the slot type enumeration for the slot type found in the database.
	 * @param slot
	 * @return
	 */
	private static SlotType stringToSlot(String slot){
		SlotType result = null;
		
		switch(slot){
		case "Helmet": result = SlotType.HEAD; break;
		case "Cape":   result = SlotType.CAPE; break;
		case "Amulet": result = SlotType.NECK; break;
		case "Ammo":   result = SlotType.AMMO; break;
		case "Weapon": result = SlotType.MAINHAND; break;
		case "Torso":  result = SlotType.BODY; break;
		case "Shield": result = SlotType.OFFHAND; break;
		case "Legs":   result = SlotType.LEGS; break;
		case "Gloves": result = SlotType.GLOVES; break;
		case "2h":     result = SlotType.MAINHAND; break;
		case "Ring":   result = SlotType.RING; break;
		case "Boots":  result = SlotType.BOOTS; break;
		default: throw new IllegalArgumentException("Invalid slot type: "+slot);
		}
		
		return result;
	}

	public ItemDatabase(){
		conn = null;
		stmt = null;
		rs   = null;
	}
	/**
	 * Gets item from database based on item name
	 * @param item
	 * @return Requested Item As Equippable
	 */
	public Equippable getItem(String item){
		Equippable result = null;
		
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:./db/items;IFEXISTS=TRUE");
			stmt = conn.prepareStatement("SELECT * FROM EQUIPMENT WHERE ITEM = ?");
			stmt.setString(1, item);
			rs = stmt.executeQuery();
			
			if(rs.next()){
			
				String name = rs.getString("Item");
				
				String slotString = rs.getString("Slot");
				boolean isTwoHanded = slotString.equals("2h");
				
				SlotType slot = stringToSlot(slotString);	
				
				int[] offensiveStats = { rs.getInt("StabAccuracy"),
										 rs.getInt("SlashAccuracy"),
										 rs.getInt("CrushAccuracy"),
										 rs.getInt("MageAccuracy"),
										 rs.getInt("RangeAccuracy") };
				
				int[] defensiveStats = { rs.getInt("StabDefence"),
										 rs.getInt("SlashDefence"),
										 rs.getInt("CrushDefence"),
										 rs.getInt("MageDefence"),
										 rs.getInt("RangeDefence") };
				
				int[] miscStats = { rs.getInt("MeleeStrength"),
									rs.getInt("RangeStrength"),
									rs.getInt("MageDamage"),
									rs.getInt("Prayer") };
				
				result = new Equippable(name, slot, offensiveStats, defensiveStats, miscStats, isTwoHanded);
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}
