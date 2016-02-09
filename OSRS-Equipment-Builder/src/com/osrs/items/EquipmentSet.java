package com.osrs.items;

import java.util.EnumMap;

import com.osrs.npc.ArmorBoostType;

public class EquipmentSet {
	private ItemDatabase db;
	private EnumMap<SlotType, Equippable> gear;
	
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
		gear = new EnumMap<SlotType, Equippable>(SlotType.class);
		for(SlotType s : SlotType.values()){
			gear.put(s, empty);
		}
	
	}
	/**
	 * Creates an equipment set of the given items
	 * @param items
	 */
	public EquipmentSet(String[] items){
		db = new ItemDatabase();
		gear = new EnumMap<SlotType, Equippable>(SlotType.class);
		
		for(SlotType s : SlotType.values()){
			gear.put(s,  empty);
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
		
		gear.put(item.getSlot(), item);
		
		if(item.isTwoHanded())
			gear.put(SlotType.OFFHAND, empty);
		
	}
	
	public void equipArray(String[] items){
		for(String item : items){
			this.equip(item);
		}
	}
	
	public void clearSlot(SlotType slot){
		gear.put(slot, empty);
	}
	
	public void clearAll(){
		for(SlotType s : SlotType.values()){
			gear.put(s, empty);
		}
	}
	
	public int[] getStats(){
		
		int[] stats = new int[14];

		for(Equippable e : gear.values()){
			int[] gStats = e.getStats();
			for(int i = 0; i < 14; i++){
				stats[i] += gStats[i];
			}
		}
		
		//In case mainhand weapon has a built in Ranged Strength bonus
		//a la Crystal bow and Toxic blowpipe
		if(gear.get(SlotType.MAINHAND).getStat(StatType.MSC_RANGE) != 0){
			stats[StatType.MSC_RANGE.index] = gear.get(SlotType.MAINHAND).getStat(StatType.MSC_RANGE);
		}
		
		
		return stats;
	}
	
	public int getStat(StatType statType){
		int stat = 0;
		for(Equippable e : gear.values()){
			stat += e.getStat(statType);
		}
		
		if(statType.equals(StatType.MSC_RANGE)){
			if(gear.get(SlotType.MAINHAND).getStat(StatType.MSC_RANGE) != 0){
				stat = gear.get(SlotType.MAINHAND).getStat(StatType.MSC_RANGE);
			}
		}
		return stat;
	}
	
	/**
	 * Class to get active armor boost from equipment set.
	 * @return ArmorBoostType
	 */
	public ArmorBoostType armorBoost(){
		if(gear.get(SlotType.BODY).getName().contains("oid") &&
		   gear.get(SlotType.LEGS).getName().contains("oid") &&
		   gear.get(SlotType.GLOVES).getName().contains("oid")){
			String head = gear.get(SlotType.HEAD).getName();
			if(head.equals("Void melee helm"))
				return ArmorBoostType.VOID_MELEE;
			if(head.equals("Void ranger helm"))
				return ArmorBoostType.VOID_RANGED;
			if(head.equals("Void mage helm"))
				return ArmorBoostType.VOID_MAGIC;
		}

		String head = gear.get(SlotType.HEAD).getName();
		if(head.contains("Black mask") || head.contains("Slayer helmet")){
			return ArmorBoostType.SLAYER_HELM;
		}
		
		String neck = gear.get(SlotType.NECK).getName();
		if(neck.contains("Salve amulet")){
			if(neck.contains("(e"))
				return ArmorBoostType.SALVE_E;
			return ArmorBoostType.SALVE;
		}
		return ArmorBoostType.NONE;
	}

}
