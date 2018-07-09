package com.leon;

public class test2digit {
	public static void main(String [] argv) {
		String score = "5.1";
		System.out.println("before:"+score);
		int dot = score.indexOf('.');
		if(dot != -1) { 
			score = score.substring(0, (dot + 3 > score.length())? score.length() : dot +3 );
		}
		
		
		
		int size = 785; 
		String sizeString = String.valueOf((785d/1024));
		
		
		System.out.println("before:" + sizeString + " after:"+getDigit(sizeString, 2));
		
	}
	private static String getDigit(String input, int n) {
		if(input == null)
			return null;
		int index_dot = input.indexOf('.');
		if(index_dot  != -1) { 
			return input.substring(0, (index_dot + (n+1) > input.length())? input.length() : index_dot + (n+1));	
		} else {
			return input;
		}
	}
	
	
	
}
