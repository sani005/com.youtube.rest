package com.youtube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.util.ToJSON;

public class SchemaSani005 extends OracleSani005 {
	
	
	public int insertIntoPC_PARTS(String PC_PARTS_TITLE, 
			                      String PC_PARTS_CODE, 
			                      String PC_PARTS_MAKER, 
			                      String PC_PARTS_AVAIL, 
			                      String PC_PARTS_DESC) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			/**
			 * In real application, you should do validation here 
			 * before starting to insert data into the database
			 */
			
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("insert into sani005.PC_PARTS " + "(PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC) " 
											+ "VALUES ( ?, ?, ?, ?, ? ) ");
			
			query.setString(1, PC_PARTS_TITLE);
			query.setString(2,  PC_PARTS_CODE);
			query.setString(3,  PC_PARTS_MAKER);
			
			int avilInt = Integer.parseInt(PC_PARTS_AVAIL);
			query.setInt(4, avilInt);
			query.setString(5,  PC_PARTS_DESC);
			query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 500;
			
		} finally {
			if (conn != null) conn.close();
		}
		return 200;
		
	}
	

	
	public JSONArray queryReturnBrandParts(String brand) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC "  + 
			                               "from sani005.PC_PARTS " + "where UPPER(PC_PARTS_MAKER) = ? ");
			
			query.setString(1, brand.toUpperCase());
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch (SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch (Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}
	
	public JSONArray queryReturnBrandItemNumber(String brand, int item_number) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC "  + 
			                               "from sani005.PC_PARTS " + 
					                       "where UPPER(PC_PARTS_MAKER) = ? " +
			                               "and  PC_PARTS_CODE = ?");
			
			query.setString(1, brand.toUpperCase());
			query.setInt(2, item_number);
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch (SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch (Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}
}
