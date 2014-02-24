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
        <title>Vehicle Models</title>
    </head>
    <body>

        <h1>Vehicle Models</h1>
        <s:if test="vehicleModel==null || id == null">
            <s:set name="title" value="%{'Add VehicleModel'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update VehicleModel'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveVehicleModel">
            <s:textfield name="description"  label="Description" value="%{vehicleModel.description}"></s:textfield>
            <s:select name="make.id" label="Make" value="%{vehicleModel.make.id}" list="makeList" listKey="id" listValue="name"/>
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Submit" name="submit" />
        </s:form>
    </body>
</html>
