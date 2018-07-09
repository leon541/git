package com.leon;

public class IntSet {
	private int [] array;
	private int num;
	public IntSet () {
		array = new int[100];
		num = 0;
	}
	
	public void add(int x){
		
	}
	public IntSet setUnion(IntSet a, IntSet b) {
		IntSet c = new IntSet();
		for(int i =0; i< a.num ; i++)
			c.add(a.array[i]);
		
		
		return c;
	}
	
}
