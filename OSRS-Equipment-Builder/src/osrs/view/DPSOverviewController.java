package osrs.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import osrs.model.npc.ArmorSet;

public class DPSOverviewController {
	private ObservableList<ArmorSet> armorList = FXCollections.observableArrayList();
	@FXML
	private ListView<ArmorSet> listView;

	@FXML
	private void initialize() {

		armorList.add(new ArmorSet());
		armorList.add(new ArmorSet());
		armorList.add(new ArmorSet());

		listView.setCellFactory(param -> new DamageCell( listView ));
		listView.setStyle("-fx-background-insets: 0 ;");
		listView.setItems(armorList);
	}

	public DPSOverviewController() {


	}

	public ObservableList<ArmorSet> getArmorList() { return armorList; }
}
