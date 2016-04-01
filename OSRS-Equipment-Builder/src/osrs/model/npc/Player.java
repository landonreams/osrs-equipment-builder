package osrs.model.npc;

import java.util.ArrayList;
import java.util.List;

import osrs.model.data.ArmorStats;
import osrs.model.data.Levels;

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

	public Double getCombatLevel() {
		Double base = 0.25 * (this.getLevel(Levels.DEFENCE) + this.getLevel(Levels.HITPOINTS) + Math.floor( this.getLevel(Levels.PRAYER) / 2));
		Double melee = 0.325 * (this.getLevel(Levels.ATTACK) + this.getLevel(Levels.STRENGTH));
		Double range = 0.325 * (Math.floor(this.getLevel(Levels.RANGED)/2) + this.getLevel(Levels.RANGED));
		Double mage  = 0.325 * (Math.floor(this.getLevel(Levels.MAGIC)/2) + this.getLevel(Levels.MAGIC));

		Double result = base + Math.max(melee, Math.max(range, mage));

		return result;
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
