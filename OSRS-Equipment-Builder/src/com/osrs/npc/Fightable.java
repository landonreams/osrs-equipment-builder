package com.osrs.npc;

import com.osrs.items.StatType;


/**
 * Abstract base class for any NPC or Player that can engage in combat.
 * @author Landon Reams
 */
public abstract class Fightable {
	protected int[] levels;
	protected CombatStyle style;
	/**
	 * Default constructor for a Fightable.
	 */
	public Fightable(){
		levels = new int[7];
	}
	
	/**
	 * Constructor taking a 7-index int array of levels.
	 * @param levels
	 * @throws IllegalArgumentException
	 */
	public Fightable(int[] levels){
		if(levels.length != 7)
			throw new IllegalArgumentException("Levels array length must be equal to 7! Current length is: "+levels.length);
		this.levels = levels;
	}
	
	/**
	 * Sets combat style. Does not change between NPC and Player.
	 * @param style
	 */
	public void setStyle(CombatStyle style){
		this.style = style;
	}
	
	/**
	 * Returns current combat style.
	 * @return style
	 */	
	public CombatStyle getStyle(){
		return style;
	}
	
	public abstract int[] getStats();
	public abstract int[] getLevels();
	public abstract int   getStat(StatType stat);
	public abstract int   getLevel(LevelType level);
}
