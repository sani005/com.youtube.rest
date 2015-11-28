package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.OracleSani005;
import com.youtube.util.ToJSON;

/**
 * This class is used to manage computer parts inventory.
 * 
 * At this point v1/inventory should be deprecated and a date should be set 
 * for this java class to be deleted.
 * 
 * @author Sani005
 */
@Path("/v1/inventory")
public class V1_inventory {
	
	/**
	 * This method will return all computer parts that are listed
	 * in PC_PARTS table.
	 * 
	 * Note: This is a good method for a tutorial but probably should never
	 * has a method that returns everything from a database.  There should be
	 * built in limits.
	 *
	 * @return - JSON array string
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		Response rb = null;
		
		try {
			conn = OracleSani005.OracleSani005Conn().getConnection();
			query = conn.prepareStatement("select * " + "from sani005.PC_PARTS");	
			
			ResultSet rs = query.executeQuery();
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
			query.close(); // close connection
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();
			


		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
		}
		return rb;
		
	
	}
	
}
