package com.osrs.npc;

import com.osrs.data.ArmorBoostType;
import com.osrs.data.CombatStance;
import com.osrs.data.LevelType;
import com.osrs.items.StatType;

/**
 * Class for creating a NPC with finite stats and no equipment selection.
 * @author Landon Reams
 */
public class NPC extends Fightable {
	private int[] stats;
	private int combatLevel;
	private String name;
	
	
	/**
	 *  Default NPC constructor
	 */
	public NPC(){
		super();
		stats = new int[14];
		stance = CombatStance.M_CONTROLLED;
		combatLevel = -1;
		name = "None";
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
		stance = CombatStance.M_CONTROLLED;
		combatLevel = -1;
		name = "None";
	}
	
	public NPC(int[] levels, int[] stats, String name){
		super(levels);
		this.stats = stats;
		stance = CombatStance.M_CONTROLLED;
		combatLevel = -1;
	}
	
	@Override
	public int[] getStats() {
		int[] newStats = new int[stats.length];
		for(int i = 0; i < newStats.length; i++){
			newStats[i] = stats[i];
		}
		return newStats;
	}

	@Override
	public int getStat(StatType stat) {
		return stats[stat.index];
	}

	@Override
	public int getLevel(LevelType level) {
		return levels[level.index];
	}
	
	public void setLevels(int[] levels){
		if(levels.length != LevelType.NUM_LEVELS)
			throw new IllegalArgumentException("Improper array length!");
		this.levels = levels;
	}
	
	public void setLevel(int level, LevelType levelType){
		this.levels[levelType.index] = level;
	}
	
	public void setStats(int[] stats){
		if(stats.length != StatType.NUM_STATS)
			throw new IllegalArgumentException("Improper array length!");
		this.stats = stats;
	}
	
	public void setStat(int stat, StatType statType){
		this.stats[statType.index] = stat;
	}
	
	/**
	 * Returns false, as NPCs cannot use armor boosts.
	 * @return usingVoid
	 */
	@Override
	public ArmorBoostType getArmorBoost(){
		return ArmorBoostType.NONE;
	}
	
	public void setPrayers(boolean[] prayersActive) {
		System.out.println("[Warning] NPCs cannot have prayers active!");
	}
	
	@Override
	public int getCombatLevel(){
		if(combatLevel == -1)
			return superCombat();
		return combatLevel;
	}
	
	public void setCombat(int combatLevel){
		this.combatLevel = combatLevel;
	}
	
	private int superCombat(){
		return super.getCombatLevel();
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	
}
