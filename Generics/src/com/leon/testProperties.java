package com.leon;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Enumeration;

public class testProperties {
	public static void main(String [] argv) throws Exception {
		File dbFile = new File("c:/temp/siem.properties");
		if (!dbFile.exists()) {
			System.out.println("not exist!");
		}
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(dbFile));
			props.load(in);
			if (props != null) {
				Enumeration<String> keys= (Enumeration<String>) props.propertyNames();
				while(keys.hasMoreElements()){
					System.out.println("--------:"+keys.nextElement());
				}
				
			} else {
				System.out.println("proper is nullt!");	
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
