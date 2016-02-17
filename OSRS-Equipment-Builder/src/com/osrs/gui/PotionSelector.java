package com.osrs.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.osrs.levels.Potions;
import com.osrs.levels.PotionsList;

@SuppressWarnings("serial")
public class PotionSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final PotionsList pl = new PotionsList();
	private boolean[] selected, selectedOnInit;
	private final JCheckBox[] chckbxPotions = new JCheckBox[Potions.NUM_POTIONS];
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
		pl.setValues(selectedOnInit);
		setSelected(selectedOnInit);
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
		
		GridBagConstraints[] gbc_chckbxPotions = new GridBagConstraints[Potions.NUM_POTIONS];
		for(int i = 0; i < Potions.NUM_POTIONS; i++){
			final int j = i;
			Potions pot = Potions.fromIndex(i);
			chckbxPotions[i] = new JCheckBox(pot.name);
			gbc_chckbxPotions[i] = new GridBagConstraints();
			gbc_chckbxPotions[i].insets = new Insets(0, 0, 5, 5);
			gbc_chckbxPotions[i].gridx = pot.gridx;
			gbc_chckbxPotions[i].gridy = pot.gridy;
			gbc_chckbxPotions[i].anchor = GridBagConstraints.WEST;
			contentPanel.add(chckbxPotions[i], gbc_chckbxPotions[i]);
			
			chckbxPotions[i].addActionListener(e -> {
				if(chckbxPotions[j].isSelected())
					pl.add(Potions.fromIndex(j));
				else
					pl.remove(Potions.fromIndex(j));
				updateCheckboxes();
			});
		}
		
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
		
		if(selected != null)
			setSelected(selectedOnInit);
		
		setResizable(false);
	}
	
	public void clear(){
		pl.clear();
		updateCheckboxes();
	}
	
	public void setSelected(boolean[] isSelected){
		for(int i = 0; i < Potions.NUM_POTIONS; i++){
			chckbxPotions[i].setSelected(isSelected[i]);
		}
	}
	
	public void updateCheckboxes(){
		boolean[] isSelected = pl.getAll();

		setSelected(isSelected);
	}
}
