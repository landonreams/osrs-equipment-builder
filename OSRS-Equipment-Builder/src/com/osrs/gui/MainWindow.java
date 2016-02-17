package com.osrs.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.osrs.items.SlotType;
import com.osrs.levels.LevelType;
import com.osrs.npc.DataSaver;
import com.osrs.npc.NPC;
import com.osrs.npc.Player;

public class MainWindow {

	private JFrame frame;
	
	private Player player1;
	private NPC    npc1;
	private JPanel left;
	private JPanel middle;
	private JPanel right;
	
	private static final InterfaceMode MODE_DEFAULT = InterfaceMode.DPS;
	
	public static final boolean DEBUG = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		player1 = new Player();
		npc1    = new NPC();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{261, 0, 261, 0, 261, 0};
		gridBagLayout.rowHeights = new int[]{540, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		left = new JPanel();
		GridBagConstraints gbc_left = new GridBagConstraints();
		gbc_left.insets = new Insets(0, 0, 5, 5);
		gbc_left.fill = GridBagConstraints.BOTH;
		gbc_left.gridx = 0;
		gbc_left.gridy = 0;
		frame.getContentPane().add(left, gbc_left);
		left.setLayout(new CardLayout(0, 0));
		
		PlayerTabbedPanel playerTabbedOne = new PlayerTabbedPanel(player1);
		left.add(playerTabbedOne, "PlayerOne");
		
		EquipmentPanel equipmentOne = new EquipmentPanel(player1);
		left.add(equipmentOne, "EquipmentOne");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.VERTICAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 0;
		frame.getContentPane().add(separator_1, gbc_separator_1);
		
		middle = new JPanel();
		GridBagConstraints gbc_middle = new GridBagConstraints();
		gbc_middle.insets = new Insets(0, 0, 5, 5);
		gbc_middle.fill = GridBagConstraints.BOTH;
		gbc_middle.gridx = 2;
		gbc_middle.gridy = 0;
		frame.getContentPane().add(middle, gbc_middle);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.VERTICAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 3;
		gbc_separator.gridy = 0;
		frame.getContentPane().add(separator, gbc_separator);
		
		right = new JPanel();
		GridBagConstraints gbc_right = new GridBagConstraints();
		gbc_right.insets = new Insets(0, 0, 5, 0);
		gbc_right.fill = GridBagConstraints.BOTH;
		gbc_right.gridx = 4;
		gbc_right.gridy = 0;
		frame.getContentPane().add(right, gbc_right);
		right.setLayout(new CardLayout(0, 0));
		
		NPCBuilder builder = new NPCBuilder(npc1);
		right.add(builder, "name_98235514946799");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSavePlayer = new JMenuItem("Save Player");
		mntmSavePlayer.addActionListener(e -> {
			
			warnUnstable();
			
			String path = chooseFile("plr");
			if(!path.equals("")){
				int[] lvls = player1.getLevels();
				int[] eqp  = player1.getEquipment();
				int[] data = DataSaver.concat(lvls,  eqp);
				
				DataSaver.save(path, data);
			}
		});
		mnFile.add(mntmSavePlayer);
		
		JMenuItem mntmLoadPlayer = new JMenuItem("Load Player");
		mntmLoadPlayer.addActionListener(e -> {
			
			warnUnstable();
			
			String path = chooseFile("plr");
			if(!path.endsWith(".plr"))
				path = path + ".plr";
			if(!path.equals("")){
				File f = new File(path);
				if(f.exists()){
					int[] data = DataSaver.read(path);

					if(data.length != (LevelType.NUM_LEVELS + SlotType.NUM_SLOTS))
						throw new IllegalArgumentException("File malformed!");
					
					int[] lvls = new int[LevelType.NUM_LEVELS];
					for(int i = 0; i < LevelType.NUM_LEVELS; i++){
						lvls[i] = data[i];
					}
					
					int[] eqp = new int[SlotType.NUM_SLOTS];
					for(int i = 0; i < SlotType.NUM_SLOTS; i++){
						eqp[i] = data[i + LevelType.NUM_LEVELS];
					}
					
					player1.setLevels(lvls);
					player1.equip(eqp);
					
					playerTabbedOne.update(player1);
				}
			}
		});
		
		DPSViewer viewer = new DPSViewer(player1, npc1);
		middle.add(viewer);
		
		mnFile.add(mntmLoadPlayer);
		
		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);
		
		JMenu mnSettings = new JMenu("Custom");
		menuBar.add(mnSettings);
		
		JMenuItem mntmAddCustomItem = new JMenuItem("Add custom item...");
		mntmAddCustomItem.addActionListener(e -> {
			CustomItemManager cim = new CustomItemManager();
			cim.display();
		});
		mnSettings.add(mntmAddCustomItem);
		
		JMenuItem mntmDeleteCustomItem = new JMenuItem("Delete custom items");
		mntmDeleteCustomItem.addActionListener(e -> {
			CustomItemManager cim = new CustomItemManager();
			int ans = JOptionPane.showConfirmDialog(frame, 
					"Are you sure you wish to delete all custom items? This cannot be undone!", 
					"Delete all custom items", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if(ans==0) cim.deleteAll();
		});
		mnSettings.add(mntmDeleteCustomItem);
		
		JMenu mnMode = new JMenu("Mode");
		menuBar.add(mnMode);
		
		JMenuItem mntmDpsCalculation = new JMenuItem("DPS Calculation");
		mntmDpsCalculation.addActionListener(e -> {
			setMode(InterfaceMode.DPS);
		});
		mnMode.add(mntmDpsCalculation);
		
		JMenuItem mntmEquipmentComparison = new JMenuItem("Equipment Comparison");
		mnMode.add(mntmEquipmentComparison);
		mntmEquipmentComparison.addActionListener(e -> {
			setMode(InterfaceMode.COMPARE);
		});
		
		setMode(MODE_DEFAULT);
	}
	
	private void setMode(InterfaceMode m){
		switch(m){
		case DPS:     ((CardLayout)left.getLayout()).show(left, "PlayerOne"); break;
		case COMPARE: ((CardLayout)left.getLayout()).show(left, "EquipmentOne"); break;
		}
	}
	
	private String chooseFile(String extension){
		String result = "";
		JFileChooser fileChooser = new JFileChooser();
		String description;
		switch(extension){
		case "plr": description = "PLAYER FILES"; break;
		case "eqp": description = "EQUIPMENT FILES"; break;
		default: throw new IllegalArgumentException();
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extension);
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			result = selectedFile.getPath();
		}
		return result;
	}
	
	private void warnUnstable(){
		if(DEBUG) JOptionPane.showMessageDialog(null, "WARNING: This tool is currently unstable! It may not produce the results you expect.");
	}
}
