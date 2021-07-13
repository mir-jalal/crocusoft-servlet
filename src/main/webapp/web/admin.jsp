<%@ page import="com.crocusoft.model.User" %><%--
  Created by IntelliJ IDEA.
  User: infernalsage
  Date: 7/13/21
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("user");
    if(user == null){
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>
<h1>Hello, <%out.print(user.getName());%></h1>
</body>
</html>
