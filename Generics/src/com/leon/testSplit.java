package com.leon;

public class testSplit {
	public static void main(String [] arg) {
		String area = "abc;dev;;;asdf;132;;23;23";
		String []each = area.split(";");
		for(String a : each) {
			System.out.println("---:"+a);
		}
		
	}
	
	
}
