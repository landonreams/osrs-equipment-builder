package com.osrs.items;

public enum StatType {
	ACC_STAB(0), ACC_SLASH(1), ACC_CRUSH(2), ACC_MAGE(3), ACC_RANGE(4),
	DEF_STAB(5), DEF_SLASH(6), DEF_CRUSH(7), DEF_MAGE(8), DEF_RANGE(9),
	MSC_MELEE(10), MSC_RANGE(11), MSC_MAGE(12), MSC_PRAYER(13);
	
	public final int index;
	
	private StatType(int index){
		this.index = index;
	}
}
