package osrs.view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import osrs.MainApp;
import osrs.model.data.ArmorStats;
import osrs.model.data.Levels;
import osrs.model.npc.ArmorSet;
import osrs.model.npc.NPC;
import osrs.model.npc.Player;

public class SetCompactViewController {


	@FXML
	private Label lblSetName;
	@FXML
	private Label lblMaxHit;
	@FXML
	private Label lblHitChance;
	@FXML
	private Label lblDPS;

	private ArmorSet armorSet;
	private Player build;
	private DamageCell cell;
	private ListView<ArmorSet> list;
	private MainApp mainApp;
	private NPC target;

	public void setCell(DamageCell cell){
		this.cell = cell;
	}

	public void setList(ListView<ArmorSet> list) {
		this.list = list;
	}

	public void setArmorSet(ArmorSet armorSet) {
		this.armorSet = armorSet;
	}

	@FXML
	private void initialize(){
		armorSet = new ArmorSet();

		updateFields();
	}

	@FXML
	private void handleDelete() {
		if(cell != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Delete Armor Set");
			alert.setContentText("Are you sure you want to delete this set?");

			Optional<ButtonType> result = alert.showAndWait();

			if(result.get() == ButtonType.OK) {
				list.getItems().remove(cell.getItem());
				cell.updateItem(null, true);
			} else
				return;

		}
	}

	@FXML
	private void handleEdit() {
		if(mainApp.playerListIsEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Player list is empty");
			alert.setContentText("Armor sets need a player to wear them!\nPlease create a player in the Player Manager.");

			alert.showAndWait();
			return;
		}

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ArmorSetOverview.fxml"));
			AnchorPane setEditor = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Armor Set Editor");
			dialogStage.initModality(Modality.WINDOW_MODAL);

			ArmorSetOverviewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setArmorSet(armorSet);

			if(build != null)
				controller.setPlayer(build);
			else
				controller.setPlayer(mainApp.getPlayerData().get(0));


			if(mainApp == null) {
				System.out.println("ERROR: Could not load main app in SCV!");
			} else {
				controller.setMainApp(mainApp);
			}

			dialogStage.setScene(new Scene(setEditor));

			dialogStage.showAndWait();

			build = controller.getBuild();

			updateFields();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		try {
			build = mainApp.getPlayerData().get(0);
		} catch (Exception e) { }
	}

	public void updateFields() {

		if(build == null) {
			lblSetName.setText(String.format("Set: %s", armorSet == null ? "Unnamed" : armorSet.getName()));
			lblDPS.setText("DPS: --");
			lblHitChance.setText("Hit chance: --");
			lblMaxHit.setText("Max hit: --");
			return;
		}

		try{
			double accuracy = build.getHitChance(mainApp.getTarget());
			int maxHit      = build.getMaxHit();
			double averageHit = (0.5 * maxHit) * accuracy;

			double dps = averageHit / build.attackSpeedInSeconds();

			lblSetName.setText(String.format("Set: %s", armorSet == null ? "Unnamed" : armorSet.getName()));
			lblDPS.setText(String.format("DPS: %.2f", dps));
			lblHitChance.setText(String.format("Hit chance: %.0f%%", 100 * accuracy));
			lblMaxHit.setText(String.format("Max hit: %d", maxHit));
		} catch (Exception e) {
			lblSetName.setText(String.format("Set: %s", armorSet == null ? "Unnamed" : armorSet.getName()));
			lblDPS.setText("DPS: --");
			lblHitChance.setText("Hit chance: --");
			lblMaxHit.setText("Max hit: --");
		}
	}

	public void setTarget(NPC target) {
		this.target = target;
		ArrayList<Property<?>> properties = new ArrayList<Property<?>>();

		properties.add(target.nameProperty());

		for(Levels level : Levels.values()){
			properties.add(target.levelProperty(level));
		}

		for(ArmorStats stat: ArmorStats.values()) {
			properties.add(target.statProperty(stat));
		}

		for(Property<?> p : properties) {
			p.addListener((observable, oldVal, newVal) -> {
				updateFields();
			});
		}
	}
}
