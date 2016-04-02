package osrs.view;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import osrs.MainApp;
import osrs.model.npc.ArmorSet;

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
	private DamageCell cell;
	private ListView<ArmorSet> list;

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

			dialogStage.setScene(new Scene(setEditor));

			dialogStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateFields() {
		lblSetName.setText(String.format("Set: %s", armorSet == null ? "" : armorSet.getName()));
	}
}
