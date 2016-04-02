package osrs.model.npc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import osrs.model.data.ArmorStats;
import osrs.model.data.ItemDatabase;
import osrs.model.data.Slot;

public class ArmorSet {
	private final StringProperty name;
	private final Map<Slot, ObjectProperty<Item>> items;
	private final Map<ArmorStats, IntegerProperty> stats;

	public ArmorSet() {
		name = new SimpleStringProperty("");
		items = new HashMap<>();
		stats = new HashMap<>();

		Arrays.asList(ArmorStats.values())
			.forEach(as ->
				stats.put(as, new SimpleIntegerProperty(0)));

		Arrays.asList(Slot.values())
			.forEach( s ->
				items.put(s, new SimpleObjectProperty<>()));

		items.values().stream()
			.forEach(p -> p.addListener(e -> recalculateStats()));

	}


	private final ItemDatabase db = ItemDatabase.getInstance();

	public void setName(String name) { this.name.set(name); }
	public String getName() { return name.get(); }
	public StringProperty nameProperty() { return name; }

	public void setItem(Item item) { items.get(item.getSlot()).set(item); }
	public Item getItem(Slot slot) { return items.get(slot).get(); }
	public ObjectProperty<Item> itemProperty(Slot slot) { return items.get(slot); }

	private void setStat(ArmorStats stat, Integer value) { stats.get(stat).set(value); }
	public Integer getStat(ArmorStats stat) { return stats.get(stat).get(); }
	public IntegerProperty statProperty(ArmorStats stat) { return stats.get(stat); }

	private void recalculateStats() {
		for(ArmorStats stat : ArmorStats.values()) {
			switch(stat) {
			case RSTR:
				Item wep = items.get(Slot.WEAPON).get();
				Item amm = items.get(Slot.AMMO).get();

				int wepStr = wep == null ? 0 : wep.getStat(stat);
				int ammStr = amm == null ? 0 : amm.getStat(stat);

				int rstr = wepStr > 0 ? wepStr : ammStr;
				setStat(stat, rstr);
				break;
			case ASPEED:
				Item weap = items.get(Slot.WEAPON).get();
				int speed = weap == null ? Player.DEFAULT_PLAYER_SPEED : weap.getStat(stat);
				setStat(stat, speed);
				break;
			default:
				int sum = 0;

				for(ObjectProperty<Item> i : items.values()) {
					Item item = i.get();
					if(item != null)
						sum += item.getStat(stat);
				}
				setStat(stat, sum);
			}
		}
	}

	public void clear(Slot slot) {
		items.get(slot).set(null);
	}

	public void equip(String item) {
		Item i = db.get(item);
		setItem(i);
	}

	public void equipArray(String[] items) {
		Item[] i = db.getAll(items);
		List<Item> list = Arrays.asList(i);
		list.remove(null);
		list.forEach(item -> setItem(item) );

	}

	public void print() {
		for(Slot s : Slot.values()){
			Item item = items.get(s).get();
			if(item != null)
				System.out.print(item.getName() + ", ");
		}
		System.out.println();
		for(ArmorStats s : ArmorStats.values()) {
			System.out.printf("%s: %d%n", s.toString(), getStat(s));
		}
	}

	public static void main(String[] args){
		ArmorSet as = new ArmorSet();
		as.equipArray(new String[] { "Shayzien helm (5)", "Shayzien platebody (5)", "Shayzien greaves (5)", "Shayzien boots (5)", "Shayzien gloves (5)" } );

		as.print();
	}
}
