package com.osrs.npc;

import com.osrs.items.EquipmentSet;
import com.osrs.items.SlotType;
import com.osrs.items.StatType;
import com.osrs.levels.ArmorBoostType;
import com.osrs.levels.Potions;
import com.osrs.levels.Prayers;

/**
 * Class to simulate Player in combat. Players use Equipment sets rather than stat array.
 * @author Landon Reams
 */
public class Player extends Fightable {
	private EquipmentSet gear;
	
	/**
	 * Default constructor taking no parameters.
	 */
	public Player(){
		super(new int[] {1, 1, 1, 1, 1, 1, 10});
		gear = new EquipmentSet();
		damageType = DamageType.CRUSH;
	}
	
	/**
	 * Constructor taking a string array of desired equipment.
	 * @param equipment
	 */
	public Player(String[] equipment){
		super(new int[] {1, 1, 1, 1, 1, 1, 10});
		gear = new EquipmentSet(equipment);
		damageType = DamageType.CRUSH;
	}
	
	/**
	 * Constructor taking an int array of levels.
	 * @param levels
	 */
	public Player(int[] levels){
		super(levels);
		gear = new EquipmentSet();
	}
	
	/**
	 * Constructor taking both an int array of levels and string array of equipment.
	 * @param levels
	 * @param equipment
	 */
	public Player(int[] levels, String[] equipment){
		super(levels);
		gear = new EquipmentSet(equipment);
	}
	
	
	
	@Override
	public int[] getStats() {
		return gear.getStats();
	}

	@Override
	public int getStat(StatType stat) {
		return gear.getStat(stat);
	}
	
	/**
	 * Applies a new potion, deactivating all conflicting ones.
	 * @param p
	 */
	public void addPotion(Potions potion){
		potions.add(potion);
	}
	
	/**
	 * Removes an active potion effect to the player in question.
	 * @param potion
	 */
	public void removePotion(Potions potion){
		potions.remove(potion);
	}
	
	/**
	 * Adds a new Prayer, deactivating all conflicting ones.
	 * @param prayer
	 */
	public void addPrayer(Prayers prayer){
		prayers.add(prayer);
	}
	
	/**
	 * Deactivates the active prayer effect.
	 * @param prayer
	 */ 
	public void removePrayer(Prayers prayer){
		prayers.remove(prayer);
	}
	
	public void clearSlot(SlotType slot){
		gear.clearSlot(slot);
	}
	
	public void equip(String item){
		gear.equip(item);
	}
	
	public void equip(String[] items){
		gear.equipArray(items);
	}
	
	@Override
	public ArmorBoostType getArmorBoost(){
		ArmorBoostType abt = gear.armorBoost();
		return abt;
	}
	
	public String getItemName(SlotType slot){
		return gear.getItem(slot);
	}
	
	public void setPrayers(boolean[] values) {
		if(values != null) prayers.setValues(values);
	}

	public void setPotions(boolean[] values) {
		if(values != null) potions.setValues(values);
	}
	
}
