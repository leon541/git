package com.leon;

public class testMillionSecond {

	public static void main(String [] argv) {
		for(int i = 0; i< 1000; i++ ) {
			System.out.println("-----" + System.currentTimeMillis());
		}
	}
}
