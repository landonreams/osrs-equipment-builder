package com.osrs.levels;

import java.util.EnumMap;

public class PotionsList{
	private EnumMap<Potions, Boolean> potions;
	
	public PotionsList(){
		potions = new EnumMap<Potions, Boolean>(Potions.class);
		for(Potions p : Potions.values()){
			potions.put(p, false);
		}
	}
	
	public void add(Potions o){
		for(Potions p : Potions.values()){
			if(o.conflicts(p)){
				this.remove(p);
			}
		}
		potions.put(o, true);
	}
	
	public void remove(Potions o){
		potions.put(o, false);
	}
		
	public boolean[] getAll(){
		boolean[] pots = new boolean[Potions.NUM_POTIONS];
		for(Potions p : Potions.values()){
			pots[p.index] = (boolean) potions.get(p);
		}
		return pots;
	}
	
	public void clear(){
		for(Potions p : Potions.values()){
			this.remove(p);
		}
	}
	
	public static void main(String[] args){
		PotionsList pl = new PotionsList();
		
		pl.getAll();
	}
}
