package com.osrs.items;
/**
 * Enumeration of item slots
 * @author Landon Reams
 */
public enum SlotType {
	MAINHAND(0), OFFHAND(1), 
	HEAD(2), BODY(3), LEGS(4), CAPE(5),
	GLOVES(6), BOOTS(7), NECK(8), RING(9), AMMO(10);
	
	public final int index;
	/**
	 * Sets the index of the enumeration to allow for use as index of EquipmentSet array
	 * @param index
	 */
	private SlotType(int index){
		this.index = index;
	}
	
	
}
