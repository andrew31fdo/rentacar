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
        <title>VehicleClass</title>
    </head>
    <body>

        <h1>VehicleClass</h1>
        <s:if test="vehicleClass==null || id == null">
            <s:set name="title" value="%{'Add VehicleClass'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update VehicleClass'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveVehicleClass">
            <s:textfield name="description"  label="Description" value="%{vehicleClass.description}"></s:textfield>
            <s:textfield name="remarks"  label="Remarks" value="%{vehicleClass.remarks}"></s:textfield>
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Submit" name="submit" />
        </s:form>
    </body>
</html>
