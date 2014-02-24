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
        <s:if test="rate==null || id == null">
            <s:set name="title" value="%{'Add Rate'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update Rate'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveRate">
            
            <s:select name="vehicle.id" label="Vehicle" value="%{rate.vehicle.id}" list="vehicleList" listKey="id" listValue="name"/>
            <s:select name="rateType.id"  label="Rate Type" value="%{rate.rateType.id}" list="rateTypeList" listKey="id" listValue="description"/>
            <s:textfield name="perDayCost"  label="Per Day Cost" value="%{rate.perDayCost}"></s:textfield>
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Submit" name="submit" />
        </s:form>
    </body>
</html>
