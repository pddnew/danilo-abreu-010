package br.com.internetbanking.util;

import java.sql.Timestamp;


/**
 * Utility for SQL Convert and other stuffs
 * */
public class SQL {

	/**
	 * Converts java.util.Date in java.sql.Date
	 * <BR/>
	 * @param date java.util.Date
	 * @return java.sql.Date
	 * */
	public static java.sql.Date toSQLDate(java.util.Date date){
		if(date != null)
			return new java.sql.Date(date.getTime());		
		return null;
	}
	
	
	/**
	 * Converts java.util.Date in java.sql.Timestamp
	 * <BR/>
	 * @param date java.util.Date
	 * @return java.sql.Timestamp
	 * */
	public static java.sql.Timestamp toSQLTimeStamp(java.util.Date date){
		if(date != null)
			return new Timestamp(date.getTime());			
		return null;
	}
}
