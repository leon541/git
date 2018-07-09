package com.leon;

import java.math.*;

public class GList<T> { 
	private T[] list; 
	private int size;
	private int pos;
	public GList (int num) {
		this.size = num;
		this.pos = 0 ;
		this.list = (T[]) new Object[size];
		//this.list =  new T[size];
	}
	public void add(T t) {
		list[pos] = t;
		pos++;
	}
	public String toString() {
		String all = "";
		for(int i= 0; i< pos ; i++) {
			all += list[i] + " ";
		}
		return all;
	}

	public static void main(String [] argv) {
		GList<Integer> list = new GList<Integer> (100);
		list.add(5);
		list.add(new Integer(10));
		System.out.println("----:"+list.toString());
		
		//String [] test = new String []  ();
		
		
		
		
	}
	
}
