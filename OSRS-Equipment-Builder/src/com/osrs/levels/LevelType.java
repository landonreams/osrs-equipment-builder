package com.osrs.levels;

/**
 * Enumeration of skills a NPC or Player can have.
 * @author Landon Reams
 */
public enum LevelType {
	ATTACK(0, "Attack"), STRENGTH(1, "Strength"), DEFENCE(2, "Defence"), RANGED(3, "Ranged"), MAGIC(4, "Magic"), PRAYER(5, "Prayer"), HITPOINTS(6, "Hitpoints");
	
	public final int index;
	public final String name;
	public static final int NUM_LEVELS = 7;
	
	private LevelType(int index, String name){
		this.index = index;
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
