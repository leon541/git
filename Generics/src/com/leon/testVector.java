package com.leon;


import java.util.*;
import java.io.*;



public class testVector {
	public static void main(String [] argv) {
		String messageIds = "111,222,333,444";
		String [] ids = messageIds.split(",");
		Vector<String> vid = new Vector<String>();
		StringBuffer sb = new StringBuffer();

		for(String one: ids) {
			vid.add(one);
			sb.append(one).append(",");
		}

		Long l1 = new Long(111L);



		System.out.println("vid contais 111:"+vid.contains(String.valueOf(l1)));
		System.out.println("vector size:"+vid.size());
		System.out.println("SB:" +sb.toString());


		sb.deleteCharAt(sb.length()-1);
		System.out.println("SB removed: " +sb.toString());

		Boolean ok = new Boolean(false);
		System.out.println("--before:"+ok);
		refer(ok);
		System.out.println("--after:"+ok);

		String over = null;
		boolean bb = Boolean.parseBoolean(over);
		System.out.println("----------- ccc: "+bb );

		System.out.println("----------- over: "+over );

		File file= null;
		try {
			file = new File(over);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if( file == null) {
			System.out.println("file is null");
		} else {
			System.out.println("file is NOT null");
		}


	}

	public static void refer(Boolean yes) {
		yes = Boolean.TRUE;


	}

}
