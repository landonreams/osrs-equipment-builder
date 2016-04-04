package osrs.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

		armorList.add(new ArmorSet());
		armorList.add(new ArmorSet());
		armorList.add(new ArmorSet());

		listView.setCellFactory(param -> new DamageCell(listView, mainApp));
		listView.setStyle("-fx-background-insets: 0 ;");
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

			if(val != null)
				target.setStat(ArmorStats.DRANGE, val);
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
}
