package com.osrs.npc;

import java.util.EnumMap;

import com.osrs.data.ArmorStats;
import com.osrs.data.ItemDatabase;
import com.osrs.data.Slot;

public class Gear {

	private EnumMap<Slot, Item> map;
	private ItemDatabase db;
	
	private final Item UNARMED;
	
	public Gear(){
		map = new EnumMap<Slot, Item>(Slot.class);
		db  = new ItemDatabase();
		UNARMED = db.get("Unarmed");
		
		map.put(Slot.WEAPON, UNARMED);
	}
	
	public int getArmorStat(ArmorStats as){
		int val = 0;
		
		for(Item i : map.values()){
			if(i != null)
				val += i.get(as);
		}
		
		return val;
	}
	
	public void equipArray(String[] items){
		for(String i : items){
			this.equip(i);
		}
	}
	
	public void equip(String item){
		Item i = db.get(item);
		
		map.put(i.getSlot(), i);
	}
	
	public boolean contains(String item){
		for(Item i : map.values()){
			if(i.toString().equals(item))
				return true;
		}
		return false;
	}
}
