package com.osrs.npc;

import com.osrs.items.EquipmentSet;
import com.osrs.items.StatType;

/*
 * Class to simulate Player in combat. Players use Equipment sets rather than stat array.
 * @author Landon Reams
 */
public class Player extends NPC {
	private EquipmentSet gear;
	
	/*
	 * Default constructor for a Player object.
	 */
	public Player(){
		gear   = new EquipmentSet();
		levels = new int[7];
	}
	
	/*
	 * Player constructor allowing for equipment and level initialization.
	 * @param equipment
	 * @param levels
	 * @throws IllegalArgumentException
	 */
	public Player(String[] equipment, int[] levels){
		if(levels.length != 7)
			throw new IllegalArgumentException("Incorrect size of levels array! Levels array must contain 7 indices.");
		this.gear   = new EquipmentSet(equipment);
		this.levels = levels;
	}
	
	/*
	 * Equips a single item.
	 * @param item
	 */
	public void equip(String item){
		gear.equip(item);
	}

	/*
	 * Equips a string array of equipment.
	 * @param equipment
	 */
	public void equipArray(String[] equipment){
		gear.equipArray(equipment);
	}
	
	
	/*
	 * Getter method for stats array.
	 * @return stats
	 */
	@Override
	public int[] getStats(){
		return gear.getStats();
	}
	
	/*
	 * Getter method for a single stat.
	 * @param statType
	 * @return stat
	 */
	@Override
	public int getStat(StatType statType){
		return gear.getStat(statType);
	}
}
