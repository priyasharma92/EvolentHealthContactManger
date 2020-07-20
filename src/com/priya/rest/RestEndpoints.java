package com.priya.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.priya.util.Database;

@Path("/")
public class RestEndpoints {

	@Path("/deleteContactDetails")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response deleteCD(
			@FormParam("cid") String cid, 
			@FormParam("uid") String uid) throws URISyntaxException {
		int status=401;
		if(cid!=null && uid!=null) {
			deleteContactDetails(uid,cid);
			status=200;
		}
		return Response.status(status).build();
	}
	
	
	private void deleteContactDetails(String u, String c) {
		try {
			int uid = Integer.parseInt(u);
			int cid = Integer.parseInt(c);
			Connection con = Database.getDatabaseConnection();
			PreparedStatement p = con.prepareStatement("DELETE FROM CONTACT_DETAILS WHERE USERID=? and CID=?");
			p.setInt(1, uid);
			p.setInt(2, cid);
			int i = p.executeUpdate();
			if(i>0) {
				con.commit();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Path("/editContactDetails")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response editCD(
			@FormParam("cid") String cid, 
			@FormParam("uid") String uid,
			@FormParam("value") String value) throws URISyntaxException {
		int status=401;
		if(cid!=null && uid!=null && value!=null) {
			editContactDetails(uid,cid,value);
			status=200;
		}
		return Response.status(status).build();
	}
	
	
	private void editContactDetails(String u, String c, String v) {
		try {
			int uid = Integer.parseInt(u);
			int cid = Integer.parseInt(c);
			Connection con = Database.getDatabaseConnection();
			PreparedStatement p = con.prepareStatement("UPDATE CONTACT_DETAILS SET VALUE=? WHERE USERID=? and CID=?");
			p.setString(1, v);
			p.setInt(2, uid);
			p.setInt(3, cid);
			int i = p.executeUpdate();
			if(i>0) {
				con.commit();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@POST
	@Path("/status")
	public Response updateStatus(
			@FormParam("uid") String uid,
			@FormParam("status") Boolean status) throws URISyntaxException {
		int st=401;
		if(uid!=null) {
			updateContact(uid,status);
			st=200;
		}
		return Response.status(st).build();
	}

	private void updateContact(String u, Boolean status) {
		try {
			int uid = Integer.parseInt(u);
			Connection con = Database.getDatabaseConnection();
			PreparedStatement p = con.prepareStatement("UPDATE CONTACT_USERS SET STATUS=? WHERE USERID=?");
			if(status) p.setInt(1, 1);
			else p.setInt(1, 0);
			p.setInt(2, uid);
			int i = p.executeUpdate();
			if(i>0) {
				con.commit();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@POST
	@Path("/deleteContact")
	public Response add(
			@FormParam("uid") String uid) throws URISyntaxException {
		int st = 401;
		if(uid!=null) {
			removeContact(uid);
			st=200;
		}
		return Response.status(st).build();
	}

	private void removeContact(String u) {
		try {
			int uid = Integer.parseInt(u);
			Connection c = Database.getDatabaseConnection();
			PreparedStatement p = c.prepareStatement("DELETE FROM CONTACT_DETAILS WHERE USERID=?");
			p.setInt(1,uid);
			int i = p.executeUpdate();
			if(i>0) {
				c.commit();
			}
			p = c.prepareStatement("DELETE FROM CONTACT_USERS WHERE USERID=?");
			p.setInt(1,uid);
			i = p.executeUpdate();
			if(i>0) {
				c.commit();
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}


	@POST
	@Path("/add")
	public Response add(
			@FormParam("type") String type, 
			@FormParam("value") String value, 
			@FormParam("uid") String uid) throws URISyntaxException {
		if(uid!=null && type!=null && value!=null) {
			addContactDetails(uid,type,value);
		}
		return Response.temporaryRedirect(new URI("contact.jsp?uid="+uid)).build();
	}

	private void addContactDetails(String uid, String type, String value) {
		try {
			Connection c = Database.getDatabaseConnection();
			PreparedStatement p = c.prepareStatement("INSERT INTO CONTACT_DETAILS(USERID,TYPE,VALUE) VALUES("+uid+",?,?)");
			p.setString(1, type.toUpperCase());
			p.setString(2, value);
			int i = p.executeUpdate();
			if(i>0) {
				c.commit();
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@POST
	@Path("/addOrUpdate")
	public Response addOrUpdate(
			@FormParam("FNAME") String fname, 
			@FormParam("LNAME") String lname, 
			@FormParam("uid") String uid 
			) throws URISyntaxException {
		
		if(fname!=null && lname !=null) {
			if(uid==null) {
				addNewUser(fname,lname);
			}
			else {
				updateUser(uid,fname,lname);
			}
		}
		return Response.temporaryRedirect(new URI("index.jsp")).build();
	}


	private void updateUser(String u, String fname, String lname) {
		try {
			int uid = Integer.parseInt(u);
			Connection con = Database.getDatabaseConnection();
			PreparedStatement p = con.prepareStatement("UPDATE CONTACT_USERS SET FIRST_NAME=?, LAST_NAME=? WHERE USERID=?");
			p.setString(1, fname);
			p.setString(2, lname);
			p.setInt(3, uid);
			int i = p.executeUpdate();
			if(i>0) {
				con.commit();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}


	private void addNewUser(String fname, String lname) {
		try {
			Connection con = Database.getDatabaseConnection();
			PreparedStatement p = con.prepareStatement("INSERT INTO CONTACT_USERS(FIRST_NAME,LAST_NAME) VALUES(?,?)");
			p.setString(1, fname);
			p.setString(2, lname);
			int i = p.executeUpdate();
			if(i>0) {
				con.commit();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
