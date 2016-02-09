package com.osrs.npc;

import com.osrs.items.BasicStat;
import com.osrs.items.EquipmentSet;
import com.osrs.items.StatType;

/**
 * Class to simulate Player in combat. Players use Equipment sets rather than stat array.
 * @author Landon Reams
 */
public class Player extends Fightable {
	private EquipmentSet gear;
	
	/**
	 * Default constructor taking no parameters.
	 */
	public Player(){
		super();
		gear = new EquipmentSet();
		primaryBonus = BasicStat.CRUSH;
	}
	
	/**
	 * Constructor taking a string array of desired equipment.
	 * @param equipment
	 */
	public Player(String[] equipment){
		super();
		gear = new EquipmentSet(equipment);
		primaryBonus = BasicStat.CRUSH;
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
	public int[] getLevels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStat(StatType stat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLevel(LevelType level) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Applies a new potion, deactivating all conflicting ones.
	 * @param p
	 */
	public void addPotion(Potions potion){
		if(!potions.contains(potion)){
			for(Potions active : potions){
				if(potion.conflicts(active)){
					potions.remove(active);
				}
			}
			potions.add(potion);
		}
	}
	
	/**
	 * Removes an active potion effect to the player in question.
	 * @param potion
	 */
	public void removePotion(Potions potion){
		if(potions.contains(potion)){
			potions.remove(potion);
		}
	}
	
	/**
	 * Adds a new Prayer, deactivating all conflicting ones.
	 * @param prayer
	 */
	public void addPrayer(Prayers prayer){
		if(!prayers.contains(prayer)){
			for(Prayers active : prayers){
				if(prayer.conflicts(active)){
					prayers.remove(active);
				}
			}
			prayers.add(prayer);
		}
	}
	
	/**
	 * Deactivates the active prayer effect.
	 * @param prayer
	 */ 
	public void removePrayer(Prayers prayer){
		if(prayers.contains(prayer))
			prayers.remove(prayer);
	}
	
	@Override
	public ArmorBoostType getArmorBoost(){
		ArmorBoostType abt = gear.armorBoost();
		return abt;
	}
	
}
