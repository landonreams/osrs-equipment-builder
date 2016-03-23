package com.osrs.data;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;

public class PrayersList implements Iterable<Prayers>{
	private EnumMap<Prayers, Boolean> prayers;
	
	public PrayersList(){
		prayers = new EnumMap<Prayers, Boolean>(Prayers.class);
		for(Prayers p : Prayers.values()){
			prayers.put(p, false);
		}
	}
	
	public void add(Prayers o){
		for(Prayers p : Prayers.values()){
			if(o.conflicts(p)){
				this.remove(p);
			}
		}
		prayers.put(o, true);
	}
	
	public void remove(Prayers o){
		prayers.put(o, false);
	}
		
	public boolean[] getAll(){
		boolean[] pots = new boolean[Prayers.NUM_PRAYERS];
		for(Prayers p : Prayers.values()){
			pots[p.index] = (boolean) prayers.get(p);
		}
		return pots;
	}
	
	public void clear(){
		for(Prayers p : Prayers.values()){
			this.remove(p);
		}
	}
	
	public void setValues(boolean[] values){
		for(Prayers p : Prayers.values()){
			prayers.put(p, values[p.index]);
		}
	}
	
	public double getDrainRate(int prayerBonus){
		double dr = 0;
		
		for(Prayers p : this){
			dr += 1.0 / (p.drainInterval * (1.0 + prayerBonus / 30.0));
		}
		
		return dr;
	}
	
	@Override
	public Iterator<Prayers> iterator() {
		Iterator<Prayers> it = new PrayersIterator(this.getAll());
		return it;
	}
	
	public class PrayersIterator implements Iterator<Prayers>{
		
		private ArrayList<Prayers> prayers;
		private int currentIndex = 0;
		
		public PrayersIterator(boolean[] pBools){
			prayers = new ArrayList<Prayers>();
			for(int i = 0; i < Prayers.NUM_PRAYERS; i++){
				if(pBools[i])
					prayers.add(Prayers.fromIndex(i));
			}
		}
		
		@Override
		public boolean hasNext() {
			return currentIndex < prayers.size();
		}

		@Override
		public Prayers next() {
			if(this.hasNext())
				return prayers.get(currentIndex++);
			else
				return null;
		}
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
}
