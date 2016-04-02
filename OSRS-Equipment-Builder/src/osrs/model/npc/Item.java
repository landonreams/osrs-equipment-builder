package osrs.model.npc;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import osrs.model.data.ArmorStats;
import osrs.model.data.Slot;

public class Item {
	private final StringProperty name;
	private final Map<ArmorStats, IntegerProperty> stats;
	private final ObjectProperty<Slot> slot;
	private final BooleanProperty is2h;

	public Item() {
		name = new SimpleStringProperty();
		stats = new HashMap<>();

		for(ArmorStats value : ArmorStats.values()) {
			stats.put(value, new SimpleIntegerProperty(0));
		}

		slot = new SimpleObjectProperty<Slot>(null);
		is2h = new SimpleBooleanProperty(false);
	}

	public boolean isValid() {
		return !name.get().equals("") &&
				name.get() != null &&
				slot.get() != null;
	}

	public void setName(String name) { this.name.set(name); }
	public String getName() { return name.get(); }
	public StringProperty nameProperty() { return name; }

	public void setStat(ArmorStats stat, Integer value) { this.stats.get(stat).set(value); }
	public Integer getStat(ArmorStats stat) { return stats.get(stat).get(); }
	public IntegerProperty statsProperty(ArmorStats stat) { return stats.get(stat); }

	public boolean is2h() { return is2h.get(); }
	public void set2h(boolean value) { is2h.set(value); }
	public BooleanProperty is2hProperty() { return is2h; }

	public void setSlot(Slot slot) { this.slot.set(slot); }
	public Slot getSlot() { return slot.get(); }
	public ObjectProperty<Slot> slotProperty() { return slot; }

	@Override
	public String toString() { return getName(); }
}
