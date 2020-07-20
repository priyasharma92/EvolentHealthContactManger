package com.priya.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.priya.util.Database;

public class ContactDetails {
	int id;
	boolean valid=false;
	Contact c;
	ArrayList<Entry> list = new ArrayList<>();
	
	public ContactDetails(String id) {
		try {
			c = new Contact(-1, "", "", false);
			this.id = Integer.parseInt(id);
			valid=true;
		}
		catch(Exception e) {}
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public Contact getContact() {
		return c;
	}
	
	public ArrayList<Entry> getList(){		
		return list;
	}
	
	public void loadData() {
		loadData(false);
	}
	
	public void loadData(Boolean onlyContact) {
		try {
			Connection con = Database.getDatabaseConnection();
			PreparedStatement s = con.prepareStatement("SELECT USERID, FIRST_NAME, LAST_NAME, STATUS FROM CONTACT_USERS WHERE USERID=?");
			s.setInt(1, this.id);
			ResultSet r = s.executeQuery();
			if(r.next()) {
				c.setId(r.getInt(1));
				c.setFirstName(r.getString(2));
				c.setLastName(r.getString(3));
				c.setActive(1==r.getInt(4));
			}
			if(!onlyContact) {
				s = con.prepareStatement("SELECT CID, USERID, TYPE, VALUE FROM CONTACT_DETAILS WHERE USERID=? ORDER BY TYPE DESC");
				s.setInt(1, this.id);
				r = s.executeQuery();
				while(r.next()) {
					Integer cid = r.getInt(1);
					Integer uid = r.getInt(2);
					String type = r.getString(3);
					String value = r.getString(4);
					list.add(new Entry(uid, cid, type, value));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
