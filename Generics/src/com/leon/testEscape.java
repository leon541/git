package com.leon;

public class testEscape {

	public static String escapeToHTML(String s) {
		if(s == null){
			return "";
		}
        StringBuilder sb = new StringBuilder();
        for (int i=0; i< s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '&':
                sb.append("&amp;");
                break;
            case '\"':
                sb.append("&quot;");
                break;
            case '\'':
                sb.append("&acute;");
                break;
            default:
                sb.append(c);
            }
        }
        return sb.toString();
    }
	
		
		public static void main(String [] argv) {
	    	String before ="test.exe <body onload=alert('xss')>";
	    	String after = escapeToHTML(before) ;
	    	System.out.println("before:"+before );
	    	System.out.println("after :"+after );
	    	
	    }
	
}
