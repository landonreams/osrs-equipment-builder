package com.osrs.items;

import java.sql.*;

public class ItemDatabase {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
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
		case "2h":     result = SlotType.TWOHAND; break;
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
				
				SlotType slot = stringToSlot(rs.getString("Slot"));	
				
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
				
				result = new Equippable(name, slot, offensiveStats, defensiveStats, miscStats);
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args){
		EquipmentSet zulrah = new EquipmentSet();
		
		
	
	}
}
