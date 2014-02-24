<%-- 
    Document   : customerForm
    Created on : Nov 26, 2013, 9:21:58 AM
    Author     : Ronal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>

        <h1>Users</h1>
        <s:if test="user==null || id == null">
            <s:set name="title" value="%{'Add User'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update User'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveUser">
            <s:textfield name="userID"  label="User Id" value="%{user.userID}"></s:textfield>
            <s:textfield name="name"  label="Name" value="%{user.name}"></s:textfield>
            <s:select name="userRole" label="Role" list="roleList" value="%{user.userRole}"></s:select>
            <s:textfield name="loginAudit"  label="Remarks" value="%{user.loginAudit}"></s:textfield>
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Submit" name="submit" />
        </s:form>
    </body>
</html>
