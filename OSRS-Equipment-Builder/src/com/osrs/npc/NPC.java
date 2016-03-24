package com.osrs.npc;

import java.util.EnumMap;

import com.osrs.data.ArmorStats;
import com.osrs.data.Levels;

public class NPC extends Fightable {
	private EnumMap<ArmorStats, Integer> armorstats;
	
	public NPC(){
		super();
		armorstats = new EnumMap<ArmorStats, Integer>(ArmorStats.class);
	}
	
	public int   getArmorStat(ArmorStats as){ return armorstats.get(as); }
	public void  setArmorStat(int value, ArmorStats as){ armorstats.put(as, value); }
	
	public int getEffectiveLevel(Levels level){
		
		
		return 0;
	}
	
}
