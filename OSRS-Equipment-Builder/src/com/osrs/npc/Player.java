package com.osrs.npc;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import com.osrs.items.EquipmentSet;
import com.osrs.items.SlotType;
import com.osrs.items.StatType;
import com.osrs.levels.ArmorBoostType;
import com.osrs.levels.LevelType;
import com.osrs.levels.Potions;
import com.osrs.levels.Prayers;

/**
 * Class to simulate Player in combat. Players use Equipment sets rather than stat array.
 * @author Landon Reams
 */
public class Player extends Fightable {
	private static final String hiscorePrefix = "http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";
	private EquipmentSet gear;
	
	/**
	 * Default constructor taking no parameters.
	 */
	public Player(){
		super(new int[] {1, 1, 1, 1, 1, 1, 10});
		gear = new EquipmentSet();
		damageType = DamageType.CRUSH;
	}
	
	/**
	 * Constructor taking a string array of desired equipment.
	 * @param equipment
	 */
	public Player(String[] equipment){
		super(new int[] {1, 1, 1, 1, 1, 1, 10});
		gear = new EquipmentSet(equipment);
		damageType = DamageType.CRUSH;
	}
	
	/**
	 * Constructor taking an int array of levels.
	 * @param levels
	 */
	public Player(int[] levels){
		super(levels);
		gear = new EquipmentSet();
	}
	
	/**
	 * Constructor taking both an int array of levels and string array of equipment.
	 * @param levels
	 * @param equipment
	 */
	public Player(int[] levels, String[] equipment){
		super(levels);
		gear = new EquipmentSet(equipment);
	}
	
	
	
	@Override
	public int[] getStats() {
		return gear.getStats();
	}

	@Override
	public int getStat(StatType stat) {
		return gear.getStat(stat);
	}
	
	/**
	 * Applies a new potion, deactivating all conflicting ones.
	 * @param p
	 */
	public void addPotion(Potions potion){
		potions.add(potion);
	}
	
	/**
	 * Removes an active potion effect to the player in question.
	 * @param potion
	 */
	public void removePotion(Potions potion){
		potions.remove(potion);
	}
	
	/**
	 * Adds a new Prayer, deactivating all conflicting ones.
	 * @param prayer
	 */
	public void addPrayer(Prayers prayer){
		prayers.add(prayer);
	}
	
	/**
	 * Deactivates the active prayer effect.
	 * @param prayer
	 */ 
	public void removePrayer(Prayers prayer){
		prayers.remove(prayer);
	}
	
	public void clearSlot(SlotType slot){
		gear.clearSlot(slot);
	}
	
	public void equip(String item){
		gear.equip(item);
	}
	
	public void equip(String[] items){
		gear.equipArray(items);
	}
	
	@Override
	public ArmorBoostType getArmorBoost(){
		ArmorBoostType abt = gear.armorBoost();
		return abt;
	}
	
	public String getItemName(SlotType slot){
		return gear.getItem(slot);
	}
	
	public void setPrayers(boolean[] values) {
		if(values != null) prayers.setValues(values);
	}

	public void setPotions(boolean[] values) {
		if(values != null) potions.setValues(values);
	}
	
	public static int[] getLevels(String playerName){
		int[] levels = new int[LevelType.NUM_LEVELS];
		
		try {
			URL url = new URL(hiscorePrefix + playerName);
			InputStream is = url.openStream();
			int ptr = 0;
			StringBuffer sb = new StringBuffer();
			ArrayList<Integer> readIn = new ArrayList<Integer>();
			
			//Order from hiscores: Overall, Attack, Defence, Strength, Hitpoints, Ranged, Prayer, Magic
			//Rank, Level, Experience
			
			while((ptr = is.read()) != -1){
				if(!Character.isDigit((char) ptr)){
					int num = 0;
					try{
						num = Integer.parseInt(sb.toString());
					} catch (NumberFormatException e){
						num = -1;
					}
					readIn.add(num);
					sb.setLength(0);
				} else {
					sb.append((char)ptr);
				}
			}
			
			//Extract levels from arraylist
			levels[LevelType.ATTACK.index] = readIn.get(4);
			levels[LevelType.DEFENCE.index] = readIn.get(7);
			levels[LevelType.STRENGTH.index] = readIn.get(10);
			levels[LevelType.HITPOINTS.index] = readIn.get(13);
			levels[LevelType.RANGED.index] = readIn.get(16);
			levels[LevelType.PRAYER.index] = readIn.get(19);
			levels[LevelType.MAGIC.index] = readIn.get(22);
			
		} catch (FileNotFoundException e) {
			return null;
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return levels;
	}
	
	public static void playerDebugLookup(String name){
		try {
			System.out.println("Name: "+name);
			URL url = new URL(hiscorePrefix + name);
			InputStream is = url.openStream();
			int ptr = 0;
			StringBuffer sb = new StringBuffer();
			ArrayList<Integer> readIn = new ArrayList<Integer>();
			
			//Order from hiscores: Overall, Attack, Defence, Strength, Hitpoints, Ranged, Prayer, Magic
			//Rank, Level, Experience
			
			while((ptr = is.read()) != -1){
				sb.append((char)ptr);
			}
			
			System.out.println(sb.toString());
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Name not found.");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public static long[][] playerLookup2D(String name){
		long[][] result = new long[24][3];
		
		try {
			URL url = new URL(hiscorePrefix + name);
			InputStream is = url.openStream();
			int ptr = 0;
			StringBuffer sb = new StringBuffer();
			ArrayList<Long> readIn = new ArrayList<Long>();
			
			//Order from hiscores: Overall, Attack, Defence, Strength, Hitpoints, Ranged, Prayer, Magic
			//Rank, Level, Experience
			
			while((ptr = is.read()) != -1){
				if(!Character.isDigit((char) ptr)){
					if(!sb.toString().equals(""))
						readIn.add(Long.parseLong(sb.toString()));
					sb.setLength(0);
				} else {
					sb.append((char)ptr);
				}
			}
			
			//Extract levels from arraylist'
			int i = 0;
			for(int r = 0; r < 24; r++){
				for(int c = 0; c < 3; c++){
					result[r][c] = readIn.get(i++);
				}
			}
			
		} catch (FileNotFoundException e) {
			return null;
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args){
		getLevels("Lynx titan");
		
	}
}
