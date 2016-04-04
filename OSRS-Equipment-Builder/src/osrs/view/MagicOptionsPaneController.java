package osrs.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import osrs.model.data.Potion;
import osrs.model.data.Prayer;
import osrs.model.data.Spell;

public class MagicOptionsPaneController {
	@FXML
	private ChoiceBox<Spell> spellBox;
	@FXML
	private ChoiceBox<Potion.Magic> potionBox;
	@FXML
	private ChoiceBox<Prayer.Magic> prayerBox;

	@FXML
	private void initialize() {
		spellBox.setItems(FXCollections.observableArrayList(Spell.values()));
		spellBox.getSelectionModel().select(Spell.STRIKE_WIND);
		spellBox.setConverter(new StringConverter<Spell>() {

			@Override
			public String toString(Spell object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Spell fromString(String string) {
				for(Spell m : Spell.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

		potionBox.setItems(FXCollections.observableArrayList(Potion.Magic.values()));
		potionBox.getSelectionModel().select(Potion.Magic.NONE);
		potionBox.setConverter(new StringConverter<Potion.Magic>() {

			@Override
			public String toString(Potion.Magic object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Potion.Magic fromString(String string) {
				for(Potion.Magic m : Potion.Magic.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

		prayerBox.setItems(FXCollections.observableArrayList(Prayer.Magic.values()));
		prayerBox.getSelectionModel().select(Prayer.Magic.NONE);
		prayerBox.setConverter(new StringConverter<Prayer.Magic>() {

			@Override
			public String toString(Prayer.Magic object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Prayer.Magic fromString(String string) {
				for(Prayer.Magic m : Prayer.Magic.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

	}
}
