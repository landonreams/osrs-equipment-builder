package com.osrs.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.osrs.items.ItemDatabase;

@SuppressWarnings("serial")
public class CustomItemManager extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField itemName;
	private JComboBox<String> itemSlot;
	private JSpinner spnCrushDef;
	private JSpinner spnSlashDef;
	private JSpinner spnStabAtt;
	private JSpinner spnPray;
	private JSpinner spnMageStr;
	private JSpinner spnMeleeStr;
	private JSpinner spnMagicAtt;
	private JSpinner spnCrushAtt;
	private JSpinner spnRangeStr;
	private JSpinner spnRangeDef;
	private JSpinner spnRangeAtt;
	private JSpinner spnStabDef;
	private JSpinner spnSlashAtt;
	private JSpinner spnMageDef;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomItemManager dialog = new CustomItemManager();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void display(){
		this.setVisible(true);
	}
	
	public void dispose(){
		this.setVisible(false);
	}
	
	public void deleteAll(){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(ItemDatabase.URL);
			stmt = conn.prepareStatement("DELETE FROM EQUIPMENT WHERE CUSTOM=TRUE");
			
			stmt.execute();
			
			dispose();
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "An unexpected error occurred creating the item. Contact the author.");
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomItemManager() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Custom Item Creator");
		setBounds(100, 100, 350, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 50, 0, 50, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblItemName = new JLabel("Item Name");
			GridBagConstraints gbc_lblItemName = new GridBagConstraints();
			gbc_lblItemName.anchor = GridBagConstraints.EAST;
			gbc_lblItemName.insets = new Insets(0, 0, 5, 5);
			gbc_lblItemName.gridx = 0;
			gbc_lblItemName.gridy = 0;
			contentPanel.add(lblItemName, gbc_lblItemName);
		}
		{
			itemName = new JTextField();
			GridBagConstraints gbc_itemName = new GridBagConstraints();
			gbc_itemName.gridwidth = 3;
			gbc_itemName.insets = new Insets(0, 0, 5, 0);
			gbc_itemName.fill = GridBagConstraints.HORIZONTAL;
			gbc_itemName.gridx = 1;
			gbc_itemName.gridy = 0;
			contentPanel.add(itemName, gbc_itemName);
			itemName.setColumns(10);
		}
		{
			JLabel lblSlot = new JLabel("Slot");
			GridBagConstraints gbc_lblSlot = new GridBagConstraints();
			gbc_lblSlot.anchor = GridBagConstraints.EAST;
			gbc_lblSlot.insets = new Insets(0, 0, 5, 5);
			gbc_lblSlot.gridx = 0;
			gbc_lblSlot.gridy = 1;
			contentPanel.add(lblSlot, gbc_lblSlot);
		}
		{
			itemSlot = new JComboBox<String>();
			itemSlot.setModel(new DefaultComboBoxModel<String>(new String[] {"Mainhand", "Offhand", "Two-hand", "Head", "Body", "Legs", "Gloves", "Boots", "Cape", "Ring", "Neck", "Ammo"}));
			GridBagConstraints gbc_itemSlot = new GridBagConstraints();
			gbc_itemSlot.gridwidth = 3;
			gbc_itemSlot.insets = new Insets(0, 0, 5, 0);
			gbc_itemSlot.fill = GridBagConstraints.HORIZONTAL;
			gbc_itemSlot.gridx = 1;
			gbc_itemSlot.gridy = 1;
			contentPanel.add(itemSlot, gbc_itemSlot);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.gridwidth = 3;
			gbc_separator.insets = new Insets(0, 0, 5, 5);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 2;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JLabel lblStabAttack = new JLabel("Stab Attack");
			GridBagConstraints gbc_lblStabAttack = new GridBagConstraints();
			gbc_lblStabAttack.anchor = GridBagConstraints.EAST;
			gbc_lblStabAttack.insets = new Insets(0, 0, 5, 5);
			gbc_lblStabAttack.gridx = 0;
			gbc_lblStabAttack.gridy = 3;
			contentPanel.add(lblStabAttack, gbc_lblStabAttack);
		}
		{
			spnStabAtt = new JSpinner();
			GridBagConstraints gbc_spnStabAtt = new GridBagConstraints();
			gbc_spnStabAtt.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnStabAtt.insets = new Insets(0, 0, 5, 5);
			gbc_spnStabAtt.gridx = 1;
			gbc_spnStabAtt.gridy = 3;
			contentPanel.add(spnStabAtt, gbc_spnStabAtt);
		}
		{
			JLabel lblStabDefence = new JLabel("Stab Defence");
			GridBagConstraints gbc_lblStabDefence = new GridBagConstraints();
			gbc_lblStabDefence.anchor = GridBagConstraints.EAST;
			gbc_lblStabDefence.insets = new Insets(0, 0, 5, 5);
			gbc_lblStabDefence.gridx = 2;
			gbc_lblStabDefence.gridy = 3;
			contentPanel.add(lblStabDefence, gbc_lblStabDefence);
		}
		{
			spnStabDef = new JSpinner();
			GridBagConstraints gbc_spnStabDef = new GridBagConstraints();
			gbc_spnStabDef.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnStabDef.insets = new Insets(0, 0, 5, 0);
			gbc_spnStabDef.gridx = 3;
			gbc_spnStabDef.gridy = 3;
			contentPanel.add(spnStabDef, gbc_spnStabDef);
		}
		{
			JLabel lblSlashAttack = new JLabel("Slash Attack");
			GridBagConstraints gbc_lblSlashAttack = new GridBagConstraints();
			gbc_lblSlashAttack.anchor = GridBagConstraints.EAST;
			gbc_lblSlashAttack.insets = new Insets(0, 0, 5, 5);
			gbc_lblSlashAttack.gridx = 0;
			gbc_lblSlashAttack.gridy = 4;
			contentPanel.add(lblSlashAttack, gbc_lblSlashAttack);
		}
		{
			spnSlashAtt = new JSpinner();
			GridBagConstraints gbc_spnSlashAtt = new GridBagConstraints();
			gbc_spnSlashAtt.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnSlashAtt.insets = new Insets(0, 0, 5, 5);
			gbc_spnSlashAtt.gridx = 1;
			gbc_spnSlashAtt.gridy = 4;
			contentPanel.add(spnSlashAtt, gbc_spnSlashAtt);
		}
		{
			JLabel lblSlashDefence = new JLabel("Slash Defence");
			GridBagConstraints gbc_lblSlashDefence = new GridBagConstraints();
			gbc_lblSlashDefence.anchor = GridBagConstraints.EAST;
			gbc_lblSlashDefence.insets = new Insets(0, 0, 5, 5);
			gbc_lblSlashDefence.gridx = 2;
			gbc_lblSlashDefence.gridy = 4;
			contentPanel.add(lblSlashDefence, gbc_lblSlashDefence);
		}
		{
			spnSlashDef = new JSpinner();
			GridBagConstraints gbc_spnSlashDef = new GridBagConstraints();
			gbc_spnSlashDef.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnSlashDef.insets = new Insets(0, 0, 5, 0);
			gbc_spnSlashDef.gridx = 3;
			gbc_spnSlashDef.gridy = 4;
			contentPanel.add(spnSlashDef, gbc_spnSlashDef);
		}
		{
			JLabel lblCrushAttack = new JLabel("Crush Attack");
			GridBagConstraints gbc_lblCrushAttack = new GridBagConstraints();
			gbc_lblCrushAttack.anchor = GridBagConstraints.EAST;
			gbc_lblCrushAttack.insets = new Insets(0, 0, 5, 5);
			gbc_lblCrushAttack.gridx = 0;
			gbc_lblCrushAttack.gridy = 5;
			contentPanel.add(lblCrushAttack, gbc_lblCrushAttack);
		}
		{
			spnCrushAtt = new JSpinner();
			GridBagConstraints gbc_spnCrushAtt = new GridBagConstraints();
			gbc_spnCrushAtt.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnCrushAtt.insets = new Insets(0, 0, 5, 5);
			gbc_spnCrushAtt.gridx = 1;
			gbc_spnCrushAtt.gridy = 5;
			contentPanel.add(spnCrushAtt, gbc_spnCrushAtt);
		}
		{
			JLabel lblCrushDefence = new JLabel("Crush Defence");
			GridBagConstraints gbc_lblCrushDefence = new GridBagConstraints();
			gbc_lblCrushDefence.anchor = GridBagConstraints.EAST;
			gbc_lblCrushDefence.insets = new Insets(0, 0, 5, 5);
			gbc_lblCrushDefence.gridx = 2;
			gbc_lblCrushDefence.gridy = 5;
			contentPanel.add(lblCrushDefence, gbc_lblCrushDefence);
		}
		{
			spnCrushDef = new JSpinner();
			GridBagConstraints gbc_spnCrushDef = new GridBagConstraints();
			gbc_spnCrushDef.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnCrushDef.insets = new Insets(0, 0, 5, 0);
			gbc_spnCrushDef.gridx = 3;
			gbc_spnCrushDef.gridy = 5;
			contentPanel.add(spnCrushDef, gbc_spnCrushDef);
		}
		{
			JLabel lblMagicAttack = new JLabel("Magic Attack");
			GridBagConstraints gbc_lblMagicAttack = new GridBagConstraints();
			gbc_lblMagicAttack.anchor = GridBagConstraints.EAST;
			gbc_lblMagicAttack.insets = new Insets(0, 0, 5, 5);
			gbc_lblMagicAttack.gridx = 0;
			gbc_lblMagicAttack.gridy = 6;
			contentPanel.add(lblMagicAttack, gbc_lblMagicAttack);
		}
		{
			spnMagicAtt = new JSpinner();
			GridBagConstraints gbc_spnMagicAtt = new GridBagConstraints();
			gbc_spnMagicAtt.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnMagicAtt.insets = new Insets(0, 0, 5, 5);
			gbc_spnMagicAtt.gridx = 1;
			gbc_spnMagicAtt.gridy = 6;
			contentPanel.add(spnMagicAtt, gbc_spnMagicAtt);
		}
		{
			JLabel lblMagicDefence = new JLabel("Magic Defence");
			GridBagConstraints gbc_lblMagicDefence = new GridBagConstraints();
			gbc_lblMagicDefence.anchor = GridBagConstraints.EAST;
			gbc_lblMagicDefence.insets = new Insets(0, 0, 5, 5);
			gbc_lblMagicDefence.gridx = 2;
			gbc_lblMagicDefence.gridy = 6;
			contentPanel.add(lblMagicDefence, gbc_lblMagicDefence);
		}
		{
			spnMageDef = new JSpinner();
			GridBagConstraints gbc_spnMageDef = new GridBagConstraints();
			gbc_spnMageDef.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnMageDef.insets = new Insets(0, 0, 5, 0);
			gbc_spnMageDef.gridx = 3;
			gbc_spnMageDef.gridy = 6;
			contentPanel.add(spnMageDef, gbc_spnMageDef);
		}
		{
			JLabel lblRangedAttack = new JLabel("Ranged Attack");
			GridBagConstraints gbc_lblRangedAttack = new GridBagConstraints();
			gbc_lblRangedAttack.anchor = GridBagConstraints.EAST;
			gbc_lblRangedAttack.insets = new Insets(0, 0, 5, 5);
			gbc_lblRangedAttack.gridx = 0;
			gbc_lblRangedAttack.gridy = 7;
			contentPanel.add(lblRangedAttack, gbc_lblRangedAttack);
		}
		{
			spnRangeAtt = new JSpinner();
			GridBagConstraints gbc_spnRangeAtt = new GridBagConstraints();
			gbc_spnRangeAtt.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnRangeAtt.insets = new Insets(0, 0, 5, 5);
			gbc_spnRangeAtt.gridx = 1;
			gbc_spnRangeAtt.gridy = 7;
			contentPanel.add(spnRangeAtt, gbc_spnRangeAtt);
		}
		{
			JLabel lblRangedDefence = new JLabel("Ranged Defence");
			GridBagConstraints gbc_lblRangedDefence = new GridBagConstraints();
			gbc_lblRangedDefence.anchor = GridBagConstraints.EAST;
			gbc_lblRangedDefence.insets = new Insets(0, 0, 5, 5);
			gbc_lblRangedDefence.gridx = 2;
			gbc_lblRangedDefence.gridy = 7;
			contentPanel.add(lblRangedDefence, gbc_lblRangedDefence);
		}
		{
			spnRangeDef = new JSpinner();
			GridBagConstraints gbc_spnRangeDef = new GridBagConstraints();
			gbc_spnRangeDef.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnRangeDef.insets = new Insets(0, 0, 5, 0);
			gbc_spnRangeDef.gridx = 3;
			gbc_spnRangeDef.gridy = 7;
			contentPanel.add(spnRangeDef, gbc_spnRangeDef);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.insets = new Insets(0, 0, 5, 5);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 8;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JLabel lblStrengthBonus = new JLabel("Strength Bonus");
			GridBagConstraints gbc_lblStrengthBonus = new GridBagConstraints();
			gbc_lblStrengthBonus.anchor = GridBagConstraints.EAST;
			gbc_lblStrengthBonus.insets = new Insets(0, 0, 5, 5);
			gbc_lblStrengthBonus.gridx = 0;
			gbc_lblStrengthBonus.gridy = 9;
			contentPanel.add(lblStrengthBonus, gbc_lblStrengthBonus);
		}
		{
			spnMeleeStr = new JSpinner();
			GridBagConstraints gbc_spnMeleeStr = new GridBagConstraints();
			gbc_spnMeleeStr.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnMeleeStr.insets = new Insets(0, 0, 5, 5);
			gbc_spnMeleeStr.gridx = 1;
			gbc_spnMeleeStr.gridy = 9;
			contentPanel.add(spnMeleeStr, gbc_spnMeleeStr);
		}
		{
			JLabel lblRangedStrength = new JLabel("Ranged Strength");
			GridBagConstraints gbc_lblRangedStrength = new GridBagConstraints();
			gbc_lblRangedStrength.anchor = GridBagConstraints.EAST;
			gbc_lblRangedStrength.insets = new Insets(0, 0, 5, 5);
			gbc_lblRangedStrength.gridx = 2;
			gbc_lblRangedStrength.gridy = 9;
			contentPanel.add(lblRangedStrength, gbc_lblRangedStrength);
		}
		{
			spnRangeStr = new JSpinner();
			GridBagConstraints gbc_spnRangeStr = new GridBagConstraints();
			gbc_spnRangeStr.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnRangeStr.insets = new Insets(0, 0, 5, 0);
			gbc_spnRangeStr.gridx = 3;
			gbc_spnRangeStr.gridy = 9;
			contentPanel.add(spnRangeStr, gbc_spnRangeStr);
		}
		{
			JLabel lblMagicDamage = new JLabel("Magic Damage");
			GridBagConstraints gbc_lblMagicDamage = new GridBagConstraints();
			gbc_lblMagicDamage.anchor = GridBagConstraints.EAST;
			gbc_lblMagicDamage.insets = new Insets(0, 0, 0, 5);
			gbc_lblMagicDamage.gridx = 0;
			gbc_lblMagicDamage.gridy = 10;
			contentPanel.add(lblMagicDamage, gbc_lblMagicDamage);
		}
		{
			spnMageStr = new JSpinner();
			GridBagConstraints gbc_spnMageStr = new GridBagConstraints();
			gbc_spnMageStr.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnMageStr.insets = new Insets(0, 0, 0, 5);
			gbc_spnMageStr.gridx = 1;
			gbc_spnMageStr.gridy = 10;
			contentPanel.add(spnMageStr, gbc_spnMageStr);
		}
		{
			JLabel lblPrayerBonus = new JLabel("Prayer Bonus");
			GridBagConstraints gbc_lblPrayerBonus = new GridBagConstraints();
			gbc_lblPrayerBonus.insets = new Insets(0, 0, 0, 5);
			gbc_lblPrayerBonus.anchor = GridBagConstraints.EAST;
			gbc_lblPrayerBonus.gridx = 2;
			gbc_lblPrayerBonus.gridy = 10;
			contentPanel.add(lblPrayerBonus, gbc_lblPrayerBonus);
		}
		{
			spnPray = new JSpinner();
			GridBagConstraints gbc_spnPray = new GridBagConstraints();
			gbc_spnPray.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnPray.gridx = 3;
			gbc_spnPray.gridy = 10;
			contentPanel.add(spnPray, gbc_spnPray);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e -> {
					Connection conn = null;
					PreparedStatement stmt = null;
					
					try {
						Class.forName("org.h2.Driver");
						conn = DriverManager.getConnection(ItemDatabase.URL);
						//                                                          1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16
						stmt = conn.prepareStatement("INSERT INTO EQUIPMENT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false, -1, true, true)");
						
						String slot;
						//"Mainhand", "Offhand", "Two-hand", "Head", "Body", "Legs", "Gloves", "Boots", "Cape", "Ring", "Neck", "Ammo"
						switch((String)itemSlot.getSelectedItem()){
						case "Offhand": slot = "Shield"; break;
						case "Neck":    slot = "Amulet"; break;
						case "Two-hand":slot = "2h"; break;
						case "Mainhand":slot = "Weapon"; break;
						case "Body":    slot = "Torso"; break;
						default: slot = (String) itemSlot.getSelectedItem();
						}
						
						stmt.setString(1, itemName.getText());
						stmt.setString(2, slot);
						stmt.setInt(3, (int) spnStabAtt.getValue());
						stmt.setInt(4, (int) spnSlashAtt.getValue());
						stmt.setInt(5, (int) spnCrushAtt.getValue());
						stmt.setInt(6, (int) spnMagicAtt.getValue());
						stmt.setInt(7, (int) spnRangeAtt.getValue());
						
						stmt.setInt(8, (int) spnStabDef.getValue());
						stmt.setInt(9, (int) spnSlashDef.getValue());
						stmt.setInt(10, (int) spnCrushDef.getValue());
						stmt.setInt(11, (int) spnMageDef.getValue());
						stmt.setInt(12, (int) spnRangeDef.getValue());
						
						stmt.setInt(13, (int) spnMeleeStr.getValue());
						stmt.setInt(14, (int) spnRangeStr.getValue());
						stmt.setInt(15, (int) spnMageStr.getValue());
						stmt.setInt(16, (int) spnPray.getValue());
						
						stmt.execute();
						
						dispose();
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "An unexpected error occurred creating the item. Contact the author.");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
