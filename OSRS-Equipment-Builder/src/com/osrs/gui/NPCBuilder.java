package com.osrs.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.osrs.items.StatType;
import com.osrs.levels.LevelType;
import com.osrs.npc.NPC;
import com.osrs.npc.NPCDatabase;

public class NPCBuilder extends JPanel implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3555447265957681003L;
	private JLabel lblMagic, lblRanged, lblDefence, lblStrength, lblNpcName,
				   lblCombatLevel, lblHitpoints, lblStab, lblSlash, lblCrush,
	               lblRngStat, lblMagStat, lblAttStat, lvlDefStat, lblAttack,
	               lblStrStat;

	private JTextField nameField;
	
	private JSeparator separator, separator_1;
	
	private JSpinner spnAttack, spnStrength, spnDefence, spnRanged, spnMagic, spnCombat, spnHitpoints;
	
	private NPC thisNPC;
	
	private final JSpinner[] spnCmbStats = new JSpinner[StatType.NUM_STATS - 2]; //NPCs have no Mage Strength or Prayer Bonus
	
	private static final int SPINNER_WIDTH = 2;
	

	private final NPCDatabase db = new NPCDatabase();

	/**
	 * Create the panel.
	 */
	public NPCBuilder(final NPC myNPC) {
		thisNPC = myNPC;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		lblNpcName = new JLabel("NPC Name");
		GridBagConstraints gbc_lblNpcName = new GridBagConstraints();
		gbc_lblNpcName.gridwidth = 2;
		gbc_lblNpcName.insets = new Insets(0, 0, 5, 5);
		gbc_lblNpcName.gridx = 2;
		gbc_lblNpcName.gridy = 1;
		add(lblNpcName, gbc_lblNpcName);
		
		nameField = new JTextField();
		nameField.setText("Rat");
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 3;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 4;
		gbc_nameField.gridy = 1;
		add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		lblCombatLevel = new JLabel("Combat Level");
		GridBagConstraints gbc_lblCombatLevel = new GridBagConstraints();
		gbc_lblCombatLevel.gridwidth = 2;
		gbc_lblCombatLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCombatLevel.gridx = 2;
		gbc_lblCombatLevel.gridy = 2;
		add(lblCombatLevel, gbc_lblCombatLevel);
		
		spnCombat = new JSpinner();
		spnCombat.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spnCombat = new GridBagConstraints();
		gbc_spnCombat.insets = new Insets(0, 0, 5, 5);
		gbc_spnCombat.gridx = 4;
		gbc_spnCombat.gridy = 2;
		add(spnCombat, gbc_spnCombat);
		
		JButton btnLoad = new JButton("Load NPC");
		
		JButton btnSave = new JButton("Save NPC");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridwidth = 2;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 5;
		gbc_btnSave.gridy = 2;
		add(btnSave, gbc_btnSave);
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoad.gridwidth = 2;
		gbc_btnLoad.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoad.gridx = 5;
		gbc_btnLoad.gridy = 3;
		add(btnLoad, gbc_btnLoad);
		
		lblHitpoints = new JLabel("Hitpoints");
		GridBagConstraints gbc_lblHitpoints = new GridBagConstraints();
		gbc_lblHitpoints.gridwidth = 2;
		gbc_lblHitpoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblHitpoints.gridx = 2;
		gbc_lblHitpoints.gridy = 3;
		add(lblHitpoints, gbc_lblHitpoints);
		
		spnHitpoints = new JSpinner();
		spnHitpoints.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spnHitpoints = new GridBagConstraints();
		gbc_spnHitpoints.insets = new Insets(0, 0, 5, 5);
		gbc_spnHitpoints.gridx = 4;
		gbc_spnHitpoints.gridy = 3;
		add(spnHitpoints, gbc_spnHitpoints);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 4;
		add(separator, gbc_separator);
		
		lblAttack = new JLabel(new ImageIcon("./img/icon_attack.png"));
		//lblAttack.setText("Attack");
		lblStrength = new JLabel(new ImageIcon("./img/icon_strength.png"));
		//lblStrength.setText("Strength");
		lblDefence = new JLabel(new ImageIcon("./img/icon_defence.png"));
		//lblDefence.setText("Defence");
		lblRanged = new JLabel(new ImageIcon("./img/icon_ranged.png"));
		//lblRanged.setText("Ranged");
		lblMagic = new JLabel(new ImageIcon("./img/icon_magic.png"));
		//lblMagic.setText("Magic");
		
		lblAttack.setToolTipText("Attack Level");
		GridBagConstraints gbc_lblAttack = new GridBagConstraints();
		gbc_lblAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttack.gridx = 2;
		gbc_lblAttack.gridy = 5;
		add(lblAttack, gbc_lblAttack);
		
		lblStrength.setToolTipText("Strength Level");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 3;
		gbc_lblStrength.gridy = 5;
		add(lblStrength, gbc_lblStrength);
		
		lblDefence.setToolTipText("Defence Level");
		GridBagConstraints gbc_lblDefence = new GridBagConstraints();
		gbc_lblDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefence.gridx = 4;
		gbc_lblDefence.gridy = 5;
		add(lblDefence, gbc_lblDefence);
		
		lblRanged.setToolTipText("Ranged Level");
		GridBagConstraints gbc_lblRanged = new GridBagConstraints();
		gbc_lblRanged.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRanged.insets = new Insets(0, 0, 5, 5);
		gbc_lblRanged.gridx = 5;
		gbc_lblRanged.gridy = 5;
		add(lblRanged, gbc_lblRanged);

		lblMagic.setToolTipText("Magic Level");
		GridBagConstraints gbc_lblMagic = new GridBagConstraints();
		gbc_lblMagic.insets = new Insets(0, 0, 5, 0);
		gbc_lblMagic.gridx = 6;
		gbc_lblMagic.gridy = 5;
		add(lblMagic, gbc_lblMagic);
		
		spnAttack = new JSpinner();
		spnAttack.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spnAttack = new GridBagConstraints();
		gbc_spnAttack.insets = new Insets(0, 0, 5, 5);
		gbc_spnAttack.gridx = 2;
		gbc_spnAttack.gridy = 6;
		add(spnAttack, gbc_spnAttack);
		
		spnStrength = new JSpinner();
		spnStrength.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spnStrength = new GridBagConstraints();
		gbc_spnStrength.insets = new Insets(0, 0, 5, 5);
		gbc_spnStrength.gridx = 3;
		gbc_spnStrength.gridy = 6;
		add(spnStrength, gbc_spnStrength);
		
		spnDefence = new JSpinner();
		spnDefence.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spnDefence = new GridBagConstraints();
		gbc_spnDefence.insets = new Insets(0, 0, 5, 5);
		gbc_spnDefence.gridx = 4;
		gbc_spnDefence.gridy = 6;
		add(spnDefence, gbc_spnDefence);
		
		spnRanged = new JSpinner();
		spnRanged.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spnRanged = new GridBagConstraints();
		gbc_spnRanged.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnRanged.insets = new Insets(0, 0, 5, 5);
		gbc_spnRanged.gridx = 5;
		gbc_spnRanged.gridy = 6;
		add(spnRanged, gbc_spnRanged);
		
		spnMagic = new JSpinner();
		spnMagic.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spnMagic = new GridBagConstraints();
		gbc_spnMagic.insets = new Insets(0, 0, 5, 0);
		gbc_spnMagic.gridx = 6;
		gbc_spnMagic.gridy = 6;
		add(spnMagic, gbc_spnMagic);
		
		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 7;
		add(separator_1, gbc_separator_1);
		
		lblStab = new JLabel(new ImageIcon("./img/icon_stab.png"));
		lblStab.setToolTipText("Stab");
		GridBagConstraints gbc_lblStab = new GridBagConstraints();
		gbc_lblStab.insets = new Insets(0, 0, 5, 5);
		gbc_lblStab.gridx = 2;
		gbc_lblStab.gridy = 8;
		add(lblStab, gbc_lblStab);
		
		lblSlash = new JLabel(new ImageIcon("./img/icon_slash.png"));
		lblSlash.setToolTipText("Slash");
		GridBagConstraints gbc_lblSlash = new GridBagConstraints();
		gbc_lblSlash.insets = new Insets(0, 0, 5, 5);
		gbc_lblSlash.gridx = 3;
		gbc_lblSlash.gridy = 8;
		add(lblSlash, gbc_lblSlash);
		
		lblCrush = new JLabel(new ImageIcon("./img/icon_crush.png"));
		lblCrush.setToolTipText("Crush");
		GridBagConstraints gbc_lblCrush = new GridBagConstraints();
		gbc_lblCrush.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrush.gridx = 4;
		gbc_lblCrush.gridy = 8;
		add(lblCrush, gbc_lblCrush);
		
		lblRngStat = new JLabel(new ImageIcon("./img/icon_ranged.png"));
		lblRngStat.setToolTipText("Ranged");
		GridBagConstraints gbc_lblRngStat = new GridBagConstraints();
		gbc_lblRngStat.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRngStat.insets = new Insets(0, 0, 5, 5);
		gbc_lblRngStat.gridx = 5;
		gbc_lblRngStat.gridy = 8;
		add(lblRngStat, gbc_lblRngStat);
		
		lblMagStat = new JLabel(new ImageIcon("./img/icon_magic.png"));
		lblMagStat.setToolTipText("Magic");
		GridBagConstraints gbc_lblMagStat = new GridBagConstraints();
		gbc_lblMagStat.insets = new Insets(0, 0, 5, 0);
		gbc_lblMagStat.gridx = 6;
		gbc_lblMagStat.gridy = 8;
		add(lblMagStat, gbc_lblMagStat);
		
		lblAttStat = new JLabel(new ImageIcon("./img/icon_attack.png"));
		lblAttStat.setToolTipText("Offensive Stat");
		GridBagConstraints gbc_lblAttStat = new GridBagConstraints();
		gbc_lblAttStat.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttStat.gridx = 1;
		gbc_lblAttStat.gridy = 9;
		add(lblAttStat, gbc_lblAttStat);
		
		GridBagConstraints[] gbc_spnCmbStats = new GridBagConstraints[spnCmbStats.length];
		for(int i = 0; i < spnCmbStats.length; i++){
			final int j = i;
			StatType s = StatType.fromIndex(i);
			spnCmbStats[i] = new JSpinner();
			gbc_spnCmbStats[i] = new GridBagConstraints();
			gbc_spnCmbStats[i].insets = new Insets(0, 0, 5, 5);
			gbc_spnCmbStats[i].gridx = s.getNPCGrid()[0];
			gbc_spnCmbStats[i].gridy = s.getNPCGrid()[1];
			add(spnCmbStats[i], gbc_spnCmbStats[i]);
			
			((JSpinner.DefaultEditor) spnCmbStats[i].getEditor()).getTextField().setColumns(SPINNER_WIDTH);
			
			spnCmbStats[i].addChangeListener(e -> {
				thisNPC.setStat( (int) spnCmbStats[j].getValue(), s);
			});
		}
		
		lvlDefStat = new JLabel(new ImageIcon("./img/icon_defence.png"));
		lvlDefStat.setText("");
		lvlDefStat.setToolTipText("Defensive Stat");
		GridBagConstraints gbc_lvlDefStat = new GridBagConstraints();
		gbc_lvlDefStat.insets = new Insets(0, 0, 5, 5);
		gbc_lvlDefStat.gridx = 1;
		gbc_lvlDefStat.gridy = 10;
		add(lvlDefStat, gbc_lvlDefStat);
		
		lblStrStat = new JLabel(new ImageIcon("./img/icon_strength.png"));
		lblStrStat.setToolTipText("Strength Bonus");
		GridBagConstraints gbc_lblStrStat = new GridBagConstraints();
		gbc_lblStrStat.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrStat.gridx = 1;
		gbc_lblStrStat.gridy = 11;
		add(lblStrStat, gbc_lblStrStat);
		
		((JSpinner.DefaultEditor) spnAttack.getEditor()).getTextField().setColumns(SPINNER_WIDTH);
		((JSpinner.DefaultEditor) spnStrength.getEditor()).getTextField().setColumns(SPINNER_WIDTH);
		((JSpinner.DefaultEditor) spnDefence.getEditor()).getTextField().setColumns(SPINNER_WIDTH);
		((JSpinner.DefaultEditor) spnRanged.getEditor()).getTextField().setColumns(SPINNER_WIDTH);
		((JSpinner.DefaultEditor) spnMagic.getEditor()).getTextField().setColumns(SPINNER_WIDTH);
		((JSpinner.DefaultEditor) spnCombat.getEditor()).getTextField().setColumns(SPINNER_WIDTH);
		((JSpinner.DefaultEditor) spnHitpoints.getEditor()).getTextField().setColumns(SPINNER_WIDTH);
		
		spnAttack.addChangeListener(e -> {
			thisNPC.setLevel( (int) spnAttack.getValue(), LevelType.ATTACK );
		});
		
		spnStrength.addChangeListener(e -> {
			thisNPC.setLevel( (int) spnStrength.getValue(), LevelType.STRENGTH);
		});
		
		spnDefence.addChangeListener(e -> {
			thisNPC.setLevel( (int) spnDefence.getValue(), LevelType.DEFENCE);
		});
		
		spnMagic.addChangeListener(e -> {
			thisNPC.setLevel( (int) spnMagic.getValue(), LevelType.MAGIC);
		});
		
		spnRanged.addChangeListener(e -> {
			thisNPC.setLevel( (int) spnRanged.getValue(), LevelType.RANGED);
		});
		
		spnHitpoints.addChangeListener(e -> {
			thisNPC.setLevel( (int) spnHitpoints.getValue(), LevelType.HITPOINTS);
		});
		
		spnCombat.addChangeListener(e -> {
			thisNPC.setCombat( (int) spnCombat.getValue() );
		});
		
		btnLoad.addActionListener(e -> {
			String npcname = "";
			
			NPCSelector npcs = new NPCSelector();
			npcname = npcs.showDialog();
			
			loadNPC(npcname);
		});
		
		
		updateAll();
	}
	
	private void loadNPC(String npcname){
		NPC npc = db.getNPC(npcname);
		int[] lvls = npc.getLevels();
		int[] stats = npc.getStats();
		nameField.setText(npc.getName());
		
		spnAttack.setValue(lvls[LevelType.ATTACK.index]);
		spnStrength.setValue(lvls[LevelType.STRENGTH.index]);
		spnDefence.setValue(lvls[LevelType.DEFENCE.index]);
		spnHitpoints.setValue(lvls[LevelType.HITPOINTS.index]);
		spnRanged.setValue(lvls[LevelType.RANGED.index]);
		spnMagic.setValue(lvls[LevelType.MAGIC.index]);
		
		for(StatType s : StatType.values()){
			switch(s){
			case MSC_MAGE:
			case MSC_PRAYER: break;
			default: spnCmbStats[s.index].setValue(stats[s.index]);
			}
		}
		
		spnCombat.setValue(npc.getCombatLevel());
		
		
		updateAll();
	}
	private void updateAll(){
//		ACC_STAB(0), ACC_SLASH(1), ACC_CRUSH(2), ACC_MAGE(3), ACC_RANGE(4),
//		DEF_STAB(5), DEF_SLASH(6), DEF_CRUSH(7), DEF_MAGE(8), DEF_RANGE(9),
//		MSC_MELEE(10), MSC_RANGE(11), MSC_MAGE(12), MSC_PRAYER(13);
		int[] stats = new int[StatType.NUM_STATS];
		for(StatType s : StatType.values()){
			switch(s){
			case MSC_MAGE:
			case MSC_PRAYER: stats[s.index] = 0; break;
			default: stats[s.index] = (int) spnCmbStats[s.index].getValue();
			}
		}
		
		thisNPC.setStats(stats);
		
		//ATTACK(0), STRENGTH(1), DEFENCE(2), RANGED(3), MAGIC(4), PRAYER(5), HITPOINTS(6);
		thisNPC.setLevels(new int[]{
				(int) spnAttack.getValue(),
				(int) spnStrength.getValue(),
				(int) spnDefence.getValue(),
				(int) spnRanged.getValue(),
				(int) spnMagic.getValue(),
				0,
				(int) spnHitpoints.getValue()
		});
		
		thisNPC.setCombat((int)spnCombat.getValue());
	}
	
	public void stateChanged(ChangeEvent e) {
		updateAll();
	}

}
