package com.priya.helper;

public class Entry {
	int uid, cid;
	String type, value;
	
	Entry(int uid, int cid, String type, String value){
		this.uid=uid;
		this.cid=cid;
		this.type=type;
		this.value=value;
	}
	
	@Override
	public String toString(){
		return "<tr><td><h6>"+type+"</h6></td><td><b>"+value+"</b></td><td><h6><input type='button' value='Delete' onClick='return deleteCD("+uid+","+cid+");'/></h6></td><td><h6><input type='button' value='Edit' onClick='return editCD("+uid+","+cid+","+value+");'/></h6></td></tr>";
	}
	
	
}
