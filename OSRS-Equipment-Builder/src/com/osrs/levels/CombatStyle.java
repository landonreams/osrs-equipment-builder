package com.osrs.levels;

public enum CombatStyle {
	M_ACCURATE(new int[]{3, 0, 0, 0, 0, 0, 0}), 
	M_AGGRESSIVE(new int[]{0, 3, 0, 0, 0, 0, 0}),
	M_DEFENSIVE(new int[]{0, 0, 3, 0, 0, 0, 0}),
	M_CONTROLLED(new int[]{1, 1, 1, 0, 0, 0, 0}),
	R_ACCURATE(new int[]{0, 0, 0, 3, 0, 0, 0}),
	R_RAPID(new int[]{0, 0, 0, 0, 0, 0, 0}),
	R_LONGRANGE(new int[]{0, 0, 0, 0, 0, 0, 0});
	
	public final int[] levelBoost;
	
	/*
	 * Array dictates the levels boosted by using a specific style, in the order
	 * ASDRMPH
	 */
	private CombatStyle(int[] array){
		levelBoost = array;
	}
}
