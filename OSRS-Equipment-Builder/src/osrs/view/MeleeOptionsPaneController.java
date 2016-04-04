package osrs.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import osrs.model.data.AttackStyle;
import osrs.model.data.Potion;
import osrs.model.data.Prayer;

public class MeleeOptionsPaneController {
	@FXML
	private ChoiceBox<AttackStyle.Melee> styleBox;
	@FXML
	private ChoiceBox<Potion.Attack> attackPotionBox;
	@FXML
	private ChoiceBox<Potion.Strength> strengthPotionBox;
	@FXML
	private ChoiceBox<Prayer.Attack> attackPrayerBox;
	@FXML
	private ChoiceBox<Prayer.Strength> strengthPrayerBox;

	@FXML
	private void initialize() {
		styleBox.setItems(FXCollections.observableArrayList(AttackStyle.Melee.values()));
		styleBox.getSelectionModel().select(AttackStyle.Melee.ACCURATE);
		styleBox.setConverter(new StringConverter<AttackStyle.Melee>() {

			@Override
			public String toString(AttackStyle.Melee object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public AttackStyle.Melee fromString(String string) {
				for(AttackStyle.Melee m : AttackStyle.Melee.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

		attackPotionBox.setItems(FXCollections.observableArrayList(Potion.Attack.values()));
		attackPotionBox.getSelectionModel().select(Potion.Attack.NONE);
		attackPotionBox.setConverter(new StringConverter<Potion.Attack>() {

			@Override
			public String toString(Potion.Attack object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Potion.Attack fromString(String string) {
				for(Potion.Attack m : Potion.Attack.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

		strengthPotionBox.setItems(FXCollections.observableArrayList(Potion.Strength.values()));
		strengthPotionBox.getSelectionModel().select(Potion.Strength.NONE);
		strengthPotionBox.setConverter(new StringConverter<Potion.Strength>() {

			@Override
			public String toString(Potion.Strength object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Potion.Strength fromString(String string) {
				for(Potion.Strength m : Potion.Strength.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

		attackPrayerBox.setItems(FXCollections.observableArrayList(Prayer.Attack.values()));
		attackPrayerBox.getSelectionModel().select(Prayer.Attack.NONE);
		attackPrayerBox.setConverter(new StringConverter<Prayer.Attack>() {

			@Override
			public String toString(Prayer.Attack object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Prayer.Attack fromString(String string) {
				for(Prayer.Attack m : Prayer.Attack.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

		strengthPrayerBox.setItems(FXCollections.observableArrayList(Prayer.Strength.values()));
		strengthPrayerBox.getSelectionModel().select(Prayer.Strength.NONE);
		strengthPrayerBox.setConverter(new StringConverter<Prayer.Strength>() {

			@Override
			public String toString(Prayer.Strength object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Prayer.Strength fromString(String string) {
				for(Prayer.Strength m : Prayer.Strength.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

	}
}
