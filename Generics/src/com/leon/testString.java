package com.leon;

public class testString {
	public static void main(String [] arg) {
		String a1 = "ABCabc";
		String a2 = a1; 

		a2 = a2.toLowerCase();

		System.out.println("a1---------:" + a1);
		System.out.println("a2---------:" + a2);

		String s1="1111";
		String s2="2222";
		refer(s1, s2);
		System.out.println("out s1:"+s1);
		System.out.println("out s2:"+s2);
				
		
	}
	public static String refer(String s1, String s2) {
		
		System.out.println("in s1:"+s1);
		System.out.println("in s2:"+s2);
		s2 = s2.replace('2', '3');		
		System.out.println("in s2:"+s2);
		return s1;
	}
	



}
