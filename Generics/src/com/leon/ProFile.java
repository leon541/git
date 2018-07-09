package com.leon;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ProFile {
	public static void main(String [] argv) {
		//String enfile = "c:/temp/ApplicationMessages.properties" ;
		//String enfile = "c:/temp/PolicyMessages.properties" ;
		String enfile = "c:/temp/ResourceMessages.properties" ;
		
		Vector<String> en_keys = new Vector<String> ();
		Hashtable<String, String> en_values= new Hashtable<String, String>();

//		String lang_file = "c:/temp/ApplicationMessages_pl.properties" ;
//		String lang_file_output = "c:/temp/ApplicationMessages_pl_new.properties" ;

//		String lang_file = "c:/temp/PolicyMessages_pl.properties" ;
//		String lang_file_output = "c:/temp/PolicyMessages_pl_new.properties" ;

		String lang_file = "c:/temp/ResourceMessages_pl.properties" ;
		String lang_file_output = "c:/temp/ResourceMessages_pl_new.properties" ;

		
		
		ProFile.readFile(enfile, en_keys, en_values);	


		Vector<String> lang_keys = new Vector<String> ();
		Hashtable<String, String> lang_values= new Hashtable<String, String>();
		ProFile.readFile(lang_file, lang_keys, lang_values);

		System.out.println("-------------begin to write -------------");

		ProFile.writeFile(lang_file_output , en_keys, en_values, lang_values);

	}

	
	public static void writeFile(String filename,Vector<String> en_keys,  Hashtable<String, String> en_values, Hashtable<String, String> lang_values) {
		try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
	
			int empty_lines = 0;
			int comment_lines = 0;
			int missed_lines = 0;
			int keyvalue_lines = 0;
			
			for(String aline : en_keys) {
				if(aline.equalsIgnoreCase("") ) {
					System.out.println("empty line!");
					bw.newLine();
					empty_lines ++;
				} else {
					String en_value = en_values.get(aline);
					if(en_value == null) {
						System.out.println("comments  line!"+ aline);
						bw.write(aline);
						bw.newLine();
						comment_lines ++;
					}else {
						String lang_value = lang_values.get(aline);
						if(lang_value == null) {
							System.out.println("missed  line!"+ aline);
							bw.write(aline);
							bw.write("=");
							bw.write(en_value);
							bw.newLine();
							missed_lines ++;
							
						} else {
							System.out.println("found line!"+ aline +"="+lang_value);
							bw.write(aline);
							bw.write("=");
							bw.write(lang_value);
							bw.newLine();
							keyvalue_lines ++ ;
							
						}
					}
				}

			}

			bw.flush();
			bw.close();
			fw.close();
			
			
			System.out.println("empty_lines  : "+ empty_lines);
			System.out.println("comment_lines: "+ comment_lines);
			System.out.println("missed_lines: "+ missed_lines);
			
			System.out.println("keyvalue_lines: "+ keyvalue_lines);
			
			System.out.println("-----end---------------write file:"+filename);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ;
	}

	/**
	 * read propert file into a Hashtable
	 * @param filename
	 * @return
	 */
	public static void readFile(String filename, Vector<String> keys,  Hashtable<String, String> values) {
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);

			System.out.println("--------------------read file:"+filename);
			String aline = null;
			int comment_lines = 0;
			int justkey_lines = 0;
			int keyvalue_lines = 0;
			
			while( (aline= br.readLine())!= null) {
				String [] key_value = aline.split("=");
				if(aline.indexOf("#")!= -1) {
					keys.add(aline);
					comment_lines++;
				} else if(key_value.length == 1) {
					keys.add(key_value[0]);
					System.out.println("just key:"+key_value[0]);
					justkey_lines++;
				} else {
					System.out.println("key and value: "+key_value[0].trim() + "="+key_value[1].trim());
					keyvalue_lines ++;
					keys.add(key_value[0].trim());
					values.put(key_value[0].trim(), key_value[1].trim());
				}
			}
			br.close();
			fr.close();
			System.out.println("comments line: "+ comment_lines);
			System.out.println("just key line: "+ justkey_lines);
			System.out.println("key and value: "+ keyvalue_lines);
			System.out.println("-----end---------------read file:"+filename);
			
			return ;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return ;

	}

}


