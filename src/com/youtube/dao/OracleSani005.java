package com.youtube.dao;

import java.sql.Connection;

import javax.naming.*;
import javax.sql.*;

public class OracleSani005 {

	private static DataSource OracleSani005 = null;
	private static Context context = null;
	
	/**
	 * This is a public method that will return the Sani005 database connection
	 * 
	 * @return Database object
	 * @throws Exception	
	 */	
	public static DataSource OracleSani005Conn() throws Exception {
		/**
		 * check to see if the database object is already defined...
		 * if it is, then return the connection, no need to look it up again.
		 */
		
		if (OracleSani005 != null) {
			return OracleSani005;
		}
		
		try {
			
			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in weblogic
			 * Sani005 will hold the database object
			 */
			if (context == null) {
				context = new InitialContext();
			}
			OracleSani005 = (DataSource) context.lookup("Sani005Oracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return OracleSani005;
	}
	
	protected static Connection oraclePcPartsConnection() {
		Connection conn = null;
		try {
			conn = OracleSani005Conn().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
