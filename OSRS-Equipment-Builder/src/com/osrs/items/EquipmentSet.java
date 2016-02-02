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

	public void equip(String itemName){
		Equippable item = db.getItem(itemName);
		if(item == null){
			throw new IllegalArgumentException("Item "+itemName+" does not exist! Check your spelling. If you are receiving this by mistake, contact the author!");
		}
		
		switch(item.getSlot()){
		case TWOHAND:  gear[0] = item; gear[1] = empty; break;
		case MAINHAND: gear[0] = item; break;
		case OFFHAND:  gear[1] = item; break;
		case HEAD:     gear[2] = item; break;
		case BODY:     gear[3] = item; break;
		case LEGS:     gear[4] = item; break;
		case GLOVES:   gear[5] = item; break;
		case BOOTS:    gear[6] = item; break;
		case NECK:     gear[7] = item; break;
		case RING:     gear[8] = item; break;
		case CAPE:     gear[9] = item; break;
		case AMMO:     gear[10] = item; break;
		default: throw new IllegalArgumentException("Error! Something went wrong equipping the item :"+itemName+". Contact the author!");
		}
		
	}
	
	public void equipArray(String[] items){
		for(String item : items){
			this.equip(item);
		}
	}
	
	public void clearSlot(SlotType slot){
		switch(slot){
		case TWOHAND:  gear[0] = empty; gear[1] = empty; break;
		case MAINHAND: gear[0] = empty; break;
		case OFFHAND:  gear[1] = empty; break;
		case HEAD:     gear[2] = empty; break;
		case BODY:     gear[3] = empty; break;
		case LEGS:     gear[4] = empty; break;
		case GLOVES:   gear[5] = empty; break;
		case BOOTS:    gear[6] = empty; break;
		case NECK:     gear[7] = empty; break;
		case RING:     gear[8] = empty; break;
		case CAPE:     gear[9] = empty; break;
		case AMMO:     gear[10] = empty; break;
		}
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
