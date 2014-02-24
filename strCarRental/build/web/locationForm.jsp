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
        <title>Locations</title>
    </head>
    <body>

        <h1>Locations</h1>
        <s:if test="location==null || id == null">
            <s:set name="title" value="%{'Add Location'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update Location'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveLocation">
            <s:textfield name="locName"  label="Name" value="%{location.locName}"></s:textfield>
            <s:textfield name="address1"  label="Address1" value="%{location.address1}"></s:textfield>
            <s:textfield name="address2"  label="Address2" value="%{location.address2}"></s:textfield>
            <s:textfield name="city"  label="City" value="%{location.city}"></s:textfield>
            <s:textfield name="contact"  label="Contact" value="%{location.contact}"></s:textfield>
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Submit" name="submit" />
        </s:form>
    </body>
</html>
