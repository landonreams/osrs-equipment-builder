package com.osrs.levels;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;

public class PotionsList implements Iterable<Potions>{
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
		System.out.println("ADDING "+o);
		potions.put(o, true);
	}
	
	public void remove(Potions o){
		potions.put(o, false);
	}
		
	public boolean[] getAll(){
		System.out.println("CALLING GETALL");
		boolean[] pots = new boolean[Potions.NUM_POTIONS];
		for(Potions p : Potions.values()){
			System.out.println("-- Adding "+p+" at index "+p.index);
			pots[p.index] = (boolean) potions.get(p);
			System.out.println("---- "+pots[p.index]);
		}
		return pots;
	}
	
	public void clear(){
		for(Potions p : Potions.values()){
			this.remove(p);
		}
	}
	
	public void setValues(boolean[] values){
		for(Potions p : Potions.values()){
			potions.put(p, values[p.index]);
		}
	}

	@Override
	public Iterator<Potions> iterator() {
		Iterator<Potions> it = new PotionsIterator(this.getAll());
		return it;
	}
	
	public class PotionsIterator implements Iterator<Potions>{
		
		private ArrayList<Potions> potions;
		private int currentIndex = 0;
		
		public PotionsIterator(boolean[] pBools){
			potions = new ArrayList<Potions>();
			for(int i = 0; i < Potions.NUM_POTIONS; i++){
				if(pBools[i])
					potions.add(Potions.fromIndex(i));
			}
		}
		
		@Override
		public boolean hasNext() {
			return currentIndex < potions.size();
		}

		@Override
		public Potions next() {
			if(this.hasNext())
				return potions.get(currentIndex++);
			else
				return null;
		}
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
			
		
	}
}
