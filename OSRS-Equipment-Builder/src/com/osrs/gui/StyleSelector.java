package com.osrs.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import com.osrs.levels.CombatTriangle;
import com.osrs.levels.Spell;
import com.osrs.npc.DamageType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class StyleSelector extends JTabbedPane {
	private final ButtonGroup btngrpStyle = new ButtonGroup();
	private final ButtonGroup btngrpStance = new ButtonGroup();
	private JRadioButton rdbtnRapid, rdbtnLongrange, rdbtnAccurate_r;
	private JRadioButton rdbtnAccurate, rdbtnAggressive, rdbtnBalanced, rdbtnDefensive, rdbtnStab, rdbtnSlash, rdbtnCrush;
	private JPanel panelMelee, panelRanged, panelMagic;
	private JSpinner spdRange;
	private JSpinner spdMelee;
	
	private JComboBox<Spell> spellSelector;
	private JLabel label_1;
	private JLabel label_2;
	private final ButtonGroup btngrpRange = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StyleSelector() {
		setTabPlacement(JTabbedPane.RIGHT);
		
		panelMelee = new JPanel();
		add(panelMelee);
		setTitleAt(0, "Melee");
		GridBagLayout gbl_panelMelee = new GridBagLayout();
		gbl_panelMelee.columnWidths = new int[]{0, 0, 0};
		gbl_panelMelee.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelMelee.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMelee.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMelee.setLayout(gbl_panelMelee);
		
		rdbtnStab = new JRadioButton("Stab");
		rdbtnStab.setSelected(true);
		btngrpStyle.add(rdbtnStab);
		GridBagConstraints gbc_rdbtnStab = new GridBagConstraints();
		gbc_rdbtnStab.anchor = GridBagConstraints.WEST;
		gbc_rdbtnStab.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnStab.gridx = 0;
		gbc_rdbtnStab.gridy = 0;
		panelMelee.add(rdbtnStab, gbc_rdbtnStab);
		
		rdbtnAccurate = new JRadioButton("Accurate");
		rdbtnAccurate.setSelected(true);
		btngrpStance.add(rdbtnAccurate);
		GridBagConstraints gbc_rdbtnAccurate = new GridBagConstraints();
		gbc_rdbtnAccurate.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAccurate.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnAccurate.gridx = 1;
		gbc_rdbtnAccurate.gridy = 0;
		panelMelee.add(rdbtnAccurate, gbc_rdbtnAccurate);
		
		rdbtnSlash = new JRadioButton("Slash");
		btngrpStyle.add(rdbtnSlash);
		GridBagConstraints gbc_rdbtnSlash = new GridBagConstraints();
		gbc_rdbtnSlash.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSlash.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSlash.gridx = 0;
		gbc_rdbtnSlash.gridy = 1;
		panelMelee.add(rdbtnSlash, gbc_rdbtnSlash);
		
		rdbtnAggressive = new JRadioButton("Aggressive");
		btngrpStance.add(rdbtnAggressive);
		GridBagConstraints gbc_rdbtnAggressive = new GridBagConstraints();
		gbc_rdbtnAggressive.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAggressive.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnAggressive.gridx = 1;
		gbc_rdbtnAggressive.gridy = 1;
		panelMelee.add(rdbtnAggressive, gbc_rdbtnAggressive);
		
		rdbtnCrush = new JRadioButton("Crush");
		btngrpStyle.add(rdbtnCrush);
		GridBagConstraints gbc_rdbtnCrush = new GridBagConstraints();
		gbc_rdbtnCrush.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCrush.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCrush.gridx = 0;
		gbc_rdbtnCrush.gridy = 2;
		panelMelee.add(rdbtnCrush, gbc_rdbtnCrush);
		
		rdbtnDefensive = new JRadioButton("Defensive");
		btngrpStance.add(rdbtnDefensive);
		GridBagConstraints gbc_rdbtnDefensive = new GridBagConstraints();
		gbc_rdbtnDefensive.anchor = GridBagConstraints.WEST;
		gbc_rdbtnDefensive.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnDefensive.gridx = 1;
		gbc_rdbtnDefensive.gridy = 2;
		panelMelee.add(rdbtnDefensive, gbc_rdbtnDefensive);
		
		rdbtnBalanced = new JRadioButton("Balanced");
		btngrpStance.add(rdbtnBalanced);
		GridBagConstraints gbc_rdbtnBalanced = new GridBagConstraints();
		gbc_rdbtnBalanced.anchor = GridBagConstraints.WEST;
		gbc_rdbtnBalanced.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnBalanced.gridx = 1;
		gbc_rdbtnBalanced.gridy = 3;
		panelMelee.add(rdbtnBalanced, gbc_rdbtnBalanced);
		
		JLabel lblAttackSpeed = new JLabel("Attack Speed");
		GridBagConstraints gbc_lblAttackSpeed = new GridBagConstraints();
		gbc_lblAttackSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttackSpeed.gridx = 0;
		gbc_lblAttackSpeed.gridy = 4;
		panelMelee.add(lblAttackSpeed, gbc_lblAttackSpeed);
		
		spdMelee = new JSpinner();
		spdMelee.setModel(new SpinnerNumberModel(6, 1, 8, 1));
		GridBagConstraints gbc_spdMelee = new GridBagConstraints();
		gbc_spdMelee.insets = new Insets(0, 0, 5, 0);
		gbc_spdMelee.gridx = 1;
		gbc_spdMelee.gridy = 4;
		panelMelee.add(spdMelee, gbc_spdMelee);
		
		label_2 = new JLabel("<html>Attack speed in as per<br> \r\nRS Wiki. Temporary,<br>\r\nuntil I update the itemdb<br>\r\nto  include attack speeds.</html>");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.gridwidth = 2;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 5;
		panelMelee.add(label_2, gbc_label_2);
		
		panelRanged = new JPanel();
		add(panelRanged);
		setTitleAt(1, "Ranged");
		GridBagLayout gbl_panelRanged = new GridBagLayout();
		gbl_panelRanged.columnWidths = new int[]{0, 0, 0};
		gbl_panelRanged.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelRanged.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelRanged.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 4.9E-324, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelRanged.setLayout(gbl_panelRanged);
		
		rdbtnAccurate_r = new JRadioButton("Accurate");
		btngrpRange.add(rdbtnAccurate_r);
		GridBagConstraints gbc_rdbtnAccurate_r = new GridBagConstraints();
		gbc_rdbtnAccurate_r.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAccurate_r.gridx = 0;
		gbc_rdbtnAccurate_r.gridy = 0;
		panelRanged.add(rdbtnAccurate_r, gbc_rdbtnAccurate_r);
		
		rdbtnRapid = new JRadioButton("Rapid");
		btngrpRange.add(rdbtnRapid);
		rdbtnRapid.setSelected(true);
		GridBagConstraints gbc_rdbtnRapid = new GridBagConstraints();
		gbc_rdbtnRapid.anchor = GridBagConstraints.WEST;
		gbc_rdbtnRapid.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnRapid.gridx = 1;
		gbc_rdbtnRapid.gridy = 0;
		panelRanged.add(rdbtnRapid, gbc_rdbtnRapid);
		
		rdbtnLongrange = new JRadioButton("Longrange");
		btngrpRange.add(rdbtnLongrange);
		GridBagConstraints gbc_rdbtnLongrange = new GridBagConstraints();
		gbc_rdbtnLongrange.gridwidth = 2;
		gbc_rdbtnLongrange.anchor = GridBagConstraints.WEST;
		gbc_rdbtnLongrange.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnLongrange.gridx = 0;
		gbc_rdbtnLongrange.gridy = 1;
		panelRanged.add(rdbtnLongrange, gbc_rdbtnLongrange);
		
		JLabel label = new JLabel("Attack Speed");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		panelRanged.add(label, gbc_label);
		
		spdRange = new JSpinner();
		spdRange.setModel(new SpinnerNumberModel(2, 1, 8, 1));
		GridBagConstraints gbc_spdRange = new GridBagConstraints();
		gbc_spdRange.insets = new Insets(0, 0, 5, 0);
		gbc_spdRange.gridx = 1;
		gbc_spdRange.gridy = 2;
		panelRanged.add(spdRange, gbc_spdRange);
		
		label_1 = new JLabel("<html>Attack speed in as per<br> \r\nRS Wiki. Temporary,<br>\r\nuntil I update the itemdb<br>\r\nto  include attack speeds.</html>");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridwidth = 2;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 3;
		panelRanged.add(label_1, gbc_label_1);
		
		panelMagic = new JPanel();
		add(panelMagic);
		setTitleAt(2, "Magic");
		GridBagLayout gbl_panelMagic = new GridBagLayout();
		gbl_panelMagic.columnWidths = new int[]{0, 0, 0};
		gbl_panelMagic.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelMagic.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMagic.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 4.9E-324, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMagic.setLayout(gbl_panelMagic);
		
		spellSelector = new JComboBox<Spell>();
		spellSelector.setModel(new DefaultComboBoxModel(Spell.values()));
		GridBagConstraints gbc_spellSelector = new GridBagConstraints();
		gbc_spellSelector.gridwidth = 2;
		gbc_spellSelector.insets = new Insets(0, 0, 5, 0);
		gbc_spellSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_spellSelector.gridx = 0;
		gbc_spellSelector.gridy = 1;
		panelMagic.add(spellSelector, gbc_spellSelector);
		
	}
	
	public DamageType getDamageType(){
		switch(this.getSelectedIndex()){
		case 0: 
			if(rdbtnStab.isSelected())
				return DamageType.STAB;
			else if(rdbtnSlash.isSelected())
				return DamageType.SLASH;
			else
				return DamageType.CRUSH;
		case 1: return DamageType.RANGED;
		case 2: return DamageType.MAGIC;
		default: return null;
		}
	}
	
	public CombatTriangle getStyle(){
		switch(this.getDamageType()){
		case CRUSH:
		case SLASH:
		case STAB:   return CombatTriangle.MELEE;
		case MAGIC:  return CombatTriangle.MAGIC;
		case RANGED: return CombatTriangle.RANGED;
		}
		return null;
	}
	
	public int getAttackSpeed(){
		switch(this.getDamageType()){
		case STAB:
		case SLASH:
		case CRUSH:  return (int) spdMelee.getValue();
		case RANGED: 
			if(rdbtnRapid.isSelected())
				return (int) spdRange.getValue() - 1;
			else
				return (int) spdRange.getValue();
		case MAGIC:
			switch((Spell)spellSelector.getSelectedItem()){
			case TRIDENT_SEAS:
			case TRIDENT_SWAMP: return 6;
			default: return 5;
			}
		}
		return -1;
	}

	public Spell getSpell() {
		return (Spell) spellSelector.getSelectedItem();
	}
	
	public void addActionListener(ActionListener e){
		rdbtnRapid.addActionListener(e);
		rdbtnLongrange.addActionListener(e);
		rdbtnAccurate_r.addActionListener(e);
		rdbtnAccurate.addActionListener(e);
		rdbtnAggressive.addActionListener(e);
		rdbtnBalanced.addActionListener(e);
		rdbtnDefensive.addActionListener(e);
		rdbtnStab.addActionListener(e);
		rdbtnSlash.addActionListener(e);
		rdbtnCrush.addActionListener(e);
		spellSelector.addActionListener(e);
	}
	
	public void addSliderChangeListener(ChangeListener e){
		spdRange.addChangeListener(e);
		spdMelee.addChangeListener(e);
	}
}
