package com.leon;

import java.util.*;
public class testContains {
	public static void main(String [] arg) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem("1","1" ));
		list.add(new SelectItem("2","2" ));
		list.add(new SelectItem("3","3" ));
		
		
		SelectItem one = new SelectItem("1","1");
		System.out.println("-------contains:"+ list.contains(one));
	}
	
}
