package com.osrs.npc;

import com.osrs.data.ArmorBoostType;
import com.osrs.data.CombatStance;
import com.osrs.data.CombatTriangle;
import com.osrs.data.LevelType;
import com.osrs.data.PotionsList;
import com.osrs.data.PrayersList;
import com.osrs.data.Spell;
import com.osrs.items.StatType;


/**
 * Abstract base class for any NPC or Player that can engage in combat.
 * @author Landon Reams
 */
public abstract class Fightable {
	protected int[]          levels;
	protected CombatStance   stance;
	protected PrayersList    prayers;
	protected PotionsList	 potions;
	protected CombatTriangle attackType;
	protected DamageType     damageType;
	protected Spell          spell;
	protected int            speed;
	
	/**
	 * Default constructor for a Fightable.
	 */
	public Fightable(){
		levels = new int[] {1, 1, 1, 1, 1, 1, 1};
		prayers = new PrayersList();
		potions = new PotionsList();
		stance  = CombatStance.M_CONTROLLED;
		spell   = Spell.STRIKE_WIND;
		attackType = CombatTriangle.MELEE;
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
		prayers = new PrayersList();
		potions = new PotionsList();
		stance  = CombatStance.M_CONTROLLED;
		spell   = Spell.STRIKE_WIND;

		attackType = CombatTriangle.MELEE;
	}
	
	public void setStance(CombatStance cs){
		this.stance = cs;
	}
	
	public CombatStance getStance(){
		return stance;
	}
	
	/**
	 * Sets combat style. Does not change between NPC and Player.
	 * @param style
	 */
	public void setAttackType(CombatTriangle style){
		this.attackType = style;
	}
	
	/**
	 * Returns the attack type of the Fightable.
	 * @return attackType
	 */
	public CombatTriangle getAttackType(){
		return attackType;
	}
	
	/**
	 * Get the Fightable's active prayers.
	 * @return prayers
	 */
	public PrayersList getPrayersList(){
		return prayers;
	}
	
	public PotionsList getPotionsList(){
		return potions;
	}
	public int[] getLevels(){
		int[] levelClones = new int[levels.length];
		for(int i = 0; i < levels.length; i++){
			levelClones[i] = levels[i];
		}
		return levelClones;
	}
	
	public int getLevel(LevelType level){
		return levels[level.index];
	}
	
	public void setLevel(int level, LevelType levelType){
		this.levels[levelType.index] = level;
	}
	
	public void setLevels(int[] levels){
		if(levels.length != 7)
			throw new IllegalArgumentException("Incorrect array length of levels array!");
		for(int i = 0; i < levels.length; i++)
			this.levels[i] = levels[i];
	}
	
	public Spell getSpell(){
		return spell;
	}
	
	public void setSpell(Spell spell){
		this.spell = spell;
	}
	
	public DamageType getDamageType(){
		return damageType;
	}
	
	public void setDamageType(DamageType dt){
		damageType = dt;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public abstract ArmorBoostType getArmorBoost();
	public abstract int   getStat(StatType stat);
	public abstract int[] getStats();
	
	public int getCombatLevel(){
		double base = 0.25 * ( this.getLevel(LevelType.DEFENCE) + this.getLevel(LevelType.HITPOINTS) + Math.floor(this.getLevel(LevelType.PRAYER) / 2) );
		double melee = 0.325 * ( this.getLevel(LevelType.ATTACK) + this.getLevel(LevelType.STRENGTH) );
		double range = 0.325 * ( Math.floor(this.getLevel(LevelType.RANGED) / 2) + this.getLevel(LevelType.MAGIC));
		double magic = 0.325 * ( Math.floor(this.getLevel(LevelType.MAGIC)/ 2) + this.getLevel(LevelType.MAGIC));
		double add = Math.max(melee, Math.max(range, magic));
		
		return (int) Math.floor(base + add);
	}
	
	public double getCombatLevelExact(){
		double base = 0.25 * ( this.getLevel(LevelType.DEFENCE) + this.getLevel(LevelType.HITPOINTS) + this.getLevel(LevelType.PRAYER) / 2 );
		double melee = 0.325 * ( this.getLevel(LevelType.ATTACK) + this.getLevel(LevelType.STRENGTH) );
		double range = 0.325 * ( this.getLevel(LevelType.RANGED) / 2 + this.getLevel(LevelType.MAGIC));
		double magic = 0.325 * ( this.getLevel(LevelType.MAGIC)/ 2 + this.getLevel(LevelType.MAGIC));
		double add = Math.max(melee, Math.max(range, magic));
		
		return base + add;
	}
	
	public abstract void setPrayers(boolean[] prayersActive);
}
