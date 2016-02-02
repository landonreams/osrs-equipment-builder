package com.osrs.items;
public class Equipable {
	private String name;
	private SlotType   slot;
	private int[]  offensiveStats;
	private int[]  defensiveStats;
	private int[]  miscStats;
	
	/*
	 * Stats are:
	 *  0: Stab Accuracy
	 *  1: Slash Accuracy
	 *  2: Crush Accuracy
	 *  3: Mage Accuracy
	 *  4: Range Accuracy
	 *  
	 *  0: Stab Defence
	 *  1: Slash Defence
	 *  2: Crush Defence
	 *  3: Mage Defence
	 *  4: Range Defence
	 *  
	 *  0: Melee Strength
	 *  1: Ranged Strength
	 *  2: Magic Damage Bonus (in %)
	 *  3: Prayer Bonus
	 * 
	 * Better stored as a 2D 5x3 array? Accuracy, Defence, Misc.?
	 */
	
	public Equipable(String name, SlotType slot, int[] offensiveStats, int[] defensiveStats, int[] miscStats){
		this.name = name;
		this.slot = slot;
		this.offensiveStats = offensiveStats;
		this.defensiveStats = defensiveStats;
		this.miscStats = miscStats;
	}
	
	public String getName(){
		return name;
	}
	
	public SlotType getSlot(){
		return slot;
	}
	
	public int[] getOffensives(){
		return offensiveStats;
	}
	
	public int[] getDefensives(){
		return defensiveStats;
	}
	
	public int[] getMisc(){
		return miscStats;
	}
	
}
