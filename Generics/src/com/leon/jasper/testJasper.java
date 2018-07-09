package com.leon.jasper;

import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.engine.JRException;
public class testJasper {

	public static void main(String [] args) {

		String fileName = null;
		boolean isXMLFile = false;

		for (int i = 0; i < args.length; i++)
		{
			if ( args[i].startsWith("-XML") )
			{
				isXMLFile = true;
			}
			else if ( args[i].startsWith("-F") )
			{
				fileName = args[i].substring(2);
			}
			else
			{
				fileName = args[i];
			}
		}

		if(fileName == null)
		{
			usage();
			return;
		}

		if (!isXMLFile && fileName.endsWith(".jrxml"))
		{
			isXMLFile = true;
		}

		try
		{
			JasperDesignViewer.viewReportDesign(fileName, isXMLFile);
		}
		catch (JRException e)
		{
			System.exit(1);
		}

	}
	
	private static void usage()
	{
		System.out.println( "JasperDesignViewer usage:" );
		System.out.println( "\tjava JasperDesignViewer [-XML] file" );
	}
}