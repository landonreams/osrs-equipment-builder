package gui.copy;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.osrs.items.ItemDatabase;
import com.osrs.items.ItemFilter;
import com.osrs.items.SlotType;

import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JList;
import javax.swing.JButton;

public class MainWindow {

	private JFrame frame;
	private ItemDatabase db;
	private JComboBox filterMode;
	
	private DefaultComboBoxModel dcbm_ring, dcbm_head, dcbm_gloves, dcbm_cape, dcbm_boots, dcbm_body, dcbm_legs, dcbm_ammo, dcbm_neck, dcbm_mainhand, dcbm_offhand;
	
	private BoundsPopupMenuListener bpm_listener;
	
	//private JComboBox drop_head, drop_body, drop_legs, drop_gloves, drop_boots, drop_mainhand, drop_offhand, drop_neck, drop_cape, drop_ammo;
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		bpm_listener = new BoundsPopupMenuListener(true, false);
		ItemFilter filter = ItemFilter.BIS;
		db = new ItemDatabase();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{261, 261, 261, 0};
		gridBagLayout.rowHeights = new int[]{540, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		playerPanel.add(tabbedPane);
		
		JPanel playerEquipment = new JPanel();
		tabbedPane.addTab("Equipment", null, playerEquipment, null);
		GridBagLayout gbl_playerEquipment = new GridBagLayout();
		gbl_playerEquipment.columnWidths = new int[]{80, 0, 0, 0};
		gbl_playerEquipment.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_playerEquipment.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_playerEquipment.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		playerEquipment.setLayout(gbl_playerEquipment);
		
		JButton btnHead = new JButton("Head");
		GridBagConstraints gbc_btnHead = new GridBagConstraints();
		gbc_btnHead.insets = new Insets(0, 0, 5, 5);
		gbc_btnHead.gridx = 1;
		gbc_btnHead.gridy = 1;
		playerEquipment.add(btnHead, gbc_btnHead);
		
		JButton btnCape = new JButton("Cape");
		GridBagConstraints gbc_btnCape = new GridBagConstraints();
		gbc_btnCape.insets = new Insets(0, 0, 5, 5);
		gbc_btnCape.gridx = 0;
		gbc_btnCape.gridy = 2;
		playerEquipment.add(btnCape, gbc_btnCape);
		
		JButton btnNeck = new JButton("Neck");
		GridBagConstraints gbc_btnNeck = new GridBagConstraints();
		gbc_btnNeck.insets = new Insets(0, 0, 5, 5);
		gbc_btnNeck.gridx = 1;
		gbc_btnNeck.gridy = 2;
		playerEquipment.add(btnNeck, gbc_btnNeck);
		
		JButton btnAmmo = new JButton("Ammo");
		GridBagConstraints gbc_btnAmmo = new GridBagConstraints();
		gbc_btnAmmo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAmmo.gridx = 2;
		gbc_btnAmmo.gridy = 2;
		playerEquipment.add(btnAmmo, gbc_btnAmmo);
		
		JButton btnMainhand = new JButton("Mainhand");
		GridBagConstraints gbc_btnMainhand = new GridBagConstraints();
		gbc_btnMainhand.insets = new Insets(0, 0, 5, 5);
		gbc_btnMainhand.gridx = 0;
		gbc_btnMainhand.gridy = 3;
		playerEquipment.add(btnMainhand, gbc_btnMainhand);
		
		JButton btnBody = new JButton("Body");
		GridBagConstraints gbc_btnBody = new GridBagConstraints();
		gbc_btnBody.insets = new Insets(0, 0, 5, 5);
		gbc_btnBody.gridx = 1;
		gbc_btnBody.gridy = 3;
		playerEquipment.add(btnBody, gbc_btnBody);
		
		JButton btnOffhand = new JButton("Offhand");
		GridBagConstraints gbc_btnOffhand = new GridBagConstraints();
		gbc_btnOffhand.insets = new Insets(0, 0, 5, 0);
		gbc_btnOffhand.gridx = 2;
		gbc_btnOffhand.gridy = 3;
		playerEquipment.add(btnOffhand, gbc_btnOffhand);
		
		JButton btnLegs = new JButton("Legs");
		GridBagConstraints gbc_btnLegs = new GridBagConstraints();
		gbc_btnLegs.insets = new Insets(0, 0, 5, 5);
		gbc_btnLegs.gridx = 1;
		gbc_btnLegs.gridy = 4;
		playerEquipment.add(btnLegs, gbc_btnLegs);
		
		JButton btnGloves = new JButton("Gloves");
		GridBagConstraints gbc_btnGloves = new GridBagConstraints();
		gbc_btnGloves.insets = new Insets(0, 0, 5, 5);
		gbc_btnGloves.gridx = 0;
		gbc_btnGloves.gridy = 5;
		playerEquipment.add(btnGloves, gbc_btnGloves);
		
		JButton btnBoots = new JButton("Boots");
		GridBagConstraints gbc_btnBoots = new GridBagConstraints();
		gbc_btnBoots.insets = new Insets(0, 0, 5, 5);
		gbc_btnBoots.gridx = 1;
		gbc_btnBoots.gridy = 5;
		playerEquipment.add(btnBoots, gbc_btnBoots);
		
		JButton btnRing = new JButton("Ring");
		GridBagConstraints gbc_btnRing = new GridBagConstraints();
		gbc_btnRing.insets = new Insets(0, 0, 5, 0);
		gbc_btnRing.gridx = 2;
		gbc_btnRing.gridy = 5;
		playerEquipment.add(btnRing, gbc_btnRing);
		
		JLabel lblFilteringMode = new JLabel("Filtering");
		GridBagConstraints gbc_lblFilteringMode = new GridBagConstraints();
		gbc_lblFilteringMode.anchor = GridBagConstraints.EAST;
		gbc_lblFilteringMode.insets = new Insets(0, 0, 0, 5);
		gbc_lblFilteringMode.gridx = 0;
		gbc_lblFilteringMode.gridy = 19;
		playerEquipment.add(lblFilteringMode, gbc_lblFilteringMode);
		
		filterMode = new JComboBox(new String[] {"No filtering", "No cosmetics", "Commonly used"} );
		filterMode.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event){
				if(event.getStateChange() == ItemEvent.SELECTED) {
					Object item = event.getItem();
					
					if(item.equals("No filtering"))
						refilter(ItemFilter.NONE);
					else if(item.equals("No cosmetics"))
						refilter(ItemFilter.NO_COSMETIC);
					else
						refilter(ItemFilter.BIS);
				}
			}
		});
		filterMode.setSelectedIndex(2);
		GridBagConstraints gbc_filterMode = new GridBagConstraints();
		gbc_filterMode.gridwidth = 2;
		gbc_filterMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterMode.gridx = 1;
		gbc_filterMode.gridy = 19;
		playerEquipment.add(filterMode, gbc_filterMode);
		
		JPanel playerCombatInfo = new JPanel();
		tabbedPane.addTab("Combat Information", null, playerCombatInfo, null);
		GridBagConstraints gbc_playerPanel = new GridBagConstraints();
		gbc_playerPanel.weightx = 0.33;
		gbc_playerPanel.fill = GridBagConstraints.BOTH;
		gbc_playerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_playerPanel.gridx = 0;
		gbc_playerPanel.gridy = 0;
		frame.getContentPane().add(playerPanel, gbc_playerPanel);

		JPanel dataPanel = new JPanel();
		dataPanel.setBorder(null);
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
		GridBagConstraints gbc_dataPanel = new GridBagConstraints();
		gbc_dataPanel.fill = GridBagConstraints.BOTH;
		gbc_dataPanel.insets = new Insets(0, 0, 0, 5);
		gbc_dataPanel.gridx = 1;
		gbc_dataPanel.gridy = 0;
		frame.getContentPane().add(dataPanel, gbc_dataPanel);
		
		JPanel targetPanel = new JPanel();
		targetPanel.setLayout(new BoxLayout(targetPanel, BoxLayout.X_AXIS));
		GridBagConstraints gbc_targetPanel = new GridBagConstraints();
		gbc_targetPanel.fill = GridBagConstraints.BOTH;
		gbc_targetPanel.gridx = 2;
		gbc_targetPanel.gridy = 0;
		frame.getContentPane().add(targetPanel, gbc_targetPanel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmAddCustomItem = new JMenuItem("Add custom item...");
		mnSettings.add(mntmAddCustomItem);
		
		
		//pack();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void refilter(ItemFilter f){
	}
}
