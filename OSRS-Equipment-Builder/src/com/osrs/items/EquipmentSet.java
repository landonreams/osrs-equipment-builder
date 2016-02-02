package com.osrs.items;
public class EquipmentSet {
	private ItemDatabase db;
	private Equippable[] gear;
	
	/*
	 * Mainhand = 0
	 * Offhand = 1
	 * Head = 2
	 * Body = 3
	 * Legs = 4
	 * Gloves = 5
	 * Boots = 6
	 * Neck = 7
	 * Ring = 8
	 * Cape = 9
	 * Ammo = 10
	 */
	
	private static final Equippable empty = new Equippable();
	/**
	 * Creates a new empty Equipment Set
	 */
	public EquipmentSet(){
		db = new ItemDatabase();
		gear = new Equippable[11];
		
		for(int i = 0; i < 11; i++){
			gear[i] = empty;
		}
	
	}
	/**
	 * Creates an equipment set of the given items
	 * @param items
	 */
	public EquipmentSet(String[] items){
		db = new ItemDatabase();
		gear = new Equippable[11];
		
		for(int i = 0; i < 11; i++){
			gear[i] = empty;
		}
		
		this.equipArray(items);
	}
	
	/**
	 * Equips a given item by getting said item from the database
	 * @param itemName
	 * @throws IllegalArgumentException
	 */
	public void equip(String itemName){
		Equippable item = db.getItem(itemName);
		if(item == null){
			throw new IllegalArgumentException("Item "+itemName+" does not exist! Check your spelling. If you are receiving this by mistake, contact the author!");
		}
		
		gear[item.getSlot().index] = item;
		
		if(item.isTwoHanded())
			gear[SlotType.OFFHAND.index] = empty;
		
	}
	
	public void equipArray(String[] items){
		for(String item : items){
			this.equip(item);
		}
	}
	
	public void clearSlot(SlotType slot){
		gear[slot.index] = empty;
	}
	
	public void clearAll(){
		for(int i = 0; i < gear.length; i++){
			gear[i] = empty;
		}
	}
	
	public int[] getStats(){
		int[] stats = new int[14];
		
		for(Equippable e : gear){
			int[] gearStats = e.getStats();
			for(int i = 0; i < 5; i++){
				stats[i] += gearStats[i];
			}
		}
		return stats;
	}
	
	public int getStat(StatType statType){
		int stat = 0;
		for(Equippable e : gear){
			stat += e.getStat(statType);
		}
		return stat;
	}

}
