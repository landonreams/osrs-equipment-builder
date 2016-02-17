package com.osrs.gui;

import javax.swing.JTabbedPane;

import com.osrs.npc.Player;

@SuppressWarnings("serial")
public class PlayerTabbedPanel extends JTabbedPane{
	private PlayerPanel playerPanel;
	private EquipmentPanel equipmentPanel;

	/**
	 * Create the panel.
	 */
	public PlayerTabbedPanel(Player p) {
		if(p == null)
			p = new Player();
		playerPanel = new PlayerPanel(p);
		add(playerPanel);
		setTitleAt(0, "Player");
		
		equipmentPanel = new EquipmentPanel(p);
		add(equipmentPanel);
		setTitleAt(1, "Equipment");

	}
	
	public void update(Player p){
		playerPanel.update(p);
		equipmentPanel.update(p);
	}

}
