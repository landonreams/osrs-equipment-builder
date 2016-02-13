package com.osrs.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.osrs.npc.Player;

public class MainWindow {

	private JFrame frame;
	
	private Player player1, player2;
	private JPanel left;
	private JPanel middle;
	private JPanel right;
	
	private static final InterfaceMode MODE_DEFAULT = InterfaceMode.DPS;
	
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
		player2 = new Player();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 850, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{261, 261, 261, 0};
		gridBagLayout.rowHeights = new int[]{540, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		
		middle = new JPanel();
		GridBagConstraints gbc_middle = new GridBagConstraints();
		gbc_middle.insets = new Insets(0, 0, 5, 5);
		gbc_middle.fill = GridBagConstraints.BOTH;
		gbc_middle.gridx = 1;
		gbc_middle.gridy = 0;
		frame.getContentPane().add(middle, gbc_middle);
		
		right = new JPanel();
		GridBagConstraints gbc_right = new GridBagConstraints();
		gbc_right.insets = new Insets(0, 0, 5, 0);
		gbc_right.fill = GridBagConstraints.BOTH;
		gbc_right.gridx = 2;
		gbc_right.gridy = 0;
		frame.getContentPane().add(right, gbc_right);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
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
}
