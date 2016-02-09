package com.osrs.npc;

import com.osrs.items.BasicStat;

/**
 * Object to simulate a battle between two NPCs
 * @author Landon Reams
 */
public class Battle {
	private Fightable attacker, defender;
	
	public Battle(){
		attacker = new NPC();
		defender = new NPC();
	}
	
	public Battle(Fightable a, Fightable d){
		attacker = a;
		defender = d;
	}
	
	public double getHitChance(BasicStat stat){
		double attRoll = Damage.getMaxRoll(attacker, stat);
		double defRoll = Damage.getMaxDefenceRoll(defender, stat);
		
		return (attRoll > defRoll) ? 
				(1 - (defRoll + 1) / (2 * attRoll)) : 
					(attRoll - 1) / (2 * defRoll); 
	}
}
