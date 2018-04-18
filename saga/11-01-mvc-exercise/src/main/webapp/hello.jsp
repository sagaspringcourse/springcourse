<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="java.sql.*" %>
<%@ page import="rs.saga.mvc.model.PlayerDAO" %>
<%@ page import="rs.saga.mvc.model.Player" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Example</title>
</head>
<body>

<%
    PlayerDAO playerDAO = new PlayerDAO();
    Player player = playerDAO.findPlayer(Integer.valueOf(request.getParameter("id")));
    request.setAttribute("firstName", player.getFirstName());
%>

<h1>Hello ${requestScope.firstName}</h1>

</body>
</html>