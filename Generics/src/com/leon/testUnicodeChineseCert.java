package com.leon;

public class testUnicodeChineseCert {
	public static void main(String [] argv) {
		byte [] unid = new byte [100];
		unid[0] = (byte) 0xE5;
		unid[1] = (byte) 0x8C;
		unid[2] = (byte) 0x97;
		unid[3] = (byte) 0xE4;
		unid[4] = (byte) 0xBA;
		unid[5] = (byte) 0xAC;
		try {
			//System.get
			String subject = new String(unid, 0, 6, "utf-8");
			String beijing = "北京";
			System.out.println("subject:"+subject);
			System.out.println("beijing:"+beijing);
			
			if(subject.equalsIgnoreCase(beijing)) {
				System.out.println("equal");
			} else {
				System.out.println("not equal");
			}
		} catch  (Exception e) {
			e.printStackTrace();
		}
		
		
		
		//String escaped = "mail.jingtian.com";
		//String escaped = "mail.jingtian.com,\\xE4\\xBF\\xA1\\xE6\\x81\\xAF\\xE6\\x8A\\x80\\xE6\\x9C\\xAF\\xE9\\x83\\xA8,\\xE5\\x8C\\x97\\xE4\\xBA";
		
		String escaped = "mail.jingtian.com,\\XE4\\xBF\\xA1\\xE6\\x81\\XAF\\xE6\\x8A\\x80\\xE6\\X9C\\xAF\\xE9\\x83\\xA8,\\xE5\\x8C\\x97\\xE4\\xBA";
		//String escaped = "\\xE4\\xBF\\xA1\\xE6\\x81\\xAF\\xE6\\x8A\\x80\\xE6\\x9C\\xAF\\xE9\\x83\\xA8,\\xE5\\x8C\\x97\\xE4\\xBA";
		
		
		System.out.println("-----before:"+escaped);
		System.out.println("-----after:"+convertFromExcape(escaped));
	}
	/**
	 * this function is used to convert the excaped utf-8 string into the real utf-8 string, which is the 
	 * 
	 * the  input would like "mail.jingtian.com,\xE4\xBF\xA1"  where E4BFA1  is utf-8 representation of  a chinese char  "信"
	 * the output  would like "mail.jingtian.com,信" 
	 *     
	 * 
	 * @param before
	 * @return
	 */
	public static String convertFromExcape(String before) {
		if(before == null )
			return "";
		int MAX_SIZE = 64 * 1024;  // max converted size in UTF string
		byte [] before_byte_stream = before.getBytes();
		byte [] after_byte_stream = new byte [MAX_SIZE];
		int after_index = 0;
		int before_index = 0;
		
		for(; before_index <  before_byte_stream.length;) {
			if( before_byte_stream [before_index] == (byte) '\\'  &&  ( before_byte_stream [before_index +1] == (byte)'x' || before_byte_stream [before_index +1] == (byte)'X')
					&& (before_index + 4 ) <= before_byte_stream.length) {  // \x or \X and \x++ is also in the range.
				
				char [] hex = new char [2];  // get two following chars
				hex[0] = (char) before_byte_stream[before_index + 2];
				hex[1] = (char) before_byte_stream[before_index + 3];
				
				after_byte_stream[ after_index ] = (byte)Integer.parseInt(new String(hex), 16);  // change it into int using HEX, then byte  
				after_index++;
				before_index +=4;
			} else { // just copy
				after_byte_stream[ after_index] = before_byte_stream [before_index];
				after_index++; 
				before_index++;
			}
			if(after_index >= MAX_SIZE) 
				break;
		}
		try {
			String after_string = new String(after_byte_stream,0, after_index, "utf-8");
			return after_string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}

	/*
	
	import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

	public byte[] hexToBytes(String hexString) {
	     HexBinaryAdapter adapter = new HexBinaryAdapter();
	     byte[] bytes = adapter.unmarshal(hexString);
	     return bytes;
	}
	*/
	
	
}
