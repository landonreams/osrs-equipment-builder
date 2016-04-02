package osrs.view;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import osrs.model.data.ItemDatabase;
import osrs.model.npc.Item;

public class ItemSelectorController {

	@FXML
	private TableView<Item> itemTable;
	@FXML
	private TableColumn<Item, String> nameColumn;
	@FXML
	private TableColumn<Item, String> slotColumn;
	@FXML
	private TableColumn<Item, String> astabColumn;
	@FXML
	private TableColumn<Item, String> aslashColumn;
	@FXML
	private TableColumn<Item, String> acrushColumn;
	@FXML
	private TableColumn<Item, String> amagicColumn;
	@FXML
	private TableColumn<Item, String> arangeColumn;
	@FXML
	private TableColumn<Item, String> dstabColumn;
	@FXML
	private TableColumn<Item, String> dslashColumn;
	@FXML
	private TableColumn<Item, String> dcrushColumn;
	@FXML
	private TableColumn<Item, String> drangeColumn;
	@FXML
	private TableColumn<Item, String> dmagicColumn;
	@FXML
	private TableColumn<Item, String> strColumn;
	@FXML
	private TableColumn<Item, String> rstrColumn;
	@FXML
	private TableColumn<Item, String> mdmgColumn;
	@FXML
	private TableColumn<Item, String> prayerColumn;


	@FXML
	private ObservableList<Item> itemList = FXCollections.observableArrayList();

	public ItemSelectorController(ItemDatabase.Search search) {
		ItemDatabase db = ItemDatabase.getInstance();
		itemList.addAll(Arrays.asList(db.executeSearch(search)));
	}

	@FXML
	private void initialize() {

	}
}
