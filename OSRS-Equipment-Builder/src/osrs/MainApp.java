package osrs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import osrs.model.npc.Player;
import osrs.view.PlayerEditDialogController;
import osrs.view.PlayerOverviewController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Player> builds = FXCollections.observableArrayList();

	public MainApp() {
		builds.add(new Player.Builder<>()
				.name("Main")
				.attack(99)
				.strength(99)
				.defence(99)
				.ranged(99)
				.hitpoints(99)
				.prayer(99)
				.magic(99)
				.build());

		builds.add(new Player.Builder<>()
				.name("Noob")
				.attack(32)
				.strength(35)
				.defence(29)
				.hitpoints(40)
				.build());

		builds.add(new Player.Builder<>()
				.name("Zerker")
				.attack(60)
				.strength(99)
				.defence(45)
				.ranged(99)
				.magic(94)
				.prayer(52)
				.hitpoints(99)
				.build());
	}

	public ObservableList<Player> getPlayerData() {
		return builds;
	}


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Old School Runescape Equipment Builder");

		initRootLayout();

		showDPSOverview();

		showPlayerManager();
	}

	/**
	 * Initializes root layout.
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the DPS Overview inside the root layout.
	 */
	public void showDPSOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DPSOverview.fxml"));
			AnchorPane dpsOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(dpsOverview);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the player manager and selection window.
	 * @return
	 */
	public Player showPlayerManager() {
		Stage dialog = new Stage();
		dialog.setTitle("Build Manager");
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PlayerOverview.fxml"));
			AnchorPane playerOverview = (AnchorPane) loader.load();

			PlayerOverviewController controller = loader.getController();
			controller.setMainApp(this);

			dialog.setScene(new Scene(playerOverview));

			dialog.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public boolean showPlayerEditDialog(Player player) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PlayerEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Player");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			PlayerEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPlayer(player);

			dialogStage.showAndWait();

			return controller.isOkClicked();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
