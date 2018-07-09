package com.leon;

import java.util.*;
public class testList {

	public static void main(String [] argv) {
		List<String> list = new ArrayList<String> ();
		list.add(new String("abc"));
		list.add(new String("123"));
		list.add(new String("456"));
		list.add(new String("abc"));
		
		printList(list);
		System.out.println("contains abc:?"+list.contains("abc"));
		for(String one: list) {
			if(one.equals("abc"))
				list.remove(one);
				System.out.println("removed one!");
		}
		System.out.println("-----------after----------");
		printList(list);
	}
	
	public static void printList(List<String> list) { 
		for(String one: list) {
			System.out.println("--:"+one);
		}
	}
}
