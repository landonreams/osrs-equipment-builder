package osrs.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import osrs.model.data.AttackStyle;
import osrs.model.data.Potion;
import osrs.model.data.Prayer;

public class RangedOptionsPaneController {
	@FXML
	private ChoiceBox<AttackStyle.Ranged> styleBox;
	@FXML
	private ChoiceBox<Potion.Ranged> potionBox;
	@FXML
	private ChoiceBox<Prayer.Ranged> prayerBox;

	@FXML
	private void initialize() {
		styleBox.setItems(FXCollections.observableArrayList(AttackStyle.Ranged.values()));
		styleBox.getSelectionModel().select(AttackStyle.Ranged.RAPID);
		styleBox.setConverter(new StringConverter<AttackStyle.Ranged>() {

			@Override
			public String toString(AttackStyle.Ranged object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public AttackStyle.Ranged fromString(String string) {
				for(AttackStyle.Ranged m : AttackStyle.Ranged.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

		potionBox.setItems(FXCollections.observableArrayList(Potion.Ranged.values()));
		potionBox.getSelectionModel().select(Potion.Ranged.NONE);
		potionBox.setConverter(new StringConverter<Potion.Ranged>() {

			@Override
			public String toString(Potion.Ranged object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Potion.Ranged fromString(String string) {
				for(Potion.Ranged m : Potion.Ranged.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

		prayerBox.setItems(FXCollections.observableArrayList(Prayer.Ranged.values()));
		prayerBox.getSelectionModel().select(Prayer.Ranged.NONE);
		prayerBox.setConverter(new StringConverter<Prayer.Ranged>() {

			@Override
			public String toString(Prayer.Ranged object) {
				if(object == null)
					return "";
				else
					return object.toString();
			}

			@Override
			public Prayer.Ranged fromString(String string) {
				for(Prayer.Ranged m : Prayer.Ranged.values()) {
					if(string.equals(m.toString()))
						return m;
				}
				return null;
			}
		});

	}
}
