package com.osrs.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.osrs.levels.Potions;
import com.osrs.levels.PotionsList;

@SuppressWarnings("serial")
public class PotionSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ArrayList<JCheckBox> allCheckBoxes = new ArrayList<JCheckBox>();
	private final PotionsList pl = new PotionsList();
	private boolean[] selected, selectedOnInit;
	private JCheckBox chckbxSuperRanging;
	private JCheckBox chckbxDefencePotion;
	private JCheckBox chckbxAttackPotion;
	private JCheckBox chckbxCombatPotion;
	private JCheckBox chckbxOverload;
	private JCheckBox chckbxSuperDefence;
	private JCheckBox chckbxSuperMagic;
	private JCheckBox chckbxStrengthPotion;
	private JCheckBox chckbxSaradominBrew;
	private JCheckBox chckbxDragonBaxe;
	private JCheckBox chckbxRangingPotion;
	private JCheckBox chckbxSuperAttack;
	private JCheckBox chckbxZamorakBrew;
	private JCheckBox chckbxExcalibur;
	private JCheckBox chckbxSuperStrength;
	private JCheckBox chckbxMagicPotion;
	private JCheckBox chckbxSuperCombat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PotionSelector dialog = new PotionSelector();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean[] display(){
		selectedOnInit = null;
		setVisible(true);
		return selected;
	}
	
	public boolean[] display(boolean[] selectedOnInit){
		this.selectedOnInit = selectedOnInit;
		setVisible(true);
		return selected;
	}
	
	public void dispose(boolean valuesChanged){
		if(valuesChanged){
			selected = pl.getAll();
		}
		setVisible(false);
	}
	/**
	 * Create the dialog.
	 */
	public PotionSelector() {
		setTitle("Select your Potions");
		setBounds(100, 100, 375, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		chckbxAttackPotion = new JCheckBox("Attack potion");
		chckbxAttackPotion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAttackPotion = new GridBagConstraints();
		gbc_chckbxAttackPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxAttackPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAttackPotion.gridx = 0;
		gbc_chckbxAttackPotion.gridy = 1;
		contentPanel.add(chckbxAttackPotion, gbc_chckbxAttackPotion);

		chckbxSuperAttack = new JCheckBox("Super attack");
		chckbxSuperAttack.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSuperAttack = new GridBagConstraints();
		gbc_chckbxSuperAttack.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperAttack.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperAttack.gridx = 1;
		gbc_chckbxSuperAttack.gridy = 1;
		contentPanel.add(chckbxSuperAttack, gbc_chckbxSuperAttack);

		chckbxOverload = new JCheckBox("Overload");
		chckbxOverload.setToolTipText("Nightmare Zone only");
		chckbxOverload.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxOverload = new GridBagConstraints();
		gbc_chckbxOverload.anchor = GridBagConstraints.WEST;
		gbc_chckbxOverload.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxOverload.gridx = 2;
		gbc_chckbxOverload.gridy = 1;
		contentPanel.add(chckbxOverload, gbc_chckbxOverload);

		chckbxStrengthPotion = new JCheckBox("Strength potion");
		chckbxStrengthPotion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxStrengthPotion = new GridBagConstraints();
		gbc_chckbxStrengthPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxStrengthPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxStrengthPotion.gridx = 0;
		gbc_chckbxStrengthPotion.gridy = 2;
		contentPanel.add(chckbxStrengthPotion, gbc_chckbxStrengthPotion);

		chckbxSuperStrength = new JCheckBox("Super strength");
		chckbxSuperStrength.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSuperStrength = new GridBagConstraints();
		gbc_chckbxSuperStrength.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperStrength.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperStrength.gridx = 1;
		gbc_chckbxSuperStrength.gridy = 2;
		contentPanel.add(chckbxSuperStrength, gbc_chckbxSuperStrength);

		chckbxSaradominBrew = new JCheckBox("Saradomin brew");
		chckbxSaradominBrew.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSaradominBrew = new GridBagConstraints();
		gbc_chckbxSaradominBrew.anchor = GridBagConstraints.WEST;
		gbc_chckbxSaradominBrew.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSaradominBrew.gridx = 2;
		gbc_chckbxSaradominBrew.gridy = 2;
		contentPanel.add(chckbxSaradominBrew, gbc_chckbxSaradominBrew);

		chckbxDefencePotion = new JCheckBox("Defence potion");
		chckbxDefencePotion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxDefencePotion = new GridBagConstraints();
		gbc_chckbxDefencePotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxDefencePotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDefencePotion.gridx = 0;
		gbc_chckbxDefencePotion.gridy = 3;
		contentPanel.add(chckbxDefencePotion, gbc_chckbxDefencePotion);

		chckbxSuperDefence = new JCheckBox("Super defence");
		chckbxSuperDefence.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSuperDefence = new GridBagConstraints();
		gbc_chckbxSuperDefence.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperDefence.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperDefence.gridx = 1;
		gbc_chckbxSuperDefence.gridy = 3;
		contentPanel.add(chckbxSuperDefence, gbc_chckbxSuperDefence);

		chckbxZamorakBrew = new JCheckBox("Zamorak brew");
		chckbxZamorakBrew.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxZamorakBrew = new GridBagConstraints();
		gbc_chckbxZamorakBrew.anchor = GridBagConstraints.WEST;
		gbc_chckbxZamorakBrew.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxZamorakBrew.gridx = 2;
		gbc_chckbxZamorakBrew.gridy = 3;
		contentPanel.add(chckbxZamorakBrew, gbc_chckbxZamorakBrew);

		chckbxCombatPotion = new JCheckBox("Combat potion");
		chckbxCombatPotion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxCombatPotion = new GridBagConstraints();
		gbc_chckbxCombatPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxCombatPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCombatPotion.gridx = 0;
		gbc_chckbxCombatPotion.gridy = 4;
		contentPanel.add(chckbxCombatPotion, gbc_chckbxCombatPotion);

		chckbxSuperCombat = new JCheckBox("Super combat");
		chckbxSuperCombat.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSuperCombat = new GridBagConstraints();
		gbc_chckbxSuperCombat.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperCombat.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperCombat.gridx = 1;
		gbc_chckbxSuperCombat.gridy = 4;
		contentPanel.add(chckbxSuperCombat, gbc_chckbxSuperCombat);
		
		chckbxDragonBaxe = new JCheckBox("Dragon B-axe");
		GridBagConstraints gbc_chckbxDragonBaxe = new GridBagConstraints();
		gbc_chckbxDragonBaxe.anchor = GridBagConstraints.WEST;
		gbc_chckbxDragonBaxe.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxDragonBaxe.gridx = 2;
		gbc_chckbxDragonBaxe.gridy = 4;
		contentPanel.add(chckbxDragonBaxe, gbc_chckbxDragonBaxe);

		chckbxRangingPotion = new JCheckBox("Ranging potion");
		GridBagConstraints gbc_chckbxRangingPotion = new GridBagConstraints();
		gbc_chckbxRangingPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxRangingPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxRangingPotion.gridx = 0;
		gbc_chckbxRangingPotion.gridy = 5;
		contentPanel.add(chckbxRangingPotion, gbc_chckbxRangingPotion);

		chckbxSuperRanging = new JCheckBox("Super ranging");
		chckbxSuperRanging.setToolTipText("Nightmare Zone only");
		GridBagConstraints gbc_chckbxSuperRanging = new GridBagConstraints();
		gbc_chckbxSuperRanging.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperRanging.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperRanging.gridx = 1;
		gbc_chckbxSuperRanging.gridy = 5;
		contentPanel.add(chckbxSuperRanging, gbc_chckbxSuperRanging);
		
		chckbxExcalibur = new JCheckBox("Excalibur");
		GridBagConstraints gbc_chckbxExcalibur = new GridBagConstraints();
		gbc_chckbxExcalibur.anchor = GridBagConstraints.WEST;
		gbc_chckbxExcalibur.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxExcalibur.gridx = 2;
		gbc_chckbxExcalibur.gridy = 5;
		contentPanel.add(chckbxExcalibur, gbc_chckbxExcalibur);

		chckbxMagicPotion = new JCheckBox("Magic potion");
		GridBagConstraints gbc_chckbxMagicPotion = new GridBagConstraints();
		gbc_chckbxMagicPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxMagicPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMagicPotion.gridx = 0;
		gbc_chckbxMagicPotion.gridy = 6;
		contentPanel.add(chckbxMagicPotion, gbc_chckbxMagicPotion);

		chckbxSuperMagic = new JCheckBox("Super magic");
		chckbxSuperMagic.setToolTipText("Nightmare Zone only");
		GridBagConstraints gbc_chckbxSuperMagic = new GridBagConstraints();
		gbc_chckbxSuperMagic.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperMagic.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperMagic.gridx = 1;
		gbc_chckbxSuperMagic.gridy = 6;
		contentPanel.add(chckbxSuperMagic, gbc_chckbxSuperMagic);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
				JButton okButton = new JButton("Confirm");
				okButton.addActionListener(e -> {
					dispose(true);
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(e -> {
			clear();
		});
		buttonPane.add(btnNewButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			dispose(false);
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		allCheckBoxes.add(chckbxAttackPotion);
		allCheckBoxes.add(chckbxStrengthPotion);
		allCheckBoxes.add(chckbxDefencePotion);
		allCheckBoxes.add(chckbxMagicPotion);
		allCheckBoxes.add(chckbxRangingPotion);
		allCheckBoxes.add(chckbxSuperAttack);
		allCheckBoxes.add(chckbxSuperDefence);
		allCheckBoxes.add(chckbxSuperStrength);
		allCheckBoxes.add(chckbxSuperMagic);
		allCheckBoxes.add(chckbxSuperRanging);
		allCheckBoxes.add(chckbxOverload);
		allCheckBoxes.add(chckbxZamorakBrew);
		allCheckBoxes.add(chckbxSaradominBrew);
		allCheckBoxes.add(chckbxDragonBaxe);
		allCheckBoxes.add(chckbxExcalibur);
		
		chckbxAttackPotion.addActionListener(e -> {
			pl.add(Potions.REG_ATT);
			updateCheckboxes();
		});

		chckbxSuperAttack.addActionListener(e -> {
			pl.add(Potions.SUP_ATT);
			updateCheckboxes();
		});

		chckbxStrengthPotion.addActionListener(e -> {
			pl.add(Potions.REG_STR);
			updateCheckboxes();
		});

		chckbxSuperStrength.addActionListener(e -> {
			pl.add(Potions.SUP_STR);
			updateCheckboxes();
		});

		chckbxDefencePotion.addActionListener(e -> {
			pl.add(Potions.REG_DEF);
			updateCheckboxes();
		});

		chckbxSuperDefence.addActionListener(e -> {
			pl.add(Potions.SUP_DEF);
			updateCheckboxes();
		});
		
		chckbxCombatPotion.addActionListener(e -> {
			pl.add(Potions.REG_CMB);
			updateCheckboxes();
		});
		
		chckbxSuperCombat.addActionListener(e -> {
			pl.add(Potions.SUP_CMB);
			updateCheckboxes();
		});
		
		chckbxMagicPotion.addActionListener(e -> {
			pl.add(Potions.REG_MAG);
			updateCheckboxes();
		});
		
		chckbxSuperMagic.addActionListener(e -> {
			pl.add(Potions.SUP_MAG);
			updateCheckboxes();
		});
		
		chckbxRangingPotion.addActionListener(e -> {
			pl.add(Potions.REG_RNG);
		});

		chckbxSuperRanging.addActionListener(e -> {
			pl.add(Potions.SUP_RNG);
			updateCheckboxes();
		});
		
		chckbxOverload.addActionListener(e -> {
			pl.add(Potions.OTH_OVL);
			updateCheckboxes();
		});
		
		chckbxZamorakBrew.addActionListener(e -> {
			pl.add(Potions.OTH_ZBR);
			updateCheckboxes();
		});
		
		chckbxSaradominBrew.addActionListener(e -> {
			pl.add(Potions.OTH_SBR);
			updateCheckboxes();
		});
		
		chckbxExcalibur.addActionListener(e -> {
			pl.add(Potions.OTH_EXC);
			updateCheckboxes();
		});
		
		chckbxDragonBaxe.addActionListener(e -> {
			pl.add(Potions.OTH_DBX);
			updateCheckboxes();
		});
		
		if(selected != null)
			setSelected(selectedOnInit);
		
		setResizable(false);
	}
	
	public void clear(){
		pl.clear();
		updateCheckboxes();
	}
	
	public void setSelected(boolean[] isSelected){
		chckbxSuperRanging.setSelected(isSelected[Potions.SUP_RNG.index]);
		chckbxDefencePotion.setSelected(isSelected[Potions.REG_DEF.index]);
		chckbxAttackPotion.setSelected(isSelected[Potions.REG_ATT.index]);
		chckbxCombatPotion.setSelected(isSelected[Potions.REG_CMB.index]);
		chckbxOverload.setSelected(isSelected[Potions.OTH_OVL.index]);
		chckbxSuperDefence.setSelected(isSelected[Potions.SUP_DEF.index]);
		chckbxSuperMagic.setSelected(isSelected[Potions.SUP_MAG.index]);
		chckbxStrengthPotion.setSelected(isSelected[Potions.REG_STR.index]);
		chckbxSaradominBrew.setSelected(isSelected[Potions.OTH_SBR.index]);
		chckbxDragonBaxe.setSelected(isSelected[Potions.OTH_DBX.index]);
		chckbxRangingPotion.setSelected(isSelected[Potions.REG_RNG.index]);
		chckbxSuperAttack.setSelected(isSelected[Potions.SUP_ATT.index]);
		chckbxZamorakBrew.setSelected(isSelected[Potions.OTH_ZBR.index]);
		chckbxExcalibur.setSelected(isSelected[Potions.OTH_EXC.index]);
		chckbxSuperStrength.setSelected(isSelected[Potions.SUP_STR.index]);
		chckbxMagicPotion.setSelected(isSelected[Potions.REG_MAG.index]);
		chckbxSuperCombat.setSelected(isSelected[Potions.SUP_CMB.index]);
	}
	
	public void updateCheckboxes(){
		boolean[] isSelected = pl.getAll();

		setSelected(isSelected);
	}
}
