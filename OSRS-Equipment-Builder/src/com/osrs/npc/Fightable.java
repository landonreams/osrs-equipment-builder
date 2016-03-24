package com.osrs.npc;

import java.util.EnumMap;

import com.osrs.data.ArmorStats;
import com.osrs.data.CombatStyle;
import com.osrs.data.Levels;

public abstract class Fightable {
	private EnumMap<Levels, Integer> levels;
	protected CombatStyle combatClass;
	
	public Fightable(){
		levels = new EnumMap<Levels, Integer>(Levels.class);
		combatClass = CombatStyle.MELEE;
	}
	
	public int   getLevel(Levels level){ return levels.get(level); }
	public void  setLevel(int value, Levels level){ levels.put(level, value); }

	public abstract int   getArmorStat(ArmorStats as);
	public abstract void  setArmorStat(int as, ArmorStats asType);
	//public abstract int   getMaxHit();
	public abstract int   getEffectiveLevel(Levels level);
	
	public double aspeedInSeconds(){
		int speed = this.getArmorStat(ArmorStats.ASPEED);
		double result = 6 - 0.6 * speed;
		return result;
	}
	
}
