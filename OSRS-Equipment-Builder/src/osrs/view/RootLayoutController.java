package osrs.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import osrs.MainApp;

public class RootLayoutController {

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleOpenPlayer() {
		unimplementedMethod();
	}

	@FXML
	private void handleSavePlayer() {
		unimplementedMethod();
	}

	@FXML
	private void handleSaveAsPlayer() {
		unimplementedMethod();
	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Old School Runescape Equipment Builder");
		alert.setHeaderText("About");
		alert.setContentText("Author: Landon Reams\n\nOSRS Equipment Builder");

		alert.showAndWait();
	}

	@FXML
	private void handleManagePlayers() {
		mainApp.showPlayerManager();
	}

	private void unimplementedMethod() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Unimplemented!");
		alert.setHeaderText("Sorry, not done yet.");
		alert.setContentText("Sorry, this hasn't been implemented yet. Check back later!");

		alert.showAndWait();
	}
}
