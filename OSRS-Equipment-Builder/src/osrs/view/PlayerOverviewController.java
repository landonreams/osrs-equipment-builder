package osrs.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import osrs.MainApp;
import osrs.model.data.Levels;
import osrs.model.npc.Player;

public class PlayerOverviewController {
	@FXML
	private TableView<Player> playerTable;
	@FXML
	private TableColumn<Player, String> nameColumn;

	@FXML
	private Label nameLabel;
	@FXML
	private Label combatLabel;
	@FXML
	private Label attackLabel;
	@FXML
	private Label strengthLabel;
	@FXML
	private Label defenceLabel;
	@FXML
	private Label rangedLabel;
	@FXML
	private Label magicLabel;
	@FXML
	private Label hitpointsLabel;
	@FXML
	private Label prayerLabel;

	private MainApp mainApp;

	public PlayerOverviewController() {

	}

	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

		showPlayerDetails(null);

		playerTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showPlayerDetails(newValue));
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		playerTable.setItems(mainApp.getPlayerData());
	}

	private void showPlayerDetails(Player player) {
		if (player != null) {
			nameLabel.setText(player.getName());
			combatLabel.setText(String.format("%.2f", player.getCombatLevel()));
			attackLabel.setText(Integer.toString(player.getLevel(Levels.ATTACK)));
			strengthLabel.setText(Integer.toString(player.getLevel(Levels.STRENGTH)));
			defenceLabel.setText(Integer.toString(player.getLevel(Levels.DEFENCE)));
			rangedLabel.setText(Integer.toString(player.getLevel(Levels.RANGED)));
			magicLabel.setText(Integer.toString(player.getLevel(Levels.MAGIC)));
			hitpointsLabel.setText(Integer.toString(player.getLevel(Levels.HITPOINTS)));
			prayerLabel.setText(Integer.toString(player.getLevel(Levels.PRAYER)));
		} else {
			nameLabel.setText("");
			combatLabel.setText("");
			attackLabel.setText("");
			strengthLabel.setText("");
			defenceLabel.setText("");
			hitpointsLabel.setText("");
			rangedLabel.setText("");
			magicLabel.setText("");
			prayerLabel.setText("");

		}
	}

	/**
	 * Called when the user clicks the delete button.
	 */
	@FXML
	private void handleDelete() {
		int selectedIndex = playerTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			playerTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Build Selected");
			alert.setContentText("Please select a build in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleEdit() {
		Player selectedPlayer = playerTable.getSelectionModel().getSelectedItem();
		if(selectedPlayer != null) {
			boolean okClicked = mainApp.showPlayerEditDialog(selectedPlayer);
			if(okClicked) {
				showPlayerDetails(selectedPlayer);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Player Selected");
	        alert.setContentText("Please select a player in the table.");

	        alert.showAndWait();
		}
	}

	@FXML
	private void handleNew() {
		Player tempPlayer = new Player.Builder<>().build();
		boolean okClicked = mainApp.showPlayerEditDialog(tempPlayer);
		if(okClicked){
			mainApp.getPlayerData().add(tempPlayer);
		}
	}
}
