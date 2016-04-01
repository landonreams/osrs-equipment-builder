package osrs.model.npc;

import java.util.EnumMap;

import osrs.model.data.ArmorStats;
import osrs.model.data.ItemDatabase;
import osrs.model.data.Slot;

public class Gear {

	private EnumMap<Slot, Item> map;
	private ItemDatabase db;

	public static final int UNARMED_SPEED_INTEGER = 6;
	
	public Gear(){
		map = new EnumMap<Slot, Item>(Slot.class);
		db  = new ItemDatabase();
	}
	
	public int getArmorStat(ArmorStats as){
		if(as.equals(ArmorStats.ASPEED)){
			if(this.isUnarmed())
				return UNARMED_SPEED_INTEGER;
			else
				return map.get(Slot.WEAPON).get(ArmorStats.ASPEED);
		} else if (as.equals(ArmorStats.RSTR)) {
			//System.out.println("GETTING RSTR????");
			int wep = 0;
			int amm = 0;
			if(this.isFilled(Slot.WEAPON)){
				Item iWeapon = map.get(Slot.WEAPON);
				//System.out.println("Weapon is "+iWeapon);
				wep = iWeapon.get(ArmorStats.RSTR);
			}
			if(this.isFilled(Slot.AMMO)){
				Item iAmmo = map.get(Slot.AMMO);
				//System.out.println("Ammo is "+iAmmo);
				amm = iAmmo.get(ArmorStats.RSTR);
			}
			
			if(wep > 0) return wep;
			else        return amm;
		} else {
			int val = 0;
			
			for(Item i : map.values()){
				if(i != null){
					val += i.get(as);
				}
			}
			
			return val;
		}
	}
	
	public void equipArray(String[] items){
		for(String i : items){
			this.equip(i);
		}
	}
	
	public Item equip(String item){
		Item i = db.get(item);
		System.out.printf("%s: Slot %s%n", i, i.getSlot());
		return map.put(i.getSlot(), i);
	}
	
	public void clear(Slot slot){
		map.put(slot, null);
	}
	
	public boolean isUnarmed(){
		return map.get(Slot.WEAPON) == null;
	}
	public boolean isFilled(Slot slot){
		return map.get(slot) != null;
	}
	
	public boolean contains(String item){
		for(Item i : map.values()){
			if(i.toString().equals(item))
				return true;
		}
		return false;
	}
}
