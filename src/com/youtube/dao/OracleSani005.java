package com.youtube.dao;

import javax.naming.*;
import javax.sql.*;

public class OracleSani005 {
	
	private static DataSource OracleSani005 = null;
	private static Context context = null;
	
	public static DataSource OracleSani005Conn() throws Exception {
		if (OracleSani005 != null) {
			return OracleSani005;
		}
		
		try {
			if (context == null) {
				context = new InitialContext();
			}
			OracleSani005 = (DataSource) context.lookup("Sani005Oracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return OracleSani005;
	}
	
	
}
