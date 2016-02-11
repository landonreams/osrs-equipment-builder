package com.osrs.levels;

public enum ArmorBoostType {
	NONE(-1, 1.0, 1.0), VOID_MELEE(0, 1.1, 1.1), VOID_RANGED(1, 1.1, 1.2), VOID_MAGIC(2, 1.3, 1.0), 
	SLAYER_HELM(3, 1.16, 1.16), 
	SALVE(4, 1.15, 1.15), SALVE_E(5, 1.2, 1.2);
	
	public final int value;
	public final double acc;
	public final double str;
	
	private ArmorBoostType(int value, double acc, double str){
		this.value = value;
		this.acc = acc;
		this.str = str;
	}
}
