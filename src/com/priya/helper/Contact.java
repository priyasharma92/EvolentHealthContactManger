package com.priya.helper;

public class Contact {
	int id;
	String fname, lname;
	boolean isActive;
	
	public Contact(Integer i, String f, String l, Boolean b) {
		id=i;
		fname=f;
		lname=l;
		isActive=b;
	}
	
	public int getId() {
		return id;
	}
	
	protected void setId(int i) {
		id=i;
	}
	
	public String getFirstName() {
		return fname;
	}
	
	protected void setFirstName(String i) {
		fname=i;
	}
	

	public String getOnlyLastName() {
		return lname;
	}
	
	public String getLastName() {
		if (lname==null || lname.trim().length()==0) return "";
		return lname+", ";
	}
	
	protected void setLastName(String i) {
		lname=i;
	}
	
	public Boolean isActive() {
		return isActive;
	}
	
	protected void setActive(boolean i) {
		isActive=i;
	}
	@Override
	public String toString() {
		String s1 = "<strike>";
		String s2 = "</strike>";
		if(isActive) {
			s1="";
			s2="";
		}
		return "<tr><td>"+s1+"<a href='contact.jsp?uid="+this.getId()+"'>"+this.getName()+"</a>"+s2+"</td></tr>";
	}

	public String getName() {
		return getLastName()+getFirstName();
	}
	
	
}
