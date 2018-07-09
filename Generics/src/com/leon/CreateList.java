package com.leon;

import java.io.*;
public class CreateList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("c:/temp/list.txt");
		try {
			FileWriter fw = new FileWriter(file);
			for(int i = 1; i< 20000; i++ ) {
				//String one = String.valueOf(i) + "@" + String.valueOf(i) + ".com";
				String one =  String.valueOf(i) + ".com";
				System.out.println("---one:"+one);
				fw.write(one);
				fw.write("\r\n");
			}
			fw.close();
			System.out.println("---done!");
		}catch(Exception e) {
				e.printStackTrace();
			}


		}

	}
