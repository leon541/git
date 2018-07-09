package com.leon;

import org.apache.commons.lang.StringEscapeUtils;

public class testHTMLEscape {


	public static void main(String [] argv) {
    	String before ="test.exe <body onload=alert('xss')>";
    	System.out.println("before:"+before );
    	String after = StringEscapeUtils.escapeHtml(before) ;
    	
    	System.out.println("after escapeHTMl :"+after );
    	after = StringEscapeUtils.escapeJavaScript(after);
    	//after = StringEscapeUtils.escapeCsv(after);
    	System.out.println("after CSV :"+after );
    	
    	
    	System.out.println("----------------------");
    	
    	
    	
    	System.out.println("my escape  :"+escapeToHTML(before));
    	
    }
	
	
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
}
