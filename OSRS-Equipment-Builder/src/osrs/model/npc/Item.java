package osrs.model.npc;

import java.util.EnumMap;

import osrs.model.data.ArmorStats;
import osrs.model.data.Slot;

public class Item {
	private String name;
	private Slot slot;
	private EnumMap<ArmorStats, Integer> stats;
	private boolean is2h;
	
	public Item(){
		stats = new EnumMap<ArmorStats, Integer>(ArmorStats.class);
		is2h = false;
	}
	
	public Integer set(ArmorStats stat, int value){ return stats.put(stat, value); }
	public int get(ArmorStats stat){ return stats.get(stat); }
	public boolean is2h(){ return is2h; }
	
	@Override
	public String toString(){ return name; }
	
	public String getName(){ return name; }
	
	public void setName(String name){ this.name = name; }
	public void set2h(boolean is2h){ this.is2h = is2h; }
	
	public Slot getSlot(){ return slot; }
	public void setSlot(Slot slot){ this.slot = slot; }
	
	@Override
	public boolean equals(Object oth){
		if(oth instanceof Item){
			Item other = (Item)oth;
			if(!other.getName().equals(this.getName())){
				System.err.println("Names are different.");
				return false;
			}
			
			if(!other.getSlot().equals(this.getSlot())){
				System.err.println("Slots are different.");
				return false;
			}
			
			if(other.is2h() != this.is2h()){
				System.err.println("2h are different.");
				return false;
			}
			
			for(ArmorStats stat : ArmorStats.values()){
				if(other.get(stat) != this.get(stat)){
					System.err.println(String.format("%s is different.", stat));
					return false;
				}
			}
			
			return true;
		}
		return false;
	}
}
