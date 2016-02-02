package com.osrs.items;
public enum SlotType {
	MAINHAND("Mainhand"), OFFHAND("Offhand"), TWOHAND("Two-hand"), 
	HEAD("Helmet"), BODY("Body"), LEGS("Legs"), CAPE("Cape"),
	GLOVES("Gloves"), BOOTS("Boots"), NECK("Neck"), RING("Ring"), AMMO("Ammo");
	
	private String name;
	
	private SlotType(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
