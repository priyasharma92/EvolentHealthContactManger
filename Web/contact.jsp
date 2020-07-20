<%@page import="com.priya.helper.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
String user = request.getParameter("uid");
if(user==null || user.trim().length()==0){
	request.getRequestDispatcher("index.jsp").forward(request, response);
}
ContactDetails d = new ContactDetails(user);
if(d.isValid()){
	d.loadData();	
}
if(d.getContact().getId()==-1){
	request.getRequestDispatcher("index.jsp").forward(request, response);
}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Details</title>

</head>
<body>
<a href="index.jsp">Back</a>
<div align="center">
<h3><% out.print(d.getContact().getName()); %></h3>
<div align="right">Status : <% out.print((d.getContact().isActive()?"Active":"Inactive")+" <input type='button' value='Change Status' onclick=\"return status("+d.getContact().getId()+",'"+!d.getContact().isActive()+"');\">"); %>
<input type="button" value="Edit Contact"   onclick="window.location='addOrUpdate.jsp?uid=<% out.print(d.getContact().getId()); %>';"/>
<input type="button" value="Delete Contact" onclick="return deleteC();"/>
</div>
<hr/>
<table>
<%
for(Entry e : d.getList()){
	out.print(e.toString());
}
%>
</table>
<hr/>
<form action="add" method="POST">
<input type="text" id="value" name="value" placeholder="Enter Email/Mobile"/><br/>
Mobile<input type="radio" name="type" value="Mobile" checked/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email<input type="radio" name="type" value="Email"/><br/>
<input type="hidden" name="uid" value="<% out.print(d.getContact().getId()); %>"/>
<input type="submit" value="Add Details"/>
</form>
</div>
<script type="text/javascript">
	function deleteCD(uid, cid){
		if (confirm("Are you sure, you want to detele this?")) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {
				 location.reload();
			 }
			};
			xhttp.open("POST", "deleteContactDetails", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("uid="+uid+"&cid="+cid); 
		} 
	}
	
	function editCD(uid, cid, old){
		if (confirm("Are you sure, you want to edit this?")) {
			var newVal = prompt("Please Enter New Value", old);
			if(newVal != null){
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
				 if (this.readyState == 4 && this.status == 200) {
					 location.reload();
				 }
				};
				xhttp.open("POST", "editContactDetails", true);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send(encodeURI("uid="+uid+"&cid="+cid+"&value="+newVal)); 
			}
		} 
	}
	
	function status(uid, flag){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			 location.reload();
		 }
		};
		xhttp.open("POST", "status", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("uid="+uid+"&status="+flag);
	}

	function deleteC(){
		if (confirm("Are you sure, you want to detele this contact?")) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {
				 window.location='index.jsp';
			 }
			};
			xhttp.open("POST", "deleteContact", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("uid="+<% out.print(d.getContact().getId()); %>); 
		} 
	}
	
</script>
</body>
</html>