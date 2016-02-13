package com.osrs.gui;

import javax.swing.JTabbedPane;

import com.osrs.npc.Player;

@SuppressWarnings("serial")
public class PlayerTabbedPanel extends JTabbedPane{

	/**
	 * Create the panel.
	 */
	public PlayerTabbedPanel(Player p) {
		if(p == null)
			p = new Player();
		PlayerPanel playerPanel = new PlayerPanel(p);
		add(playerPanel);
		setTitleAt(0, "Player");
		
		EquipmentPanel equipmentPanel = new EquipmentPanel(p);
		add(equipmentPanel);
		setTitleAt(1, "Equipment");

	}

}
