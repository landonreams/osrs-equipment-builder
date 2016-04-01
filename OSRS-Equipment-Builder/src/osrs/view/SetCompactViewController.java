package osrs.view;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import osrs.model.npc.ArmorSet;

public class SetCompactViewController {

	private DamageCell cell;
	private ListView<ArmorSet> list;

	public void setCell(DamageCell cell){
		this.cell = cell;
	}

	public void setList(ListView<ArmorSet> list) {
		this.list = list;
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
		System.out.println("Can't let you do that, starfox.");
	}
}
