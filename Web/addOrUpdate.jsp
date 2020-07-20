<%@page import="com.priya.helper.ContactDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
String user = request.getParameter("uid");
ContactDetails d = new ContactDetails(user);
if(d.isValid()){
	d.loadData(true);
}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add/Update Contact</title>
</head>
<body>
<a href="index.jsp">Back</a>
<div align="center">
<h3><%
if(d.isValid())	out.print("Update Contact");
else out.print("Add Contact");
%></h3>
<hr/>
<form action="addOrUpdate" METHOD="POST" id="form1">
First Name : <input type="text" id="FNAME" name="FNAME" value="<% out.print(d.getContact().getFirstName()); %>"/><br/>
Last Name : <input type="text" name="LNAME" value="<% out.print(d.getContact().getOnlyLastName()); %>"/><br/><br/>
<%
if(d.isValid())	out.print("<input type='hidden' name='uid' value='"+d.getContact().getId()+"'/>");
%>
<input type="button" onclick="checkValidFirstName();" value="<% out.print(d.isValid()?"Update":"Add"); %>"/>
</form>
</div>
<script>
function checkValidFirstName(){
	if(document.getElementById("FNAME").value.trim().length > 0 ){
		document.getElementById("form1").submit();
	}
	else{
		alert("First Name can't be empty");
	}
}
</script>
</body>
</html>