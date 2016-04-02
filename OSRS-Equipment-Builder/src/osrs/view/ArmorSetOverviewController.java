package osrs.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import osrs.model.npc.ArmorSet;
import osrs.model.npc.Player;

public class ArmorSetOverviewController {

	private ObservableList<ArmorSet> armorList;
	@FXML
	private ObservableList<Player>   buildList;

	private ArmorSet armorSet;

	@FXML
	private void initialize() { }

	public ArmorSetOverviewController(ArmorSet armorSet) {
		this.armorSet = armorSet;
	}




	@FXML
	private void handleAmmo() { }

	@FXML
	private void handleBoots() { }

	@FXML
	private void handleCape() { }

	@FXML
	private void handleHands() { }

	@FXML
	private void handleHead() { }

	@FXML
	private void handleLegs() { }

	@FXML
	private void handleNeck() { }

	@FXML
	private void handleRing() { }

	@FXML
	private void handleShield() { }

	@FXML
	private void handleWeapon() { }
}
