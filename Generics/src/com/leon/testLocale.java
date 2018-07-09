package com.leon;

import java.util.*;

public class testLocale {

	public static void main(String [] argv) {
		Locale[] locals = Locale.getAvailableLocales();
		for(Locale loc : locals) {
			System.out.println("---------"+loc.toString() + " "+loc.getDisplayLanguage() + " "+loc.getCountry() + " " + loc.getLanguage());
			System.out.println("TimeZone:"+TimeZone.getDefault().getDisplayName(loc));
			System.out.println("\n\n");
		}


		//Locale loc2 = getLocale("pl");
		Locale loc2 = getLocale("pl_PL");
		if(loc2 != null) {
			System.out.println("-pl--------"+loc2.toString() + " "+loc2.getDisplayLanguage() + " "+loc2.getCountry() + " " + loc2.getLanguage());
			System.out.println("TimeZone:"+TimeZone.getDefault().getDisplayName(loc2));
		} else {
			System.out.println(" loc2 is null");
		}
		
	}
	
	
	public static  Locale getLocale(String language){
		Locale[] ls = Locale.getAvailableLocales();
		// query with country code:
		for(int i=0; i<ls.length; i++){
			
			String con = ls[i].getCountry();
			String lan = ls[i].getLanguage();
			
			if(con !=null && con.trim().length()>0){
				if(language.equals(lan+"_"+con))
					return ls[i];
			}else{
			}
		}
		// query without country code:
		//Fix CR58636
		String lang = language.split("_")[0];
		for(int i=0; i<ls.length; i++){
			
			String con = ls[i].getCountry();
			String lan = ls[i].getLanguage();
			
			if(con !=null && con.trim().length()>0){
			}else{
				if(lang.equals(lan))
					return ls[i];
			}
		}
		return null;
	}
	
}
