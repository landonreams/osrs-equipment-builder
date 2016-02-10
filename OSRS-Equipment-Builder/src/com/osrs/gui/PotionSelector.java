package com.osrs.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PotionSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();

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

	/**
	 * Create the dialog.
	 */
	public PotionSelector() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		final JCheckBox chckbxAttackPotion = new JCheckBox("Attack potion");
		chckbxAttackPotion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAttackPotion = new GridBagConstraints();
		gbc_chckbxAttackPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxAttackPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAttackPotion.gridx = 1;
		gbc_chckbxAttackPotion.gridy = 1;
		contentPanel.add(chckbxAttackPotion, gbc_chckbxAttackPotion);

		final JCheckBox chckbxSuperAttack = new JCheckBox("Super attack");
		chckbxSuperAttack.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSuperAttack = new GridBagConstraints();
		gbc_chckbxSuperAttack.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperAttack.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperAttack.gridx = 2;
		gbc_chckbxSuperAttack.gridy = 1;
		contentPanel.add(chckbxSuperAttack, gbc_chckbxSuperAttack);

		final JCheckBox chckbxOverload = new JCheckBox("Overload");
		chckbxOverload.setToolTipText("Nightmare Zone only");
		chckbxOverload.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxOverload = new GridBagConstraints();
		gbc_chckbxOverload.anchor = GridBagConstraints.WEST;
		gbc_chckbxOverload.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxOverload.gridx = 3;
		gbc_chckbxOverload.gridy = 1;
		contentPanel.add(chckbxOverload, gbc_chckbxOverload);

		final JCheckBox chckbxStrengthPotion = new JCheckBox("Strength potion");
		chckbxStrengthPotion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxStrengthPotion = new GridBagConstraints();
		gbc_chckbxStrengthPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxStrengthPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxStrengthPotion.gridx = 1;
		gbc_chckbxStrengthPotion.gridy = 2;
		contentPanel.add(chckbxStrengthPotion, gbc_chckbxStrengthPotion);

		final JCheckBox chckbxSuperStrength = new JCheckBox("Super strength");
		chckbxSuperStrength.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSuperStrength = new GridBagConstraints();
		gbc_chckbxSuperStrength.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperStrength.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperStrength.gridx = 2;
		gbc_chckbxSuperStrength.gridy = 2;
		contentPanel.add(chckbxSuperStrength, gbc_chckbxSuperStrength);

		final JCheckBox chckbxSaradominBrew = new JCheckBox("Saradomin brew");
		chckbxSaradominBrew.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSaradominBrew = new GridBagConstraints();
		gbc_chckbxSaradominBrew.anchor = GridBagConstraints.WEST;
		gbc_chckbxSaradominBrew.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSaradominBrew.gridx = 3;
		gbc_chckbxSaradominBrew.gridy = 2;
		contentPanel.add(chckbxSaradominBrew, gbc_chckbxSaradominBrew);

		final JCheckBox chckbxDefencePotion = new JCheckBox("Defence potion");
		chckbxDefencePotion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxDefencePotion = new GridBagConstraints();
		gbc_chckbxDefencePotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxDefencePotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDefencePotion.gridx = 1;
		gbc_chckbxDefencePotion.gridy = 3;
		contentPanel.add(chckbxDefencePotion, gbc_chckbxDefencePotion);

		final JCheckBox chckbxSuperDefence = new JCheckBox("Super defence");
		chckbxSuperDefence.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSuperDefence = new GridBagConstraints();
		gbc_chckbxSuperDefence.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperDefence.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperDefence.gridx = 2;
		gbc_chckbxSuperDefence.gridy = 3;
		contentPanel.add(chckbxSuperDefence, gbc_chckbxSuperDefence);

		final JCheckBox chckbxZamorakBrew = new JCheckBox("Zamorak brew");
		chckbxZamorakBrew.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxZamorakBrew = new GridBagConstraints();
		gbc_chckbxZamorakBrew.anchor = GridBagConstraints.WEST;
		gbc_chckbxZamorakBrew.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxZamorakBrew.gridx = 3;
		gbc_chckbxZamorakBrew.gridy = 3;
		contentPanel.add(chckbxZamorakBrew, gbc_chckbxZamorakBrew);

		final JCheckBox chckbxCombatPotion = new JCheckBox("Combat potion");
		chckbxCombatPotion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxCombatPotion = new GridBagConstraints();
		gbc_chckbxCombatPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxCombatPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCombatPotion.gridx = 1;
		gbc_chckbxCombatPotion.gridy = 4;
		contentPanel.add(chckbxCombatPotion, gbc_chckbxCombatPotion);

		final JCheckBox chckbxSuperCombat = new JCheckBox("Super combat");
		chckbxSuperCombat.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxSuperCombat = new GridBagConstraints();
		gbc_chckbxSuperCombat.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperCombat.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperCombat.gridx = 2;
		gbc_chckbxSuperCombat.gridy = 4;
		contentPanel.add(chckbxSuperCombat, gbc_chckbxSuperCombat);

		final JCheckBox chckbxRangingPotion = new JCheckBox("Ranging potion");
		GridBagConstraints gbc_chckbxRangingPotion = new GridBagConstraints();
		gbc_chckbxRangingPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxRangingPotion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxRangingPotion.gridx = 1;
		gbc_chckbxRangingPotion.gridy = 5;
		contentPanel.add(chckbxRangingPotion, gbc_chckbxRangingPotion);

		final JCheckBox chckbxSuperRanging = new JCheckBox("Super ranging");
		chckbxSuperRanging.setToolTipText("Nightmare Zone only");
		GridBagConstraints gbc_chckbxSuperRanging = new GridBagConstraints();
		gbc_chckbxSuperRanging.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperRanging.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSuperRanging.gridx = 2;
		gbc_chckbxSuperRanging.gridy = 5;
		contentPanel.add(chckbxSuperRanging, gbc_chckbxSuperRanging);

		final JCheckBox chckbxMagicPotion = new JCheckBox("Magic potion");
		GridBagConstraints gbc_chckbxMagicPotion = new GridBagConstraints();
		gbc_chckbxMagicPotion.anchor = GridBagConstraints.WEST;
		gbc_chckbxMagicPotion.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxMagicPotion.gridx = 1;
		gbc_chckbxMagicPotion.gridy = 6;
		contentPanel.add(chckbxMagicPotion, gbc_chckbxMagicPotion);

		final JCheckBox chckbxSuperMagic = new JCheckBox("Super magic");
		chckbxSuperMagic.setToolTipText("Nightmare Zone only");
		GridBagConstraints gbc_chckbxSuperMagic = new GridBagConstraints();
		gbc_chckbxSuperMagic.anchor = GridBagConstraints.WEST;
		gbc_chckbxSuperMagic.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSuperMagic.gridx = 2;
		gbc_chckbxSuperMagic.gridy = 6;
		contentPanel.add(chckbxSuperMagic, gbc_chckbxSuperMagic);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		final ArrayList<JCheckBox> attackBoosters = new ArrayList<JCheckBox>();
		final ArrayList<JCheckBox> strengthBoosters = new ArrayList<JCheckBox>();
		final ArrayList<JCheckBox> defenceBoosters = new ArrayList<JCheckBox>();
		final ArrayList<JCheckBox> magicBoosters = new ArrayList<JCheckBox>();
		final ArrayList<JCheckBox> rangedBoosters = new ArrayList<JCheckBox>();

		attackBoosters.add(chckbxAttackPotion);
		attackBoosters.add(chckbxSuperAttack);
		attackBoosters.add(chckbxCombatPotion);
		attackBoosters.add(chckbxSuperCombat);
		attackBoosters.add(chckbxOverload);
		attackBoosters.add(chckbxZamorakBrew);

		strengthBoosters.add(chckbxStrengthPotion);
		strengthBoosters.add(chckbxSuperStrength);
		strengthBoosters.add(chckbxCombatPotion);
		strengthBoosters.add(chckbxSuperCombat);
		strengthBoosters.add(chckbxOverload);
		strengthBoosters.add(chckbxZamorakBrew);

		defenceBoosters.add(chckbxDefencePotion);
		defenceBoosters.add(chckbxSuperDefence);
		defenceBoosters.add(chckbxSuperCombat);
		defenceBoosters.add(chckbxOverload);
		defenceBoosters.add(chckbxSaradominBrew);

		rangedBoosters.add(chckbxRangingPotion);
		rangedBoosters.add(chckbxSuperRanging);
		rangedBoosters.add(chckbxOverload);

		magicBoosters.add(chckbxMagicPotion);
		magicBoosters.add(chckbxSuperMagic);
		magicBoosters.add(chckbxOverload);

		chckbxAttackPotion.addActionListener(e -> {
			for (JCheckBox chk : attackBoosters) {
				if (!chk.equals(chckbxAttackPotion))
					chk.setSelected(false);
			}
		});

		chckbxSuperAttack.addActionListener(e -> {
			for (JCheckBox chk : attackBoosters) {
				if (!chk.equals(chckbxSuperAttack))
					chk.setSelected(false);
			}
		});

		chckbxStrengthPotion.addActionListener(e -> {
			for (JCheckBox chk : strengthBoosters) {
				if (!chk.equals(chckbxStrengthPotion))
					chk.setSelected(false);
			}
		});

		chckbxSuperStrength.addActionListener(e -> {
			for (JCheckBox chk : strengthBoosters) {
				if (!chk.equals(chckbxSuperStrength))
					chk.setSelected(false);
			}
		});

		chckbxDefencePotion.addActionListener(e -> {
			for (JCheckBox chk : defenceBoosters) {
				if (!chk.equals(chckbxDefencePotion))
					chk.setSelected(false);
			}
		});

		chckbxSuperDefence.addActionListener(e -> {
			for (JCheckBox chk : defenceBoosters) {
				if (!chk.equals(chckbxSuperDefence))
					chk.setSelected(false);
			}
		});
		
		chckbxCombatPotion.addActionListener(e -> {
			for (JCheckBox chk : attackBoosters){
				if (!chk.equals(chckbxCombatPotion))
					chk.setSelected(false);
			}
			for (JCheckBox chk : strengthBoosters){
				if(!chk.equals(chckbxCombatPotion))
					chk.setSelected(false);
			}
		});
		
		chckbxSuperCombat.addActionListener(e -> {
			for (JCheckBox chk : attackBoosters){
				if (!chk.equals(chckbxSuperCombat))
					chk.setSelected(false);
			}
			for (JCheckBox chk : strengthBoosters){
				if(!chk.equals(chckbxSuperCombat))
					chk.setSelected(false);
			}
			for (JCheckBox chk : defenceBoosters){
				if(!chk.equals(chckbxSuperCombat))
					chk.setSelected(false);
			}
		});
		
		chckbxMagicPotion.addActionListener(e -> {
			for(JCheckBox chk : magicBoosters){
				if(!chk.equals(chckbxMagicPotion))
					chk.setSelected(false);
			}
		});
		
		chckbxSuperMagic.addActionListener(e -> {
			for(JCheckBox chk : magicBoosters){
				if(!chk.equals(chckbxSuperMagic))
					chk.setSelected(false);
			}
		});
		
		chckbxRangingPotion.addActionListener(e -> {
			for(JCheckBox chk : rangedBoosters){
				if(!chk.equals(chckbxRangingPotion))
					chk.setSelected(false);
			}
		});

		chckbxSuperRanging.addActionListener(e -> {
			for(JCheckBox chk : rangedBoosters){
				if(!chk.equals(chckbxSuperRanging))
					chk.setSelected(false);
			}
		});
		
		chckbxOverload.addActionListener(e -> {
			for(JCheckBox chk : attackBoosters){
				if(!chk.equals(chckbxOverload))
					chk.setSelected(false);
			}
			for(JCheckBox chk : strengthBoosters){
				if(!chk.equals(chckbxOverload))
					chk.setSelected(false);
			}
			for(JCheckBox chk : defenceBoosters){
				if(!chk.equals(chckbxOverload))
					chk.setSelected(false);
			}
			for(JCheckBox chk : magicBoosters){
				if(!chk.equals(chckbxOverload))
					chk.setSelected(false);
			}
			for(JCheckBox chk : rangedBoosters){
				if(!chk.equals(chckbxOverload))
					chk.setSelected(false);
			}
		});
		
		chckbxZamorakBrew.addActionListener(e -> {
			for(JCheckBox chk : attackBoosters){
				if(!chk.equals(chckbxZamorakBrew))
					chk.setSelected(false);
			}
			for(JCheckBox chk : strengthBoosters){
				if(!chk.equals(chckbxZamorakBrew))
					chk.setSelected(false);
			}
		});
		
		chckbxSaradominBrew.addActionListener(e -> {
			for(JCheckBox chk : defenceBoosters){
				if(!chk.equals(chckbxSaradominBrew))
					chk.setSelected(false);
			}
		});
		
		setResizable(false);
	}

}
