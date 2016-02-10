package com.osrs.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.osrs.items.ItemFilter;
import com.osrs.items.SlotType;
import com.osrs.items.StatType;
import com.osrs.npc.Player;

public class EquipmentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1607456473402238121L;

	@SuppressWarnings("rawtypes")
	private JComboBox filterMode;

	private JTextField text_mainhand;
	private JTextField text_Offhand;
	private JTextField text_Head;
	private JTextField text_Body;
	private JTextField text_Legs;
	private JTextField text_Boots;
	private JTextField text_Gloves;
	private JTextField text_Amulet;
	private JTextField text_Ring;
	private JTextField text_Cape;
	private JTextField text_Ammo;

	private ItemFilter filter;

	//private Player player;
	private JTextField text_att_stb;
	private JTextField text_def_stb;
	private JTextField text_att_sls;
	private JTextField text_att_crs;
	private JTextField text_att_mag;
	private JTextField text_att_rng;
	private JTextField text_def_rng;
	private JTextField text_def_mag;
	private JTextField text_def_crs;
	private JTextField text_def_sls;
	private JTextField text_msc_str;
	private JTextField text_msc_rng;
	private JTextField text_msc_mag;
	private JTextField text_msc_pry;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EquipmentPanel(final Player player) {
		filter = ItemFilter.BIS;

		GridBagLayout gbl_playerEquipment = new GridBagLayout();
		gbl_playerEquipment.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		gbl_playerEquipment.columnWidths = new int[] { 0, 47, 0, 0 };
		setLayout(gbl_playerEquipment);

		JButton btnMainhand = new JButton("Mainhand");
		GridBagConstraints gbc_btnMainhand = new GridBagConstraints();
		gbc_btnMainhand.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMainhand.insets = new Insets(0, 0, 5, 5);
		gbc_btnMainhand.gridx = 0;
		gbc_btnMainhand.gridy = 0;
		add(btnMainhand, gbc_btnMainhand);

		text_mainhand = new JTextField();
		text_mainhand.setHorizontalAlignment(SwingConstants.CENTER);
		text_mainhand.setText("Empty");
		text_mainhand.setEditable(false);
		GridBagConstraints gbc_text_mainhand = new GridBagConstraints();
		gbc_text_mainhand.gridwidth = 3;
		gbc_text_mainhand.insets = new Insets(0, 0, 5, 0);
		gbc_text_mainhand.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_mainhand.gridx = 1;
		gbc_text_mainhand.gridy = 0;
		add(text_mainhand, gbc_text_mainhand);
		text_mainhand.setColumns(10);

		btnMainhand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.MAINHAND, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.MAINHAND);
					text_mainhand.setText(selection);
					text_Offhand.setText(player.getItemName(SlotType.OFFHAND));
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_mainhand.setText(selection);
					text_Offhand.setText(player.getItemName(SlotType.OFFHAND));
				}
				updateStats(player.getStats());
			}
		});

		JButton btnOffhand = new JButton("Offhand");
		GridBagConstraints gbc_btnOffhand = new GridBagConstraints();
		gbc_btnOffhand.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOffhand.insets = new Insets(0, 0, 5, 5);
		gbc_btnOffhand.gridx = 0;
		gbc_btnOffhand.gridy = 1;
		add(btnOffhand, gbc_btnOffhand);

		text_Offhand = new JTextField();
		text_Offhand.setHorizontalAlignment(SwingConstants.CENTER);
		text_Offhand.setText("Empty");
		text_Offhand.setEditable(false);
		GridBagConstraints gbc_text_Offhand = new GridBagConstraints();
		gbc_text_Offhand.gridwidth = 3;
		gbc_text_Offhand.insets = new Insets(0, 0, 5, 0);
		gbc_text_Offhand.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Offhand.gridx = 1;
		gbc_text_Offhand.gridy = 1;
		add(text_Offhand, gbc_text_Offhand);
		text_Offhand.setColumns(10);

		btnOffhand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.OFFHAND, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.OFFHAND);
					text_Offhand.setText(selection);
					text_mainhand.setText(player.getItemName(SlotType.MAINHAND));
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Offhand.setText(selection);
					text_mainhand.setText(player.getItemName(SlotType.MAINHAND));
				}
				updateStats(player.getStats());
			}
		});

		JButton btnHead = new JButton("Head");
		GridBagConstraints gbc_btnHead = new GridBagConstraints();
		gbc_btnHead.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnHead.insets = new Insets(0, 0, 5, 5);
		gbc_btnHead.gridx = 0;
		gbc_btnHead.gridy = 2;
		add(btnHead, gbc_btnHead);

		text_Head = new JTextField();
		text_Head.setHorizontalAlignment(SwingConstants.CENTER);
		text_Head.setText("Empty");
		text_Head.setEditable(false);
		GridBagConstraints gbc_text_Head = new GridBagConstraints();
		gbc_text_Head.gridwidth = 3;
		gbc_text_Head.insets = new Insets(0, 0, 5, 0);
		gbc_text_Head.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Head.gridx = 1;
		gbc_text_Head.gridy = 2;
		add(text_Head, gbc_text_Head);
		text_Head.setColumns(10);

		btnHead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.HEAD, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.HEAD);
					text_Head.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Head.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JButton btnBody = new JButton("Body");
		GridBagConstraints gbc_btnBody = new GridBagConstraints();
		gbc_btnBody.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBody.insets = new Insets(0, 0, 5, 5);
		gbc_btnBody.gridx = 0;
		gbc_btnBody.gridy = 3;
		add(btnBody, gbc_btnBody);

		text_Body = new JTextField();
		text_Body.setHorizontalAlignment(SwingConstants.CENTER);
		text_Body.setText("Empty");
		text_Body.setEditable(false);
		GridBagConstraints gbc_text_Body = new GridBagConstraints();
		gbc_text_Body.gridwidth = 3;
		gbc_text_Body.insets = new Insets(0, 0, 5, 0);
		gbc_text_Body.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Body.gridx = 1;
		gbc_text_Body.gridy = 3;
		add(text_Body, gbc_text_Body);
		text_Body.setColumns(10);

		btnBody.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.BODY, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.BODY);
					text_Body.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Body.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JButton btnLegs = new JButton("Legs");
		GridBagConstraints gbc_btnLegs = new GridBagConstraints();
		gbc_btnLegs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLegs.insets = new Insets(0, 0, 5, 5);
		gbc_btnLegs.gridx = 0;
		gbc_btnLegs.gridy = 4;
		add(btnLegs, gbc_btnLegs);

		text_Legs = new JTextField();
		text_Legs.setHorizontalAlignment(SwingConstants.CENTER);
		text_Legs.setText("Empty");
		text_Legs.setEditable(false);
		GridBagConstraints gbc_text_Legs = new GridBagConstraints();
		gbc_text_Legs.gridwidth = 3;
		gbc_text_Legs.insets = new Insets(0, 0, 5, 0);
		gbc_text_Legs.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Legs.gridx = 1;
		gbc_text_Legs.gridy = 4;
		add(text_Legs, gbc_text_Legs);
		text_Legs.setColumns(10);

		btnLegs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.LEGS, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.LEGS);
					text_Legs.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Legs.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JButton btnBoots = new JButton("Boots");
		GridBagConstraints gbc_btnBoots = new GridBagConstraints();
		gbc_btnBoots.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBoots.insets = new Insets(0, 0, 5, 5);
		gbc_btnBoots.gridx = 0;
		gbc_btnBoots.gridy = 5;
		add(btnBoots, gbc_btnBoots);

		text_Boots = new JTextField();
		text_Boots.setHorizontalAlignment(SwingConstants.CENTER);
		text_Boots.setText("Empty");
		text_Boots.setEditable(false);
		GridBagConstraints gbc_text_Boots = new GridBagConstraints();
		gbc_text_Boots.gridwidth = 3;
		gbc_text_Boots.insets = new Insets(0, 0, 5, 0);
		gbc_text_Boots.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Boots.gridx = 1;
		gbc_text_Boots.gridy = 5;
		add(text_Boots, gbc_text_Boots);
		text_Boots.setColumns(10);

		btnBoots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.BOOTS, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.BOOTS);
					text_Boots.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Boots.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JButton btnGloves = new JButton("Gloves");
		GridBagConstraints gbc_btnGloves = new GridBagConstraints();
		gbc_btnGloves.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGloves.insets = new Insets(0, 0, 5, 5);
		gbc_btnGloves.gridx = 0;
		gbc_btnGloves.gridy = 6;
		add(btnGloves, gbc_btnGloves);

		text_Gloves = new JTextField();
		text_Gloves.setHorizontalAlignment(SwingConstants.CENTER);
		text_Gloves.setText("Empty");
		text_Gloves.setEditable(false);
		GridBagConstraints gbc_text_Gloves = new GridBagConstraints();
		gbc_text_Gloves.gridwidth = 3;
		gbc_text_Gloves.insets = new Insets(0, 0, 5, 0);
		gbc_text_Gloves.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Gloves.gridx = 1;
		gbc_text_Gloves.gridy = 6;
		add(text_Gloves, gbc_text_Gloves);
		text_Gloves.setColumns(10);

		btnGloves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.GLOVES, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.GLOVES);
					text_Gloves.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Gloves.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JButton btnAmulet = new JButton("Amulet");
		GridBagConstraints gbc_btnAmulet = new GridBagConstraints();
		gbc_btnAmulet.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAmulet.insets = new Insets(0, 0, 5, 5);
		gbc_btnAmulet.gridx = 0;
		gbc_btnAmulet.gridy = 7;
		add(btnAmulet, gbc_btnAmulet);

		text_Amulet = new JTextField();
		text_Amulet.setHorizontalAlignment(SwingConstants.CENTER);
		text_Amulet.setText("Empty");
		text_Amulet.setEditable(false);
		GridBagConstraints gbc_text_Amulet = new GridBagConstraints();
		gbc_text_Amulet.gridwidth = 3;
		gbc_text_Amulet.insets = new Insets(0, 0, 5, 0);
		gbc_text_Amulet.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Amulet.gridx = 1;
		gbc_text_Amulet.gridy = 7;
		add(text_Amulet, gbc_text_Amulet);
		text_Amulet.setColumns(10);

		btnAmulet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.NECK, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.NECK);
					text_Amulet.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Amulet.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JButton btnRing = new JButton("Ring");
		GridBagConstraints gbc_btnRing = new GridBagConstraints();
		gbc_btnRing.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRing.insets = new Insets(0, 0, 5, 5);
		gbc_btnRing.gridx = 0;
		gbc_btnRing.gridy = 8;
		add(btnRing, gbc_btnRing);

		text_Ring = new JTextField();
		text_Ring.setHorizontalAlignment(SwingConstants.CENTER);
		text_Ring.setText("Empty");
		text_Ring.setEditable(false);
		GridBagConstraints gbc_text_Ring = new GridBagConstraints();
		gbc_text_Ring.gridwidth = 3;
		gbc_text_Ring.insets = new Insets(0, 0, 5, 0);
		gbc_text_Ring.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Ring.gridx = 1;
		gbc_text_Ring.gridy = 8;
		add(text_Ring, gbc_text_Ring);
		text_Ring.setColumns(10);

		btnRing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.RING, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.RING);
					text_Ring.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Ring.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JButton btnCape = new JButton("Cape");
		GridBagConstraints gbc_btnCape = new GridBagConstraints();
		gbc_btnCape.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCape.insets = new Insets(0, 0, 5, 5);
		gbc_btnCape.gridx = 0;
		gbc_btnCape.gridy = 9;
		add(btnCape, gbc_btnCape);

		text_Cape = new JTextField();
		text_Cape.setHorizontalAlignment(SwingConstants.CENTER);
		text_Cape.setText("Empty");
		text_Cape.setEditable(false);
		GridBagConstraints gbc_text_Cape = new GridBagConstraints();
		gbc_text_Cape.gridwidth = 3;
		gbc_text_Cape.insets = new Insets(0, 0, 5, 0);
		gbc_text_Cape.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Cape.gridx = 1;
		gbc_text_Cape.gridy = 9;
		add(text_Cape, gbc_text_Cape);
		text_Cape.setColumns(10);

		btnCape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.CAPE, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.CAPE);
					text_Cape.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Cape.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JButton btnAmmo = new JButton("Ammo");
		GridBagConstraints gbc_btnAmmo = new GridBagConstraints();
		gbc_btnAmmo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAmmo.insets = new Insets(0, 0, 5, 5);
		gbc_btnAmmo.gridx = 0;
		gbc_btnAmmo.gridy = 10;
		add(btnAmmo, gbc_btnAmmo);

		text_Ammo = new JTextField();
		text_Ammo.setHorizontalAlignment(SwingConstants.CENTER);
		text_Ammo.setText("Empty");
		text_Ammo.setEditable(false);
		GridBagConstraints gbc_text_Ammo = new GridBagConstraints();
		gbc_text_Ammo.gridwidth = 3;
		gbc_text_Ammo.insets = new Insets(0, 0, 5, 0);
		gbc_text_Ammo.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_Ammo.gridx = 1;
		gbc_text_Ammo.gridy = 10;
		add(text_Ammo, gbc_text_Ammo);
		text_Ammo.setColumns(10);

		btnAmmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelector is = new ItemSelector(SlotType.AMMO, filter);
				is.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				String selection = is.showDialog();
				if (selection.equals("Empty")) {
					player.clearSlot(SlotType.AMMO);
					text_Ammo.setText(selection);
				} else if (!selection.equals("")) {
					player.equip(selection);
					text_Ammo.setText(selection);
				}
				updateStats(player.getStats());
			}
		});

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 4;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 11;
		add(separator_1, gbc_separator_1);

		JLabel lblStabAttack = new JLabel("Stab Attack");
		GridBagConstraints gbc_lblStabAttack = new GridBagConstraints();
		gbc_lblStabAttack.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStabAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblStabAttack.gridx = 0;
		gbc_lblStabAttack.gridy = 12;
		add(lblStabAttack, gbc_lblStabAttack);

		text_att_stb = new JTextField();
		text_att_stb.setEditable(false);
		GridBagConstraints gbc_text_att_stb = new GridBagConstraints();
		gbc_text_att_stb.anchor = GridBagConstraints.NORTH;
		gbc_text_att_stb.insets = new Insets(0, 0, 5, 5);
		gbc_text_att_stb.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_att_stb.gridx = 1;
		gbc_text_att_stb.gridy = 12;
		add(text_att_stb, gbc_text_att_stb);
		text_att_stb.setColumns(10);

		JLabel lblStabDefence = new JLabel("Stab Defence");
		GridBagConstraints gbc_lblStabDefence = new GridBagConstraints();
		gbc_lblStabDefence.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStabDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblStabDefence.gridx = 2;
		gbc_lblStabDefence.gridy = 12;
		add(lblStabDefence, gbc_lblStabDefence);

		text_def_stb = new JTextField();
		text_def_stb.setEditable(false);
		GridBagConstraints gbc_text_def_stb = new GridBagConstraints();
		gbc_text_def_stb.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_def_stb.insets = new Insets(0, 0, 5, 0);
		gbc_text_def_stb.gridx = 3;
		gbc_text_def_stb.gridy = 12;
		add(text_def_stb, gbc_text_def_stb);
		text_def_stb.setColumns(10);

		JLabel lblSlashAttack = new JLabel("Slash Attack");
		GridBagConstraints gbc_lblSlashAttack = new GridBagConstraints();
		gbc_lblSlashAttack.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSlashAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblSlashAttack.gridx = 0;
		gbc_lblSlashAttack.gridy = 13;
		add(lblSlashAttack, gbc_lblSlashAttack);

		text_att_sls = new JTextField();
		text_att_sls.setEditable(false);
		GridBagConstraints gbc_text_att_sls = new GridBagConstraints();
		gbc_text_att_sls.anchor = GridBagConstraints.NORTH;
		gbc_text_att_sls.insets = new Insets(0, 0, 5, 5);
		gbc_text_att_sls.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_att_sls.gridx = 1;
		gbc_text_att_sls.gridy = 13;
		add(text_att_sls, gbc_text_att_sls);
		text_att_sls.setColumns(10);

		JLabel lblSlashDefence = new JLabel("Slash Defence");
		GridBagConstraints gbc_lblSlashDefence = new GridBagConstraints();
		gbc_lblSlashDefence.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSlashDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblSlashDefence.gridx = 2;
		gbc_lblSlashDefence.gridy = 13;
		add(lblSlashDefence, gbc_lblSlashDefence);

		text_def_sls = new JTextField();
		text_def_sls.setEditable(false);
		GridBagConstraints gbc_text_def_sls = new GridBagConstraints();
		gbc_text_def_sls.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_def_sls.insets = new Insets(0, 0, 5, 0);
		gbc_text_def_sls.gridx = 3;
		gbc_text_def_sls.gridy = 13;
		add(text_def_sls, gbc_text_def_sls);
		text_def_sls.setColumns(10);

		JLabel lblCrushAttack = new JLabel("Crush Attack");
		GridBagConstraints gbc_lblCrushAttack = new GridBagConstraints();
		gbc_lblCrushAttack.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCrushAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrushAttack.gridx = 0;
		gbc_lblCrushAttack.gridy = 14;
		add(lblCrushAttack, gbc_lblCrushAttack);

		text_att_crs = new JTextField();
		text_att_crs.setEditable(false);
		GridBagConstraints gbc_text_att_crs = new GridBagConstraints();
		gbc_text_att_crs.anchor = GridBagConstraints.NORTH;
		gbc_text_att_crs.insets = new Insets(0, 0, 5, 5);
		gbc_text_att_crs.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_att_crs.gridx = 1;
		gbc_text_att_crs.gridy = 14;
		add(text_att_crs, gbc_text_att_crs);
		text_att_crs.setColumns(10);

		JLabel lblCrushDefence = new JLabel("Crush Defence");
		GridBagConstraints gbc_lblCrushDefence = new GridBagConstraints();
		gbc_lblCrushDefence.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCrushDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrushDefence.gridx = 2;
		gbc_lblCrushDefence.gridy = 14;
		add(lblCrushDefence, gbc_lblCrushDefence);

		text_def_crs = new JTextField();
		text_def_crs.setEditable(false);
		GridBagConstraints gbc_text_def_crs = new GridBagConstraints();
		gbc_text_def_crs.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_def_crs.insets = new Insets(0, 0, 5, 0);
		gbc_text_def_crs.gridx = 3;
		gbc_text_def_crs.gridy = 14;
		add(text_def_crs, gbc_text_def_crs);
		text_def_crs.setColumns(10);

		JLabel lblMagicAttack = new JLabel("Magic Attack");
		GridBagConstraints gbc_lblMagicAttack = new GridBagConstraints();
		gbc_lblMagicAttack.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMagicAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblMagicAttack.gridx = 0;
		gbc_lblMagicAttack.gridy = 15;
		add(lblMagicAttack, gbc_lblMagicAttack);

		text_att_mag = new JTextField();
		text_att_mag.setEditable(false);
		GridBagConstraints gbc_text_att_mag = new GridBagConstraints();
		gbc_text_att_mag.anchor = GridBagConstraints.NORTH;
		gbc_text_att_mag.insets = new Insets(0, 0, 5, 5);
		gbc_text_att_mag.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_att_mag.gridx = 1;
		gbc_text_att_mag.gridy = 15;
		add(text_att_mag, gbc_text_att_mag);
		text_att_mag.setColumns(10);

		JLabel lblMagicDefence = new JLabel("Magic Defence");
		GridBagConstraints gbc_lblMagicDefence = new GridBagConstraints();
		gbc_lblMagicDefence.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMagicDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblMagicDefence.gridx = 2;
		gbc_lblMagicDefence.gridy = 15;
		add(lblMagicDefence, gbc_lblMagicDefence);

		text_def_mag = new JTextField();
		text_def_mag.setEditable(false);
		GridBagConstraints gbc_text_def_mag = new GridBagConstraints();
		gbc_text_def_mag.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_def_mag.anchor = GridBagConstraints.NORTH;
		gbc_text_def_mag.insets = new Insets(0, 0, 5, 0);
		gbc_text_def_mag.gridx = 3;
		gbc_text_def_mag.gridy = 15;
		add(text_def_mag, gbc_text_def_mag);
		text_def_mag.setColumns(10);

		JLabel lblRangedAttack = new JLabel("Ranged Attack");
		GridBagConstraints gbc_lblRangedAttack = new GridBagConstraints();
		gbc_lblRangedAttack.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRangedAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblRangedAttack.gridx = 0;
		gbc_lblRangedAttack.gridy = 16;
		add(lblRangedAttack, gbc_lblRangedAttack);

		text_att_rng = new JTextField();
		text_att_rng.setEditable(false);
		GridBagConstraints gbc_text_att_rng = new GridBagConstraints();
		gbc_text_att_rng.anchor = GridBagConstraints.NORTH;
		gbc_text_att_rng.insets = new Insets(0, 0, 5, 5);
		gbc_text_att_rng.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_att_rng.gridx = 1;
		gbc_text_att_rng.gridy = 16;
		add(text_att_rng, gbc_text_att_rng);
		text_att_rng.setColumns(10);

		JLabel lblRangedDefence = new JLabel("Ranged Def.");
		GridBagConstraints gbc_lblRangedDefence = new GridBagConstraints();
		gbc_lblRangedDefence.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRangedDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblRangedDefence.gridx = 2;
		gbc_lblRangedDefence.gridy = 16;
		add(lblRangedDefence, gbc_lblRangedDefence);

		text_def_rng = new JTextField();
		text_def_rng.setEditable(false);
		GridBagConstraints gbc_text_def_rng = new GridBagConstraints();
		gbc_text_def_rng.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_def_rng.insets = new Insets(0, 0, 5, 0);
		gbc_text_def_rng.gridx = 3;
		gbc_text_def_rng.gridy = 16;
		add(text_def_rng, gbc_text_def_rng);
		text_def_rng.setColumns(10);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 17;
		add(separator, gbc_separator);

		JLabel lblStrength = new JLabel("Strength");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 0;
		gbc_lblStrength.gridy = 18;
		add(lblStrength, gbc_lblStrength);

		text_msc_str = new JTextField();
		text_msc_str.setEditable(false);
		GridBagConstraints gbc_text_msc_str = new GridBagConstraints();
		gbc_text_msc_str.insets = new Insets(0, 0, 5, 5);
		gbc_text_msc_str.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_msc_str.gridx = 1;
		gbc_text_msc_str.gridy = 18;
		add(text_msc_str, gbc_text_msc_str);
		text_msc_str.setColumns(10);

		JLabel lblRangedStr = new JLabel("Ranged Str.");
		GridBagConstraints gbc_lblRangedStr = new GridBagConstraints();
		gbc_lblRangedStr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRangedStr.insets = new Insets(0, 0, 5, 5);
		gbc_lblRangedStr.gridx = 2;
		gbc_lblRangedStr.gridy = 18;
		add(lblRangedStr, gbc_lblRangedStr);

		text_msc_rng = new JTextField();
		text_msc_rng.setEditable(false);
		GridBagConstraints gbc_text_msc_rng = new GridBagConstraints();
		gbc_text_msc_rng.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_msc_rng.insets = new Insets(0, 0, 5, 0);
		gbc_text_msc_rng.gridx = 3;
		gbc_text_msc_rng.gridy = 18;
		add(text_msc_rng, gbc_text_msc_rng);
		text_msc_rng.setColumns(10);

		JLabel lblMagicDamage = new JLabel("Magic Damage");
		GridBagConstraints gbc_lblMagicDamage = new GridBagConstraints();
		gbc_lblMagicDamage.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMagicDamage.insets = new Insets(0, 0, 5, 5);
		gbc_lblMagicDamage.gridx = 0;
		gbc_lblMagicDamage.gridy = 19;
		add(lblMagicDamage, gbc_lblMagicDamage);

		text_msc_mag = new JTextField();
		text_msc_mag.setEditable(false);
		GridBagConstraints gbc_text_msc_mag = new GridBagConstraints();
		gbc_text_msc_mag.insets = new Insets(0, 0, 5, 5);
		gbc_text_msc_mag.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_msc_mag.gridx = 1;
		gbc_text_msc_mag.gridy = 19;
		add(text_msc_mag, gbc_text_msc_mag);
		text_msc_mag.setColumns(10);

		JLabel lblPrayer = new JLabel("Prayer");
		GridBagConstraints gbc_lblPrayer = new GridBagConstraints();
		gbc_lblPrayer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPrayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrayer.gridx = 2;
		gbc_lblPrayer.gridy = 19;
		add(lblPrayer, gbc_lblPrayer);

		text_msc_pry = new JTextField();
		text_msc_pry.setEditable(false);
		GridBagConstraints gbc_text_msc_pry = new GridBagConstraints();
		gbc_text_msc_pry.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_msc_pry.insets = new Insets(0, 0, 5, 0);
		gbc_text_msc_pry.gridx = 3;
		gbc_text_msc_pry.gridy = 19;
		add(text_msc_pry, gbc_text_msc_pry);
		text_msc_pry.setColumns(10);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 4;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 20;
		add(separator_2, gbc_separator_2);

		JLabel lblFilteringMode = new JLabel("Filtering");
		GridBagConstraints gbc_lblFilteringMode = new GridBagConstraints();
		gbc_lblFilteringMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFilteringMode.insets = new Insets(0, 0, 0, 5);
		gbc_lblFilteringMode.gridx = 0;
		gbc_lblFilteringMode.gridy = 21;
		add(lblFilteringMode, gbc_lblFilteringMode);

		filterMode = new JComboBox(new String[] { "No filtering",
				"No cosmetics", "Commonly used" });
		filterMode.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					Object item = event.getItem();

					if (item.equals("No filtering"))
						refilter(ItemFilter.NONE);
					else if (item.equals("No cosmetics"))
						refilter(ItemFilter.NO_COSMETIC);
					else
						refilter(ItemFilter.BIS);
				}
			}
		});
		filterMode.setSelectedIndex(2);
		GridBagConstraints gbc_filterMode = new GridBagConstraints();
		gbc_filterMode.gridwidth = 3;
		gbc_filterMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterMode.gridx = 1;
		gbc_filterMode.gridy = 21;
		add(filterMode, gbc_filterMode);
		GridBagConstraints gbc_playerPanel = new GridBagConstraints();
		gbc_playerPanel.weightx = 0.33;
		gbc_playerPanel.fill = GridBagConstraints.BOTH;
		gbc_playerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_playerPanel.gridx = 0;
		gbc_playerPanel.gridy = 0;

		updateStats(player.getStats());

		// pack();
	}

	private void refilter(ItemFilter f) {
		this.filter = f;
	}

	private void updateStats(int[] stats) {
		text_att_stb.setText(stats[StatType.ACC_STAB.index] + "");
		text_att_sls.setText(stats[StatType.ACC_SLASH.index] + "");
		text_att_crs.setText(stats[StatType.ACC_CRUSH.index] + "");
		text_att_mag.setText(stats[StatType.ACC_MAGE.index] + "");
		text_att_rng.setText(stats[StatType.ACC_RANGE.index] + "");

		text_def_stb.setText(stats[StatType.DEF_STAB.index] + "");
		text_def_sls.setText(stats[StatType.DEF_SLASH.index] + "");
		text_def_crs.setText(stats[StatType.DEF_CRUSH.index] + "");
		text_def_mag.setText(stats[StatType.DEF_MAGE.index] + "");
		text_def_rng.setText(stats[StatType.DEF_RANGE.index] + "");

		text_msc_str.setText(stats[StatType.MSC_MELEE.index] + "");
		text_msc_rng.setText(stats[StatType.MSC_RANGE.index] + "");
		text_msc_mag.setText(stats[StatType.MSC_MAGE.index] + "%");
		text_msc_pry.setText(stats[StatType.MSC_PRAYER.index] + "");
	}

}
