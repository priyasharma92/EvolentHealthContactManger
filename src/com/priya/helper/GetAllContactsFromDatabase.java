package com.priya.helper;

import java.sql.*;
import java.util.*;

import com.priya.util.Database;

public class GetAllContactsFromDatabase {
	public static ArrayList<Contact> getAllContacts(){
		ArrayList<Contact> retVal = new ArrayList<>();
		try {
			Connection c = Database.getDatabaseConnection();
			Statement s  = c.createStatement();
			ResultSet r = s.executeQuery("SELECT USERID, FIRST_NAME, LAST_NAME, STATUS FROM CONTACT_USERS");
			while(r.next()) {
				Integer id = r.getInt(1);
				String f = r.getString(2);
				String l = r.getString(3);
				Integer status = r.getInt(4);
				retVal.add(new Contact(id, f, l, status==1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
}
