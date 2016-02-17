package com.osrs.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.osrs.npc.Damage;
import com.osrs.npc.Fightable;

@SuppressWarnings("serial")
public class DPSViewer extends JPanel {
	private JTextField txthit;
	private JTextField txtdps;
	private JButton btnUpdate;

//	private Fightable a, b;
	
	/**
	 * Create the panel.
	 */
	public DPSViewer(Fightable a, Fightable b) {
//		this.a = a;
//		this.b = b;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(e -> {
			double hitChance = Damage.getHitChance(a, b);
			int    maxHit    = Damage.getMaxHit(a, a.getAttackType());
			double dph       = hitChance * maxHit / 2;
			
			double sec = 1;
			
			double dps = dph / sec;
			
			txthit.setText(String.format("%.2f%%",100 * hitChance));
			txtdps.setText(String.format("%.2f",dps));
		});
		
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdate.gridx = 3;
		gbc_btnUpdate.gridy = 0;
		add(btnUpdate, gbc_btnUpdate);
		
		JLabel lblHitChance = new JLabel("Hit Chance");
		GridBagConstraints gbc_lblHitChance = new GridBagConstraints();
		gbc_lblHitChance.insets = new Insets(0, 0, 5, 5);
		gbc_lblHitChance.anchor = GridBagConstraints.EAST;
		gbc_lblHitChance.gridx = 1;
		gbc_lblHitChance.gridy = 1;
		add(lblHitChance, gbc_lblHitChance);
		
		txthit = new JTextField();
		txthit.setEditable(false);
		GridBagConstraints gbc_txthit = new GridBagConstraints();
		gbc_txthit.insets = new Insets(0, 0, 5, 0);
		gbc_txthit.fill = GridBagConstraints.HORIZONTAL;
		gbc_txthit.gridx = 3;
		gbc_txthit.gridy = 1;
		add(txthit, gbc_txthit);
		txthit.setColumns(10);
		
		JLabel lblDps = new JLabel("DPS");
		GridBagConstraints gbc_lblDps = new GridBagConstraints();
		gbc_lblDps.anchor = GridBagConstraints.EAST;
		gbc_lblDps.insets = new Insets(0, 0, 0, 5);
		gbc_lblDps.gridx = 1;
		gbc_lblDps.gridy = 2;
		add(lblDps, gbc_lblDps);
		
		txtdps = new JTextField();
		txtdps.setEditable(false);
		GridBagConstraints gbc_txtdps = new GridBagConstraints();
		gbc_txtdps.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtdps.gridx = 3;
		gbc_txtdps.gridy = 2;
		add(txtdps, gbc_txtdps);
		txtdps.setColumns(10);
		


	}

}
