package com.osrs.npc;

public enum CombatStyle {
	ACCURATE(new int[]{3, 0, 0, 3, 0, 0, 0}), 
	AGGRESSIVE(new int[]{0, 3, 0, 0, 0, 0, 0}),
	DEFENSIVE(new int[]{0, 0, 3, 0, 0, 0, 0}),
	CONTROLLED(new int[]{1, 1, 1, 0, 0, 0, 0}),
	RAPID(new int[]{0, 0, 0, 0, 0, 0, 0}),
	LONGRANGE(new int[]{0, 0, 0, 0, 0, 0, 0});
	
	public final int[] levelBoost;
	
	/*
	 * Array dictates the levels boosted by using a specific style, in the order
	 * ASDRMPH
	 * Accurate represents both the Melee and Ranged Accurate combat styles.
	 */
	private CombatStyle(int[] array){
		levelBoost = array;
	}
}
