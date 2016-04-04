package osrs.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import osrs.MainApp;
import osrs.model.data.ArmorStats;
import osrs.model.data.CombatStyle;
import osrs.model.data.ItemDatabase;
import osrs.model.data.Slot;
import osrs.model.npc.ArmorSet;
import osrs.model.npc.Item;
import osrs.model.npc.Player;

public class ArmorSetOverviewController {

	private ObservableList<Player>   buildList = FXCollections.observableArrayList();

	@FXML
	private TextField nameField;
	@FXML
	private Label lblHead;
	@FXML
	private Label lblBody;
	@FXML
	private Label lblLegs;
	@FXML
	private Label lblBoots;
	@FXML
	private Label lblGloves;
	@FXML
	private Label lblCape;
	@FXML
	private Label lblNeck;
	@FXML
	private Label lblRing;
	@FXML
	private Label lblAmmo;
	@FXML
	private Label lblWeapon;
	@FXML
	private Label lblShield;


	@FXML
	private Label lblAStab;
	@FXML
	private Label lblASlash;
	@FXML
	private Label lblACrush;
	@FXML
	private Label lblARanged;
	@FXML
	private Label lblAMagic;
	@FXML
	private Label lblDStab;
	@FXML
	private Label lblDSlash;
	@FXML
	private Label lblDCrush;
	@FXML
	private Label lblDRanged;
	@FXML
	private Label lblDMagic;
	@FXML
	private Label lblStr;
	@FXML
	private Label lblRStr;
	@FXML
	private Label lblMDmg;
	@FXML
	private Label lblPrayer;

	@FXML
	private StackPane stylePaneHolder;

	@FXML
	private ComboBox<Player> playerBox;

	@FXML
	private ChoiceBox<CombatStyle> styleBox;

	private Player currentPlayer;

	private ArmorSet armorSet;
	private MainApp  mainApp;

	private Stage dialogStage;

	private AnchorPane meleePane, rangedPane, magicPane;

	private MeleeOptionsPaneController meleePaneController;
	private RangedOptionsPaneController rangedPaneController;
	private MagicOptionsPaneController magicPaneController;

	@FXML
	private void initialize() {
		nameField.textProperty().addListener((observable, oldValue, newValue) -> {
			armorSet.setName(newValue);
		});

		playerBox.setItems(buildList);

		playerBox.setConverter(new StringConverter<Player>() {
			@Override
			public String toString(Player player) {
				if(player == null) {
					return "";
				} else {
					return player.getName();
				}
			}

			@Override
			public Player fromString(String str) {
				for(Player p : buildList) {
					if(p.getName().equals(str))
						return p;
				}
				return null;
			}
		});

		styleBox.setItems(FXCollections.observableArrayList(CombatStyle.values()));
		styleBox.getSelectionModel().select(CombatStyle.MELEE);

		styleBox.setConverter(new StringConverter<CombatStyle>() {

			@Override
			public String toString(CombatStyle object) {
				if(object != null)
					return object.toString();
				else
					return null;
			}

			@Override
			public CombatStyle fromString(String string) {
				for(CombatStyle s : CombatStyle.values()) {
					if(s.toString().equals(string))
						return s;
				}
				return null;
			}
		});

		styleBox.valueProperty().addListener((observable, oldVal, newVal) -> {
			displayStylePane(newVal);
		});

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MeleeOptionsPane.fxml"));
			meleePane = (AnchorPane) loader.load();
			meleePaneController = loader.getController();
		} catch (Exception e) {
			System.out.println("Unable to create Melee Options Pane!");
			e.printStackTrace();
		}

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RangedOptionsPane.fxml"));
			rangedPane = (AnchorPane) loader.load();
			rangedPaneController = loader.getController();
		} catch (Exception e) {
			System.out.println("Unable to create Ranged Options Pane!");
			e.printStackTrace();
		}

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MagicOptionsPane.fxml"));
			magicPane = (AnchorPane) loader.load();
			magicPaneController = loader.getController();
		} catch (Exception e) {
			System.out.println("Unable to create Magic Options Pane!");
			e.printStackTrace();
		}

		displayStylePane(CombatStyle.MELEE);
	}

	private void displayStylePane(CombatStyle style) {
		stylePaneHolder.getChildren().clear();

		AnchorPane selected = null;

		switch(style) {
		case MELEE: selected = meleePane; break;
		case RANGED: selected = rangedPane; break;
		case MAGIC: selected = magicPane; break;
		}

		stylePaneHolder.getChildren().add(selected);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		buildList.clear();
		buildList.setAll(mainApp.getPlayerData());
	}

	public void setArmorSet(ArmorSet armorSet) {
		this.armorSet = armorSet;
		updateStats();
		updateOthers();
	}

	public Player getBuild() {
		return playerBox.getSelectionModel().getSelectedItem();
	}

	public void setPlayer(Player p) {
		try {
			playerBox.getSelectionModel().select(p);
		} catch (Exception e) {

		}
	}

	@FXML
	private void handleAmmo() {
		Item item = handleGenericButton(Slot.AMMO);
		lblAmmo.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleBody() {
		Item item = handleGenericButton(Slot.BODY);
		lblBody.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleBoots() {
		Item item = handleGenericButton(Slot.BOOTS);
		lblBoots.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleCape() {
		Item item = handleGenericButton(Slot.CAPE);
		lblCape.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleHands() {
		Item item = handleGenericButton(Slot.HANDS);
		lblGloves.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleHead() {
		Item item = handleGenericButton(Slot.HEAD);
		lblHead.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleLegs() {
		Item item = handleGenericButton(Slot.LEGS);
		lblLegs.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleNeck() {
		Item item = handleGenericButton(Slot.NECK);
		lblNeck.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleRing() {
		Item item = handleGenericButton(Slot.RING);
		lblRing.setText(item == null ? "None" : item.getName());
	}

	@FXML
	private void handleShield() {
		Item item = handleGenericButton(Slot.SHIELD);
		lblShield.setText(item == null ? "None" : item.getName());

		Item iWeapon = armorSet.getItem(Slot.WEAPON);
		lblWeapon.setText(iWeapon == null ? "None" : iWeapon.getName());
	}

	@FXML
	private void handleWeapon() {
		Item item = handleGenericButton(Slot.WEAPON);
		lblWeapon.setText(item == null ? "None" : item.getName());

		Item iShield = armorSet.getItem(Slot.SHIELD);
		lblShield.setText(iShield == null ? "None" : iShield.getName());
	}

	private Item handleGenericButton(Slot slot) {
		ItemDatabase.Search search = new ItemDatabase.Search().slot(slot);

		Item i = getItemFromSearch(search, slot);
		if(i == null)
			armorSet.clear(slot);
		else
			armorSet.equip(i);

		updateStats();
		return i;
	}

	private Item getItemFromSearch(ItemDatabase.Search search, Slot slot) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ItemSelector.fxml"));
			AnchorPane itemSelector = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Item Selector");
			dialogStage.initModality(Modality.APPLICATION_MODAL);

			ItemSelectorController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setSearch(search);

			dialogStage.setScene(new Scene(itemSelector));

			dialogStage.showAndWait();

			if(controller.clearPressed())
				armorSet.clear(slot);

			if(controller.okPressed())
				return controller.getSelection();
			else
				return armorSet.getItem(slot);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void updateOthers() {
		Item iAmmo = armorSet.getItem(Slot.AMMO);
		lblAmmo.setText(iAmmo == null ? "None" : iAmmo.getName());

		Item iHead = armorSet.getItem(Slot.HEAD);
		lblHead.setText(iHead == null ? "None" : iHead.getName());

		Item iBody = armorSet.getItem(Slot.BODY);
		lblBody.setText(iBody == null ? "None" : iBody.getName());

		Item iLegs = armorSet.getItem(Slot.LEGS);
		lblLegs.setText(iLegs == null ? "None" : iLegs.getName());

		Item iGloves = armorSet.getItem(Slot.HANDS);
		lblGloves.setText(iGloves == null ? "None" : iGloves.getName());

		Item iBoots = armorSet.getItem(Slot.BOOTS);
		lblBoots.setText(iBoots == null ? "None" : iBoots.getName());

		Item iCape = armorSet.getItem(Slot.CAPE);
		lblCape.setText(iCape == null ? "None" : iCape.getName());

		Item iRing = armorSet.getItem(Slot.RING);
		lblRing.setText(iRing == null ? "None" : iRing.getName());

		Item iNeck = armorSet.getItem(Slot.NECK);
		lblNeck.setText(iNeck == null ? "None" : iNeck.getName());

		Item iWeapon = armorSet.getItem(Slot.WEAPON);
		lblWeapon.setText(iWeapon == null ? "None" : iWeapon.getName());

		Item iShield = armorSet.getItem(Slot.SHIELD);
		lblShield.setText(iShield == null ? "None" : iShield.getName());

		nameField.setText(armorSet.getName() == null ? "" : armorSet.getName());
	}

	private void updateStats() {

		Integer astab = armorSet.getStat(ArmorStats.ASTAB);
		Integer aslash = armorSet.getStat(ArmorStats.ASLASH);
		Integer acrush = armorSet.getStat(ArmorStats.ACRUSH);
		Integer arange = armorSet.getStat(ArmorStats.ARANGE);
		Integer amagic = armorSet.getStat(ArmorStats.AMAGIC);

		Integer dstab = armorSet.getStat(ArmorStats.DSTAB);
		Integer dslash = armorSet.getStat(ArmorStats.DSLASH);
		Integer dcrush = armorSet.getStat(ArmorStats.DCRUSH);
		Integer drange = armorSet.getStat(ArmorStats.DRANGE);
		Integer dmagic = armorSet.getStat(ArmorStats.DMAGIC);

		Integer str = armorSet.getStat(ArmorStats.STR);
		Integer rstr = armorSet.getStat(ArmorStats.RSTR);
		Integer mdmg = armorSet.getStat(ArmorStats.MDMG);
		Integer prayer = armorSet.getStat(ArmorStats.PRAYER);

		Integer[] stats = new Integer[] { astab, aslash, acrush, arange, amagic, dstab, dslash, dcrush, drange, dmagic, str, rstr, mdmg, prayer };

		Label[] labels = new Label[] {
				lblAStab, lblASlash, lblACrush, lblARanged, lblAMagic,
				lblDStab, lblDSlash, lblDCrush, lblDRanged, lblDMagic,
				lblStr, lblRStr, lblMDmg, lblPrayer
		};

		if(lblAStab == null)
			System.out.println("ERROR: LABELS ARE NULL!");

		for(int i = 0; i < labels.length; i++) {
			if(stats[i] == null)
				stats[i] = 0;
			if(labels[i] == null)
				System.out.println("LABL NUL");
			else
			labels[i].setText(String.format("%s%d", stats[i] > -1 ? "+" : "", stats[i]));
		}
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleOk() {
		dialogStage.close();
	}
}
