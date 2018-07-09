package com.leon;

import java.io.*;
import java.util.*;



public class TestHtml {

	private static String moveStyleInContent(String data)
	{
		final String headTagStart = "<head";
		final String headTagFullEnd = "</head>";
		final String styleTagStart = "<style";
		final String styleTagFullEnd = "</style>";
		final String styleTagEmptyEnd = "/>";
		StringBuilder tempData;
		String style;
		int startHead;
		int endHead;
		int startStyle;
		int endStyle;
		int startBodyTag;
		int endBodyTag;
		ArrayList<String> styles = new ArrayList<String>();
		StringBuilder joinedStyles = new StringBuilder();
		if(data != null && data.length() > 0)
		{
			tempData = new StringBuilder(data);
			startHead = tempData.indexOf(headTagStart);
			endHead = tempData.indexOf(headTagFullEnd, startHead + 5);
			startStyle = tempData.indexOf(styleTagStart);
			endStyle = tempData.indexOf(styleTagFullEnd, startStyle + styleTagStart.length());
			// <style> is between <head> and </head>
			//
			for(;startStyle > startHead && startStyle < endHead; startStyle = tempData.indexOf(styleTagStart, startStyle))
			{
				if(tempData.indexOf(styleTagFullEnd, startStyle + styleTagStart.length()) > startHead && tempData.indexOf(styleTagFullEnd, startStyle + styleTagStart.length()) < endHead)
				{
					endStyle = tempData.indexOf(styleTagFullEnd, startStyle + styleTagStart.length());
					style = tempData.substring(startStyle, endStyle + styleTagFullEnd.length());
					styles.add(style);
					endHead -= style.length();
					tempData.replace(startStyle, endStyle + styleTagFullEnd.length(), "");
					
					System.out.println("111111111");
					
				}
				else if(tempData.indexOf(styleTagEmptyEnd, startStyle + styleTagStart.length()) > startHead && tempData.indexOf(styleTagEmptyEnd, startStyle + styleTagStart.length()) < endHead)
				{
					endStyle = tempData.indexOf(styleTagEmptyEnd, startStyle + styleTagStart.length());
					style = tempData.substring(startStyle, endStyle + styleTagEmptyEnd.length());
					styles.add(style);
					endHead -= style.length();
					tempData.replace(startStyle, endStyle + styleTagEmptyEnd.length(), "");
					System.out.println("2222222");
				}
				else
				{
					tempData.replace(startStyle, startStyle + styleTagStart.length(), "");
					System.out.println("3333");
					break;
				}
			}
			startBodyTag = tempData.indexOf("<body");
			endBodyTag = tempData.indexOf(">", startBodyTag + "<body".length());
			for (String string : styles)
			{
				joinedStyles.append("\n" + string);
			}
			tempData.insert(endBodyTag+1, joinedStyles.toString());
		}
		else
		{
			tempData = new StringBuilder();
		}
		return tempData.toString();
	}
	
	
	
	private static String clearStyleInContent(String data)
	{
		try
		{
			if(data==null) 
			{
				data = "";
			}else if(data.length()>0)
			{
				String tmpData = data;
				tmpData = tmpData.toLowerCase();
				
				int startIdx = tmpData.indexOf("<style");
				int endIdx = tmpData.indexOf("</style>",startIdx+6);
				
				while(startIdx!=-1)
				{
					if(endIdx!=-1)
					{
						String prefix = data.substring(0,startIdx);
						String suffix = data.substring(endIdx+8); 
						data = prefix + suffix;
						tmpData = data.toLowerCase();
						startIdx = tmpData.indexOf("<style",startIdx);
						endIdx = tmpData.indexOf("</style>",startIdx+6);
					}else
					{
						endIdx = tmpData.indexOf("/>",startIdx+6);
						if(endIdx!=-1)
						{
							String prefix = data.substring(0,startIdx);
							String suffix = data.substring(endIdx+2); 
							data = prefix + suffix;
							tmpData = data.toLowerCase();
							startIdx = tmpData.indexOf("<style",startIdx);
							endIdx = tmpData.indexOf("</style>",startIdx+6);
						}else
						{
							String prefix = data.substring(0,startIdx);
							String suffix = data.substring(startIdx + 6); 
							data = prefix + suffix;
							break;
						}
					}
				}
				
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			return data;
		}
	}


	
	public static void main(String[] argv) {
		int size = 102400; 
		char [] buffer = new char[size];
		try {
			System.out.println("------------");
			File fis = new File("C:/WangLiang/style.html");
			FileReader fr = new FileReader(fis);
			int length = fr.read(buffer, 0,  size);
			
			fr.close();
			System.out.println("----input--------"+ length);
			String fileContent = moveStyleInContent(new String(buffer, 0, length));
			//String fileContent = clearStyleInContent(new String(buffer, 0, length));
			
			System.out.println("----converted--------"+ fileContent.length());
			
			File fos = new File("C:/WangLiang/style-move.html");
			FileWriter fw = new FileWriter(fos);
			fw.write(fileContent, 0, fileContent.length());
			
			fw.flush();
			fw.close();
			//System.out.println("------------"+ fileContent);

			//StringBuilder sb = new StringBuilder("ABC");
			//sb.d
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


