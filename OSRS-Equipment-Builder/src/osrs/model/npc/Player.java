package osrs.model.npc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import osrs.model.data.ArmorStats;
import osrs.model.data.AttackStyle;
import osrs.model.data.CombatStyle;
import osrs.model.data.Levels;
import osrs.model.data.Potion;
import osrs.model.data.Prayer;

public class Player extends Fightable {
	private ArmorSet armor;

	private ObjectProperty<Potion.Attack>   attackPotionProperty;
	private ObjectProperty<Potion.Strength> strengthPotionProperty;
	private ObjectProperty<Potion.Ranged>   rangedPotionProperty;
	private ObjectProperty<Potion.Magic>    magicPotionProperty;

	private ObjectProperty<Prayer.Attack>   attackPrayerProperty;
	private ObjectProperty<Prayer.Strength> strengthPrayerProperty;
	private ObjectProperty<Prayer.Ranged>   rangedPrayerProperty;
	private ObjectProperty<Prayer.Magic>    magicPrayerProperty;

	private ObjectProperty<CombatStyle>        combatStyleProperty;
	private ObjectProperty<AttackStyle.Melee>  meleeStyleProperty;
	private ObjectProperty<AttackStyle.Ranged> rangedStyleProperty;
	private ObjectProperty<AttackStyle.Magic>  magicStyleProperty;

	public static final int DEFAULT_PLAYER_SPEED = 6;

	private static final String HISCORES_LOOKUP = "http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=%s";

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

	public Player() {
		super(new Player.Builder<>().name("None"));
		armor = new ArmorSet();
		initProperties();
	}

	protected Player(Builder<? extends Player> builder, String[] items) {
		super(builder);
		armor = new ArmorSet();
		armor.equipArray(items);
		initProperties();
	}

	private void initProperties() {
		attackPotionProperty = new SimpleObjectProperty<>(Potion.Attack.NONE);
		strengthPotionProperty = new SimpleObjectProperty<>(Potion.Strength.NONE);
		rangedPotionProperty = new SimpleObjectProperty<>(Potion.Ranged.NONE);
		magicPotionProperty = new SimpleObjectProperty<>(Potion.Magic.NONE);

		attackPrayerProperty = new SimpleObjectProperty<>(Prayer.Attack.NONE);
		strengthPrayerProperty = new SimpleObjectProperty<>(Prayer.Strength.NONE);
		rangedPrayerProperty = new SimpleObjectProperty<>(Prayer.Ranged.NONE);
		magicPrayerProperty = new SimpleObjectProperty<>(Prayer.Magic.NONE);

		combatStyleProperty = new SimpleObjectProperty<>(CombatStyle.MELEE);
		meleeStyleProperty = new SimpleObjectProperty<>(AttackStyle.Melee.CONTROLLED);
		rangedStyleProperty = new SimpleObjectProperty<>(AttackStyle.Ranged.RAPID);
		magicStyleProperty = new SimpleObjectProperty<>(AttackStyle.Magic.ACCURATE);
	}

	@Override
	public void setStat(ArmorStats stat, Integer value) { throw new UnsupportedOperationException("Player stats are determined by equipment!"); }

	protected void updateStats() {
		for(ArmorStats stat : ArmorStats.values() ){
			Integer value = armor.getStat(stat);
			super.setStat(stat, value);
		}
	}

	public ArmorSet equipSet(ArmorSet set) {
		ArmorSet old = armor;
		armor = set;
		updateStats();
		return old;
	}

	public ArmorSet getArmor() { return armor; }

	public void equip(String item) {
		armor.equip(item);
		updateStats();
	}

	public void equipList(String[] items) {
		armor.equipArray(items);
		updateStats();
	}



	public Potion.Attack getAttackPotion() { return attackPotionProperty.get(); }
	public void setAttackPotion(Potion.Attack potion) { attackPotionProperty.set(potion); }
	public ObjectProperty<Potion.Attack> attackPotionProperty() { return attackPotionProperty; }

	public Potion.Strength getStrengthPotion() { return strengthPotionProperty.get(); }
	public void setStrengthPotion(Potion.Strength potion) { strengthPotionProperty.set(potion); }
	public ObjectProperty<Potion.Strength> strengthPotionProperty() { return strengthPotionProperty; }

	public Potion.Ranged getRangedPotion() { return rangedPotionProperty.get(); }
	public void setRangedPotion(Potion.Ranged potion) { rangedPotionProperty.set(potion); }
	public ObjectProperty<Potion.Ranged> rangedPotionProperty() { return rangedPotionProperty; }

	public Potion.Magic getMagicPotion() { return magicPotionProperty.get(); }
	public void setMagicPotion(Potion.Magic potion) { magicPotionProperty.set(potion); }
	public ObjectProperty<Potion.Magic> magicPotionProperty() { return magicPotionProperty; }

	public Prayer.Attack getAttackPrayer() { return attackPrayerProperty.get(); }
	public void setAttackPrayer(Prayer.Attack prayer) { attackPrayerProperty.set(prayer); }
	public ObjectProperty<Prayer.Attack> attackPrayerProperty() { return attackPrayerProperty; }

	public Prayer.Strength getStrengthPrayer() { return strengthPrayerProperty.get(); }
	public void setStrengthPrayer(Prayer.Strength prayer) { strengthPrayerProperty.set(prayer); }
	public ObjectProperty<Prayer.Strength> strengthPrayerProperty() { return strengthPrayerProperty; }

	public Prayer.Ranged getRangedPrayer() { return rangedPrayerProperty.get(); }
	public void setRangedPrayer(Prayer.Ranged prayer) { rangedPrayerProperty.set(prayer); }
	public ObjectProperty<Prayer.Ranged> rangedPrayerProperty() { return rangedPrayerProperty; }

	public Prayer.Magic getMagicPrayer() { return magicPrayerProperty.get(); }
	public void setMagicPrayer(Prayer.Magic prayer) { magicPrayerProperty.set(prayer); }
	public ObjectProperty<Prayer.Magic> magicPrayerProperty() { return magicPrayerProperty; }

	public CombatStyle getCombatStyle() { return combatStyleProperty.get(); }
	public void setCombatStyle(CombatStyle style) { combatStyleProperty.set(style); }
	public ObjectProperty<CombatStyle> combatStyleProperty() { return combatStyleProperty; }

	public AttackStyle.Melee getMeleeStyle() { return meleeStyleProperty.get(); }
	public void setMeleeStyle(AttackStyle.Melee style) { meleeStyleProperty.set(style); }
	public ObjectProperty<AttackStyle.Melee> meleeStyleProperty() { return meleeStyleProperty; }

	public AttackStyle.Ranged getRangedStyle() { return rangedStyleProperty.get(); }
	public void setRangedStyle(AttackStyle.Ranged style) { rangedStyleProperty.set(style); }
	public ObjectProperty<AttackStyle.Ranged> rangedStyleProperty() { return rangedStyleProperty; }

	public AttackStyle.Magic getMagicStyle() { return magicStyleProperty.get(); }
	public void setMagicStyle(AttackStyle.Magic style) { magicStyleProperty.set(style); }
	public ObjectProperty<AttackStyle.Magic> magicStyleProperty() { return magicStyleProperty; }

	public static Player fromHiscores(String name) {
		try {
			System.out.println("URL IS: "+String.format(HISCORES_LOOKUP, name));
			URL hiscores = new URL(String.format(HISCORES_LOOKUP, name));
			BufferedReader in = new BufferedReader(
								new InputStreamReader(
								hiscores.openConnection().getInputStream()));

			ArrayList<String> lines = new ArrayList<String>();
			String inputLine;

			int count = 0;

			while((inputLine = in.readLine()) != null && count < 8) {
				lines.add(inputLine);
				count++;
			}
			//System.out.println(fullText);

			/*
			 * 0: TOTAL
			 * 1: ATTACK
			 * 2: DEFENCE
			 * 3: STRENGTH
			 * 4: HITPOINTS
			 * 5: RANGED
			 * 6: PRAYER
			 * 7: MAGIC
			 */

			Player.Builder<Player> builder = new Player.Builder<>();

			builder.name(name);

			for(int i = 1; i < 8; i++) {
				String[] skill = lines.get(i).split(",");
				Integer level = Integer.parseInt(skill[1]);
				switch(i) {
				case 1: builder.attack(level); break;
				case 2: builder.defence(level); break;
				case 3: builder.strength(level); break;
				case 4: builder.hitpoints(level); break;
				case 5: builder.ranged(level); break;
				case 6: builder.prayer(level); break;
				case 7: builder.magic(level); break;
				default: throw new ParseException("Unable to parse URL!", 0);
				}
			}

			return builder.build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void duplicate(Player other) {

	}

	public void print() {
		System.out.println("PLAYER: " + name.get());
		System.out.println("- Atk: " + levels.get(Levels.ATTACK).get());
		System.out.println("- Str: " + levels.get(Levels.STRENGTH).get());
		System.out.println("- Def: " + levels.get(Levels.DEFENCE).get());
		System.out.println("- Rng: " + levels.get(Levels.RANGED).get());
		System.out.println("- Mag: " + levels.get(Levels.MAGIC).get());
		System.out.println("- Pry: " + levels.get(Levels.PRAYER).get());
		System.out.println("- Hit: " + levels.get(Levels.HITPOINTS).get());
	}

}
