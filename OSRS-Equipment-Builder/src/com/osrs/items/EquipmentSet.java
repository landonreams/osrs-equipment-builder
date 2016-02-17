package com.osrs.items;

import java.util.EnumMap;

import com.osrs.levels.ArmorBoostType;

public class EquipmentSet {
	private ItemDatabase db;
	private EnumMap<SlotType, Equippable> gear;

	public static final Equippable EMPTY = new Equippable();
	/**
	 * Creates a new EMPTY Equipment Set
	 */
	public EquipmentSet(){
		db = new ItemDatabase();
		gear = new EnumMap<SlotType, Equippable>(SlotType.class);
		for(SlotType s : SlotType.values()){
			gear.put(s, EMPTY);
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
			gear.put(s,  EMPTY);
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
			gear.put(SlotType.OFFHAND, EMPTY);
		if(item.getSlot().equals(SlotType.OFFHAND)){
			if(gear.get(SlotType.MAINHAND).isTwoHanded())
				gear.put(SlotType.MAINHAND, EMPTY);
		}
		
	}
	
	public void equip(Equippable item){
		if(item == null) throw new IllegalArgumentException();
		gear.put(item.getSlot(), item);
		
		if(item.isTwoHanded())
			gear.put(SlotType.OFFHAND, EMPTY);
		if(item.getSlot().equals(SlotType.OFFHAND)){
			if(gear.get(SlotType.MAINHAND).isTwoHanded())
				gear.put(SlotType.MAINHAND, EMPTY);
		}
		
	}
	
	public void equipArray(String[] items){
		for(String item : items){
			this.equip(item);
		}
	}
	
	public void clearSlot(SlotType slot){
		gear.put(slot, EMPTY);
	}
	
	public void clearAll(){
		for(SlotType s : SlotType.values()){
			gear.put(s, EMPTY);
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
	
	public String getItem(SlotType slot){
		return gear.get(slot).getName();
	}
	
	//MAINHAND(0), OFFHAND(1), HEAD(2), BODY(3), LEGS(4), CAPE(5), GLOVES(6),
   // BOOTS(7), NECK(8), RING(9), AMMO(10);

	public int[] toArray(){
		return new int[] {
			gear.get(SlotType.MAINHAND).getID(),
			gear.get(SlotType.OFFHAND).getID(),
			gear.get(SlotType.HEAD).getID(),
			gear.get(SlotType.BODY).getID(),
			gear.get(SlotType.LEGS).getID(),
			gear.get(SlotType.CAPE).getID(),
			gear.get(SlotType.GLOVES).getID(),
			gear.get(SlotType.BOOTS).getID(),
			gear.get(SlotType.NECK).getID(),
			gear.get(SlotType.RING).getID(),
			gear.get(SlotType.AMMO).getID()
		};
	}
	
	public void equip(int[] array) {
		Equippable[] items = db.getAll(array);
		for(Equippable item : items){
			this.equip(item);
		}
	}

}
