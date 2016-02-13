package com.osrs.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

import com.osrs.items.StatType;
import com.osrs.levels.CombatTriangle;
import com.osrs.levels.LevelType;
import com.osrs.levels.Potions;
import com.osrs.levels.Prayers;
import com.osrs.levels.PrayersList;
import com.osrs.npc.Damage;
import com.osrs.npc.Player;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {

	private NumberFormatter levelFormat;
	private boolean[] potionsActive;
	private boolean[] prayersActive;
	private JTextField prayDrain;
	private JTextField txtCbl;
	private JSpinner spin_magic;
	private JSpinner spin_strength;
	private JSpinner spin_ranged;
	private JSpinner spin_hitpoints;
	private JSpinner spin_attack;
	private JSpinner spin_defence;
	private JSpinner spin_prayer;
	private JTextField txtMaxHit;
	private StyleSelector styleSelector;
	private JTextField nameLookup;
	
	private static final String NAME_VERIFICATION = "[a-zA-Z0-9]([a-zA-Z0-9\\s\\-]){0,10}[a-zA-Z0-9]?";
	
	/**
	 * Create the panel.
	 */
	public PlayerPanel(final Player player) {
		levelFormat = new NumberFormatter();
		levelFormat.setMinimum(new Integer(1));
		levelFormat.setMaximum(new Integer(999));
		
		potionsActive = new boolean[Potions.NUM_POTIONS];
		prayersActive = new boolean[Prayers.NUM_PRAYERS];
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 50, 40, 50, 50, 40, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblUsernameLookup = new JLabel("Username Lookup");
		GridBagConstraints gbc_lblUsernameLookup = new GridBagConstraints();
		gbc_lblUsernameLookup.gridwidth = 3;
		gbc_lblUsernameLookup.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsernameLookup.anchor = GridBagConstraints.EAST;
		gbc_lblUsernameLookup.gridx = 0;
		gbc_lblUsernameLookup.gridy = 0;
		add(lblUsernameLookup, gbc_lblUsernameLookup);
		
		nameLookup = new JTextField();
		GridBagConstraints gbc_nameLookup = new GridBagConstraints();
		gbc_nameLookup.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLookup.gridwidth = 4;
		gbc_nameLookup.insets = new Insets(0, 0, 5, 0);
		gbc_nameLookup.gridx = 3;
		gbc_nameLookup.gridy = 0;
		add(nameLookup, gbc_nameLookup);
		nameLookup.setColumns(10);
		
		spin_attack = new JSpinner();
		spin_attack.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spin_attack.setValue(player.getLevel(LevelType.ATTACK));
		((JSpinner.DefaultEditor) spin_attack.getEditor()).getTextField().setColumns(3);
		
		JLabel lblAttack = new JLabel("Attack");
		GridBagConstraints gbc_lblAttack = new GridBagConstraints();
		gbc_lblAttack.anchor = GridBagConstraints.EAST;
		gbc_lblAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttack.gridx = 2;
		gbc_lblAttack.gridy = 1;
		add(lblAttack, gbc_lblAttack);
		GridBagConstraints gbc_spin_attack = new GridBagConstraints();
		gbc_spin_attack.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_attack.insets = new Insets(0, 0, 5, 5);
		gbc_spin_attack.gridx = 3;
		gbc_spin_attack.gridy = 1;
		add(spin_attack, gbc_spin_attack);
		
		JLabel lblHitpoints = new JLabel("Hitpoints");
		GridBagConstraints gbc_lblHitpoints = new GridBagConstraints();
		gbc_lblHitpoints.anchor = GridBagConstraints.EAST;
		gbc_lblHitpoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblHitpoints.gridx = 4;
		gbc_lblHitpoints.gridy = 1;
		add(lblHitpoints, gbc_lblHitpoints);
		
		spin_hitpoints = new JSpinner();
		spin_hitpoints.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
		spin_hitpoints.setValue(player.getLevel(LevelType.HITPOINTS));
		GridBagConstraints gbc_spin_hitpoints = new GridBagConstraints();
		gbc_spin_hitpoints.insets = new Insets(0, 0, 5, 5);
		gbc_spin_hitpoints.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_hitpoints.gridx = 5;
		gbc_spin_hitpoints.gridy = 1;
		add(spin_hitpoints, gbc_spin_hitpoints);
		
		JLabel lblStrength = new JLabel("Strength");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.anchor = GridBagConstraints.EAST;
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 2;
		gbc_lblStrength.gridy = 2;
		add(lblStrength, gbc_lblStrength);
		
		spin_strength = new JSpinner();
		spin_strength.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spin_strength.setValue(player.getLevel(LevelType.STRENGTH));
		GridBagConstraints gbc_spin_strength = new GridBagConstraints();
		gbc_spin_strength.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_strength.insets = new Insets(0, 0, 5, 5);
		gbc_spin_strength.gridx = 3;
		gbc_spin_strength.gridy = 2;
		add(spin_strength, gbc_spin_strength);
		
		JLabel lblRanged = new JLabel("Ranged");
		GridBagConstraints gbc_lblRanged = new GridBagConstraints();
		gbc_lblRanged.anchor = GridBagConstraints.EAST;
		gbc_lblRanged.insets = new Insets(0, 0, 5, 5);
		gbc_lblRanged.gridx = 4;
		gbc_lblRanged.gridy = 2;
		add(lblRanged, gbc_lblRanged);
		
		spin_ranged = new JSpinner();
		spin_ranged.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spin_ranged.setValue(player.getLevel(LevelType.RANGED));
		GridBagConstraints gbc_spin_ranged = new GridBagConstraints();
		gbc_spin_ranged.insets = new Insets(0, 0, 5, 5);
		gbc_spin_ranged.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_ranged.gridx = 5;
		gbc_spin_ranged.gridy = 2;
		add(spin_ranged, gbc_spin_ranged);
		
		JLabel lblDefence = new JLabel("Defence");
		GridBagConstraints gbc_lblDefence = new GridBagConstraints();
		gbc_lblDefence.anchor = GridBagConstraints.EAST;
		gbc_lblDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefence.gridx = 2;
		gbc_lblDefence.gridy = 3;
		add(lblDefence, gbc_lblDefence);
		
		spin_defence = new JSpinner();
		spin_defence.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		spin_defence.setValue(player.getLevel(LevelType.DEFENCE));
		GridBagConstraints gbc_spin_defence = new GridBagConstraints();
		gbc_spin_defence.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_defence.insets = new Insets(0, 0, 5, 5);
		gbc_spin_defence.gridx = 3;
		gbc_spin_defence.gridy = 3;
		add(spin_defence, gbc_spin_defence);
		
		JLabel lblMagic = new JLabel("Magic");
		GridBagConstraints gbc_lblMagic = new GridBagConstraints();
		gbc_lblMagic.anchor = GridBagConstraints.EAST;
		gbc_lblMagic.insets = new Insets(0, 0, 5, 5);
		gbc_lblMagic.gridx = 4;
		gbc_lblMagic.gridy = 3;
		add(lblMagic, gbc_lblMagic);
		
		spin_magic = new JSpinner();
		spin_magic.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spin_magic.setValue(player.getLevel(LevelType.MAGIC));
		GridBagConstraints gbc_spin_magic = new GridBagConstraints();
		gbc_spin_magic.insets = new Insets(0, 0, 5, 5);
		gbc_spin_magic.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_magic.gridx = 5;
		gbc_spin_magic.gridy = 3;
		add(spin_magic, gbc_spin_magic);
		
		JLabel lblPrayer = new JLabel("Prayer");
		GridBagConstraints gbc_lblPrayer = new GridBagConstraints();
		gbc_lblPrayer.anchor = GridBagConstraints.EAST;
		gbc_lblPrayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrayer.gridx = 4;
		gbc_lblPrayer.gridy = 4;
		add(lblPrayer, gbc_lblPrayer);
		
		spin_prayer = new JSpinner();
		spin_prayer.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spin_prayer.setValue(player.getLevel(LevelType.PRAYER));
		GridBagConstraints gbc_spin_prayer = new GridBagConstraints();
		gbc_spin_prayer.insets = new Insets(0, 0, 5, 5);
		gbc_spin_prayer.fill = GridBagConstraints.HORIZONTAL;
		gbc_spin_prayer.gridx = 5;
		gbc_spin_prayer.gridy = 4;
		add(spin_prayer, gbc_spin_prayer);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridwidth = 5;
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 5;
		add(separator, gbc_separator);
		
		JButton btnSelectPotions = new JButton("Select Potions");
		GridBagConstraints gbc_btnSelectPotions = new GridBagConstraints();
		gbc_btnSelectPotions.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSelectPotions.gridwidth = 3;
		gbc_btnSelectPotions.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectPotions.gridx = 2;
		gbc_btnSelectPotions.gridy = 6;
		add(btnSelectPotions, gbc_btnSelectPotions);
		
		JButton btnSelectPrayers = new JButton("Select Prayers");
		GridBagConstraints gbc_btnSelectPrayers = new GridBagConstraints();
		gbc_btnSelectPrayers.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSelectPrayers.gridwidth = 3;
		gbc_btnSelectPrayers.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectPrayers.gridx = 2;
		gbc_btnSelectPrayers.gridy = 7;
		add(btnSelectPrayers, gbc_btnSelectPrayers);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridwidth = 6;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 8;
		add(separator_1, gbc_separator_1);
		
		JLabel lblCombatLevel = new JLabel("Combat Level:");
		GridBagConstraints gbc_lblCombatLevel = new GridBagConstraints();
		gbc_lblCombatLevel.gridwidth = 2;
		gbc_lblCombatLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCombatLevel.gridx = 1;
		gbc_lblCombatLevel.gridy = 9;
		add(lblCombatLevel, gbc_lblCombatLevel);
		
		txtCbl = new JTextField();
		txtCbl.setEditable(false);
		GridBagConstraints gbc_txtCbl = new GridBagConstraints();
		gbc_txtCbl.gridwidth = 2;
		gbc_txtCbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCbl.insets = new Insets(0, 0, 5, 5);
		gbc_txtCbl.gridx = 3;
		gbc_txtCbl.gridy = 9;
		add(txtCbl, gbc_txtCbl);
		txtCbl.setColumns(10);
		
		JLabel lblPrayerDrain = new JLabel("Prayer Drain:");
		GridBagConstraints gbc_lblPrayerDrain = new GridBagConstraints();
		gbc_lblPrayerDrain.gridwidth = 2;
		gbc_lblPrayerDrain.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrayerDrain.gridx = 1;
		gbc_lblPrayerDrain.gridy = 10;
		add(lblPrayerDrain, gbc_lblPrayerDrain);
		
		prayDrain = new JTextField();
		prayDrain.setEditable(false);
		GridBagConstraints gbc_prayDrain = new GridBagConstraints();
		gbc_prayDrain.gridwidth = 2;
		gbc_prayDrain.fill = GridBagConstraints.HORIZONTAL;
		gbc_prayDrain.insets = new Insets(0, 0, 5, 5);
		gbc_prayDrain.gridx = 3;
		gbc_prayDrain.gridy = 10;
		add(prayDrain, gbc_prayDrain);
		prayDrain.setColumns(10);
		
		JLabel lblMaxHit = new JLabel("Max Hit:");
		GridBagConstraints gbc_lblMaxHit = new GridBagConstraints();
		gbc_lblMaxHit.gridwidth = 2;
		gbc_lblMaxHit.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxHit.gridx = 1;
		gbc_lblMaxHit.gridy = 11;
		add(lblMaxHit, gbc_lblMaxHit);
		
		txtMaxHit = new JTextField();
		txtMaxHit.setEditable(false);
		GridBagConstraints gbc_txtMaxHit = new GridBagConstraints();
		gbc_txtMaxHit.gridwidth = 2;
		gbc_txtMaxHit.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMaxHit.insets = new Insets(0, 0, 5, 5);
		gbc_txtMaxHit.gridx = 3;
		gbc_txtMaxHit.gridy = 11;
		add(txtMaxHit, gbc_txtMaxHit);
		txtMaxHit.setColumns(10);
		
		styleSelector = new StyleSelector();
		GridBagConstraints gbc_styleSelector = new GridBagConstraints();
		gbc_styleSelector.gridwidth = 7;
		gbc_styleSelector.fill = GridBagConstraints.BOTH;
		gbc_styleSelector.gridx = 0;
		gbc_styleSelector.gridy = 12;
		add(styleSelector, gbc_styleSelector);
		
		((JSpinner.DefaultEditor) spin_attack.getEditor()).getTextField().setColumns(3);
		((JSpinner.DefaultEditor) spin_ranged.getEditor()).getTextField().setColumns(3);
		
		//Action Listeners below!
		spin_attack.addChangeListener(e -> {
			player.setLevel((int) spin_attack.getValue(), LevelType.ATTACK);
			updateFields(player);
		});
		
		spin_strength.addChangeListener(e -> {
			player.setLevel((int) spin_strength.getValue(), LevelType.STRENGTH);
			updateFields(player);
		});

		spin_defence.addChangeListener(e -> {
			player.setLevel((int) spin_defence.getValue(), LevelType.DEFENCE);
			updateFields(player);
		});
		
		spin_ranged.addChangeListener(e -> {
			player.setLevel((int) spin_ranged.getValue(), LevelType.RANGED);
			updateFields(player);
		});
		
		spin_magic.addChangeListener(e -> {
			player.setLevel((int) spin_magic.getValue(), LevelType.MAGIC);
			updateFields(player);
		});
		
		spin_prayer.addChangeListener(e -> {
			player.setLevel((int) spin_prayer.getValue(), LevelType.PRAYER);
			updateFields(player);
		});
		
		spin_hitpoints.addChangeListener(e -> {
			player.setLevel((int) spin_hitpoints.getValue(), LevelType.HITPOINTS);
			updateFields(player);
		});


		btnSelectPotions.addActionListener(e -> {
			PotionSelector ps = new PotionSelector();
			ps.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			
			potionsActive = player.getPotionsList().getAll();
			
			boolean[] newActive = ps.display(potionsActive);
			potionsActive = newActive == null ? potionsActive : newActive;
			
			player.setPotions(potionsActive);
			
			updateFields(player);
		});
		
		btnSelectPrayers.addActionListener(e -> {
			PrayerSelector ps = new PrayerSelector();
			ps.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			
			prayersActive = player.getPrayersList().getAll();
			
			boolean[] newActive = ps.display(prayersActive);
			prayersActive = newActive == null ? prayersActive : newActive;
			
			player.setPrayers(prayersActive);
			
			updateFields(player);
		});
		
		styleSelector.addChangeListener(e -> {
			updateFields(player);
		});
		
		styleSelector.addSliderChangeListener(e -> {
			updateFields(player);
		});
		
		styleSelector.addActionListener(e -> {
			updateFields(player);
		});
		
		nameLookup.addActionListener(e -> {
			if(nameLookup.getText().matches(NAME_VERIFICATION)){
				String name = nameLookup.getText();
				int[] levels = Player.getLevels(name);
				
				if(levels == null){
					JOptionPane.showMessageDialog(null, "Username not found!");
				} else {
					player.setLevels(levels);
					updateSpinners(player);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Invalid username!");
			}
		});
		
		updateFields(player);

	}
	
	private void updateSpinners(Player player){
		int[] levels = player.getLevels();
		spin_attack.setValue(levels[LevelType.ATTACK.index]);
		spin_strength.setValue(levels[LevelType.STRENGTH.index]);
		spin_defence.setValue(levels[LevelType.DEFENCE.index]);
		spin_ranged.setValue(levels[LevelType.RANGED.index]);
		spin_hitpoints.setValue(levels[LevelType.HITPOINTS.index]);
		spin_magic.setValue(levels[LevelType.MAGIC.index]);
		spin_prayer.setValue(levels[LevelType.PRAYER.index]);
	}
	
	private void updateFields(Player player){
		player.setLevels(new int[] {
			(int) spin_attack.getValue(),
			(int) spin_strength.getValue(),
			(int) spin_defence.getValue(),
			(int) spin_ranged.getValue(),
			(int) spin_magic.getValue(),
			(int) spin_prayer.getValue(),
			(int) spin_hitpoints.getValue()
		});
		
		
		txtCbl.setText(String.format("%.2f",player.getCombatLevelExact()));
		
		PrayersList prl = player.getPrayersList();
		prl.setValues(prayersActive);
		prayDrain.setText(String.format("%.2f / s",prl.getDrainRate(player.getStat(StatType.MSC_PRAYER))));
		
		CombatTriangle styleInUse = styleSelector.getStyle();
		
		player.setSpell(styleSelector.getSpell());
		
		txtMaxHit.setText(Damage.getMaxHit(player, styleInUse) + "");
	}
}
