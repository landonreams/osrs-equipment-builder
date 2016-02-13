package com.osrs.items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Item Database object for retrieving items from the database
 * @author Landon Reams
 */
public class ItemDatabase {
	public static final String URL = "jdbc:h2:file:./db/items;IFEXISTS=TRUE";
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
	
	private static String slotToString(SlotType slot){
		switch(slot){
		case HEAD: return "Helmet";
		case BODY: return "Torso";
		case LEGS: return "Legs";
		case CAPE: return "Cape";
		case AMMO: return "Ammo";
		case BOOTS: return "Boots";
		case GLOVES: return "Gloves";
		case MAINHAND: return "Weapon";
		case NECK: return "Amulet";
		case OFFHAND: return "Shield";
		case RING: return "Ring";
		default: throw new IllegalArgumentException("This shouldn't happen. You dun goofed.");
		}
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
			conn = DriverManager.getConnection(URL);
			stmt = conn.prepareStatement("SELECT * FROM EQUIPMENT WHERE ITEM = ?");
			stmt.setString(1, item);
			rs = stmt.executeQuery();
			
			if(rs.next()){
			
				String name = rs.getString("Item");
				
				String slotString = rs.getString("Slot");
				boolean isTwoHanded = slotString.equals("2h");
				
				SlotType slot = stringToSlot(slotString);	
				
				int[] stats = { rs.getInt("StabAccuracy"),
								rs.getInt("SlashAccuracy"),
								rs.getInt("CrushAccuracy"),
			    				rs.getInt("MageAccuracy"),
								rs.getInt("RangeAccuracy"),
								rs.getInt("StabDefence"),
								rs.getInt("SlashDefence"),
								rs.getInt("CrushDefence"),
							    rs.getInt("MageDefence"),
							    rs.getInt("RangeDefence"),
							    rs.getInt("MeleeStrength"),
								rs.getInt("RangeStrength"),
								rs.getInt("MageDamage"),
								rs.getInt("Prayer") };
				
				result = new Equippable(name, slot, stats, isTwoHanded);
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Equippable[] getAll(String[] items){
		//Equippable[] result = null;
		ArrayList<Equippable> alResult = new ArrayList<Equippable>();
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:./db/items;IFEXISTS=TRUE");
			stmt = conn.prepareStatement("SELECT * FROM EQUIPMENT WHERE ITEM = ?");
			for(String item : items){
				stmt.setString(1, item);
				rs = stmt.executeQuery();
				
				if(rs.next()){
				
					String name = rs.getString("Item");
					
					String slotString = rs.getString("Slot");
					boolean isTwoHanded = slotString.equals("2h");
					
					SlotType slot = stringToSlot(slotString);	
					
					int[] stats = { rs.getInt("StabAccuracy"),
									rs.getInt("SlashAccuracy"),
									rs.getInt("CrushAccuracy"),
				    				rs.getInt("MageAccuracy"),
									rs.getInt("RangeAccuracy"),
									rs.getInt("StabDefence"),
									rs.getInt("SlashDefence"),
									rs.getInt("CrushDefence"),
								    rs.getInt("MageDefence"),
								    rs.getInt("RangeDefence"),
								    rs.getInt("MeleeStrength"),
									rs.getInt("RangeStrength"),
									rs.getInt("MageDamage"),
									rs.getInt("Prayer") };
					
					alResult.add(new Equippable(name, slot, stats, isTwoHanded));
				} 
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		Equippable[] result = new Equippable[alResult.size()];
		return alResult.toArray(result);
	}
	
	public String[] findMatching(String item){
		ArrayList<String> alResult = new ArrayList<String>();
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:./db/items;IFEXISTS=TRUE");
			stmt = conn.prepareStatement("SELECT ITEM FROM EQUIPMENT WHERE ITEM LIKE ?");
			stmt.setString(1, "%%" + item + "%%");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				alResult.add(rs.getString("Item"));
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		String[] result = new String[alResult.size()];
		Collections.sort(alResult);
		return alResult.toArray(result);
	}
	
	public String[] findMatching(String item, SlotType slot){
		ArrayList<String> alResult = new ArrayList<String>();
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:./db/items;IFEXISTS=TRUE");
			stmt = conn.prepareStatement("SELECT ITEM FROM EQUIPMENT WHERE ITEM LIKE ? AND SLOT LIKE ?");
			stmt.setString(1, "%%" + item + "%%");
			stmt.setString(2, slotToString(slot));
			rs = stmt.executeQuery();
			
			while(rs.next()){
				alResult.add(rs.getString("Item"));
			}
			
			if(slot.equals(SlotType.MAINHAND)){
				stmt.setString(2, "2h");
				rs = stmt.executeQuery();
				
				while(rs.next()){
					alResult.add(rs.getString("Item"));
				}
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		String[] result = new String[alResult.size()];
		Collections.sort(alResult);
		return alResult.toArray(result);
	}
	
	//TODO: Add search filter options.
	public String[] getAllInSlot(SlotType slot, ItemFilter filter){
		ArrayList<String> alResult = new ArrayList<String>();
		if(filter == null) filter = ItemFilter.NO_COSMETIC;
		
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:./db/items;IFEXISTS=TRUE");
			if(filter.equals(ItemFilter.NO_COSMETIC))
				stmt = conn.prepareStatement("SELECT ITEM FROM EQUIPMENT WHERE SLOT LIKE ? AND COSMETIC IS FALSE");
//			else if(filter.equals(ItemFilter.BIS))
//				stmt = conn.prepareStatement("SELECT ITEM FROM EQUIPMENT WHERE SLOT LIKE ? AND BIS_P2P IS TRUE");
			else
				stmt = conn.prepareStatement("SELECT ITEM FROM EQUIPMENT WHERE SLOT LIKE ?");
			stmt.setString(1, slotToString(slot));
			rs = stmt.executeQuery();
			
			while(rs.next()){
				String name = rs.getString("Item");
				alResult.add(name);
			}
			
			if(slot.equals(SlotType.MAINHAND)){
				stmt.setString(1, "2h");
				rs = stmt.executeQuery();
				
				while(rs.next()){
					alResult.add(rs.getString("Item"));
				}
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//filterArrayList(alResult, filter);
		
		Collections.sort(alResult);
		String[] result = new String[alResult.size()];
		return alResult.toArray(result);
	}
	
//	private void filterArrayList(ArrayList<String> list, ItemFilter filter){
//		switch(filter){
//		case NO_COSMETIC: 
//			Iterator<String> itr = list.iterator();
//			String s = null;
//			while(itr.hasNext()){
//				s = itr.next();
//				Equippable item = this.getItem(s);
//				if(item.isCosmetic()){
//					itr.remove();
//				}
//			}
//			return;
//		case BIS:
//			
//			
//			return;
//		case NONE:
//		default: return;
//		}
//	}
	
	
}
