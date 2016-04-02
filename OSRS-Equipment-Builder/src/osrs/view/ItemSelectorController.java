package osrs.view;

import java.util.Arrays;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import osrs.model.data.ArmorStats;
import osrs.model.data.ItemDatabase;
import osrs.model.npc.Item;

public class ItemSelectorController {


	@FXML
	private TextField filterField;

	@FXML
	private TableView<Item> itemTable;
	@FXML
	private TableColumn<Item, String> nameColumn;
	@FXML
	private TableColumn<Item, String> slotColumn;
	@FXML
	private TableColumn<Item, Integer> astabColumn;
	@FXML
	private TableColumn<Item, Integer> aslashColumn;
	@FXML
	private TableColumn<Item, Integer> acrushColumn;
	@FXML
	private TableColumn<Item, Integer> amagicColumn;
	@FXML
	private TableColumn<Item, Integer> arangeColumn;
	@FXML
	private TableColumn<Item, Integer> dstabColumn;
	@FXML
	private TableColumn<Item, Integer> dslashColumn;
	@FXML
	private TableColumn<Item, Integer> dcrushColumn;
	@FXML
	private TableColumn<Item, Integer> dmagicColumn;
	@FXML
	private TableColumn<Item, Integer> drangeColumn;
	@FXML
	private TableColumn<Item, Integer> strColumn;
	@FXML
	private TableColumn<Item, Integer> rstrColumn;
	@FXML
	private TableColumn<Item, Integer> mdmgColumn;
	@FXML
	private TableColumn<Item, Integer> prayColumn;

	private boolean okPressed = false;
	private Stage dialogStage;

	private ObservableList<Item> itemList = FXCollections.observableArrayList();

	public ItemSelectorController() { }

	public void setSearch(ItemDatabase.Search search) {
		ItemDatabase db = ItemDatabase.getInstance();

		Item[] list = db.executeSearch(search);

		itemList.addAll(Arrays.asList(list));

		setupSorting();
	}

	private void setupSorting() {
		FilteredList<Item> filteredData = new FilteredList<>(itemList, p -> true);

		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if(newValue == null || newValue.isEmpty() )
					return true;

				if(item.getName().toLowerCase().contains(newValue.toLowerCase())){
					return true;
				} else {
					return false;
				}

			});
		});

		SortedList<Item> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(itemTable.comparatorProperty());

		itemTable.setItems(sortedData);

	}

	@FXML
	private void initialize() {

		nameColumn.setCellValueFactory(cd -> cd.getValue().nameProperty());
		slotColumn.setCellValueFactory(cd ->
			new ReadOnlyStringWrapper(cd.getValue().slotProperty().get().toString()));

		astabColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.ASTAB).asObject());
		aslashColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.ASLASH).asObject());
		acrushColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.ACRUSH).asObject());
		arangeColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.ARANGE).asObject());
		amagicColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.AMAGIC).asObject());

		dstabColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.DSTAB).asObject());
		dslashColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.DSLASH).asObject());
		dcrushColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.DCRUSH).asObject());
		drangeColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.DRANGE).asObject());
		dmagicColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.DMAGIC).asObject());

		strColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.STR).asObject());
		rstrColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.RSTR).asObject());
		mdmgColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.MDMG).asObject());
		prayColumn.setCellValueFactory(cd -> cd.getValue().statsProperty(ArmorStats.PRAYER).asObject());


	}

	@FXML
	private void handleOk(){
		okPressed = true;
		dialogStage.close();
	}

	@FXML
	private void handleCancel(){
		okPressed = false;
		dialogStage.close();
	}

	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}

	public Item getSelection() {
		if(okPressed) {
			return itemTable.getSelectionModel().getSelectedItem();
		} else {
			return null;
		}
	}
}
