package com.osrs.items;
public enum SlotType {
	MAINHAND(0), OFFHAND(1), 
	HEAD(2), BODY(3), LEGS(4), CAPE(5),
	GLOVES(6), BOOTS(7), NECK(8), RING(9), AMMO(10);
	
	public final int index;
	
	private SlotType(int index){
		this.index = index;
	}
	
	
}
