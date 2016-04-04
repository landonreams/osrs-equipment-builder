package osrs.model.npc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import osrs.model.data.ArmorStats;
import osrs.model.data.Levels;

public abstract class Fightable {
	protected final StringProperty  name;
	protected final Map<Levels, IntegerProperty> levels;
	protected final Map<ArmorStats, IntegerProperty> stats;

	public static abstract class Builder<T extends Fightable> {
		private String name;
		private int attack, strength, defence, ranged, magic, hitpoints, prayer;

		public Builder() {
			name = "None";
			attack = 1;
			strength = 1;
			defence = 1;
			ranged = 1;
			magic = 1;
			hitpoints = 1;
			prayer = 1;
		}

		public Builder<T> name(String name)		   { this.name = name;    	     return this; }
		public Builder<T> attack(int attack)	   { this.attack = attack;       return this; }
		public Builder<T> strength(int strength)   { this.strength = strength;   return this; }
		public Builder<T> defence(int defence)	   { this.defence = defence;     return this; }
		public Builder<T> magic(int magic)         { this.magic = magic; 		 return this; }
		public Builder<T> ranged(int ranged)       { this.ranged = ranged; 		 return this; }
		public Builder<T> hitpoints(int hitpoints) { this.hitpoints = hitpoints; return this; }
		public Builder<T> prayer(int prayer)       { this.prayer = prayer; 		 return this; }

		public boolean isValid() {
			return !name.equals("") &&
					attack > 0 &&
					strength > 0 &&
					defence > 0 &&
					ranged > 0 &&
					magic > 0 &&
					hitpoints > 0 &&
					prayer > 0;
		}

		public final T build() {
			if(isValid())
				return getInstance();
			throw new IllegalStateException();
		}

		protected abstract T getInstance();
	}

	protected Fightable(Builder<? extends Fightable> builder) {
		this();
		setName(builder.name);
		setLevel(Levels.ATTACK, builder.attack);
		setLevel(Levels.STRENGTH, builder.strength);
		setLevel(Levels.DEFENCE, builder.defence);
		setLevel(Levels.RANGED, builder.ranged);
		setLevel(Levels.MAGIC, builder.magic);
		setLevel(Levels.HITPOINTS, builder.hitpoints);
		setLevel(Levels.PRAYER, builder.prayer);
	}

	public abstract int applyPotion(Levels level, int value);
	public abstract int applyPrayer(Levels level, int value);

	protected Fightable() {
		name   = new SimpleStringProperty("");
		levels = new HashMap<>();
		stats  = new HashMap<>();

		Arrays.asList(Levels.values())
		.forEach(lvl ->
			levels.put(lvl, new SimpleIntegerProperty(0)));

		Arrays.asList(ArmorStats.values())
		.forEach(as ->
			stats.put(as, new SimpleIntegerProperty(0)));
	}

	public String getName() { return name.get(); }
	public void setName(String name) { this.name.set(name); }
	public StringProperty nameProperty() { return name; }

	public Integer getLevel(Levels level) { return levels.get(level).get(); }
	public void setLevel(Levels level, Integer value) { levels.get(level).set(value); }
	public IntegerProperty levelProperty(Levels level) { return levels.get(level); }

	public Integer getStat(ArmorStats stat) { return stats.get(stat).get(); }
	public void setStat(ArmorStats stat, Integer value) { stats.get(stat).set(value); }
	public IntegerProperty statProperty(ArmorStats stat) { return stats.get(stat); }

}
