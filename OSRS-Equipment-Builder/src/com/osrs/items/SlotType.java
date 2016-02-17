package com.osrs.items;

public enum SlotType {
	MAINHAND(0), OFFHAND(1), HEAD(2), BODY(3), LEGS(4), CAPE(5), GLOVES(6),
    BOOTS(7), NECK(8), RING(9), AMMO(10);

	public final int index;
	public static final int NUM_SLOTS = 11;
	private SlotType(int index) {
		this.index = index;
	}
	
	public String toString(){
		switch(this){
		case MAINHAND: return "Mainhand";
		case OFFHAND: return "Offhand";
		case HEAD: return "Head";
		case BODY: return "Body";
		case LEGS: return "Legs";
		case CAPE: return "Cape";
		case GLOVES: return "Gloves";
		case BOOTS: return "Boots";
		case NECK: return "Neck";
		case RING: return "Ring";
		case AMMO: return "Ammo";
		default: return "";
		}
	}

}
