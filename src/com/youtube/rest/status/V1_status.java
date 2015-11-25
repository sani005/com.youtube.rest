package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/**
 * This is the root path for our restful api service
 * In the web.xml file, we specified that /api/* need to be in the URL
 * to get to this class
 * 
 * We are versioning the class in the URL path. This is the first version V1.
 * Example how to get to the root of this api resource:
 * http://localhost:7001/com.youtube.rest/api/v1/status
 * 
 * @Sani005
 * 
 */
@Path("/v1/status/")
public class V1_status {
	//version of the api
	private static final String api_version = "00.01.00";
	
	/**
	 * This method sits at the root of the api. It will return the name of this api.
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Services</p>";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root. You will need to 
	 * add version into the URL path.
	 * 
	 * Example"
	 * http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
}
