<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    
</head>
<body>
<div align="center" style="font-weight:bold"><h2>Car Rental System</h2></div><br/>
<div align="center" style="font-weight:bold">User: <c:out value="${logUser}" /><br/>
    <a href="welcomeLink.action" >Home</a> <a href="dashboardReservation.action" >Dashboard</a> <a href="logoutLink.action" >Logout</a></div>
</body>
</html>

