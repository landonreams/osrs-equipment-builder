package com.osrs.items;

public enum ItemFilter {
	NONE("No filter"), COMMON("Common items"), NO_COSMETIC("No cosmetics"), CUSTOM("Custom items");
	
	public final String name;
	
	private ItemFilter(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
