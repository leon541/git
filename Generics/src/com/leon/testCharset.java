package com.leon;

import java.util.regex.*;


public class testCharset {

	public static String getCharsetFromContentType(String contentType) {
		if(contentType == null) return null;
		Pattern p=Pattern.compile("charset[\\s]*=[\\s\"']*([^\\s\"';]*)");
		Matcher m = p.matcher(contentType);
		if(m.find()) {
			String charset =  m.group(1);
			if(charset != null)
				return charset.trim();
		} 
		return null;
	}
	
	public static void main (String [] argv) {

		String contentType [] = {"Content-Type: text/html; charset =            windows-1251  ", 
								"Content-Type: text/html; charset =   \"         windows-1251  \" ; abc=txt",
								"Content-Type: text/html;        charset=\"windows-1251\"",
								"Content-Type: text/html;        charset=windows-1251", 
								"Content-Type: text/html;        charset='windows-1251'",
								"Content-Type: text/html;        charset  =  '  windows-1251  ' ",
								"Content-Type: text/html;        charsets  =  '  windows-1251  ' ",
								"Content-Type: text/html;        charset '  windows-1251  ' ",
								"Content-Type: text/html;        sets  =  '  windows-1251  ' ",
								};
/*
		Pattern p=Pattern.compile("charset[\\s]*=[\\s\"']*([^\\s\"';]*)");
		Matcher m = p.matcher(contentType);
		if(m.find()) {
			String set = m.group(1);
			System.out.println("found!"+set + "!"+set.length());
		} else {
			System.out.println("Notfound!");
		}
*/			
	/*	
		for(String one: contentType) {
			System.out.println("content type:"+ one + "\n     charset:"+getCharsetFromContentType(one));
		}
	    
	*/
		
		String testIps [] = {"111.111.222.333", 
								"1.2.3",
								"abc.1.1.1",
				"444.444.444.555",
				"444.444.444.555.55",
				"444.444.",
				};
		
		for(String ip: testIps) {
			System.out.println("value: "+ ip + " " + isLikeIP(ip));
		}
		
	}
	
	
	 public static boolean isLikeIP(String value) {
		 Pattern p= Pattern.compile("^\\d{0,3}\\.?\\d{0,3}\\.?\\d{0,3}\\.?\\d{0,3}$");
		 Matcher m = p.matcher(value);
		 if(m.find()) {
			 return true; 
		 } else 
			 return false;
		 
	 }
	
}
