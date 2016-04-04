package osrs.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import osrs.MainApp;
import osrs.model.npc.ArmorSet;

public class DamageCell extends ListCell<ArmorSet> {


	private AnchorPane pane;
	private SetCompactViewController controller;
	private ListView<ArmorSet> listView;
	private MainApp mainApp;

	public DamageCell( ListView<ArmorSet> listView, MainApp mainApp ) {
		super();

		this.mainApp = mainApp;

		this.listView = listView;

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SetCompactView.fxml"));
			pane = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setCell(this);
			controller.setList(listView);

			if(mainApp == null) {
				System.out.println("ERROR: Could not load main app in DamageCell!");
			} else {
				controller.setMainApp(mainApp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			pane = null;
		}
		setText(null);
	}

	@Override
	public void updateItem(ArmorSet item, boolean empty) {
		super.updateItem(item, empty);
		setEditable(false);
		if(item != null) {
			if(controller != null)
				controller.setArmorSet(item);
			setGraphic(pane);
		} else {
			setGraphic(null);
		}
	}

//	private void editItem() {
//		try {
//
//		} catch (Exception e) {
//
//		}
//	}
}
