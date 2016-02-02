package com.osrs.items;
/**
 * Stores the data for an equippable item
 * @author Landon Reams
 *
 */
public class Equippable {
	private String name;
	private SlotType   slot;
	private boolean twoHanded;
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
	/**
	 * Creates an empty Eqippable item with all stats set to zero
	 */
	public Equippable(){
		name = "Empty";
		slot = null;
		offensiveStats = new int[]{ 0, 0, 0, 0, 0 };
		defensiveStats = new int[]{ 0, 0, 0, 0, 0 };
		miscStats      = new int[]{ 0, 0, 0, 0 };
		twoHanded = false;
	}
	/**
	 * Creates an Equippable item based on parameters
	 * @param name
	 * @param slot
	 * @param offensiveStats
	 * @param defensiveStats
	 * @param miscStats
	 * @param twoHanded
	 */
	public Equippable(String name, SlotType slot, int[] offensiveStats, int[] defensiveStats, int[] miscStats, boolean twoHanded){
		this.name = name;
		this.slot = slot;
		this.offensiveStats = offensiveStats;
		this.defensiveStats = defensiveStats;
		this.miscStats = miscStats;
		this.twoHanded = twoHanded;
	}
	/**
	 * Gets the name of the item
	 * @return The name of the item
	 */
	public String getName(){
		return name;
	}
	/**
	 * Gets the slot of the item
	 * @return The slot of the item
	 */
	public SlotType getSlot(){
		return slot;
	}
	/**
	 * Gets the array of defensive stats of the item
	 * @return The defensive stats of the item
	 */
	public int[] getOffensives(){
		return offensiveStats;
	}
	/**
	 * Gets the array of defensive stats of the item
	 * @return The defensive stats of the item
	 */
	public int[] getDefensives(){
		return defensiveStats;
	}
	/**
	 * Gets the array of the misc stats of the item
	 * @return The misc stats of the item
	 */
	public int[] getMisc(){
		return miscStats;
	}
	
	public boolean isTwoHanded(){
		return twoHanded;
	}
	
}
