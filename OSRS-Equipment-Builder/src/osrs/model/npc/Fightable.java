package osrs.model.npc;

import java.util.EnumMap;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import osrs.model.data.ArmorStats;
import osrs.model.data.Levels;
import osrs.util.ArmorStatsEnumMapAdapter;
import osrs.util.LevelsEnumMapAdapter;

public abstract class Fightable {
	protected final StringProperty  name;
	protected final ObjectProperty<EnumMap<Levels, Integer>> levels;
	protected final ObjectProperty<EnumMap<ArmorStats, Integer>> stats;

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

		@SuppressWarnings("unchecked")
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

	protected Fightable() {
		name   = new SimpleStringProperty("");
		levels = new SimpleObjectProperty<>(new EnumMap<Levels, Integer>(Levels.class));
		stats  = new SimpleObjectProperty<>(new EnumMap<ArmorStats, Integer>(ArmorStats.class));
	}

	public String getName() { return name.get(); }
	public void setName(String name) { this.name.set(name); }
	public StringProperty nameProperty() { return name; }


	public Integer getLevel(Levels level) { return levels.get().get(level); }
	public void setLevel(Levels level, Integer value) { levels.get().put(level, value); }
	public ObjectProperty<EnumMap<Levels, Integer>> levelProperty() { return levels; }

	public Integer getStat(ArmorStats stat) { return stats.get().get(stat); }
	public void setStat(ArmorStats stat, Integer value) { stats.get().put(stat, value); }
	public ObjectProperty<EnumMap<ArmorStats, Integer>> statProperty() { return stats; }

}
