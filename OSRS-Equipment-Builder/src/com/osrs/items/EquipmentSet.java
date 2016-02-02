package com.osrs.items;
public class EquipmentSet {
	private ItemDatabase db;
	
	private Equippable mainhand, offhand, helmet, body, legs,
					  gloves, boots, ring, neck, cape, ammo;
	
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
	
	public EquipmentSet(){
		db = new ItemDatabase();
		gear = new Equippable[11];
		
		for(int i = 0; i < 11; i++){
			gear[i] = empty;
		}
	
	}
	
	public EquipmentSet(String[] items){
		db = new ItemDatabase();
		gear = new Equippable[11];
		
		for(int i = 0; i < 11; i++){
			gear[i] = empty;
		}
		
		this.equipArray(items);
	}

	/*
	 * @Throws IllegalArgumentException
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
	
	public int[] getOffensives(){
		int[] offensives = new int[5];
		
		for(Equippable e : gear){
			int[] gearOffensives = e.getOffensives();
			for(int i = 0; i < 5; i++){
				offensives[i] += gearOffensives[i];
			}
		}
		return offensives;
	}
	
	public int[] getDefensives(){
		int[] defensives = new int[5];
		
		for(Equippable e : gear){
			int[] gearDefensives = e.getDefensives();
			for(int i = 0; i < 5; i++){
				defensives[i] += gearDefensives[i];
			}
		}
		return defensives;
	}
	
	public int[] getMisc(){
		int[] misc = new int[4];
		
		for(Equippable e : gear){
			int[] gearMisc = e.getMisc();
			for(int i = 0; i < 4; i++){
				misc[i] += gearMisc[i];
			}
		}
		return misc;
	}

}
