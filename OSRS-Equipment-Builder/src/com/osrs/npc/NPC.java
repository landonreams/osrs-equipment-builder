package com.osrs.npc;

import com.osrs.items.StatType;

/*
 * Defines a NPC for use in combat calculations.
 * @author Landon Reams
 */
public class NPC {
	protected int[] levels;
	protected int[] stats;
	protected CombatStyle style;
	
	/*
	 * Default constructor for NPC class.
	 */
	public NPC(){
		levels = new int[7];
		stats  = new int[14];
		style  = CombatStyle.CONTROLLED;
	}
	
	/*
	 * Constructor allowing for skill and stat assignment.
	 * @param levels
	 * @param stats
	 * @throws IllegalArgumentException
	 */
	public NPC(int[] levels, int[] stats){
		if(levels.length != 7)
			throw new IllegalArgumentException("Incorrect number of indices in levels array!");
		if(stats.length != 14)
			throw new IllegalArgumentException("Incorrect number of indices in stats array!");
		
		this.levels = levels;
		this.stats  = stats;
		style  = CombatStyle.CONTROLLED;
	}
	
	/*
	 * Getter method for levels array.
	 * @return levels
	 */
	public int[] getLevels(){
		return levels;
	}
	
	/*
	 * Getter method for stats array.
	 * @return stats
	 */
	public int[] getStats(){
		return stats;
	}
	
	/*
	 * Getter method for a single level.
	 * @param levelType
	 * @return skill
	 */
	public int getLevel(LevelType levelType){
		return levels[levelType.index];
	}
	
	/*
	 * Getter method for a single stat.
	 * @param statType
	 * @return stat
	 */
	public int getStat(StatType statType){
		return stats[statType.index];
	}
		
	
}
