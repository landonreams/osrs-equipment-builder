package osrs;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import osrs.model.npc.NPC;
import osrs.model.npc.Player;
import osrs.view.DPSOverviewController;
import osrs.view.PlayerEditDialogController;
import osrs.view.PlayerOverviewController;
import osrs.view.RootLayoutController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private NPC target;

	private ObservableList<Player>             builds = FXCollections.observableArrayList();
	private ObservableObjectValue<NPC> targetProperty = new SimpleObjectProperty<>(target);

	public MainApp() {
		target = new NPC();

		builds.add(new Player.Builder<>()
				.name("Maxed")
				.attack(99).strength(99).defence(99)
				.ranged(99).magic(99).hitpoints(99).prayer(99).build());

		builds.add(new Player.Builder<>()
				.name("High Level")
				.attack(90)
				.strength(90)
				.defence(90)
				.ranged(90)
				.magic(90)
				.hitpoints(95)
				.prayer(70)
				.build());

		builds.add(new Player.Builder<>()
				.name("Mid Level")
				.attack(70)
				.strength(70)
				.defence(70)
				.ranged(70)
				.magic(70)
				.hitpoints(75)
				.prayer(52)
				.build());

		builds.add(new Player.Builder<>()
				.name("Low Level")
				.attack(50)
				.strength(50)
				.defence(50)
				.ranged(50)
				.magic(55)
				.hitpoints(55)
				.prayer(43)
				.build());

	}

	public ObservableObjectValue<NPC> getTargetData() {
		return targetProperty;
	}

	public ObservableList<Player> getPlayerData() {
		return builds;
	}

	public boolean playerListIsEmpty() {
		return builds.isEmpty();
	}


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Old School Runescape Equipment Builder");

		initRootLayout();

		showDPSOverview();

	//	editArmorSet(new ArmorSet());
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

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

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

			DPSOverviewController controller = loader.getController();
			controller.setTarget(target);
			controller.setMainApp(this);

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
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PlayerOverview.fxml"));
			AnchorPane playerOverview = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Build Manager");
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.initOwner(primaryStage);

			PlayerOverviewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);

			dialogStage.setScene(new Scene(playerOverview));

			dialogStage.showAndWait();

			return controller.getSelected();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NPC getTarget() {
		return target;
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
