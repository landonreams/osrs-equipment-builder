package com.osrs.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.text.NumberFormatter;

import com.osrs.npc.LevelType;
import com.osrs.npc.Player;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {

	private NumberFormatter levelFormat;
	
	/**
	 * Create the panel.
	 */
	public PlayerPanel(final Player player) {
		levelFormat = new NumberFormatter();
		levelFormat.setMinimum(new Integer(1));
		levelFormat.setMaximum(new Integer(999));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 38, 0, 37, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAttack = new JLabel("Attack");
		GridBagConstraints gbc_lblAttack = new GridBagConstraints();
		gbc_lblAttack.anchor = GridBagConstraints.EAST;
		gbc_lblAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttack.gridx = 0;
		gbc_lblAttack.gridy = 1;
		add(lblAttack, gbc_lblAttack);
		
		JSpinner spin_attack = new JSpinner();
		spin_attack.setValue(player.getLevel(LevelType.ATTACK));
		GridBagConstraints gbc_spin_attack = new GridBagConstraints();
		gbc_spin_attack.insets = new Insets(0, 0, 5, 5);
		gbc_spin_attack.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_attack.gridx = 1;
		gbc_spin_attack.gridy = 1;
		add(spin_attack, gbc_spin_attack);
		
		JLabel lblHitpoints = new JLabel("Hitpoints");
		GridBagConstraints gbc_lblHitpoints = new GridBagConstraints();
		gbc_lblHitpoints.anchor = GridBagConstraints.EAST;
		gbc_lblHitpoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblHitpoints.gridx = 2;
		gbc_lblHitpoints.gridy = 1;
		add(lblHitpoints, gbc_lblHitpoints);
		
		JSpinner spin_hitpoints = new JSpinner();
		spin_hitpoints.setValue(player.getLevel(LevelType.HITPOINTS));
		GridBagConstraints gbc_spin_hitpoints = new GridBagConstraints();
		gbc_spin_hitpoints.insets = new Insets(0, 0, 5, 0);
		gbc_spin_hitpoints.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_hitpoints.gridx = 3;
		gbc_spin_hitpoints.gridy = 1;
		add(spin_hitpoints, gbc_spin_hitpoints);
		
		JLabel lblStrength = new JLabel("Strength");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.anchor = GridBagConstraints.EAST;
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 0;
		gbc_lblStrength.gridy = 2;
		add(lblStrength, gbc_lblStrength);
		
		JSpinner spin_strength = new JSpinner();
		spin_strength.setValue(player.getLevel(LevelType.STRENGTH));
		GridBagConstraints gbc_spin_strength = new GridBagConstraints();
		gbc_spin_strength.insets = new Insets(0, 0, 5, 5);
		gbc_spin_strength.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_strength.gridx = 1;
		gbc_spin_strength.gridy = 2;
		add(spin_strength, gbc_spin_strength);
		
		JLabel lblRanged = new JLabel("Ranged");
		GridBagConstraints gbc_lblRanged = new GridBagConstraints();
		gbc_lblRanged.anchor = GridBagConstraints.EAST;
		gbc_lblRanged.insets = new Insets(0, 0, 5, 5);
		gbc_lblRanged.gridx = 2;
		gbc_lblRanged.gridy = 2;
		add(lblRanged, gbc_lblRanged);
		
		JSpinner spin_ranged = new JSpinner();
		spin_ranged.setValue(player.getLevel(LevelType.RANGED));
		GridBagConstraints gbc_spin_ranged = new GridBagConstraints();
		gbc_spin_ranged.insets = new Insets(0, 0, 5, 0);
		gbc_spin_ranged.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_ranged.gridx = 3;
		gbc_spin_ranged.gridy = 2;
		add(spin_ranged, gbc_spin_ranged);
		
		JLabel lblDefence = new JLabel("Defence");
		GridBagConstraints gbc_lblDefence = new GridBagConstraints();
		gbc_lblDefence.anchor = GridBagConstraints.EAST;
		gbc_lblDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefence.gridx = 0;
		gbc_lblDefence.gridy = 3;
		add(lblDefence, gbc_lblDefence);
		
		JSpinner spin_defence = new JSpinner();

		spin_defence.setValue(player.getLevel(LevelType.DEFENCE));
		GridBagConstraints gbc_spin_defence = new GridBagConstraints();
		gbc_spin_defence.insets = new Insets(0, 0, 5, 5);
		gbc_spin_defence.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_defence.gridx = 1;
		gbc_spin_defence.gridy = 3;
		add(spin_defence, gbc_spin_defence);
		
		JLabel lblMagic = new JLabel("Magic");
		GridBagConstraints gbc_lblMagic = new GridBagConstraints();
		gbc_lblMagic.anchor = GridBagConstraints.EAST;
		gbc_lblMagic.insets = new Insets(0, 0, 5, 5);
		gbc_lblMagic.gridx = 2;
		gbc_lblMagic.gridy = 3;
		add(lblMagic, gbc_lblMagic);
		
		JSpinner spin_magic = new JSpinner();
		spin_magic.setValue(player.getLevel(LevelType.MAGIC));
		GridBagConstraints gbc_spin_magic = new GridBagConstraints();
		gbc_spin_magic.insets = new Insets(0, 0, 5, 0);
		gbc_spin_magic.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_magic.gridx = 3;
		gbc_spin_magic.gridy = 3;
		add(spin_magic, gbc_spin_magic);
		
		JLabel lblPrayer = new JLabel("Prayer");
		GridBagConstraints gbc_lblPrayer = new GridBagConstraints();
		gbc_lblPrayer.anchor = GridBagConstraints.EAST;
		gbc_lblPrayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrayer.gridx = 2;
		gbc_lblPrayer.gridy = 4;
		add(lblPrayer, gbc_lblPrayer);
		
		JSpinner spin_prayer = new JSpinner();
		spin_prayer.setValue(player.getLevel(LevelType.PRAYER));
		GridBagConstraints gbc_spin_prayer = new GridBagConstraints();
		gbc_spin_prayer.insets = new Insets(0, 0, 5, 0);
		gbc_spin_prayer.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_prayer.gridx = 3;
		gbc_spin_prayer.gridy = 4;
		add(spin_prayer, gbc_spin_prayer);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 4;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 5;
		add(separator, gbc_separator);
		
		JButton btnSelectPotions = new JButton("Select Potions");
		GridBagConstraints gbc_btnSelectPotions = new GridBagConstraints();
		gbc_btnSelectPotions.gridwidth = 2;
		gbc_btnSelectPotions.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectPotions.gridx = 1;
		gbc_btnSelectPotions.gridy = 6;
		add(btnSelectPotions, gbc_btnSelectPotions);
		
		JButton btnSelectPrayers = new JButton("Select Prayers");
		GridBagConstraints gbc_btnSelectPrayers = new GridBagConstraints();
		gbc_btnSelectPrayers.gridwidth = 2;
		gbc_btnSelectPrayers.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelectPrayers.gridx = 1;
		gbc_btnSelectPrayers.gridy = 7;
		add(btnSelectPrayers, gbc_btnSelectPrayers);
		
		

	}

}
