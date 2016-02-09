package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.sort.RowFilters;

import com.osrs.items.Equippable;
import com.osrs.items.ItemDatabase;
import com.osrs.items.ItemFilter;
import com.osrs.items.SlotType;
import com.osrs.items.StatType;

public class ChangeEquipPopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233870953094312158L;
	private JPanel contentPane;
	private ItemDatabase db;
	private JXTable itemTable;
	private String mostRecent;
	private JTextField textField;
	private JXSearchField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeEquipPopup frame = new ChangeEquipPopup(SlotType.MAINHAND, ItemFilter.BIS);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** 
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public ChangeEquipPopup(SlotType slot, ItemFilter filter) {
		db = new ItemDatabase();
		mostRecent = "Abyssal whip";
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		itemTable = new JXTable();
		itemTable.setEditable(false);
		itemTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item name", "Slot", "Stab Atk", "Slash Atk", "Crush Atk", "Magic Atk", "Range Atk", "Stab Def", "Slash Def", "Crush Def", "Magic Def", "Range Def", "Melee Str", "Range Str", "Magic Dmg", "Prayer"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, String.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		String[] itemNames = db.getAllInSlot(slot, filter);
		Equippable[] items = db.getAll(itemNames);
		
		for(Equippable item : items){
			int[] stats = item.getStats();
			((DefaultTableModel)itemTable.getModel()).addRow( new Object[] {
					item.getName(),
					item.getSlot().toString(),
					stats[StatType.ACC_STAB.index],
					stats[StatType.ACC_SLASH.index],
					stats[StatType.ACC_CRUSH.index],
					stats[StatType.ACC_MAGE.index],
					stats[StatType.ACC_RANGE.index],
					stats[StatType.DEF_STAB.index],
					stats[StatType.DEF_SLASH.index],
					stats[StatType.DEF_CRUSH.index],
					stats[StatType.DEF_MAGE.index],
					stats[StatType.DEF_RANGE.index],
					stats[StatType.MSC_MELEE.index],
					stats[StatType.MSC_RANGE.index],
					stats[StatType.MSC_MAGE.index] + "%",
					stats[StatType.MSC_PRAYER.index]
			});
		}
		
		itemTable.setHighlighters(HighlighterFactory.createSimpleStriping());
		itemTable.setColumnControlVisible(true);
		itemTable.packAll();
		itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				
				if(itemTable.getSelectedRow() > -1){
					mostRecent = (String) itemTable.getValueAt(itemTable.getSelectedRow(), 0);
				}
				
			}
			
		});
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(itemTable);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		JLabel lblSearch = new JLabel("Search");
		panel.add(lblSearch);
		
		searchField = new JXSearchField();
		panel.add(searchField);
		searchField.setColumns(20);
		searchField.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e){
				String rowFilter = searchField.getText().trim();
				DefaultRowSorter rs = (DefaultRowSorter)itemTable.getRowSorter();
				rs.setRowFilter(rowFilter.length() > 0 ? RowFilters.regexFilter("(?i).*?"+rowFilter+".*?", 0, 1, 2) : null);
			}
		});
		panel.add(btnConfirm);
		
		JButton btnClearSlot = new JButton("Clear Slot");
		btnClearSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostRecent = "Empty";
				setVisible(false);
				dispose();
			}
		});
		panel.add(btnClearSlot);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostRecent = "";
				setVisible(false);
				dispose();
			}
		});
		panel.add(btnCancel);
	}
	
	public String showDialog(){
		setVisible(true);
		return mostRecent;
	}

}
