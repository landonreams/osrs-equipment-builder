package com.osrs.levels;

public enum ArmorBoostType {
	NONE(-1, 1.0, 1.0, "None"), VOID_MELEE(0, 1.1, 1.1, "Void melee set"), VOID_RANGED(1, 1.1, 1.2, "Void ranged set"), VOID_MAGIC(2, 1.3, 1.0, "Void magic set"), 
	SLAYER_HELM(3, 1.16, 1.16, "Slayer helm"), 
	SALVE(4, 1.15, 1.15, "Salve amulet"), SALVE_E(5, 1.2, 1.2, "Enhanced salve amulet");
	
	public final int value;
	public final double acc;
	public final double str;
	public final String name;
	
	private ArmorBoostType(int value, double acc, double str, String name){
		this.value = value;
		this.acc = acc;
		this.str = str;
		this.name = name;
	}
	
	@Override
	public String toString(){
		switch(this){
		case NONE: return "No armor bonuses active.";
		default: return name + " is active!";
		}
	}
}
