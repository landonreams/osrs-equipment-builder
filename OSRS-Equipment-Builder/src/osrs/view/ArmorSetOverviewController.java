package osrs.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import osrs.MainApp;
import osrs.model.data.ArmorStats;
import osrs.model.data.ItemDatabase;
import osrs.model.data.Slot;
import osrs.model.npc.ArmorSet;
import osrs.model.npc.Item;
import osrs.model.npc.Player;

public class ArmorSetOverviewController {

	@FXML
	private ObservableList<Player>   buildList;

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

	private ArmorSet armorSet;
	private MainApp  mainApp;

	private Label[] labels = new Label[] {
			lblAStab, lblASlash, lblACrush, lblARanged, lblAMagic,
			lblDStab, lblDSlash, lblDCrush, lblDRanged, lblDMagic,
			lblStr, lblRStr, lblMDmg, lblPrayer
	};

	private Stage dialogStage;

	@FXML
	private void initialize() {
		nameField.textProperty().addListener((observable, oldValue, newValue) -> {
			armorSet.setName(newValue);
		});
	}

	public ArmorSetOverviewController() {
	}

	public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

	public void setArmorSet(ArmorSet armorSet) {
		this.armorSet = armorSet;
		updateStats();
		updateOthers();
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

		Item i = mainApp.getItemFromSearch(search);
		armorSet.equip(i);

		updateStats();
		return i;
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
}
