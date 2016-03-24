package com.osrs.data;

public enum Levels {
	ATTACK(0, Boost.FLAG_ATTACK),
	STRENGTH(1, Boost.FLAG_STRENGTH),
	DEFENCE(2, Boost.FLAG_DEFENCE),
	HITPOINTS(3, 0),
	RANGED(4, Boost.FLAG_RANGED),
	MAGIC(5, Boost.FLAG_MAGIC),
	PRAYER(6, Boost.FLAG_PRAYER);
	
	public final int index;
	public final int flag;
	public static final int COUNT = 7;
	
	private Levels(int index, int flag){
		this.index = index;
		this.flag  = flag;
	}
}
