package com.osrs.npc;

/**
 * Short enum for use declaring a Fightable's combat style.
 * Note that only NPCs can used Magical_Melee and Magical_Ranged.
 * These attack types roll off of the attacker's melee/ranged stat,
 * but the defender's magic defence.
 * @author Landon
 *
 */
public enum CombatTriangle {
	MELEE(0), RANGED(1), MAGIC(2), MAGICAL_MELEE(3), MAGICAL_RANGED(4);
	
	public final int value;
	
	private CombatTriangle(int value){
		this.value = value;
	}
	
}
