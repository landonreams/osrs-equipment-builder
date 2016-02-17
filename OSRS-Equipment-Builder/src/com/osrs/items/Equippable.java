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
	private int[] stats;
	private int id;
	
	public Equippable(){
		name = "Empty";
		slot = null;
		stats = new int[14];
		twoHanded = false;
	}
	
	public Equippable(String name, SlotType slot, int[] stats, boolean twoHanded, int id){
		this.name = name;
		this.slot = slot;
		this.stats = stats;
		this.twoHanded = twoHanded;
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getID(){
		return id;
	}
	
	public SlotType getSlot(){
		return slot;
	}
	
	public int[] getStats(){
		return stats;
	}
	
	public int getStat(StatType stat){
		return stats[stat.index];
	}
	
	public boolean isTwoHanded(){
		return twoHanded;
	}
	
	public boolean isCosmetic(){
		for(int s : stats){
			if(s != 0)
				return false;
		}
		return true;
	}
	
}
