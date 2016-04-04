package osrs.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import osrs.model.data.Levels;
import osrs.model.npc.Player;

public class PlayerEditDialogController {

	@FXML
	private TextField nameField;
	@FXML
	private Spinner<Integer> attackSpinner;
	@FXML
	private Spinner<Integer> strengthSpinner;
	@FXML
	private Spinner<Integer> defenceSpinner;
	@FXML
	private Spinner<Integer> hitpointsSpinner;
	@FXML
	private Spinner<Integer> rangedSpinner;
	@FXML
	private Spinner<Integer> magicSpinner;
	@FXML
	private Spinner<Integer> prayerSpinner;

	private Stage dialogStage;
	private Player player;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
		SpinnerValueFactory<Integer> attackValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
		SpinnerValueFactory<Integer> strengthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
		SpinnerValueFactory<Integer> defenceValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
		SpinnerValueFactory<Integer> hitpointsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, Integer.MAX_VALUE);
		SpinnerValueFactory<Integer> rangedValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
		SpinnerValueFactory<Integer> magicValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
		SpinnerValueFactory<Integer> prayerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);

		attackSpinner.setValueFactory(attackValueFactory);
		strengthSpinner.setValueFactory(strengthValueFactory);
		defenceSpinner.setValueFactory(defenceValueFactory);
		rangedSpinner.setValueFactory(rangedValueFactory);
		magicSpinner.setValueFactory(magicValueFactory);
		hitpointsSpinner.setValueFactory(hitpointsValueFactory);
		prayerSpinner.setValueFactory(prayerValueFactory);

	    attackSpinner.setEditable(true);
	    strengthSpinner.setEditable(true);
	    defenceSpinner.setEditable(true);
	    rangedSpinner.setEditable(true);
	    magicSpinner.setEditable(true);
	    prayerSpinner.setEditable(true);
	    hitpointsSpinner.setEditable(true);

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPlayer(Player player) {
		this.player = player;

		nameField.setText(player.getName());
		attackSpinner.getValueFactory().setValue(player.getLevel(Levels.ATTACK));
		strengthSpinner.getValueFactory().setValue(player.getLevel(Levels.STRENGTH));
		defenceSpinner.getValueFactory().setValue(player.getLevel(Levels.DEFENCE));
		rangedSpinner.getValueFactory().setValue(player.getLevel(Levels.RANGED));
		hitpointsSpinner.getValueFactory().setValue(player.getLevel(Levels.HITPOINTS));
		prayerSpinner.getValueFactory().setValue(player.getLevel(Levels.PRAYER));
		magicSpinner.getValueFactory().setValue(player.getLevel(Levels.MAGIC));
	}

	@FXML
	private void handleLookup() {
		String name = nameField.getText();

		if(name == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Name field empty");
			alert.setContentText("Please enter a name to lookup in the name field.");

			alert.showAndWait();
			return;
		}

		Player lookup = Player.fromHiscores(name);
		if(lookup == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Player not found");
			alert.setContentText("The name you entered was not found!\nPlease double-check your spelling.");

			alert.showAndWait();
		} else {
			player.setName(lookup.getName());

			for(Levels level : Levels.values()) {
				player.setLevel(level, lookup.getLevel(level));
			}

			nameField.setText(player.getName());
			attackSpinner.getValueFactory().setValue(player.getLevel(Levels.ATTACK));
			strengthSpinner.getValueFactory().setValue(player.getLevel(Levels.STRENGTH));
			defenceSpinner.getValueFactory().setValue(player.getLevel(Levels.DEFENCE));
			rangedSpinner.getValueFactory().setValue(player.getLevel(Levels.RANGED));
			hitpointsSpinner.getValueFactory().setValue(player.getLevel(Levels.HITPOINTS));
			prayerSpinner.getValueFactory().setValue(player.getLevel(Levels.PRAYER));
			magicSpinner.getValueFactory().setValue(player.getLevel(Levels.MAGIC));
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if(isInputValid()) {
			player.setName(nameField.getText());

			player.setLevel(Levels.ATTACK, attackSpinner.getValue());
			player.setLevel(Levels.STRENGTH, strengthSpinner.getValue());
			player.setLevel(Levels.DEFENCE, defenceSpinner.getValue());
			player.setLevel(Levels.RANGED, rangedSpinner.getValue());
			player.setLevel(Levels.MAGIC, magicSpinner.getValue());
			player.setLevel(Levels.HITPOINTS, hitpointsSpinner.getValue());
			player.setLevel(Levels.PRAYER, prayerSpinner.getValue());

			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if(nameField.getText() == null || nameField.getText().equals("") ){
			errorMessage += "No valid name!\n";
		}
		if(attackSpinner.getValue() < 1){
			errorMessage += "Attack must be at least 1!\n";
		}
		if(strengthSpinner.getValue() < 1){
			errorMessage += "Strength must be at least 1!\n";
		}
		if(defenceSpinner.getValue() < 1){
			errorMessage += "Defence must be at least 1!\n";
		}
		if(rangedSpinner.getValue() < 1){
			errorMessage += "Ranged must be at least 1!\n";
		}
		if(magicSpinner.getValue() < 1){
			errorMessage += "Magic must be at least 1!\n";
		}
		if(prayerSpinner.getValue() < 1){
			errorMessage += "Prayer must be at least 1!\n";
		}
		if(hitpointsSpinner.getValue() < 9){
			errorMessage += "Hitpoints must be at least 9!\n";
		}


		if(errorMessage.equals("")) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();
			return false;
		}
	}
}
