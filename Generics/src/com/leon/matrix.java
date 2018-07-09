package com.leon;

public class matrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//print(5);
		print(3);
	}

	public static void print(int size) {
		for(int i=0 ;i<= size; i++)  {
			for (int j=0; j<=size; j++) {
				
				if(i ==0) {
					System.out.print(j);
				} else if (j==0) {
					System.out.print(i);
				} else {
					System.out.print(i*j);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}	
}
