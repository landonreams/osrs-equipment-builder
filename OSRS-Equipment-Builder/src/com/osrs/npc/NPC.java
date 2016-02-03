package com.osrs.npc;

import com.osrs.items.StatType;

/**
 * Class for creating a NPC with finite stats and no equipment selection.
 * @author Landon Reams
 */
public class NPC extends Fightable {
	private int[] stats;
	
	
	/**
	 *  Default NPC constructor
	 */
	public NPC(){
		super();
		stats = new int[14];
	}
	
	/**
	 * NPC constructor receiving an int array of levels and of stats 
	 * @param levels
	 * @param stats
	 * @throws IllegalArgumentException
	 */
	public NPC(int[] levels, int[] stats){
		super(levels);
		this.stats = stats;
	}
	
	@Override
	public int[] getStats() {
		return stats;
	}

	@Override
	public int[] getLevels() {
		return levels;
	}

	@Override
	public int getStat(StatType stat) {
		return stats[stat.index];
	}

	@Override
	public int getLevel(LevelType level) {
		return levels[level.index];
	}
	
		
	
}
