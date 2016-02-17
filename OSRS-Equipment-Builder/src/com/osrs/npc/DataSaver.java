package com.osrs.npc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.osrs.items.SlotType;
import com.osrs.levels.LevelType;

public class DataSaver {
	
	public static final int NUM_ITEMS = LevelType.NUM_LEVELS + SlotType.NUM_SLOTS;
	
	public static int[] read(String path, int size){
		DataInputStream in = null;
		int[] result = null;
		try {
			in = new DataInputStream(new FileInputStream(path));
			
			result = new int[size];
			
			for(int j = 0; j < size; j++){
				int i = in.readInt();
				result[j] = i;
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) { }
		}
		return result;
	}
	
	public static int[] read(String path){
		DataInputStream in = null;
		int[] result = null;
		try {
			in = new DataInputStream(new FileInputStream(path));
			
			result = new int[NUM_ITEMS];
			
			for(int j = 0; j < NUM_ITEMS; j++){
				int i = in.readInt();
				result[j] = i;
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) { }
		}
		return result;
	}
	
	public static void save(String path, int[] data){
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream(path));
			for(int i : data){
				out.writeInt(i);
			}
			out.flush();
		} catch(Exception e){
			System.out.println("Something happened:");
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) { }
		}
	}
	
	public static int[] concat(int[] a, int[] b){
		int[] c = new int[a.length + b.length];
		
		for(int i = 0; i < a.length; i++){
			c[i] = a[i];
		}
		
		for(int i = a.length; i < a.length + b.length; i++){
			c[i] = b[i - a.length];
		}
		
		return c;
	}
	
	public static void main(String[] args){
//		byte[] result = read("C:\\Users\\Landon\\Documents\\Napstabloook.plr");
//		for(byte i : result){
//			System.out.println(i);
//		}
		
		String path = "test.plr";
		int[] levels = { 99, 99, 99, 99, 99, 99, 99, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111 };
		
		save(path, levels);
		int[] loaded = read(path);
		
		for(int i = 0; i < NUM_ITEMS; i++){
			System.out.printf(" %d | %d%n", levels[i], loaded[i]);
		}
	}
}
