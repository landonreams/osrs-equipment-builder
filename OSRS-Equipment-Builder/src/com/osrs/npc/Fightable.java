package com.osrs.npc;

import java.util.EnumMap;

import com.osrs.data.ArmorStats;
import com.osrs.data.CombatStyle;
import com.osrs.data.Levels;
import com.osrs.data.Spell;

public abstract class Fightable {
	protected EnumMap<Levels, Integer> levels;
	protected String      name;
	protected CombatStyle combatClass;
	protected Spell       currentSpell;
	
	public Fightable(){
		levels = new EnumMap<Levels, Integer>(Levels.class);
		combatClass = CombatStyle.MELEE;
		currentSpell = Spell.NONE;
	}
	
	public int   getLevel(Levels level){ return levels.get(level); }
	public void  setLevel(int value, Levels level){ levels.put(level, value); }

	public abstract int   getArmorStat(ArmorStats as);
	public abstract void  setArmorStat(int as, ArmorStats asType);
	
	public abstract int   getEffectiveLevel(Levels level);
	
	public double aspeedInSeconds(){
		int speed = this.getArmorStat(ArmorStats.ASPEED);
		double result = 6 - 0.6 * speed;
		return result;
	}
	
	/**
	 * NOT YET IMPLEMENTED
	 * @return Fightable's maximum hit
	 */
	public int getMaxHit(){
		
		
		return -1;
	}
	
	/**
	 * NOT YET IMPLEMENTED
	 * @param target
	 * @return Hit chance as percentage
	 */
	public double getHitChance(Fightable target){
		
		
		return 0.0;
	}
	
}
