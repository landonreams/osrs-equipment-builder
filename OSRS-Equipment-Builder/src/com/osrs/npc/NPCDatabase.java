package com.osrs.npc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.osrs.items.StatType;
import com.osrs.levels.LevelType;

public class NPCDatabase {
	public static final String URL = "jdbc:h2:file:./db/items;IFEXISTS=TRUE";
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public NPCDatabase(){
		conn = null;
		stmt = null;
		rs   = null;
	}
	
	public NPC getNPC(String monsterName){
		NPC result = null;
		
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(URL);
			stmt = conn.prepareStatement("SELECT * FROM MONSTERS WHERE NAME = ?");
			stmt.setString(1, monsterName);
			rs = stmt.executeQuery();
			
			if(rs.next()){
			
				String name = rs.getString("Name");
				//ATTACK(0, "Attack"), STRENGTH(1, "Strength"), DEFENCE(2, "Defence"), RANGED(3, "Ranged"), MAGIC(4, "Magic"), PRAYER(5, "Prayer"), HITPOINTS(6, "Hitpoints");
				
				int[] levels = new int[] {
					rs.getInt("Attack"),
					rs.getInt("Strength"),
					rs.getInt("Defence"),
					rs.getInt("Ranged"),
					rs.getInt("Magic"),
					0,
					rs.getInt("Hitpoints")
				};
				
//				ACC_STAB(0, "Stab accuracy"), ACC_SLASH(1, "Slash accuracy"), ACC_CRUSH(2, "Crush accuracy"), ACC_MAGE(3, "Magic accuracy"), ACC_RANGE(4, "Ranged accuracy"),
//				DEF_STAB(5, "Stab defence"), DEF_SLASH(6, "Slash defence"), DEF_CRUSH(7, "Crush defence"), DEF_MAGE(8, "Magic defence"), DEF_RANGE(9, "Ranged defence"),
//				MSC_MELEE(10, "Melee strength"), MSC_RANGE(11, "Ranged strength"), MSC_MAGE(12, "Magic damage bonus"), MSC_PRAYER(13, "Prayer bonus");
				
				int[] stats = new int[] {
					rs.getInt("Stabatt"),
					rs.getInt("Slashatt"),
					rs.getInt("Crushatt"),
					rs.getInt("Mageatt"),
					rs.getInt("Rangeatt"),
					
					rs.getInt("Stabdef"),
					rs.getInt("Slashdef"),
					rs.getInt("Crushdef"),
					rs.getInt("Magedef"),
					rs.getInt("Rangedef"),
					
					rs.getInt("Meleestr"),
					rs.getInt("Rangestr"),
					0, 0
				};
				
				result = new NPC(levels, stats, name);
				
				result.setCombat(rs.getInt("Combat"));
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public NPC[] getAll(){
		ArrayList<NPC> resultAL = new ArrayList<NPC>();
		
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(URL);
			stmt = conn.prepareStatement("SELECT * FROM MONSTERS");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				NPC current;
			
				String name = rs.getString("Name");
				//ATTACK(0, "Attack"), STRENGTH(1, "Strength"), DEFENCE(2, "Defence"), RANGED(3, "Ranged"), MAGIC(4, "Magic"), PRAYER(5, "Prayer"), HITPOINTS(6, "Hitpoints");
				
				int[] levels = new int[] {
					rs.getInt("Attack"),
					rs.getInt("Strength"),
					rs.getInt("Defence"),
					rs.getInt("Ranged"),
					rs.getInt("Magic"),
					0,
					rs.getInt("Hitpoints")
				};
				
//				ACC_STAB(0, "Stab accuracy"), ACC_SLASH(1, "Slash accuracy"), ACC_CRUSH(2, "Crush accuracy"), ACC_MAGE(3, "Magic accuracy"), ACC_RANGE(4, "Ranged accuracy"),
//				DEF_STAB(5, "Stab defence"), DEF_SLASH(6, "Slash defence"), DEF_CRUSH(7, "Crush defence"), DEF_MAGE(8, "Magic defence"), DEF_RANGE(9, "Ranged defence"),
//				MSC_MELEE(10, "Melee strength"), MSC_RANGE(11, "Ranged strength"), MSC_MAGE(12, "Magic damage bonus"), MSC_PRAYER(13, "Prayer bonus");
				
				int[] stats = new int[] {
					rs.getInt("Stabatt"),
					rs.getInt("Slashatt"),
					rs.getInt("Crushatt"),
					rs.getInt("Mageatt"),
					rs.getInt("Rangeatt"),
					
					rs.getInt("Stabdef"),
					rs.getInt("Slashdef"),
					rs.getInt("Crushdef"),
					rs.getInt("Magedef"),
					rs.getInt("Rangedef"),
					
					rs.getInt("Meleestr"),
					rs.getInt("Rangestr"),
					0, 0
				};
				
				current = new NPC(levels, stats, name);
				
				current.setName(name);
				
				current.setCombat(rs.getInt("Combat"));
				
				resultAL.add(current);
			}
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		NPC[] result = new NPC[resultAL.size()];
		return resultAL.toArray(result);
	}
	
	public void saveNPC(NPC anNPC){
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(URL);                 //1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
			stmt = conn.prepareStatement("INSERT INTO MONSTERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			/*
			 * Name, Combat Level
			 * HP, ATK, STR, DEF, RANGE, MAGE
			 * Att: Stab, Slash, Crush, Range, Mage
			 * Def: Stab, Slash, Crush, Range, Mage
			 * Melee Str, Range Str
			 */
			
			stmt.setString(1, anNPC.toString());
			stmt.setInt(2, anNPC.getCombatLevel());
			
			int[] levels = anNPC.getLevels();
			
			stmt.setInt(3, levels[LevelType.HITPOINTS.index]);
			stmt.setInt(4, levels[LevelType.ATTACK.index]);
			stmt.setInt(5, levels[LevelType.STRENGTH.index]);
			stmt.setInt(6, levels[LevelType.DEFENCE.index]);
			stmt.setInt(7, levels[LevelType.RANGED.index]);
			stmt.setInt(8, levels[LevelType.MAGIC.index]);
			
			int[] stats = anNPC.getStats();
			
			stmt.setInt(9, stats[StatType.ACC_STAB.index]);
			stmt.setInt(10, stats[StatType.ACC_SLASH.index]);
			stmt.setInt(11, stats[StatType.ACC_CRUSH.index]);
			stmt.setInt(12, stats[StatType.ACC_RANGE.index]);
			stmt.setInt(13, stats[StatType.ACC_MAGE.index]);
			
			stmt.setInt(14, stats[StatType.DEF_STAB.index]);
			stmt.setInt(15, stats[StatType.DEF_SLASH.index]);
			stmt.setInt(16, stats[StatType.DEF_CRUSH.index]);
			stmt.setInt(17, stats[StatType.DEF_RANGE.index]);
			stmt.setInt(18, stats[StatType.DEF_MAGE.index]);
			
			stmt.setInt(19, stats[StatType.MSC_MELEE.index]);
			stmt.setInt(20, stats[StatType.MSC_RANGE.index]);
			
			stmt.execute();
			
			conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
