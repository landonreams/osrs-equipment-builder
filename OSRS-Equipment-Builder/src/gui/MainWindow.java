package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		playerPanel.add(tabbedPane);
		
		JPanel playerEquipment = new JPanel();
		tabbedPane.addTab("Equipment", null, playerEquipment, null);
		GridBagLayout gbl_playerEquipment = new GridBagLayout();
		gbl_playerEquipment.columnWidths = new int[]{0, 0, 0};
		gbl_playerEquipment.rowHeights = new int[]{0, 0};
		gbl_playerEquipment.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_playerEquipment.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		playerEquipment.setLayout(gbl_playerEquipment);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		playerEquipment.add(comboBox, gbc_comboBox);
		
		JPanel playerCombatInfo = new JPanel();
		tabbedPane.addTab("Combat Information", null, playerCombatInfo, null);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setBorder(null);
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
		
		JPanel targetPanel = new JPanel();
		targetPanel.setLayout(new BoxLayout(targetPanel, BoxLayout.X_AXIS));
		frame.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
		frame.getContentPane().add(playerPanel);
		frame.getContentPane().add(dataPanel);
		frame.getContentPane().add(targetPanel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmAddCustomItem = new JMenuItem("Add custom item...");
		mnSettings.add(mntmAddCustomItem);
	}

}
