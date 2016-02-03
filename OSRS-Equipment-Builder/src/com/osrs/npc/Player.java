package com.osrs.npc;

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
	}
	
	/**
	 * Constructor taking a string array of desired equipment.
	 * @param equipment
	 */
	public Player(String[] equipment){
		super();
		gear = new EquipmentSet(equipment);
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
	
}
