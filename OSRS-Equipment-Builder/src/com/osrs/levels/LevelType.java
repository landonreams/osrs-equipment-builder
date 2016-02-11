package com.osrs.levels;

/**
 * Enumeration of skills a NPC or Player can have.
 * @author Landon Reams
 */
public enum LevelType {
	ATTACK(0), STRENGTH(1), DEFENCE(2), RANGED(3), MAGIC(4), PRAYER(5), HITPOINTS(6);
	
	public final int index;
	public static final int NUM_LEVELS = 7;
	
	private LevelType(int index){
		this.index = index;
	}
}
