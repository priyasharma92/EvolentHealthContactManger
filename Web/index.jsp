<%@page import="java.util.ArrayList"%>
<%@page import="com.priya.helper.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evolent Health - Contact Manager</title>
</head>
<body>
<div align="center">
<h1>Evolent's Contact Manager by Priya Sharma</h1>
<div align="right"><a href="addOrUpdate.jsp">Add Contact</a></div><hr/>
<table>
<%
ArrayList<Contact> list = GetAllContactsFromDatabase.getAllContacts();
out.println("<tr><th>Total Contacts : "+list.size()+"</th></tr>");
for(Contact c : list){
	out.println(c.toString());
}
%>
</table>
</div>
</body>
</html>