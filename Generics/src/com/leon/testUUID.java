package com.leon;


import java.util.UUID;
public class testUUID {
	
	public static void main(String [] argv) {
		
		for( int i = 0 ; i < 1000; i++) {
			
			System.out.println("---uuid:"+UUID.randomUUID().toString());
		}
	}

}
