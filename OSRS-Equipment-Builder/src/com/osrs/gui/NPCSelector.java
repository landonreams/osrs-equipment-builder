package com.osrs.gui;

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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.sort.RowFilters;

import com.osrs.items.StatType;
import com.osrs.levels.LevelType;
import com.osrs.npc.NPC;
import com.osrs.npc.NPCDatabase;

public class NPCSelector extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233870953094312158L;
	private JPanel contentPane;
	private NPCDatabase db;
	private JXTable npcTable;
	private String mostRecent;
	private JXSearchField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NPCSelector frame = new NPCSelector();
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
	public NPCSelector() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		db = new NPCDatabase();
		mostRecent = "Zulrah (Ranged)";
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		npcTable = new JXTable();
		npcTable.setEditable(false);
		npcTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Monster", "Combat", "Hitpoints", "Attack", "Strength", "Defence", "Ranged", "Magic", "Stab Atk", "Slash Atk", "Crush Atk", "Magic Atk", "Range Atk", "Stab Def", "Slash Def", "Crush Def", "Magic Def", "Range Def", "Melee Str", "Range Str"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Float.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		npcTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		npcTable.getColumnModel().getColumn(0).setMinWidth(100);
		npcTable.getColumnModel().getColumn(2).setResizable(false);
		npcTable.getColumnModel().getColumn(3).setResizable(false);
		npcTable.getColumnModel().getColumn(4).setResizable(false);
		npcTable.getColumnModel().getColumn(5).setResizable(false);
		npcTable.getColumnModel().getColumn(6).setResizable(false);
		npcTable.getColumnModel().getColumn(7).setResizable(false);
		
		NPC[] npcs = db.getAll();
		
		for(NPC npc : npcs){
			int[] levels = npc.getLevels();
			int[] stats = npc.getStats();
			
			((DefaultTableModel)npcTable.getModel()).addRow(new Object[] {
					npc.getName(),
					npc.getCombatLevel(),
					levels[LevelType.HITPOINTS.index],
					levels[LevelType.ATTACK.index],
					levels[LevelType.STRENGTH.index],
					levels[LevelType.DEFENCE.index],
					levels[LevelType.RANGED.index],
					levels[LevelType.MAGIC.index],
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
					stats[StatType.MSC_RANGE.index]
			});
		}
		
		npcTable.setHighlighters(HighlighterFactory.createSimpleStriping());
		npcTable.setColumnControlVisible(true);
		npcTable.packAll();
		npcTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		npcTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				
				if(npcTable.getSelectedRow() > -1){
					mostRecent = (String) npcTable.getValueAt(npcTable.getSelectedRow(), 0);
				}
				
			}
			
		});
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(npcTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
				DefaultRowSorter rs = (DefaultRowSorter)npcTable.getRowSorter();
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
		
//		chckbxLiteView = new JCheckBox("Lite View");
//		chckbxLiteView.addActionListener(e -> {
//			if(chckbxLiteView.isSelected()){
//				switchToLite();
//			} else {
//				switchToAdvanced();
//			}
//		});
//		chckbxLiteView.setSelected(true);
//		panel.add(chckbxLiteView);
//		switchToLite();
	}
	
	public String showDialog(){
		setVisible(true);
		return mostRecent;
	}
	
//	public void switchToLite(){
//		TableColumnModel colmod = npcTable.getColumnModel();
//		for(int i = 1; i < 19; i++){
//			npcTable.removeColumn(colmod.getColumn(i));
//		}
//	}
//	
//	public void switchToAdvanced(){
//		TableColumnModel colmod = npcTable.getColumnModel();
//		for(int i = 1; i < 20; i++){
//			npcTable.addColumn(colmod.getColumn(i));
//		}
//	}

}
