package com.osrs.npc;

import java.util.ArrayList;

import com.osrs.items.BasicStat;
import com.osrs.items.StatType;
import com.osrs.levels.ArmorBoostType;
import com.osrs.levels.CombatStyle;
import com.osrs.levels.CombatTriangle;
import com.osrs.levels.LevelType;
import com.osrs.levels.Potions;
import com.osrs.levels.Prayers;


/**
 * Abstract base class for any NPC or Player that can engage in combat.
 * @author Landon Reams
 */
public abstract class Fightable {
	protected int[] levels;
	protected CombatStyle style;
	protected ArrayList<Prayers> prayers;
	protected ArrayList<Potions> potions;
	protected CombatTriangle     attackType;
	protected BasicStat			 primaryBonus;
	
	/**
	 * Default constructor for a Fightable.
	 */
	public Fightable(){
		levels = new int[] {1, 1, 1, 1, 1, 1, 1};
		prayers = new ArrayList<Prayers>();
		potions = new ArrayList<Potions>();
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
		prayers = new ArrayList<Prayers>();
		potions = new ArrayList<Potions>();
	}
	
	public void setPrimaryBonus(BasicStat bs){
		this.primaryBonus = bs;
	}
	
	public BasicStat getPrimaryBonus(){
		return primaryBonus;
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
	
	/**
	 * Returns the attack type of the Fightable.
	 * @return attackType
	 */
	public CombatTriangle usingAttackType(){
		return attackType;
	}
	
	/**
	 * Get the Fightable's active prayers.
	 * @return prayers
	 */
	public ArrayList<Prayers> getPrayers(){
		ArrayList<Prayers> newList = new ArrayList<Prayers>();
		for(Prayers p : prayers){
			newList.add(p);
		}
		return newList;
	}
	
	/**
	 * Get the Fightable's active potions.
	 * @return prayers
	 */
	public ArrayList<Potions> getPotions(){
		ArrayList<Potions> newList = new ArrayList<Potions>();
		for(Potions p : potions){
			newList.add(p);
		}
		return newList;
	}
	
	public abstract int[] getStats();
	public int[] getLevels(){
		return levels;
	}
	public abstract int   getStat(StatType stat);
	public int   getLevel(LevelType level){
		return levels[level.index];
	}
	public abstract ArmorBoostType getArmorBoost();
	
}
