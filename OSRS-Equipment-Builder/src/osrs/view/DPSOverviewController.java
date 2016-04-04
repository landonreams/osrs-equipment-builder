package osrs.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import osrs.MainApp;
import osrs.model.data.ArmorStats;
import osrs.model.data.Levels;
import osrs.model.npc.ArmorSet;
import osrs.model.npc.NPC;
import osrs.model.npc.Player;

public class DPSOverviewController {
	private ObservableList<ArmorSet> armorList = FXCollections.observableArrayList();
	@FXML
	private ListView<ArmorSet> listView;

	@FXML
	private TextField txtMagic;
	@FXML
	private TextField txtDefence;
	@FXML
	private TextField txtDStab;
	@FXML
	private TextField txtDSlash;
	@FXML
	private TextField txtDCrush;
	@FXML
	private TextField txtDRange;
	@FXML
	private TextField txtDMagic;

	private NPC target;
	private MainApp mainApp;
	private ObservableList<Player> playerList;


	@FXML
	private void initialize() {

		listView.setCellFactory(param -> {
			DamageCell dc = new DamageCell(listView);
			dc.setMainApp(mainApp);
			dc.setTarget(target);
			return dc;
		});
		//listView.setStyle("-fx-background-insets: 0 ;");
		listView.setItems(armorList);

		txtMagic.textProperty().addListener((ovservable, oldVal, newVal) -> {
			Integer val;
			try {
				val = Integer.parseInt(newVal);
			} catch (Exception e) {
				val = null;
			}

			if(val != null)
				target.setLevel(Levels.MAGIC, val);
		});

		txtDefence.textProperty().addListener((ovservable, oldVal, newVal) -> {
			Integer val;
			try {
				val = Integer.parseInt(newVal);
			} catch (Exception e) {
				val = null;
			}

			if(val != null)
				target.setLevel(Levels.DEFENCE, val);
		});

		txtDStab.textProperty().addListener((ovservable, oldVal, newVal) -> {
			Integer val;
			try {
				val = Integer.parseInt(newVal);
			} catch (Exception e) {
				val = null;
			}

			if(val != null)
				target.setStat(ArmorStats.DSTAB, val);
		});

		txtDSlash.textProperty().addListener((ovservable, oldVal, newVal) -> {
			Integer val;
			try {
				val = Integer.parseInt(newVal);
			} catch (Exception e) {
				val = null;
			}

			if(val != null)
				target.setStat(ArmorStats.DSLASH, val);
		});

		txtDCrush.textProperty().addListener((ovservable, oldVal, newVal) -> {
			Integer val;
			try {
				val = Integer.parseInt(newVal);
			} catch (Exception e) {
				val = null;
			}

			if(val != null)
				target.setStat(ArmorStats.DCRUSH, val);
		});

		txtDRange.textProperty().addListener((ovservable, oldVal, newVal) -> {
			Integer val;
			try {
				val = Integer.parseInt(newVal);
			} catch (Exception e) {
				val = null;
			}

			if(val != null) {
				target.setStat(ArmorStats.DRANGE, val);

			}
		});

		txtDMagic.textProperty().addListener((ovservable, oldVal, newVal) -> {
			Integer val;
			try {
				val = Integer.parseInt(newVal);
			} catch (Exception e) {
				val = null;
			}

			if(val != null)
				target.setStat(ArmorStats.DMAGIC, val);
		});


	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public DPSOverviewController() {

	}

	public void setTarget(NPC target){
		this.target = target;
	}

	public ObservableList<ArmorSet> getArmorList() { return armorList; }

	@FXML
	private void handleNew() {
		if(mainApp.playerListIsEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Player list is empty");
			alert.setContentText("Armor sets need a player to wear them!\nPlease create a player in the Player Manager.");

			alert.showAndWait();
			return;
		}
		System.out.println("ADDING NEW!!!");
		armorList.add(new ArmorSet());
	}
}
