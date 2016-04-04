package osrs.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import osrs.MainApp;
import osrs.model.npc.ArmorSet;
import osrs.model.npc.NPC;

public class DamageCell extends ListCell<ArmorSet> {


	private AnchorPane pane;
	private SetCompactViewController controller;
	private ListView<ArmorSet> listView;
	private MainApp mainApp;
	private NPC target;

	public DamageCell( ListView<ArmorSet> listView ) {
		super();

		this.listView = listView;

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SetCompactView.fxml"));
			pane = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setCell(this);
			controller.setList(listView);

		} catch (Exception e) {
			e.printStackTrace();
			pane = null;
		}
		setText(null);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		controller.setMainApp(mainApp);
	}

	public void setTarget(NPC target){
		this.target = target;
		controller.setTarget(target);
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
