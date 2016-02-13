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
import com.osrs.levels.Prayers;
import com.osrs.levels.PrayersList;

@SuppressWarnings("serial")
public class PrayerSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final PrayersList pl = new PrayersList();
	
	private final JCheckBox[] chckbxPrayers = new JCheckBox[Prayers.NUM_PRAYERS];
	
	private boolean[] selected, selectedOnInit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PrayerSelector dialog = new PrayerSelector();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			boolean[] selectedOnInit = new boolean[Prayers.NUM_PRAYERS];
			for(int i = 0; i < Prayers.NUM_PRAYERS; i++)
				selectedOnInit[i] = true;
			dialog.display(selectedOnInit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean[] display(boolean[] selectedOnInit){
		this.selectedOnInit = selectedOnInit;
		pl.setValues(selectedOnInit);
		updateCheckboxes(this.selectedOnInit);
		setVisible(true);
		return selected;
	}
	public boolean[] display(){
		this.selectedOnInit = null;
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
	public PrayerSelector() {
		setResizable(false);
		setTitle("Select your Potions");
		setBounds(100, 100, 750, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		
		GridBagConstraints[] gbc_chckbxPrayers = new GridBagConstraints[Prayers.NUM_PRAYERS];
		
		for(int i = 0; i < chckbxPrayers.length; i++){
			final int j = i;
			Prayers pray = Prayers.fromIndex(i);
			chckbxPrayers[i] = new JCheckBox(pray.name);
			gbc_chckbxPrayers[i] = new GridBagConstraints();
			gbc_chckbxPrayers[i].insets = new Insets(0, 0, 5, 5);
			gbc_chckbxPrayers[i].gridx = pray.gridx;
			gbc_chckbxPrayers[i].gridy = pray.gridy;
			gbc_chckbxPrayers[i].anchor = GridBagConstraints.WEST;
			contentPanel.add(chckbxPrayers[i], gbc_chckbxPrayers[i]);
			
			chckbxPrayers[i].addActionListener(e -> {
				if(chckbxPrayers[j].isSelected())
					pl.add(Prayers.fromIndex(j));
				else
					pl.remove(Prayers.fromIndex(j));
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
		
		if(selectedOnInit != null)
			updateCheckboxes(selectedOnInit);
	}
	
	public void clear(){
		pl.clear();
		updateCheckboxes();
	}
	
	public void updateCheckboxes(boolean[] isSelected){
		for(int i = 0; i < isSelected.length; i++){
			chckbxPrayers[i].setSelected(isSelected[i]);
		}
	}
	
	public void updateCheckboxes(){
		boolean[] isSelected = pl.getAll();
		updateCheckboxes(isSelected);
	}
}
