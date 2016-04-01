package osrs.model.npc;

import java.util.ArrayList;
import java.util.List;

import osrs.model.data.ArmorStats;

public class Player extends Fightable {

	private ArmorSet armor;

	public static class Builder<T extends Player> extends Fightable.Builder<T> {
		private List<String> itemsToEquip = new ArrayList<String>();

		public Builder<T> equip(String item) {
			itemsToEquip.add(item);
			return this;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected T getInstance() {
			String[] items = new String[itemsToEquip.size()];
			items = itemsToEquip.toArray(items);
			return (T) new Player(this, items);
		}
	}

	protected Player(Builder<? extends Player> builder, String[] items) {
		super(builder);
		armor = new ArmorSet();
		armor.equipArray(items);
	}

	@Override
	public void setStat(ArmorStats stat, Integer value) { throw new UnsupportedOperationException("Player stats are determined by equipment!"); }

	protected void updateStats() {
		for(ArmorStats stat : ArmorStats.values() ){
			Integer value = armor.getArmorStat(stat);
			super.setStat(stat, value);
		}
	}

	public ArmorSet equipSet(ArmorSet set) {
		ArmorSet old = armor;
		armor = set;
		updateStats();
		return old;
	}

	public void equip(String item) {
		armor.equip(item);
		updateStats();
	}

	public void equipList(String[] items) {
		armor.equipArray(items);
		updateStats();
	}
}
