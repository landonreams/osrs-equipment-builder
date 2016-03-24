package com.osrs.npc;

import java.util.EnumMap;

import com.osrs.data.ArmorStats;
import com.osrs.data.Slot;

public class Item {
	private String name;
	private Slot slot;
	private EnumMap<ArmorStats, Integer> stats;
	private boolean is2h;
	
	public Item(){
		stats = new EnumMap<ArmorStats, Integer>(ArmorStats.class);
		is2h = false;
	}
	
	public Integer set(ArmorStats stat, int value){ return stats.put(stat, value); }
	public int get(ArmorStats stat){ return stats.get(stat); }
	public boolean is2h(){ return is2h; }
	
	@Override
	public String toString(){ return name; }
	
	public void setName(String name){ this.name = name; }
	public void set2h(boolean is2h){ this.is2h = is2h; }
	
	public Slot getSlot(){ return slot; }
	public void setSlot(Slot slot){ this.slot = slot; }
}
